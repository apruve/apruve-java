package com.apruve.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;
import com.apruve.ShaUtil;

/**
 * A request to a payer for payment on behalf of a shopper. In order to provide
 * the prospective payer with as much information as possible, and enhance the
 * likelihood for approval, as much detail should be provided as possible.
 * 
 * @author neal
 * 
 */
@XmlRootElement(name="order")
public class Order {
	static final String ORDERS_PATH = "/orders/";
	static final String ORDER_PATH = ORDERS_PATH + "%orderId";

	@XmlJavaTypeAdapter(value = OrderStatusAdapter.class)
	public static enum OrderStatus {
		NEW, PENDING, ACCEPTED, CANCELED;

		public String getDisplay() {
			return StringUtils.capitalize(this.toString());
		}
	}

	private static class OrderStatusAdapter extends XmlAdapter<String, OrderStatus> {
		@Override
		public OrderStatus unmarshal(String v) throws Exception {
			return v == null ? null : OrderStatus.valueOf(v.toUpperCase());
		}

		@Override
		public String marshal(OrderStatus v) throws Exception {
			return v == null ? null : v.toString().toLowerCase();
		}
	}

	private String id;
	@XmlElement(name = "merchant_id")
	private String merchantId;
	private OrderStatus status = null;
	@XmlElement(name = "merchant_order_id")
	private String merchantOrderId;
	@XmlElement(name = "amount_cents")
	private Integer amountCents;
	@XmlElement(name = "tax_cents")
	private Integer taxCents;
	@XmlElement(name = "shipping_cents")
	private Integer shippingCents;
	@XmlElement(name = "currency")
	private String currency;
	@XmlElement(name = "created_at")
	private Date createdAt;
	@XmlElement(name = "updated_at")
	private Date updatedAt;
	@XmlElement(name = "shopper_id")
	private String shopperId;
	@XmlElement(name = "accepts_payment_via")
	private String acceptsPaymentVia;
	@XmlElement(name = "accepts_payment_terms")
	private String acceptsPaymentTerms;
	@XmlElement(name = "final_state_at")
	private Date finalStateAt;
	@XmlElement(name = "expire_at")
	private Date expireAt;
	@XmlElement(name = "default_payment_method")
	private String defaultPaymentMethod;
	@XmlElement(name = "links")
	private OrderLinks links;
	@XmlElement(name = "finalize_on_create")
	private Boolean finalizeOnCreate;
	@XmlElement(name = "invoice_on_create")
	private Boolean invoiceOnCreate;
	@XmlElement(name = "po_number")
	private String poNumber;
	@XmlElement(name = "order_items")
	private List<OrderItem> orderItems;
	@XmlElement(name = "payment_term")
	private PaymentTerm paymentTerm;

	@XmlTransient
	private String apiKey;
	
	protected Order() {
		super();
		this.orderItems = new ArrayList<OrderItem>();
	}

	public Order(String merchantId, String apiKey) {
		this();
		this.merchantId = merchantId;
		this.apiKey = apiKey;
	}

	public static ApruveResponse<List<Order>> getAll(ApruveClient client) {
		return client.index(ORDERS_PATH, new GenericType<List<Order>>() {});
	}
	
	public static ApruveResponse<List<Order>> getAll(ApruveClient client, String merchantOrderId) {
		HashMap<String, Object> params = new HashMap<String, Object>();		
		if (merchantOrderId != null) params.put("merchant_order_id", merchantOrderId);
		return client.index(ORDERS_PATH, new GenericType<List<Order>>() {}, params);
	}
	
