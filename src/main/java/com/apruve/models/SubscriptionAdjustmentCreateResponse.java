package com.apruve.models;

import java.net.URL;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apruve.JsonUtil;
import com.apruve.models.SubscriptionAdjustment.SubscriptionAdjustmentStatus;

@XmlRootElement
public class SubscriptionAdjustmentCreateResponse {

	private String id;
	private SubscriptionAdjustmentStatus status;
	@XmlElement(name = "subscription_id")
	private String subscriptionId;
	@XmlElement(name = "api_url")
	private URL apiUrl;
	
	@Override
	public String toString() {
		return toJson();
	}

	public String toJson() {
		return JsonUtil.getInstance().toJson(this);		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SubscriptionAdjustmentStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionAdjustmentStatus status) {
		this.status = status;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public URL getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(URL apiUrl) {
		this.apiUrl = apiUrl;
	}
}
