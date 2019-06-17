package com.apruve.test;

import com.apruve.URLUtil;
import com.apruve.models.OrderLinks;
import com.github.javafaker.Faker;
import com.github.javafaker.Internet;

public class OrderLinksOM {
	private static final Internet faker = new Faker().internet();

	public static OrderLinks getOrderLinks() {
		OrderLinks links = new OrderLinks();
		links.setSelf(URLUtil.buildURL(faker.url()));
		links.setCustomer(URLUtil.buildURL(faker.url()));
		links.setMerchant(URLUtil.buildURL(faker.url()));
		links.setInvoices(URLUtil.buildURL(faker.url()));
		links.setShopper(URLUtil.buildURL(faker.url()));

		return links;
	}
}
