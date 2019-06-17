package com.apruve.models;

import org.junit.Test;

import com.apruve.test.InvoiceReturnOM;

public class InvoiceReturnTest {

	@Test
	public void testMarshal() throws Exception {
		InvoiceReturn ir = InvoiceReturnOM.getInvoiceReturn();
		ApruveModelTestHelper.doMarshalTest(ir);
	}
}
