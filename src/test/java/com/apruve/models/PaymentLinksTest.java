package com.apruve.models;

import org.junit.Test;

import com.apruve.test.PaymentLinksOM;

public class PaymentLinksTest {

	@Test
	public void testMarshal() throws Exception {
		PaymentLinks links = PaymentLinksOM.getPaymentLinks();
		ApruveModelTestHelper.doMarshalTest(links);
	}
}
