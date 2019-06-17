package com.apruve.models;

import java.net.URL;

import javax.xml.bind.annotation.XmlElement;

import com.apruve.JsonUtil;

public class InvoiceItem {

	private String id;
	@XmlElement(name = "invoice_id")
	private String invoiceId;
	@XmlElement(name = "order_id")
	private String orderId;
	private String title;
	private String description;
	private Integer quantity;
	@XmlElement(name = "price_ea_cents")
	private Integer priceEachCents;
	@XmlElement(name = "price_total_cents")
	private Integer priceTotalCents;
	private String currency;
	@XmlElement(name = "product_url")
	private URL productUrl;
	@XmlElement(name = "variant_info")
	private String variantInfo;
	private String vendor;
	private String sku;
	@XmlElement(name = "merchant_notes")
	private String merchantNotes;
	private InvoiceItemLinks links;

	protected InvoiceItem() {
		// required by JAXB
	}
	
	public InvoiceItem(String title, Integer priceTotalCents) {
		this.title = title;
		this.priceTotalCents = priceTotalCents;
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

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPriceEachCents() {
		return priceEachCents;
	}

	public void setPriceEachCents(Integer priceEachCents) {
		this.priceEachCents = priceEachCents;
	}

	public Integer getPriceTotalCents() {
		return priceTotalCents;
	}

	public void setPriceTotalCents(Integer priceTotalCents) {
		this.priceTotalCents = priceTotalCents;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public URL getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(URL productUrl) {
		this.productUrl = productUrl;
	}

	public String getVariantInfo() {
		return variantInfo;
	}

	public void setVariantInfo(String variantInfo) {
		this.variantInfo = variantInfo;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getMerchantNotes() {
		return merchantNotes;
	}

	public void setMerchantNotes(String merchantNotes) {
		this.merchantNotes = merchantNotes;
	}

	public InvoiceItemLinks getLinks() {
		return links;
	}

	public void setLinks(InvoiceItemLinks links) {
		this.links = links;
	}
}