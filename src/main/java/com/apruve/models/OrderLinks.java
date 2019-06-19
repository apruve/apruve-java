package com.apruve.models;

import java.net.URL;

import com.apruve.JsonUtil;

/**
 * @author todd
 *
 */
public class OrderLinks {

	private URL self;
	private URL customer;
	private URL shopper;
	private URL merchant;
	private URL invoices;

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
	public URL getCustomer() {
		return customer;
	}
	public void setCustomer(URL customer) {
		this.customer = customer;
	}
	public URL getShopper() {
		return shopper;
	}
	public void setShopper(URL shopper) {
		this.shopper = shopper;
	}
	public URL getMerchant() {
		return merchant;
	}
	public void setMerchant(URL merchant) {
		this.merchant = merchant;
	}
	public URL getInvoices() {
		return invoices;
	}
	public void setInvoices(URL invoices) {
		this.invoices = invoices;
	}
}