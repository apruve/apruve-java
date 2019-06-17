package com.apruve.models;

import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.xml.bind.annotation.XmlElement;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

public class InvoiceReturn {
	static final String INVOICE_RETURNS_PATH = Invoice.INVOICE_PATH + "/invoice_returns/";
	static final String INVOICE_RETURN_PATH = INVOICE_RETURNS_PATH + "%invoiceReturnId";
	
	private String id; 
	@XmlElement(name="invoice_id")
	private String invoiceId; 
	@XmlElement(name="amount_cents")
	private Integer amountCents; 
	private String currency; 
	private String reason; 
	@XmlElement(name="merchant_id")
	private String merchantId;
	
	public static String getInvoiceReturnsPath(String invoiceId) {
		return INVOICE_RETURNS_PATH.replace("%invoiceId", invoiceId);
	}
	
	public static String getInvoiceReturnPath(String invoiceId, String invoiceReturnId) {
		return INVOICE_RETURN_PATH.replace("%invoiceId", invoiceId).replace("%invoiceReturnId", invoiceReturnId);
	}
	
	public static ApruveResponse<List<InvoiceReturn>> getForInvoice(ApruveClient client, String invoiceId) {
		return client.index(getInvoiceReturnsPath(invoiceId), new GenericType<List<InvoiceReturn>>() {});
	}
	
	public static ApruveResponse<InvoiceReturn> get(ApruveClient client, String invoiceId, String invoiceReturnId) {
		return client.get(getInvoiceReturnPath(invoiceId, invoiceReturnId), InvoiceReturn.class);
	}
	
	public ApruveResponse<InvoiceReturn> create(ApruveClient client) {
		return client.post(InvoiceReturn.getInvoiceReturnsPath(this.invoiceId), this, InvoiceReturn.class);
	}
	
	public ApruveResponse<InvoiceReturn> update(ApruveClient client) {
		return client.put(InvoiceReturn.getInvoiceReturnPath(this.invoiceId, this.id), this, InvoiceReturn.class);
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
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
}
