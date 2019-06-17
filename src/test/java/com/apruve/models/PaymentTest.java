package com.apruve.models;

import org.junit.Test;

import com.apruve.test.PaymentOM;

public class PaymentTest {

	@Test
	public void testMarshal() throws Exception {
		Payment payment = PaymentOM.getPayment();
		ApruveModelTestHelper.doMarshalTest(payment, Payment.class);
	}
}
