package com.apruve.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.InvoiceReturnOM;

public class InvoiceReturnTest {

	@Test
	public void testMarshal() throws Exception {
		InvoiceReturn ir = InvoiceReturnOM.getInvoiceReturn();
		ApruveModelTestHelper.doMarshalTest(ir);
	}
	
	@Test
	public void testGetInvoiceReturnsPath() throws Exception {
		assertEquals("/invoices/invoice_id/invoice_returns/", InvoiceReturn.getInvoiceReturnsPath("invoice_id"));
	}
	
	@Test
	public void testGetInvoiceReturnPath() throws Exception {
		assertEquals("/invoices/invoice_id/invoice_returns/invoice_return_id", InvoiceReturn.getInvoiceReturnPath("invoice_id", "invoice_return_id"));		
	}

	@Test
	public void testToJson() {
		InvoiceReturn obj = InvoiceReturnOM.getInvoiceReturn();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
