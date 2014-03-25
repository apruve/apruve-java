package com.apruve.models;

import java.net.URL;
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

@XmlRootElement
public class SubscriptionAdjustment {
	protected static final String SUBSCRIPTION_ADJUSTMENTS_PATH = Subscription.SUBSCRIPTIONS_PATH
			+ "%subId/adjustments/";

	@XmlJavaTypeAdapter(value = SubscriptionAdjustmentStatusAdapter.class)
	public static enum SubscriptionAdjustmentStatus {
		CREATED, APPLIED;

		public String getDisplayString() {
			return StringUtils.capitalize(this.toString());
		}
	}

	private static class SubscriptionAdjustmentStatusAdapter extends
			XmlAdapter<String, SubscriptionAdjustmentStatus> {

		@Override
		public String marshal(SubscriptionAdjustmentStatus v) throws Exception {
			return v == null ? null : v.toString().toLowerCase();
		}

		@Override
		public SubscriptionAdjustmentStatus unmarshal(String v)
				throws Exception {
			return v == null ? null : SubscriptionAdjustmentStatus.valueOf(v
					.toUpperCase());
		}
	}

	private String id;
	private SubscriptionAdjustmentStatus status;
	private String title;
	@XmlElement(name = "amount_cents")
	private Integer amountCents;
	@XmlElement(name = "price_ea_cents")
	private Integer priceEachCents;
	private Integer quantity;
	private String description;
	@XmlElement(name = "variant_info")
	private String variantInfo;
	private String sku;
	private String vendor;
	@XmlElement(name = "view_product_url")
	private URL viewProductUrl;

	@Override
	public String toString() {
		return toJson();
	}

	public String toJson() {
		return JsonUtil.getInstance().toJson(this);
	}

	/**
	 * Get the SubscriptionAdjustment with the given ID
	 * 
	 * @param subscriptionId
	 *            The ID of the {@link Subscription} that owns the
	 *            SubscriptionAdjustment
	 * @param adjustmentId
	 *            The ID of the SubscriptionAdjustment
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return Subscription, or null if not found
	 */
	public static ApruveResponse<SubscriptionAdjustment> get(
			String subscriptionId, String adjustmentId) {
		return ApruveClient.getInstance().get(
				getSubscriptionAdjustmentsPath(subscriptionId) + adjustmentId,
				SubscriptionAdjustment.class);
	}

	/**
	 * Get all SubscriptionAdjustments for the specified Subscription.
	 * 
	 * @param subscriptionId
	 *            The ID of the {@link Subscription} that owns the
	 *            SubscriptionAdjustment
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return List of SubscriptionAdjustment
	 */
	public static ApruveResponse<List<SubscriptionAdjustment>> getAllAdjustments(
			String subscriptionId) {
		return ApruveClient.getInstance().index(
				getSubscriptionAdjustmentsPath(subscriptionId),
				new GenericType<List<SubscriptionAdjustment>>() {
				});
	}

	/**
	 * Create this SubscriptionAdjustment on the Apruve servers.
	 * 
	 * @param subscriptionId
	 *            The ID of the {@link Subscription} that will own the
	 *            SubscriptionAdjustment
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return {@link SubscriptionAdjustmentCreateResponse}
	 */
	public ApruveResponse<SubscriptionAdjustmentCreateResponse> create(
			String subscriptionId) {
		return ApruveClient.getInstance().post(
				getSubscriptionAdjustmentsPath(subscriptionId), this,
				SubscriptionAdjustmentCreateResponse.class);
	}

	/**
	 * Delete the specified SubscriptionAdjustment from the Apruve servers. This
	 * is only allowed for SubscriptionAdjustments that are not in APPLIED
	 * status.
	 * 
	 * @param subscriptionId
	 *            The ID of the {@link Subscription} that will own the
	 *            SubscriptionAdjustment
	 * @param adjustmentId
	 *            The ID of the SubscriptionAdjustment to delete
	 * 
	 * @return {@link SubscriptionAdjustmentDeleteResponse}
	 */
	public static ApruveResponse<SubscriptionAdjustmentDeleteResponse> delete(
			String subscriptionId, String adjustmentId) {
		return ApruveClient.getInstance().delete(
				getSubscriptionAdjustmentsPath(subscriptionId) + adjustmentId,
				SubscriptionAdjustmentDeleteResponse.class);
	}

	// ////////////////////
	// Getters and Setters
	// ////////////////////

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getAmountCents() {
		return amountCents;
	}

	public void setAmountCents(Integer amountCents) {
		this.amountCents = amountCents;
	}

	public Integer getPriceEachCents() {
		return priceEachCents;
	}

	public void setPriceEachCents(Integer priceEachCents) {
		this.priceEachCents = priceEachCents;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariantInfo() {
		return variantInfo;
	}

	public void setVariantInfo(String variantInfo) {
		this.variantInfo = variantInfo;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public URL getViewProductUrl() {
		return viewProductUrl;
	}

	public void setViewProductUrl(URL viewProductUrl) {
		this.viewProductUrl = viewProductUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SubscriptionAdjustmentStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionAdjustmentStatus status) {
		this.status = status;
	}

	public static String getSubscriptionAdjustmentsPath(String subscriptionId) {
		return SUBSCRIPTION_ADJUSTMENTS_PATH.replace("%subId", subscriptionId);
	}
}
