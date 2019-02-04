package com.rate.app.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rate.app.controler.LenderController;
import com.rate.app.controler.impl.LenderControllerImpl;


public class Quote {

	private List<Lender> sortLenders;
	private Integer amount;
	private double rate;
	private double rateview;
	private BigDecimal monthlyRepayments;
	private BigDecimal totalRepayment;
	private static Logger log = LogManager.getLogger(Quote.class);
	private final LenderController lenderControler = new LenderControllerImpl(); 
	
	public Quote(String csv, int amount) throws Exception {
		
		this.amount = amount;
		sortLenders = lenderControler.getLendersForAmount(amount, lenderControler.csvToLender(csv));
		rate = lenderControler.rateOffer(sortLenders).doubleValue();
		monthlyRepayments = lenderControler.payPerMonth(amount, new BigDecimal(rate)).setScale(2, BigDecimal.ROUND_UP);
		totalRepayment = monthlyRepayments.multiply(new BigDecimal(36));
	}

	public double getRateview() {
		return rateview;
	}

	public void setRateview(double rateview) {
		this.rateview = rateview;
	}

	public Integer getAmount() {
		return amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public BigDecimal getTotalRepayment() {
		return totalRepayment;
	}

	public void setTotalRepayment(BigDecimal totalRepayment) {
		this.totalRepayment = totalRepayment;
	}


	public BigDecimal getMonthlyRepayments() {
		return monthlyRepayments;
	}

	public void setMonthlyRepayments(BigDecimal monthlyRepayments) {
		this.monthlyRepayments = monthlyRepayments;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		log.debug("Requested Amount: $" + getAmount() + "\n" + "Rate:" + lenderControler.round(getRate() * 100 ,1) + "% \n" + "Monthly Repayments: $"
				+ getMonthlyRepayments() + " \n" + "Total Repayment: $" + getTotalRepayment());
		
		return "Requested Amount: $" + getAmount() + "\n" + "Rate:" + lenderControler.round(getRate() * 100 ,1) + "% \n" + "Monthly Repayments: $"
				+ getMonthlyRepayments() + " \n" + "Total Repayment: $" + getTotalRepayment();
	}

}
