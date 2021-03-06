package com.apruve.models;

import java.net.URL;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apruve.JsonUtil;
import com.apruve.models.PaymentRequest.PaymentRequestStatus;

@XmlRootElement
public class PaymentRequestUpdateResponse {
	private String id;
	private PaymentRequestStatus status;
	@XmlElement(name="api_url")
	private URL apiUrl;
	@XmlElement(name="view_url")
	private URL viewUrl;
	
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

	public PaymentRequestStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentRequestStatus status) {
		this.status = status;
	}

	public URL getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(URL apiUrl) {
		this.apiUrl = apiUrl;
	}

	public URL getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(URL viewUrl) {
		this.viewUrl = viewUrl;
	}
}
