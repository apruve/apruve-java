package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.PaymentMethodOM;

public class PaymentMethodTest {

	@Test
	public void testMarshal() throws Exception {
		PaymentMethod method = PaymentMethodOM.getPaymentMethod();
		ApruveModelTestHelper.doMarshalTest(method);
	}

	@Test
	public void testToJson() {
		PaymentMethod obj = PaymentMethodOM.getPaymentMethod();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
