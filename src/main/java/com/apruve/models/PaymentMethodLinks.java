package com.apruve.models;

import java.net.URL;

import com.apruve.JsonUtil;

public class PaymentMethodLinks {

	private URL owner;

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
	
	public URL getOwner() {
		return owner;
	}

	public void setOwner(URL owner) {
		this.owner = owner;
	}
}
