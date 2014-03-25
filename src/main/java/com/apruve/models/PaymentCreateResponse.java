package com.apruve.models;

import java.net.URL;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apruve.JsonUtil;
import com.apruve.models.Payment.PaymentStatus;

@XmlRootElement
public class PaymentCreateResponse {

	private String id;
	private PaymentStatus status;
	@XmlElement(name = "payment_request_id")
	private String paymentRequestId;
	@XmlElement(name = "view_url")
	private URL viewUrl;
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

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public String getPaymentRequestId() {
		return paymentRequestId;
	}

	public void setPaymentRequestId(String paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
	}

	public URL getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(URL viewUrl) {
		this.viewUrl = viewUrl;
	}

	public URL getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(URL apiUrl) {
		this.apiUrl = apiUrl;
	}
}
