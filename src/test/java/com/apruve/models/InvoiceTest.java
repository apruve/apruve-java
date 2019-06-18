package com.apruve.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.InvoiceOM;

public class InvoiceTest {

	@Test
	public void testMarshal() throws Exception {
		Invoice obj = InvoiceOM.getInvoiceBasic();
		ApruveModelTestHelper.doMarshalTest(obj);
	}

	@Test
	public void testCreateInvoice() {
		Invoice invoice = new Invoice("order_id");
		assertNotNull(invoice);
		assertEquals("order_id", invoice.getOrderId());
	}

	@Test
	public void testGetInvoicesPath() {
		assertEquals("/orders/order_id/invoices/", Invoice.getInvoicesPath("order_id"));
	}
	
	@Test
	public void testGetInvoicePath() {
		assertEquals("/invoices/invoice_id", Invoice.getInvoicePath("invoice_id"));
	}
}
