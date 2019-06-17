package com.apruve.models;

import javax.xml.bind.annotation.XmlElement;

import com.apruve.JsonUtil;

public class ShipmentItem {
	
	private String id;
	@XmlElement(name="shipment_id")
	private String shipmentId;
	private String title;
	private String description;
	private Integer quantity;
	@XmlElement(name="price_ea_cents")
	private Integer priceEachCents;
	@XmlElement(name="price_total_cents")
	private Integer priceTotalCents;
	@XmlElement(name="shipping_cents")
	private Integer shippingCents;
	@XmlElement(name="tax_cents")
	private Integer taxCents;
	private String currency;
	@XmlElement(name="variant_info")
	private String variantInfo;
	private String vendor;
	private String sku;
	@XmlElement(name="merchant_notes")
	private String merchantNotes;
	
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
	public String getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
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
	public Integer getShippingCents() {
		return shippingCents;
	}
	public void setShippingCents(Integer shippingCents) {
		this.shippingCents = shippingCents;
	}
	public Integer getTaxCents() {
		return taxCents;
	}
	public void setTaxCents(Integer taxCents) {
		this.taxCents = taxCents;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
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
}