	public static ApruveResponse<List<Order>> getAll(ApruveClient client, Integer page, Integer perPage) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (page != null) params.put("page", page);
		if (perPage != null) params.put("per_page", perPage);
		return client.index(ORDERS_PATH, new GenericType<List<Order>>() {}, params);
	}
	
	/**
	 * Fetches the Order with the given ID from Apruve.
	 * 
	 * @see <a href=
	 *      "https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @param orderId
	 * @return Order, or null if not found
	 */
	public static ApruveResponse<Order> get(ApruveClient client, String orderId) {
		return client.get(ORDERS_PATH + orderId, Order.class);
	}

	/**
	 * Invokes the "finalize" action on the order. This allows you to
	 * escalate the request to the payer without creating a Payment first.
	 * 
	 * @see <a href=
	 *      "https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return PaymentRequestUpdateResponse, or null if the request does not
	 *         exist
	 */
	public static ApruveResponse<Order> finalizeOrder(ApruveClient client, String orderId) {
		return client.post(getOrderPath(orderId) + "/finalize", "", Order.class);
	}
	
	public static ApruveResponse<Order> cancelOrder(ApruveClient client, String orderId) {
		return client.post(getOrderPath(orderId) + "/cancel", "", Order.class);
	}
	
	/**
	 * @return
	 */
	public ApruveResponse<Order> create(ApruveClient client) {
		return client.post(ORDERS_PATH, this, Order.class);
	}
	
	/**
	 * Updates the Order state at Apruve to match the current object
	 * state.
	 * 
	 * @see <a href=
	 *      "https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return Order reflecting the new state, or null if the request does not
	 *         exist
	 */
	public ApruveResponse<Order> update(ApruveClient client) {
		return client.put(ORDERS_PATH + this.id, this, Order.class);
	}

	/**
	 * For use by merchants, and depends on proper initialization of
	 * ApruveClient. Returns the secure hash for an Order, suitable for
	 * use with the property of apruve.js JavaScript library on a merchant
	 * checkout page. Use this to populate the value of apruve.secureHash.
	 * 
	 * @return
	 */
	public String toSecureHash() {
		String shaInput = this.apiKey + toValueString();

		return ShaUtil.getDigest(shaInput);
	}

	protected String toValueString() {
		StringBuilder buf = new StringBuilder();

		buf.append(StringUtils.defaultString(merchantId));
		buf.append(StringUtils.defaultString(merchantOrderId));
		if (amountCents != null)
			buf.append(amountCents);
		if (currency != null)
			buf.append(currency);
		if (taxCents != null)
			buf.append(taxCents);
		if (shippingCents != null)
			buf.append(shippingCents);
		if (expireAt != null) {
			buf.append(DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(expireAt));
		}
		if (acceptsPaymentTerms != null) {
			buf.append(acceptsPaymentTerms);
		}
		if (invoiceOnCreate != null) {
			buf.append(invoiceOnCreate);
		}
		if (finalizeOnCreate != null) {
			buf.append(finalizeOnCreate);
		}
		for (OrderItem line : orderItems) {
			buf.append(line.toValueString());
		}
		return buf.toString();
	}

	public void addOrderItem(OrderItem item) {
		this.orderItems.add(item);
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
	
	//////////////////////
	// Getters and Setters
	//////////////////////

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public Integer getAmountCents() {
		return amountCents;
	}

	public void setAmountCents(Integer amountCents) {
		this.amountCents = amountCents;
	}

	public Integer getTaxCents() {
		return taxCents;
	}

	public void setTaxCents(Integer taxCents) {
		this.taxCents = taxCents;
	}

	public Integer getShippingCents() {
		return shippingCents;
	}

	public void setShippingCents(Integer shippingCents) {
		this.shippingCents = shippingCents;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getShopperId() {
		return shopperId;
	}

	public void setShopperId(String shopperId) {
		this.shopperId = shopperId;
	}

	public String getAcceptsPaymentVia() {
		return acceptsPaymentVia;
	}

	public void setAcceptsPaymentVia(String acceptsPaymentVia) {
		this.acceptsPaymentVia = acceptsPaymentVia;
	}

	public String getAcceptsPaymentTerms() {
		return acceptsPaymentTerms;
	}

	public void setAcceptsPaymentTerms(String acceptsPaymentTerms) {
		this.acceptsPaymentTerms = acceptsPaymentTerms;
	}

	public Date getFinalStateAt() {
		return finalStateAt;
	}

	public void setFinalStateAt(Date finalStateAt) {
		this.finalStateAt = finalStateAt;
	}

	public String getDefaultPaymentMethod() {
		return defaultPaymentMethod;
	}

	public void setDefaultPaymentMethod(String defaultPaymentMethod) {
		this.defaultPaymentMethod = defaultPaymentMethod;
	}

	public OrderLinks getLinks() {
		return links;
	}

	public void setLinks(OrderLinks links) {
		this.links = links;
	}

	public Boolean getFinalizeOnCreate() {
		return finalizeOnCreate;
	}

	public void setFinalizeOnCreate(Boolean finalizeOnCreate) {
		this.finalizeOnCreate = finalizeOnCreate;
	}

	public Boolean getInvoiceOnCreate() {
		return invoiceOnCreate;
	}

	public void setInvoiceOnCreate(Boolean invoiceOnCreate) {
		this.invoiceOnCreate = invoiceOnCreate;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public PaymentTerm getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(PaymentTerm paymentTerm) {
		this.paymentTerm = paymentTerm;
	}

	public Date getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(Date expireAt) {
		this.expireAt = expireAt;
	}

	public static String getOrderPath(String orderId) {
		return ORDER_PATH.replace("%orderId", orderId);
	}
}
