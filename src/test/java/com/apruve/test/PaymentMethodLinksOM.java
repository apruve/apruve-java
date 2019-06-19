package com.apruve.test;

import com.apruve.URLUtil;
import com.apruve.models.PaymentMethodLinks;
import com.github.javafaker.Faker;
import com.github.javafaker.Internet;

public class PaymentMethodLinksOM {
	private static final Internet faker = new Faker().internet();

	public static PaymentMethodLinks getPaymentMethodLinks() {
		PaymentMethodLinks links = new PaymentMethodLinks();
		links.setOwner(URLUtil.buildURL(faker.url()));

		return links;
	}
}
