package com.apruve.models;

import java.net.URL;
import java.util.List;

import javax.ws.rs.core.GenericType;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

public class WebhookEndpoint {
	static final String WEBHOOK_ENDPOINTS_PATH = Merchant.MERCHANT_PATH + "/webhook_endpoints/";
	static final String WEBHOOK_ENDPOINT_PATH = WEBHOOK_ENDPOINTS_PATH + "%endpointId";

	private String id;
	private String version = "v4";
	private URL url;

	protected WebhookEndpoint() {
		// Required by JAXB
	}

	public WebhookEndpoint(URL url) {
		super();
		this.url = url;
	}

	public static String getWebhookEndpointsPath(String merchantId) {
		return WEBHOOK_ENDPOINTS_PATH.replace("%merchantId", merchantId);
	}

	public static String getWebhookEndpointPath(String merchantId, String endpointId) {
		return WEBHOOK_ENDPOINT_PATH.replace("%merchantId", merchantId).replace("%endpointId", endpointId);
	}

	public static ApruveResponse<List<WebhookEndpoint>> getAll(ApruveClient client, String merchantId) {
		return client.index(getWebhookEndpointsPath(merchantId), new GenericType<List<WebhookEndpoint>>() {
		});
	}

	public static ApruveResponse<WebhookEndpoint> get(ApruveClient client, String merchantId, String endpointId) {
		return client.get(getWebhookEndpointPath(merchantId, endpointId), WebhookEndpoint.class);
	}

	public static ApruveResponse<WebhookEndpoint> delete(ApruveClient client, String merchantId, String endpointId) {
		return client.delete(getWebhookEndpointPath(merchantId, endpointId), WebhookEndpoint.class);
	}

	public ApruveResponse<WebhookEndpoint> create(ApruveClient client, String merchantId) {
		return client.post(getWebhookEndpointsPath(merchantId), this, WebhookEndpoint.class);
	}

	@Override
	public String toString() {
		return toJson();
	}

	/**
	 * Returns the JSON string for a model.
	 * 
	 * @return JSON representation of the model.
	 */
	public String toJson() {
		return JsonUtil.getInstance().toJson(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	protected void setVersion(String version) {
		this.version = version;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
}
