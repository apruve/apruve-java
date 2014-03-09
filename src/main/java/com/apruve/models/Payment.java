package com.apruve.models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.Date;

public class Payment
{
	private String id;

	private String paymentRequestId;

	private String paymentRequestStatus;
	
	private Integer amountCents;

	private Currency currency;

	private String merchantNotes;

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
	 * @return the paymentRequestId
	 */
	public String getPaymentRequestId()
	{
		return paymentRequestId;
	}


	/**
	 * @param paymentRequestId the paymentRequestId to set
	 */
	public void setPaymentRequestId(String paymentRequestId)
	{
		this.paymentRequestId = paymentRequestId;
	}


	/**
	 * @return the paymentRequestStatus
	 */
	public String getPaymentRequestStatus()
	{
		return paymentRequestStatus;
	}


	/**
	 * @param paymentRequestStatus the paymentRequestStatus to set
	 */
	public void setPaymentRequestStatus(String paymentRequestStatus)
	{
		this.paymentRequestStatus = paymentRequestStatus;
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
	 * @param currencyCode
	 */
	public void setCurrency(String currencyCode)
	{
		this.currency = Currency.getInstance(currencyCode);
	}


	/**
	 * @return the merchantNotes
	 */
	public String getMerchantNotes()
	{
		return merchantNotes;
	}


	/**
	 * @param merchantNotes the merchantNotes to set
	 */
	public void setMerchantNotes(String merchantNotes)
	{
		this.merchantNotes = merchantNotes;
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
	 * @param apiUrl
	 * @throws MalformedURLException
	 */
	public void setApiUrl(String apiUrl) throws MalformedURLException
	{
		this.apiUrl = new URL(apiUrl);
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
	 * @param viewUrl
	 * @throws MalformedURLException 
	 */
	public void setViewUrl(String viewUrl) throws MalformedURLException
	{
		this.viewUrl = new URL(viewUrl);
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
}
