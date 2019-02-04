package com.rate.app.controler;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.rate.app.model.Lender;

public interface LenderController {
	
	public BigDecimal rateOffer(List<Lender> lendersOrdenado);
	public List<Lender> getLendersForAmount(Integer requestedAmount, List<Lender> lenders) throws Exception;
	public List<Lender> csvToLender(String csv) throws IOException;
	public BigDecimal payPerMonth(Integer amount, BigDecimal rate);
	public double round(double value, int places);

}
