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


	public LineItem()
	{
		super();
	}


	/**
	 * Minimum for recurring payment requests.
	 * 
	 * @param title
	 */
	public LineItem(String title)
	{
		super();
		this.title = title;
	}


	/**
	 * Minimum for non-recurring payment requests.
	 * 
	 * @param title
	 * @param amountCents
	 */
	public LineItem(String title, Integer amountCents)
	{
		super();
		this.title = title;
		this.amountCents = amountCents;
	}
	

	/**
	 * @return
	 */
	public Integer getAmount_cents() {
		return amountCents;
	}


	/**
	 * @param amountCents
	 */
	public void setAmount_cents(Integer amountCents) {
		this.amountCents = amountCents;
	}


	/**
	 * @return
	 */
	public String getCurrency() {
		return currency;
	}


	/**
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}


	/**
	 * @return
	 */
	public Integer getQuantity() {
		return quantity;
	}


	/**
	 * @param quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return
	 */
	public String getSku() {
		return sku;
	}


	/**
	 * @param sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
}
