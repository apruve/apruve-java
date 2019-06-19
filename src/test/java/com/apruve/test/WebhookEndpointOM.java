package com.apruve.test;

import java.net.MalformedURLException;
import java.net.URL;

import com.apruve.models.WebhookEndpoint;
import com.github.javafaker.Faker;

public class WebhookEndpointOM {
	private static final Faker faker = new Faker();

	public static WebhookEndpoint getWebhookEndpoint() {
		WebhookEndpoint endpoint = null;
		try {
			endpoint = new WebhookEndpoint(new URL("http://" + faker.internet().url()));
			endpoint.setId(faker.internet().uuid());
		} catch (MalformedURLException e) {
			System.err.println(e.getMessage());
		}
		return endpoint;
	}
}
