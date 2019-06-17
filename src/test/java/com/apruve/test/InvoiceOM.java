package com.apruve.test;

import com.apruve.models.Invoice;

public class InvoiceOM {
	private static final String ORDER_ID = "AnOrderID";

	public static final Invoice getInvoiceMinimal() {
		Invoice invoice = new Invoice(ORDER_ID);
		invoice.setAmountCents(12345);
		return invoice;
	}
	
	public static final Invoice getInvoiceBasic() {
		Invoice invoice = getInvoiceMinimal();
		invoice.setShippingCents(678);
		invoice.setTaxCents(456);
		invoice.setMerchantInvoiceId("INV-1234");
		invoice.setInvoiceItems(InvoiceItemOM.getInvoiceItems());
		invoice.setLinks(OrderChildLinksOM.getOrderChildLinks());
		return invoice;
	}
}
