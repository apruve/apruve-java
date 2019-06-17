package com.apruve.models;

import org.junit.Test;

import com.apruve.test.WebhookEndpointOM;

public class WebhookEndpointTest {

	@Test
	public void testMarshal() throws Exception {
		WebhookEndpoint endpoint = WebhookEndpointOM.getWebhookEndpoint();
		ApruveModelTestHelper.doMarshalTest(endpoint, WebhookEndpoint.class);
	}
}
