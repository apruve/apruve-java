package com.apruve;

import java.io.Serializable;



public class Payment implements Serializable {
	private static final long serialVersionUID = -6516938813494453774L;
	private static final String PAYMENTS_PATH = "/api/v3/payment_requests/%s/payments";
	private String paymentRequestId;
	private Integer amountCents;
	private String id;
	private String status;
	
	public String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	protected void setStatus(String status) {
		this.status = status;
	}

	public Payment(String paymentRequestId, Integer amountCents) {
		this.paymentRequestId = paymentRequestId;
		this.amountCents = amountCents;
	}
	
	public String toJson() {
		StringBuffer buf = new StringBuffer();
		buf.append("{\"amount_cents\":").append(this.amountCents).append("\"}");
		return buf.toString();
	}
	
	public String getUrl() {
		String url = PAYMENTS_PATH.replaceAll("%s", paymentRequestId);
		return ApruveClient.getInstance().getApruveUrl() + url;
	}

	public String getPaymentRequestId() {
		return paymentRequestId;
	}

	public Integer getAmountCents() {
		return amountCents;
	}
	
	
}
