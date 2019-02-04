package com.rate.app.controler.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rate.app.controler.LenderController;
import com.rate.app.model.Lender;
import com.rate.app.model.Quote;

public class LenderControllerImpl implements LenderController{
	
	private static Logger log = LogManager.getLogger(Quote.class);

	public BigDecimal rateOffer(List<Lender> lendersOrdenado) {
		return lendersOrdenado.stream().map(Lender::getRate).reduce(BigDecimal::add).get()
				.divide(BigDecimal.valueOf(lendersOrdenado.size()), MathContext.DECIMAL64);
	}

	public BigDecimal payPerMonth(Integer amount, BigDecimal rate) {
		return ratePerMonth(rate).multiply(BigDecimal.valueOf(amount))
				.divide(BigDecimal.valueOf(1)
						.subtract((BigDecimal.valueOf(1).add(ratePerMonth(rate)).pow(-36, MathContext.DECIMAL64))),
						RoundingMode.HALF_UP);
	}

	private static BigDecimal ratePerMonth(BigDecimal rate) {
		return rate.divide(new BigDecimal(12), 100, RoundingMode.HALF_UP);
	}


	public List<Lender> csvToLender(String csv) throws IOException {
		List<Lender> lenders;
		File file = new File(csv);
		InputStream inputStream = new FileInputStream(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		lenders = reader.lines().skip(1).map(mapToLender).collect(Collectors.toList());
		reader.close();

		return lenders;
	}

	public Function<String, Lender> mapToLender = (String line) -> {
		String[] details = line.split(",");

		String name = details[0];
		BigDecimal rate = new BigDecimal(details[1]);
		Integer availableAmount = Integer.valueOf(details[2]);

		return new Lender(name, rate, availableAmount);
	};

	public List<Lender> getLendersForAmount(Integer requestedAmount, List<Lender> lenders) throws Exception {
		Integer remainingLoan = requestedAmount;
		List<Lender> requiredLenders = new ArrayList<>();
		Integer availableAmountToLend = lenders.stream().mapToInt(Lender::getAvailable).sum();
		
        if (requestedAmount > availableAmountToLend) {
        	log.error("The amount requested is greater than the amount we can grant");
            throw new Exception("The amount requested is greater than the amount we can grant");
        }

        Collections.sort(lenders);

		for (Lender lender : lenders) {
			if (remainingLoan <= 0) {
				break;
			}
			if (lender.getAvailable() > remainingLoan) {
				requiredLenders.add(lender);
				remainingLoan = 0;

			} else {
				remainingLoan -= lender.getAvailable();
				requiredLenders.add(lender);
			}
		}
		return requiredLenders;
	}
	

	public double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
