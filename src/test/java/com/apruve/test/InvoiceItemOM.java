package com.apruve.test;

import java.util.ArrayList;
import java.util.List;

import com.apruve.models.InvoiceItem;
import com.github.javafaker.Faker;

public class InvoiceItemOM {
	private static final Faker faker = new Faker();

	public static final InvoiceItem getInvoiceItemSimple() {
		InvoiceItem item = new InvoiceItem("item", 1234);
		item.setPriceTotalCents(100);
		item.setDescription("A discription for this line");
		item.setSku("A_SKU_NUMBER");
		return item;
	}
	
	public static final InvoiceItem getInvoiceItem() {
		InvoiceItem item = getInvoiceItemSimple();
		item.setQuantity(1);
		item.setPriceTotalCents(faker.number().numberBetween(50, 100000));
		item.setPriceEachCents(item.getPriceTotalCents());
		item.setLinks(InvoiceItemLinksOM.getInvoiceItemLinks());
		return item;
	}
	
	public static final List<InvoiceItem> getInvoiceItems() {
		List<InvoiceItem> items = new ArrayList<InvoiceItem>();
		items.add(getInvoiceItemSimple());
		return items;
	}
}
