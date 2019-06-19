package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.PaymentTermOM;

public class PaymentTermTest {

	@Test
	public void testMarshal() throws Exception {
		PaymentTerm term = PaymentTermOM.getPaymentTerm();
		ApruveModelTestHelper.doMarshalTest(term);
	}

	@Test
	public void testToJson() {
		PaymentTerm obj = PaymentTermOM.getPaymentTerm();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}