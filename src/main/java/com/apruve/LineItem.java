package com.apruve;

import java.io.Serializable;

import net.sf.json.processors.PropertyNameProcessor;

/**
 * Represents a line item on a PaymentRequest.
 * 
 * @author neal
 * 
 */
public class LineItem implements Serializable {
	private static final long serialVersionUID = 8079294991285612248L;
	public static final PropertyNameProcessor JSON_PROP_NAME_PROCESSOR;
	static {
		JSON_PROP_NAME_PROCESSOR = new PropertyNameProcessor() {
			@SuppressWarnings("rawtypes")
			@Override
			public String processPropertyName(Class parentClass,
					String propertyName) {
				String jsonName = propertyName;
				switch (propertyName) {
				case "amountCents":
					jsonName = "amount_cents";
					break;
				case "priceEachCents":
					jsonName = "price_ea_cents";
					break;
				case "variantInfo":
					jsonName = "variant_info";
					break;
				case "viewProductUrl":
					jsonName = "view_product_url";
				}
				return jsonName;
			}
		};
	}

	private Integer amountCents = null;
	private Integer priceEachCents = null;
	private String currency = null;
	private Integer quantity = null;
	private String title = null;
	private String description = null;
	private String sku = null;
	private String variantInfo = null;
	private String vendor = null;
	private String viewProductUrl = null;

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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getPriceEachCents() {
		return priceEachCents;
	}

	public void setPriceEachCents(Integer priceEachCents) {
		this.priceEachCents = priceEachCents;
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

	public String getViewProductUrl() {
		return viewProductUrl;
	}

	public void setViewProductUrl(String viewProductUrl) {
		this.viewProductUrl = viewProductUrl;
	}

}
