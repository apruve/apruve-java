package com.apruve;

import java.io.Serializable;

/**
 * Represents a line item on a PaymentRequest.
 * 
 * @author neal
 * 
 */
public class LineItem implements Serializable {
	private static final long serialVersionUID = 8079294991285612248L;
	private Integer amountCents = null;
	private String currency = null;
	private Integer quantity = null;
	private String title = null;
	private String description = null;
	private String sku = null;

	public Integer getAmount_cents() {
		return amountCents;
	}

	public void setAmount_cents(Integer amountCents) {
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

}
