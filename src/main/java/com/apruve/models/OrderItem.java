package com.apruve.models;

import java.net.URL;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.StringUtils;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

/**
 * @author todd
 * @since 1.0.0
 */
public class OrderItem {
	static final String ORDER_ITEM_PATH = "/order_items/";
	static final String ORDER_ITEMS_PATH = Order.ORDER_PATH + ORDER_ITEM_PATH;
	
	private String id;
	@XmlElement(name="order_id")
	private String orderId;
	private String title;
	private String description;
	private Integer quantity;
	@XmlElement(name="price_ea_cents")
	private Integer priceEachCents;
	@XmlElement(name="price_total_cents")
	private Integer priceTotalCents;
	private String currency;
	@XmlElement(name="product_url")
	private URL productUrl;
	@XmlElement(name="product_image_url")
	private URL productImageUrl;
	@XmlElement(name="variant_info")
	private String variantInfo;
	private String vendor;
	private String sku;
	@XmlElement(name="merchant_notes")
	private String merchantNotes;
	private OrderChildLinks links;

	protected OrderItem() {
		// required by JAXB
	}
	
	public OrderItem(String title) {
		this.title = title;
	}
	
	public static ApruveResponse<List<OrderItem>> getAllForOrder(ApruveClient client, String orderId) {
		return client.index(getOrderItemsPath(orderId), new GenericType<List<OrderItem>>() {});
	}

	public static ApruveResponse<? extends OrderItem> get(ApruveClient client, String orderItemId) {
		return client.get(getOrderItemPath(orderItemId), OrderItem.class);
	}
	
	public static ApruveResponse<OrderItem> delete(ApruveClient client, String orderItemId) {
		return client.delete(getOrderItemPath(orderItemId), OrderItem.class);
	}
	
	public ApruveResponse<OrderItem> create(ApruveClient client) {
		return client.post(OrderItem.getOrderItemsPath(this.getOrderId()), this, OrderItem.class);
	}

	public ApruveResponse<OrderItem> update(ApruveClient client) {
		return client.put(OrderItem.getOrderItemsPath(this.getOrderId()), this, OrderItem.class);
	}
	
	protected String toValueString() {
		StringBuilder buf = new StringBuilder();
		buf.append(this.getTitle());
		if (this.getPriceTotalCents() != null)
			buf.append(this.getPriceTotalCents());
		if (this.getPriceEachCents() != null)
			buf.append(this.getPriceEachCents());
		if (this.getQuantity() != null)
			buf.append(this.getQuantity());
		buf.append(StringUtils.defaultString(this.getMerchantNotes()));
		buf.append(StringUtils.defaultString(this.getDescription()));
		buf.append(StringUtils.defaultString(this.getVariantInfo()));
		buf.append(StringUtils.defaultString(this.getSku()));
		buf.append(StringUtils.defaultString(this.getVendor()));
		if (this.getProductUrl() != null)
			buf.append(this.getProductUrl());
		return buf.toString();
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
	
	/*
	 * ========== Getters and Setters ==========
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public URL getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(URL productImageUrl) {
		this.productImageUrl = productImageUrl;
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

	public OrderChildLinks getLinks() {
		return links;
	}

	public void setLinks(OrderChildLinks links) {
		this.links = links;
	}

	public static String getOrderItemPath(String orderItemId) {
		return ORDER_ITEM_PATH + orderItemId;
	}
	
	public static String getOrderItemsPath(String orderId) {
		return ORDER_ITEMS_PATH.replace("%orderId", orderId);
	}
}
