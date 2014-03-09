package com.apruve.models;

import java.net.URL;

/**
 * @author Robert Nelson
 * @since 0.2.0
 */
public class PaymentRequestLineItem
{
	private String title;

	private Integer amountCents;

	private Integer priceEachCents;

	private Integer quantity;
	
	private String description;

	private String variantInfo;

	private String sku;

	private String vendor;

	private URL viewProductUrl;


	/*
	 * ========== Getters and Setters ==========
	 */


	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}


	/**
	 * @return the amountCents
	 */
	public Integer getAmountCents()
	{
		return amountCents;
	}


	/**
	 * @param amountCents the amountCents to set
	 */
	public void setAmountCents(Integer amountCents)
	{
		this.amountCents = amountCents;
	}


	/**
	 * @return the priceEachCents
	 */
	public Integer getPriceEachCents()
	{
		return priceEachCents;
	}


	/**
	 * @param priceEachCents the priceEachCents to set
	 */
	public void setPriceEachCents(Integer priceEachCents)
	{
		this.priceEachCents = priceEachCents;
	}


	/**
	 * @return the quantity
	 */
	public Integer getQuantity()
	{
		return quantity;
	}


	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity)
	{
		this.quantity = quantity;
	}


	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}


	/**
	 * @return the variantInfo
	 */
	public String getVariantInfo()
	{
		return variantInfo;
	}


	/**
	 * @param variantInfo the variantInfo to set
	 */
	public void setVariantInfo(String variantInfo)
	{
		this.variantInfo = variantInfo;
	}


	/**
	 * @return the sku
	 */
	public String getSku()
	{
		return sku;
	}


	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku)
	{
		this.sku = sku;
	}


	/**
	 * @return the vendor
	 */
	public String getVendor()
	{
		return vendor;
	}


	/**
	 * @param vendor the vendor to set
	 */
	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}


	/**
	 * @return the viewProductUrl
	 */
	public URL getViewProductUrl()
	{
		return viewProductUrl;
	}


	/**
	 * @param viewProductUrl the viewProductUrl to set
	 */
	public void setViewProductUrl(URL viewProductUrl)
	{
		this.viewProductUrl = viewProductUrl;
	}
}
