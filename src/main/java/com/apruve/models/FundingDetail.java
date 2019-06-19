package com.apruve.models;

import javax.xml.bind.annotation.XmlElement;

import com.apruve.JsonUtil;

public class FundingDetail {

	@XmlElement(name="invoice_id")
	private String invoiceId;
	@XmlElement(name="amount_cents")
	private Integer amountCents;
	@XmlElement(name="fee_cents")
	private Integer feeCents;
	
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
	
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Integer getAmountCents() {
		return amountCents;
	}
	public void setAmountCents(Integer amountCents) {
		this.amountCents = amountCents;
	}
	public Integer getFeeCents() {
		return feeCents;
	}
	public void setFeeCents(Integer feeCents) {
		this.feeCents = feeCents;
	}
}
