package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.PaymentLinksOM;

public class PaymentLinksTest {

	@Test
	public void testMarshal() throws Exception {
		PaymentLinks links = PaymentLinksOM.getPaymentLinks();
		ApruveModelTestHelper.doMarshalTest(links);
	}

	@Test
	public void testToJson() {
		PaymentLinks obj = PaymentLinksOM.getPaymentLinks();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
