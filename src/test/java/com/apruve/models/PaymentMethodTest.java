package com.apruve.models;

import org.junit.Test;

import com.apruve.test.PaymentMethodOM;

public class PaymentMethodTest {

	@Test
	public void testMarshal() throws Exception {
		PaymentMethod method = PaymentMethodOM.getPaymentMethod();
		ApruveModelTestHelper.doMarshalTest(method);
	}
}
