package com.apruve.models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import static com.apruve.Utilities.*;

public class PaymentRequest
{
	private String id;

	private String merchantId;

	private PaymentRequestStatus status;

	private String merchantOrderId;

	private Integer amountCents;

	private Boolean recurring;

	private Integer taxCents;
	
	private Integer shippingCents;

	private Currency currency;

	private List<PaymentRequestLineItem> lineItems;

	private URL apiUrl;

	private URL viewUrl;
	
	private Date createdAt;

	private Date updatedAt;


	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}


	/**
	 * @return the merchantId
	 */
	public String getMerchantId()
	{
		return merchantId;
	}


	/**
	 * @param merchantId the merchantId to set
	 */
	public void setMerchantId(String merchantId)
	{
		this.merchantId = merchantId;
	}


	/**
	 * @return the status
	 */
	public PaymentRequestStatus getStatus()
	{
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(PaymentRequestStatus status)
	{
		this.status = status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status)
	{
		this.status = PaymentRequestStatus.valueOf(status.toUpperCase());
	}


	/**
	 * @return the merchantOrderId
	 */
	public String getMerchantOrderId()
	{
		return merchantOrderId;
	}


	/**
	 * @param merchantOrderId the merchantOrderId to set
	 */
	public void setMerchantOrderId(String merchantOrderId)
	{
		this.merchantOrderId = merchantOrderId;
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
	 * @param amountCents the amountCents to set
	 */
	public void setAmountCents(String amountCents)
	{
		this.amountCents = hasText(amountCents) ? new Integer(amountCents) : null;
	}


	/**
	 * @return the recurring
	 */
	public Boolean getRecurring()
	{
		return recurring;
	}


	/**
	 * @param recurring the recurring to set
	 */
	public void setRecurring(Boolean recurring)
	{
		this.recurring = recurring;
	}


	/**
	 * @return the taxCents
	 */
	public Integer getTaxCents()
	{
		return taxCents;
	}


	/**
	 * @param taxCents the taxCents to set
	 */
	public void setTaxCents(Integer taxCents)
	{
		this.taxCents = taxCents;
	}


	/**
	 * @param taxCents the taxCents to set
	 */
	public void setTaxCents(String taxCents)
	{
		this.taxCents = hasText(taxCents) ? new Integer(taxCents) : null;
	}


	/**
	 * @return the shippingCents
	 */
	public Integer getShippingCents()
	{
		return shippingCents;
	}


	/**
	 * @param shippingCents the shippingCents to set
	 */
	public void setShippingCents(Integer shippingCents)
	{
		this.shippingCents = shippingCents;
	}


	/**
	 * @param shippingCents
	 */
	public void setShippingCenter(String shippingCents)
	{
		this.shippingCents = hasText(shippingCents) ? new Integer(shippingCents) : null;
	}


	/**
	 * @return the currency
	 */
	public Currency getCurrency()
	{
		return currency;
	}


	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}


	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency)
	{
		this.currency = hasText(currency) ? Currency.getInstance(currency) : null;
	}


	/**
	 * @return the lineItems
	 */
	public List<PaymentRequestLineItem> getLineItems()
	{
		return lineItems;
	}


	/**
	 * @param lineItems the lineItems to set
	 */
	public void setLineItems(List<PaymentRequestLineItem> lineItems)
	{
		this.lineItems = lineItems;
	}


	/**
	 * @return the apiUrl
	 */
	public URL getApiUrl()
	{
		return apiUrl;
	}


	/**
	 * @param apiUrl the apiUrl to set
	 */
	public void setApiUrl(URL apiUrl)
	{
		this.apiUrl = apiUrl;
	}


	/**
	 * @param apiUrl the apiUrl to set
	 * @throws MalformedURLException 
	 */
	public void setApiUrl(String apiUrl) throws MalformedURLException
	{
		this.apiUrl = hasText(apiUrl) ? new URL(apiUrl) : null;
	}


	/**
	 * @return the viewUrl
	 */
	public URL getViewUrl()
	{
		return viewUrl;
	}


	/**
	 * @param viewUrl the viewUrl to set
	 */
	public void setViewUrl(URL viewUrl)
	{
		this.viewUrl = viewUrl;
	}


	/**
	 * @param viewUrl the viewUrl to set
	 * @throws MalformedURLException 
	 */
	public void setViewUrl(String viewUrl) throws MalformedURLException
	{
		this.viewUrl = hasText(viewUrl) ? new URL(viewUrl) : null;
	}


	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt()
	{
		return createdAt;
	}


	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}


	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(String createdAt)
	{
		this.createdAt = parseTimestamp(createdAt);
	}


	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt()
	{
		return updatedAt;
	}


	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Date updatedAt)
	{
		this.updatedAt = updatedAt;
	}


	/**
	 * @param updatedAt
	 */
	public void setUpdatedAt(String updatedAt)
	{
		this.updatedAt = parseTimestamp(updatedAt);
	}
}
