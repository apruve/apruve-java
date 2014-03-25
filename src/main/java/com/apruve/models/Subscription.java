package com.apruve.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;
import com.apruve.Utilities;

@XmlRootElement
public class Subscription extends LineItem {
	protected static String SUBSCRIPTIONS_PATH = "/subscriptions/";
	private static String CANCEL_PATH = SUBSCRIPTIONS_PATH + "%subId/cancel";

	@XmlElement(name = "start_at")
	private Date startAt;
	@XmlElement(name = "next_charge_at")
	private Date nextChargeAt;
	@XmlElement(name = "last_charge_at")
	private Date lastChargeAt;
	@XmlElement(name = "end_at")
	private Date endAt;
	@XmlElement(name = "canceled_at")
	private Date canceledAt;

	@Override
	public String toString() {
		return toJson();
	}

	public String toJson() {
		return JsonUtil.getInstance().toJson(this);
	}

	@Override
	protected String subscriptionValues() {
		StringBuilder buf = new StringBuilder();
		buf.append(StringUtils.defaultString(this.getPlanCode()));
		if (startAt != null) {
			buf.append(Utilities.formatTimestamp(startAt));
		}
		return buf.toString();
	}

	/**
	 * Get the Subscription with the given ID
	 * 
	 * @param suscriptionId
	 *            The ID of the Subscription to get
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return Subscription, or null if not found
	 */
	public static ApruveResponse<Subscription> get(String suscriptionId) {
		return ApruveClient.getInstance().get(
				getSubscriptionsPath() + suscriptionId, Subscription.class);
	}

	/**
	 * Updates the Subscription state at Apruve to match the current object
	 * state.
	 * <p>
	 * Only the following fields are updated by this API call, and then only if
	 * the Subscription has not been canceled (i.e., canceledAt is null):
	 * <ul>
	 * <li>quantity</li>
	 * <li>amountCents</li>
	 * <li>priceEachCents</li>
	 * </ul>
	 * 
	 * Changes to any other fields are ignored.
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return SubscriptionUpdateResponse, or null if the Subscription does not
	 *         exist
	 */
	public ApruveResponse<SubscriptionUpdateResponse> update() {
		return ApruveClient.getInstance().put(getSubscriptionsPath(), this,
				SubscriptionUpdateResponse.class);
	}

	/**
	 * Cancels the Subscription with the given ID. This cannot be undone once
	 * completed, so use with care!
	 * 
	 * @see <a
	 *      href="https://www.apruve.com/doc/developers/rest-api/">https://www.apruve.com/doc/developers/rest-api/</a>
	 * @return SubscriptionCancelResponse
	 */
	public static ApruveResponse<SubscriptionCancelResponse> cancel(
			String subscriptionId) {
		return ApruveClient.getInstance().post(getCancelPath(subscriptionId),
				"", SubscriptionCancelResponse.class);
	}

	// ////////////////////
	// Getters and Setters
	// ////////////////////

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getNextChargeAt() {
		return nextChargeAt;
	}

	public void setNextChargeAt(Date nextChargeAt) {
		this.nextChargeAt = nextChargeAt;
	}

	public Date getLastChargeAt() {
		return lastChargeAt;
	}

	public void setLastChargeAt(Date lastChargeAt) {
		this.lastChargeAt = lastChargeAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public Date getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(Date canceledAt) {
		this.canceledAt = canceledAt;
	}

	public static String getSubscriptionsPath() {
		return SUBSCRIPTIONS_PATH;
	}

	public static String getCancelPath(String subscriptionId) {
		return CANCEL_PATH.replace("%subId", subscriptionId);
	}
}
