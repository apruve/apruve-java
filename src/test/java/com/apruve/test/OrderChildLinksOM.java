package com.apruve.test;

import com.apruve.URLUtil;
import com.apruve.models.OrderChildLinks;
import com.github.javafaker.Faker;
import com.github.javafaker.Internet;

public class OrderChildLinksOM {
	private static final Internet faker = new Faker().internet();

	public static OrderChildLinks getOrderChildLinks() {
		OrderChildLinks links = new OrderChildLinks();
		links.setOrder(URLUtil.buildURL(faker.url()));
		links.setSelf(URLUtil.buildURL(faker.url()));

		return links;
	}
}
