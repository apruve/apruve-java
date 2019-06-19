package com.apruve.models;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.apruve.JsonUtil;

public class PaymentLinks {

	private URL self;
	private List<URL> invoices;
	private URL payer;
	private List<URL> refunds;

	public PaymentLinks() {
		invoices = new ArrayList<URL>();
		refunds = new ArrayList<URL>();
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

	public URL getSelf() {
		return self;
	}

	public void setSelf(URL self) {
		this.self = self;
	}

	public List<URL> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<URL> invoices) {
		this.invoices = invoices;
	}

	public URL getPayer() {
		return payer;
	}

	public void setPayer(URL payer) {
		this.payer = payer;
	}

	public List<URL> getRefunds() {
		return refunds;
	}

	public void setRefunds(List<URL> refunds) {
		this.refunds = refunds;
	}
}
