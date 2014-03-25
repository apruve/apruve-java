package com.apruve.models;

import java.net.URL;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

/**
 * @author Robert Nelson
 * @since 0.2.0
 */
@XmlRootElement
public class LineItem {
	protected static final String LINE_ITEM_PATH = PaymentRequest.PAYMENT_REQUESTS_PATH + "%reqId/line_items/";
	
	private String id;
	private String title;
	@XmlElement(name="amount_cents")
	private Integer amountCents;
	@XmlElement(name="price_ea_cents")
	private Integer priceEachCents;
	private Integer quantity;
	private String description;
	@XmlElement(name="variant_info")
	private String variantInfo;
	private String sku;
	private String vendor;
	@XmlElement(name="view_product_url")
	private URL viewProductUrl;
	@XmlElement(name="plan_code")
	private String planCode;
	@XmlElement(name="line_item_api_url")
	private URL apiUrl;
	@XmlElement(name="subscription_url")
	private URL subscriptionUrl;

	protected LineItem() {
		// Required for JAXB
	}
	
	public LineItem(String title) {
		this.title = title;
	}
	
	protected String toValueString() {
		StringBuilder buf = new StringBuilder();
		buf.append(this.getTitle());
		buf.append(this.subscriptionValues());
		if (this.getAmountCents() != null)
			buf.append(this.getAmountCents());
		if (this.getPriceEachCents() != null)
			buf.append(this.getPriceEachCents());
		if (this.getQuantity() != null)
			buf.append(this.getQuantity());
		buf.append(StringUtils.defaultString(this.getDescription()));
		buf.append(StringUtils.defaultString(this.getVariantInfo()));
		buf.append(StringUtils.defaultString(this.getSku()));
		buf.append(StringUtils.defaultString(this.getVendor()));
		if (this.getViewProductUrl() != null) {
			buf.append(this.getViewProductUrl().toString());
		}
		return buf.toString();
	}
	
	protected String subscriptionValues() {
		return "";
	}
	
	public static ApruveResponse<List<LineItem>> getAll(String paymentRequestId) {
		return ApruveClient.getInstance().index(getLineItemsPath(paymentRequestId), new GenericType<List<LineItem>>() {});
	}

	public static ApruveResponse<? extends LineItem> get(String paymentRequestId, String lineItemId) {
		return ApruveClient.getInstance().get(getLineItemsPath(paymentRequestId) + lineItemId, LineItem.class);
	}
	
	public String toJson() {
		return JsonUtil.getInstance().toJson(this);
	}
	
	@Override
	public String toString() {
		return toJson();
	}
	
	/*
	 * ========== Getters and Setters ==========
	 */

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the amountCents
	 */
	public Integer getAmountCents() {
		return amountCents;
	}

	/**
	 * @param amountCents
	 *            the amountCents to set
	 */
	public void setAmountCents(Integer amountCents) {
		this.amountCents = amountCents;
	}

	/**
	 * @return the priceEachCents
	 */
	public Integer getPriceEachCents() {
		return priceEachCents;
	}

	/**
	 * @param priceEachCents
	 *            the priceEachCents to set
	 */
	public void setPriceEachCents(Integer priceEachCents) {
		this.priceEachCents = priceEachCents;
	}

	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the variantInfo
	 */
	public String getVariantInfo() {
		return variantInfo;
	}

	/**
	 * @param variantInfo
	 *            the variantInfo to set
	 */
	public void setVariantInfo(String variantInfo) {
		this.variantInfo = variantInfo;
	}

	/**
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku
	 *            the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the vendor
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the viewProductUrl
	 */
	public URL getViewProductUrl() {
		return viewProductUrl;
	}

	/**
	 * @param viewProductUrl
	 *            the viewProductUrl to set
	 */
	public void setViewProductUrl(URL viewProductUrl) {
		this.viewProductUrl = viewProductUrl;
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}

	public URL getSubscriptionUrl() {
		return subscriptionUrl;
	}

	public void setSubscriptionUrl(URL subscriptionUrl) {
		this.subscriptionUrl = subscriptionUrl;
	}

	public URL getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(URL apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static String getLineItemsPath(String paymentRequestId) {
		return LINE_ITEM_PATH.replace("%reqId", paymentRequestId);
	}
}
