package com.apruve.models;

import org.junit.Test;

import com.apruve.test.InvoiceItemLinksOM;

public class InvoiceItemLinksTest {

	@Test
	public void testMarshal() throws Exception {
		InvoiceItemLinks links = InvoiceItemLinksOM.getInvoiceItemLinks();
		ApruveModelTestHelper.doMarshalTest(links);
	}
}
