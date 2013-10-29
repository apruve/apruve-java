package com.apruve;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.apruve.ApruveClient.Environment;

public class PaymentTest {

	@Test
	public void testGetUrl(){
		ApruveClient.init("anApiKey", Environment.TEST);
		Payment payment = new Payment("ARequestId", 123);
		assertEquals("https://test.apruve.com/api/v3/payment_requests/ARequestId/payments", payment.getUrl());
	}
}
