package com.apruve.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.InvoiceItemOM;

public class InvoiceItemTest {

	@Test
	public void testMarshal() throws Exception {
		InvoiceItem obj = InvoiceItemOM.getInvoiceItemSimple();
		ApruveModelTestHelper.doMarshalTest(obj);
	}
	
	@Test
	public void testCreateInvoiceItem() {
		InvoiceItem item = new InvoiceItem("title", 4321);
		assertNotNull(item);
		assertEquals("title", item.getTitle());
		assertEquals(4321, item.getPriceTotalCents().intValue());
	}

	@Test
	public void testToJson() {
		InvoiceItem obj = InvoiceItemOM.getInvoiceItem();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
