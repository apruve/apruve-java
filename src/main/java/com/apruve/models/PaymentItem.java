package com.apruve.models;

import java.net.URL;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apruve.JsonUtil;

@XmlRootElement
public class PaymentItem {

	private String title;
	@XmlElement(name="amount_cents")
	private Integer amountCents;
	@XmlElement(name="price_each_cents")
	private Integer priceEachCents;
	private Integer quantity;
	@XmlElement(name="merchant_notes")
	private String merchantNotes;
	private String description;
	@XmlElement(name="variant_info")
	private String variantInfo;
	private String sku;
	private String vendor;
	@XmlElement(name="view_product_url")
	private URL viewProductUrl;
	
	@Override
	public String toString() {
		return toJson();
	}

	public String toJson() {
		return JsonUtil.getInstance().toJson(this);		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAmountCents() {
		return amountCents;
	}

	public void setAmountCents(Integer amountCents) {
		this.amountCents = amountCents;
	}

	public Integer getPriceEachCents() {
		return priceEachCents;
	}

	public void setPriceEachCents(Integer priceEachCents) {
		this.priceEachCents = priceEachCents;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getMerchantNotes() {
		return merchantNotes;
	}
	
	public void setMerchantNotes(String merchantNotes) {
		this.merchantNotes = merchantNotes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariantInfo() {
		return variantInfo;
	}

	public void setVariantInfo(String variantInfo) {
		this.variantInfo = variantInfo;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public URL getViewProductUrl() {
		return viewProductUrl;
	}

	public void setViewProductUrl(URL viewProductUrl) {
		this.viewProductUrl = viewProductUrl;
	}

}
