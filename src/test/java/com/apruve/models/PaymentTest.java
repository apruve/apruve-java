package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.PaymentOM;

public class PaymentTest {

	@Test
	public void testMarshal() throws Exception {
		Payment payment = PaymentOM.getPayment();
		ApruveModelTestHelper.doMarshalTest(payment);
	}

	@Test
	public void testToJson() {
		Payment obj = PaymentOM.getPayment();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
