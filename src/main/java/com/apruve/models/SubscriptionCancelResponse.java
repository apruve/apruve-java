package com.apruve.models;

import java.net.URL;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apruve.JsonUtil;

@XmlRootElement
public class SubscriptionCancelResponse {

	private String id;
	@XmlElement(name = "canceled_at")
	private Date canceledAt;
	@XmlElement(name = "end_at")
	private Date endAt;
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

	public Date getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(Date canceledAt) {
		this.canceledAt = canceledAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public URL getSubscriptionApiUrl() {
		return subscriptionApiUrl;
	}

	public void setSubscriptionApiUrl(URL subscriptionApiUrl) {
		this.subscriptionApiUrl = subscriptionApiUrl;
	}
}
