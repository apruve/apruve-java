package com.apruve.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.apruve.test.WebhookEndpointOM;

public class WebhookEndpointTest {

	@Test
	public void testMarshal() throws Exception {
		WebhookEndpoint endpoint = WebhookEndpointOM.getWebhookEndpoint();
		ApruveModelTestHelper.doMarshalTest(endpoint);
	}
	
	@Test
	public void testGetWebhookEndpointsPath() {
		assertEquals("/merchants/merchant_id/webhook_endpoints/", WebhookEndpoint.getWebhookEndpointsPath("merchant_id"));
	}
	
	@Test
	public void testGetWebhookEndpointPath() {
		assertEquals("/merchants/merchant_id/webhook_endpoints/endpoint_id", WebhookEndpoint.getWebhookEndpointPath("merchant_id", "endpoint_id"));
	}

	@Test
	public void testToJson() {
		WebhookEndpoint obj = WebhookEndpointOM.getWebhookEndpoint();
		String json = obj.toJson();
		assertNotNull(json);
		assertFalse(json.isEmpty());
	}
}
