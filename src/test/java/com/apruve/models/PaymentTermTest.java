package com.apruve.models;

import org.junit.Test;

import com.apruve.test.PaymentTermOM;

public class PaymentTermTest {

	@Test
	public void testMarshal() throws Exception {
		PaymentTerm term = PaymentTermOM.getPaymentTerm();
		ApruveModelTestHelper.doMarshalTest(term, PaymentTerm.class);
	}
}
