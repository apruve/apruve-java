package com.apruve.models;

import com.apruve.JsonUtil;

public class PaymentMethod {
	
	private String type;
	private String last4;
	private String nickname;
	private PaymentMethodLinks links;

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
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLast4() {
		return last4;
	}
	public void setLast4(String last4) {
		this.last4 = last4;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public PaymentMethodLinks getLinks() {
		return links;
	}
	public void setLinks(PaymentMethodLinks links) {
		this.links = links;
	}
}
