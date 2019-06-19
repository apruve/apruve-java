package com.apruve.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.InvoiceItemLinksOM;

public class InvoiceItemLinksTest {

	@Test
	public void testMarshal() throws Exception {
		InvoiceItemLinks links = InvoiceItemLinksOM.getInvoiceItemLinks();
		ApruveModelTestHelper.doMarshalTest(links);
	}

	@Test
	public void testToJson() {
		InvoiceItemLinks obj = InvoiceItemLinksOM.getInvoiceItemLinks();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
