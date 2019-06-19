package com.apruve.models;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import com.apruve.JsonUtil;

public class Payment {

	private String id; 
	@XmlElement(name="invoice_ids")
	private List<String> invoiceIds; 
	@XmlElement(name="payer_id")
	private String payerId; 
	@XmlElement(name="amount_cents")
	private Integer amountCents; 
	@XmlElement(name="refunded_amount_cents")
	private Integer refundedAmountCents; 
	private String currency;
	@XmlElement(name="failure_reason")
	private String failureReason; 
	private String status; 
	@XmlElement(name="created_at")
	private Date createdAt; 
	@XmlElement(name="captured_at")
	private Date capturedAt; 
	@XmlElement(name="state_changed_at")
	private Date stateChangedAt; 
	@XmlElement(name="paid_out")
	private Boolean paidOut; 
	private PaymentLinks links;
	@XmlElement(name="payment_method")
    private PaymentMethod paymentMethod;

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
	public List<String> getInvoiceIds() {
		return invoiceIds;
	}
	public void setInvoiceIds(List<String> invoiceIds) {
		this.invoiceIds = invoiceIds;
	}
	public String getPayerId() {
		return payerId;
	}
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	public Integer getAmountCents() {
		return amountCents;
	}
	public void setAmountCents(Integer amountCents) {
		this.amountCents = amountCents;
	}
	public Integer getRefundedAmountCents() {
		return refundedAmountCents;
	}
	public void setRefundedAmountCents(Integer refundedAmountCents) {
		this.refundedAmountCents = refundedAmountCents;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getFailureReason() {
		return failureReason;
	}
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getCapturedAt() {
		return capturedAt;
	}
	public void setCapturedAt(Date capturedAt) {
		this.capturedAt = capturedAt;
	}
	public Date getStateChangedAt() {
		return stateChangedAt;
	}
	public void setStateChangedAt(Date stateChangedAt) {
		this.stateChangedAt = stateChangedAt;
	}
	public Boolean getPaidOut() {
		return paidOut;
	}
	public void setPaidOut(Boolean paidOut) {
		this.paidOut = paidOut;
	}
	public PaymentLinks getLinks() {
		return links;
	}
	public void setLinks(PaymentLinks links) {
		this.links = links;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
