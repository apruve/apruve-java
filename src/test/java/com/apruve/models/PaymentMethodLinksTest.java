package com.apruve.models;

import org.junit.Test;

import com.apruve.test.PaymentMethodLinksOM;

public class PaymentMethodLinksTest {

	@Test
	public void testMarshal() throws Exception {
		PaymentMethodLinks links = PaymentMethodLinksOM.getPaymentMethodLinks();
		ApruveModelTestHelper.doMarshalTest(links, PaymentMethodLinks.class);
	}
}
