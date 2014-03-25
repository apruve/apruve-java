package com.apruve.models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.StringUtils;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

/**
 * A Payment represents a request to collect funds in accordance with a
 * PaymentRequest.
 * 
 * @author Todd Cochran
 * 
 */
@XmlRootElement
public class Payment {
	private static final String PAYMENTS_PATH = PaymentRequest
			.getPaymentRequestsPath() + "%s/payments/";

	@XmlJavaTypeAdapter(value = PaymentStatusAdapter.class)
	public static enum PaymentStatus {
		PENDING, AUTHORIZED, CAPTURED, REJECTED, FAILED;

		public String getDisplayString() {
			return StringUtils.capitalize(this.toString());
		}
	}

	private static class PaymentStatusAdapter extends
			XmlAdapter<String, PaymentStatus> {
		@Override
		public String marshal(PaymentStatus v) throws Exception {
			return v == null ? null : v.toString().toLowerCase();
		}

		@Override
		public PaymentStatus unmarshal(String v) throws Exception {
			return v == null ? null : PaymentStatus.valueOf(v.toUpperCase());
		}
	}

	private String id;
	@XmlElement(name = "payment_request_id")
	private String paymentRequestId;
	private PaymentStatus status;
	@XmlElement(name = "amount_cents")
	private Integer amountCents;
	private Currency currency;
	@XmlElement(name = "merchant_notes")
	private String merchantNotes;
	@XmlElement(name = "payment_items")
	private List<PaymentItem> paymentItems;
	@XmlElement(name = "api_url")
	private URL apiUrl;
	@XmlElement(name = "view_url")
	private URL viewUrl;
	@XmlElement(name = "created_at")
	private Date createdAt;
	@XmlElement(name = "updated_at")
	private Date updatedAt;

	protected Payment() {
		// Default constructor for marshalling/unmarshalling
	}

	/**
	 * @param paymentRequestId
	 * @param amountCents
	 */
	public Payment(String paymentRequestId, Integer amountCents) {
		this.paymentRequestId = paymentRequestId;
		this.amountCents = amountCents;
	}

	/**
	 * Fetches the Payment with the given ID from Apruve.
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @param paymentRequestId
	 *            The ID of the PaymentRequest that owns the Payment
	 * @param paymentId
	 *            The ID of the Payment
	 * @return Payment, or null if not found
	 */
	public static ApruveResponse<Payment> get(String paymentRequestId, String paymentId) {
		return ApruveClient.getInstance().get(
				getPaymentsPath(paymentRequestId) + paymentId, Payment.class);
	}

	/**
	 * Fetches all Payments belonging to the PaymentRequest with the specified
	 * ID.
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @param paymentRequestId
	 *            The ID of the PaymentRequest that owns the Payment
	 * @return List of Payments, or null if the PaymentRequest is not found
	 */
	public static ApruveResponse<List<Payment>> getAll(String paymentRequestId) {
		return ApruveClient.getInstance().index(
				getPaymentsPath(paymentRequestId),
				new GenericType<List<Payment>>() {
				});
	}

	/**
	 * Create a new Payment on the Apruve servers.
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return PaymentCreateResponse
	 */
	public ApruveResponse<PaymentCreateResponse> create() {
		return ApruveClient.getInstance().post(
				getPaymentsPath(this.paymentRequestId), this,
				PaymentCreateResponse.class);
	}

	public String toJson() {
		return JsonUtil.getInstance().toJson(this);
	}

	public String getUrl(ApruveClient client) {
		String url = PAYMENTS_PATH.replaceAll("%s", paymentRequestId);
		return client.getEnvironment().getBaseUrl() + url;
	}

	// ////////////////////
	// Getters and Setters
	// ////////////////////

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
	 * @return the paymentRequestId
	 */
	public String getPaymentRequestId() {
		return paymentRequestId;
	}

	/**
	 * @param paymentRequestId
	 *            the paymentRequestId to set
	 */
	public void setPaymentRequestId(String paymentRequestId) {
		this.paymentRequestId = paymentRequestId;
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
	 * @param currencyCode
	 */
	public void setCurrency(String currencyCode) {
		this.currency = Currency.getInstance(currencyCode);
	}

	/**
	 * @return the merchantNotes
	 */
	public String getMerchantNotes() {
		return merchantNotes;
	}

	/**
	 * @param merchantNotes
	 *            the merchantNotes to set
	 */
	public void setMerchantNotes(String merchantNotes) {
		this.merchantNotes = merchantNotes;
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
	public void setApiUrl(URL apiUrl) {
		this.apiUrl = apiUrl;
	}

	/**
	 * @param apiUrl
	 * @throws MalformedURLException
	 */
	public void setApiUrl(String apiUrl) throws MalformedURLException {
		this.apiUrl = new URL(apiUrl);
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
	public void setViewUrl(URL viewUrl) {
		this.viewUrl = viewUrl;
	}

	/**
	 * @param viewUrl
	 * @throws MalformedURLException
	 */
	public void setViewUrl(String viewUrl) throws MalformedURLException {
		this.viewUrl = new URL(viewUrl);
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
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public List<PaymentItem> getPaymentItems() {
		return paymentItems;
	}

	public void setPaymentItems(List<PaymentItem> paymentItems) {
		this.paymentItems = paymentItems;
	}

	public static String getPaymentsPath(String paymentRequestId) {
		return PAYMENTS_PATH.replace("%s", paymentRequestId);
	}
}
