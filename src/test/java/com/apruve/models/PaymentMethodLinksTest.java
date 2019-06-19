package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.PaymentMethodLinksOM;

public class PaymentMethodLinksTest {

	@Test
	public void testMarshal() throws Exception {
		PaymentMethodLinks links = PaymentMethodLinksOM.getPaymentMethodLinks();
		ApruveModelTestHelper.doMarshalTest(links);
	}

	@Test
	public void testToJson() {
		PaymentMethodLinks obj = PaymentMethodLinksOM.getPaymentMethodLinks();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
