package com.apruve.models;

import java.net.URL;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apruve.JsonUtil;

@XmlRootElement
public class SubscriptionUpdateResponse {
	private String id;
	@XmlElement(name = "subscription_api_url")
	private URL subscriptionApiUrl;
	
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

	public URL getSubscriptionApiUrl() {
		return subscriptionApiUrl;
	}

	public void setSubscriptionApiUrl(URL subscriptionApiUrl) {
		this.subscriptionApiUrl = subscriptionApiUrl;
	}
}
