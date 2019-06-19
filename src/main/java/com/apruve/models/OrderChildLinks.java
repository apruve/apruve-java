package com.apruve.models;

import java.net.URL;

import com.apruve.JsonUtil;

/**
 * @author todd
 *
 */
public class OrderChildLinks {

	private URL self;
	private URL order;
	
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
	
	public URL getSelf() {
		return self;
	}
	public void setSelf(URL self) {
		this.self = self;
	}
	public URL getOrder() {
		return order;
	}
	public void setOrder(URL order) {
		this.order = order;
	}
}