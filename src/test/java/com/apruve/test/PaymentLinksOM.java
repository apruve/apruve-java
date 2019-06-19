package com.apruve.test;

import java.net.URL;
import java.util.ArrayList;

import com.apruve.URLUtil;
import com.apruve.models.PaymentLinks;
import com.github.javafaker.Faker;
import com.github.javafaker.Internet;

public class PaymentLinksOM {
	private static final Internet faker = new Faker().internet();

	public static PaymentLinks getPaymentLinks() {
		PaymentLinks links = new PaymentLinks();
		links.setPayer(URLUtil.buildURL(faker.url()));
		links.setSelf(URLUtil.buildURL(faker.url()));
		links.setInvoices(new ArrayList<URL>());
		links.setRefunds(new ArrayList<URL>());
		links.getInvoices().add(URLUtil.buildURL(faker.url()));

		return links;
	}
}
