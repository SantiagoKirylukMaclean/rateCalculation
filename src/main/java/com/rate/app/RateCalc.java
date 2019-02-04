package com.rate.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rate.app.model.Quote;
import com.rate.app.utils.CustomExeption;

public class RateCalc {

	private static Logger log = LogManager.getLogger(RateCalc.class);

	public static void main(String[] args) throws Exception {
		try {
			String file = args[0];
			Integer amount = Integer.valueOf(args[1]);

			log.debug("File Market: " + file + " - Amount: " + amount);
			if (inputAmountControl(amount)) {
				Quote srv = new Quote(file, amount);
				System.out.println(srv);
			}
		} catch (Exception e) {
			log.error(e);
			throw new CustomExeption(e);
		}

	}

	private static boolean inputAmountControl(double amount) {
		if (amount % 100 != 0 || amount < 1000 || amount > 15000) {
			log.error("The amount has to be $100 increment between $1000 and $15000 inclusive");
			System.out.println("The amount has to be $100 increment between $1000 and $15000 inclusive");
			return false;
		}
		return true;
	}

}
