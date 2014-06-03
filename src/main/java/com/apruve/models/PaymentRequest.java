package com.apruve.models;

import static com.apruve.Utilities.hasText;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.StringUtils;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;
import com.apruve.ShaUtil;
import com.apruve.Utilities;

/**
 * A request to a payer for payment on behalf of a shopper. In order to provide
 * the prospective payer with as much information as possible, and enhance the
 * likelihood for approval, as much detail should be provided as possible.
 * 
 * @author neal
 * 
 */
@XmlRootElement
public class PaymentRequest {
	protected static final String PAYMENT_REQUESTS_PATH = "/payment_requests/";
	private static final String FINALIZE_PATH = PAYMENT_REQUESTS_PATH
			+ "%reqId/finalize";

	@XmlJavaTypeAdapter(value = PaymentRequestStatusAdapter.class)
	public static enum PaymentRequestStatus {
		NEW, READY, PENDING, APPROVED, REJECTED, CANCELED;

		public String getDisplay() {
			return StringUtils.capitalize(this.toString());
		}
	}

	private static class PaymentRequestStatusAdapter extends
			XmlAdapter<String, PaymentRequestStatus> {
		@Override
		public PaymentRequestStatus unmarshal(String v) throws Exception {
			return v == null ? null : PaymentRequestStatus.valueOf(v
					.toUpperCase());
		}

		@Override
		public String marshal(PaymentRequestStatus v) throws Exception {
			return v == null ? null : v.toString().toLowerCase();
		}
	}

	private String id;
	@XmlElement(name = "merchant_id")
	private String merchantId;
	private String username;
	private PaymentRequestStatus status = PaymentRequestStatus.NEW;
	@XmlElement(name = "merchant_order_id")
	private String merchantOrderId;
	@XmlElement(name = "amount_cents")
	private Integer amountCents;
	@XmlElement(name = "tax_cents")
	private Integer taxCents;
	@XmlElement(name = "shipping_cents")
	private Integer shippingCents;
	private Currency currency;
	@XmlElement(name = "line_items")
	private List<LineItem> lineItems;
	@XmlElement(name = "api_url")
	private URL apiUrl;
	@XmlElement(name = "view_url")
	private URL viewUrl;
	@XmlElement(name = "created_at")
	private Date createdAt;
	@XmlElement(name = "updated_at")
	private Date updatedAt;

	protected PaymentRequest() {
		// Required for JAXB
		this.lineItems = new ArrayList<LineItem>();
	}

	public PaymentRequest(String merchantId) {
		this();
		this.merchantId = merchantId;
	}

	/**
	 * Fetches the PaymentRequest with the given ID from Apruve.
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @param paymentRequestId
	 * @return PaymentRequest, or null if not found
	 */
	public static ApruveResponse<PaymentRequest> get(String paymentRequestId) {
		return ApruveClient.getInstance().get(
				PAYMENT_REQUESTS_PATH + paymentRequestId, PaymentRequest.class);
	}

	/**
	 * Updates the PaymentRequest state at Apruve to match the current object
	 * state.
	 * <p>
	 * Only the following fields are updated by this API call, and then only if
	 * the payment request status is NEW:
	 * <ul>
	 * <li>merchantOrderId</li>
	 * <li>amountCents</li>
	 * <li>taxCents</li>
	 * <li>shippingCents</li>
	 * </ul>
	 * 
	 * Changes to any other fields are ignored.
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return PaymentRequestUpdateResponse, or null if the request does not
	 *         exist
	 */
	public ApruveResponse<PaymentRequestUpdateResponse> update() {
		return ApruveClient.getInstance().put(PAYMENT_REQUESTS_PATH + this.id,
				this, PaymentRequestUpdateResponse.class);
	}

	/**
	 * Invokes the "finalize" action on the payment request. This allows you to
	 * escalate the request to the payer without creating a Payment first.
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return PaymentRequestUpdateResponse, or null if the request does not
	 *         exist
	 */
	public ApruveResponse<PaymentRequestUpdateResponse> finalizeRequest() {
		return ApruveClient.getInstance().post(getFinalizePath(this.id), "",
				PaymentRequestUpdateResponse.class);
	}

	@Override
	public String toString() {
		return toJson();
	}

	/**
	 * Returns the JSON string for a PaymentRequest.
	 * 
	 * Notably, this is suitable for merchants using the apruve.js JavaScript
	 * library. Use this to populate the value of apruve.paymentRequest.
	 * 
	 * @param paymentRequest
	 * @return
	 */
	public String toJson() {
		return JsonUtil.getInstance().toJson(this);
	}

