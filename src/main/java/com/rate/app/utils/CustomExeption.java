package com.rate.app.utils;

public class CustomExeption extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public CustomExeption(String message) {
		super(message);
	}
	public CustomExeption(Throwable e) {
		super(e);
	}
	public CustomExeption(String message,Throwable e) {
		super(message, e);
	}

}
