package com.apruve.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import com.apruve.JsonUtil;

public class PaymentTerm {

	@XmlElement(name="order_id")
	private String orderId;
	@XmlElement(name="corporate_account_id")
	private String corpAccountId;
	private String type;
	private String status;
	@XmlElement(name="final_state_at")
	private Date finalStateAt;
	private OrderChildLinks links;
	
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
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCorpAccountId() {
		return corpAccountId;
	}
	public void setCorpAccountId(String corpAccountId) {
		this.corpAccountId = corpAccountId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getFinalStateAt() {
		return finalStateAt;
	}
	public void setFinalStateAt(Date finalStateAt) {
		this.finalStateAt = finalStateAt;
	}
	public OrderChildLinks getLinks() {
		return links;
	}
	public void setLinks(OrderChildLinks links) {
		this.links = links;
	}
}
