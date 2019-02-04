package com.rate.app.model;

import java.math.BigDecimal;

public class Lender implements Comparable<Lender> {
	
	private String name;
	private BigDecimal rate;
	private Integer available;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Integer getAvailable() {
		return available;
	}
	public void setAvailable(Integer available) {
		this.available = available;
	}
	
	public Lender(String name, BigDecimal rate, Integer available) {
		super();
		this.name = name;
		this.rate = rate;
		this.available = available;
	}
	@Override
	public String toString() {
		return "Lender [name=" + name + ", rate=" + rate + ", available=" + available + "]";
	}
	@Override
	public int compareTo(Lender o) {
		return getRate().compareTo(o.getRate());
	}

	
}
