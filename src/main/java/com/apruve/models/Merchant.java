package com.apruve.models;

import javax.xml.bind.annotation.XmlElement;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

public class Merchant {
	static final String MERCHANTS_PATH = "/merchants/";
	static final String MERCHANT_PATH = MERCHANTS_PATH + "%merchantId";
	
	private String id;
	private String name;
	private String email;
	@XmlElement(name="web_url")
	private String webUrl;
	private String phone;
	@XmlElement(name="url_slug")
	private String urlSlug;

	public static String getMerchantPath(String merchantId) {
		return MERCHANT_PATH.replace("%merchantId", merchantId);
	}
	
	public static ApruveResponse<Merchant> get(ApruveClient client, String merchantId) {
		return client.get(getMerchantPath(merchantId), Merchant.class);
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUrlSlug() {
		return urlSlug;
	}

	public void setUrlSlug(String urlSlug) {
		this.urlSlug = urlSlug;
	}
	
}