	/**
	 * For use by merchants, and depends on proper initialization of
	 * ApruveClient. Returns the secure hash for a PaymentRequest, suitable for
	 * use with the property of apruve.js JavaScript library on a merchant
	 * checkout page. Use this to populate the value of apruve.secureHash.
	 * 
	 * @return
	 */
	public String toSecureHash() {
		String apiKey = ApruveClient.getInstance().getApiKey();
		String shaInput = apiKey + toValueString();

		return ShaUtil.getDigest(shaInput);
	}

	protected String toValueString() {
		StringBuilder buf = new StringBuilder();

		buf.append(StringUtils.defaultString(merchantId));
		buf.append(StringUtils.defaultString(merchantOrderId));
		if (amountCents != null)
			buf.append(amountCents);
		if (currency != null)
			buf.append(StringUtils.defaultString(currency.getCurrencyCode()));
		if (taxCents != null)
			buf.append(taxCents);
		if (shippingCents != null)
			buf.append(shippingCents);

		for (LineItem line : lineItems) {
			buf.append(line.toValueString());
		}
		return buf.toString();
	}

	public void addLineItem(LineItem item) {
		this.lineItems.add(item);
	}
	
	//////////////////////
	// Getters and Setters
	//////////////////////
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the merchantId
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * @param merchantId
	 *            the merchantId to set
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * @return the status
	 */
	public PaymentRequestStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	protected void setStatus(PaymentRequestStatus status) {
		this.status = status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = PaymentRequestStatus.valueOf(status.toUpperCase());
	}

	/**
	 * @return the merchantOrderId
	 */
	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	/**
	 * @param merchantOrderId
	 *            the merchantOrderId to set
	 */
	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
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
	 * @param amountCents
	 *            the amountCents to set
	 */
	public void setAmountCents(String amountCents) {
		this.amountCents = hasText(amountCents) ? new Integer(amountCents)
				: null;
	}

	/**
	 * @return the taxCents
	 */
	public Integer getTaxCents() {
		return taxCents;
	}

	/**
	 * @param taxCents
	 *            the taxCents to set
	 */
	public void setTaxCents(Integer taxCents) {
		this.taxCents = taxCents;
	}

	/**
	 * @param taxCents
	 *            the taxCents to set
	 */
	public void setTaxCents(String taxCents) {
		this.taxCents = hasText(taxCents) ? new Integer(taxCents) : null;
	}

	/**
	 * @return the shippingCents
	 */
	public Integer getShippingCents() {
		return shippingCents;
	}

	/**
	 * @param shippingCents
	 *            the shippingCents to set
	 */
	public void setShippingCents(Integer shippingCents) {
		this.shippingCents = shippingCents;
	}

	/**
	 * @param shippingCents
	 */
	public void setShippingCents(String shippingCents) {
		this.shippingCents = hasText(shippingCents) ? new Integer(shippingCents)
				: null;
	}

	/**
	 * @return the currency
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = hasText(currency) ? Currency.getInstance(currency)
				: null;
	}

	/**
	 * @return the lineItems
	 */
	public List<LineItem> getLineItems() {
		return lineItems;
	}

	/**
	 * @param lineItems
	 *            the lineItems to set
	 */
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	/**
	 * @return the apiUrl
	 */
	public URL getApiUrl() {
		return apiUrl;
	}

	/**
	 * @param apiUrl
	 *            the apiUrl to set
	 */
	protected void setApiUrl(URL apiUrl) {
		this.apiUrl = apiUrl;
	}

	/**
	 * @param apiUrl
	 *            the apiUrl to set
	 * @throws MalformedURLException
	 */
	protected void setApiUrl(String apiUrl) throws MalformedURLException {
		this.apiUrl = hasText(apiUrl) ? new URL(apiUrl) : null;
	}

	/**
	 * @return the viewUrl
	 */
	public URL getViewUrl() {
		return viewUrl;
	}

	/**
	 * @param viewUrl
	 *            the viewUrl to set
	 */
	protected void setViewUrl(URL viewUrl) {
		this.viewUrl = viewUrl;
	}

	/**
	 * @param viewUrl
	 *            the viewUrl to set
	 * @throws MalformedURLException
	 */
	protected void setViewUrl(String viewUrl) throws MalformedURLException {
		this.viewUrl = hasText(viewUrl) ? new URL(viewUrl) : null;
	}

	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	protected void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	protected void setCreatedAt(String createdAt) {
		this.createdAt = Utilities.parseTimestamp(createdAt);
	}

	/**
	 * @return the updatedAt
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	protected void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @param updatedAt
	 */
	protected void setUpdatedAt(String updatedAt) {
		this.updatedAt = Utilities.parseTimestamp(updatedAt);
	}
	
	public String getUsername() {
		return username;
	}

	public static String getPaymentRequestsPath() {
		return PAYMENT_REQUESTS_PATH;
	}

	public static String getFinalizePath(String paymentRequestId) {
		return FINALIZE_PATH.replace("%reqId", paymentRequestId);
	}
}
