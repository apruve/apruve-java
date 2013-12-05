package com.apruve;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class PaymentTest {

	@Test
	public void testGetUrl(){
		ApruveClient.init("anApiKey", ApruveEnvironment.TEST);
		Payment payment = new Payment("ARequestId", new Integer(123));
		assertEquals("https://test.apruve.com/api/v3/payment_requests/ARequestId/payments", payment.getUrl());
	}
}
