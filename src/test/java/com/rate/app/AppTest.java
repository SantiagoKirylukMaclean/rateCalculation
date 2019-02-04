package com.rate.app;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.rate.app.model.Quote;


/**
 * Unit test for simple App.
 */
public class AppTest{

    Quote srv;

    @Before
    public void setUp() throws Exception {
        srv = new Quote( "src/main/resources/MarketDataforExercise.csv",1000);
    }

    @Test
    public void getMonthlyRepaymentsTest() throws Exception {
        assertEquals(30.88, srv.getMonthlyRepayments().doubleValue(), 0);
    }

    @Test
    public void getRateTest() throws Exception {
        assertEquals(0.07, srv.getRate(), 0);
    }


	@Test
    public void getTotalRepaymentTest() throws Exception {
        assertEquals(1111.68 , srv.getTotalRepayment().doubleValue(), 0);
    }

}

