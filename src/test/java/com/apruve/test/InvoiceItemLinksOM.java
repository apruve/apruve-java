package com.apruve.test;

import com.apruve.URLUtil;
import com.apruve.models.InvoiceItemLinks;
import com.github.javafaker.Faker;
import com.github.javafaker.Internet;

public class InvoiceItemLinksOM {
	private static final Internet faker = new Faker().internet();

	public static InvoiceItemLinks getInvoiceItemLinks() {
		InvoiceItemLinks links = new InvoiceItemLinks();
		links.setInvoice(URLUtil.buildURL(faker.url()));
		links.setOrder(URLUtil.buildURL(faker.url()));
		links.setSelf(URLUtil.buildURL(faker.url()));

		return links;
	}
}
