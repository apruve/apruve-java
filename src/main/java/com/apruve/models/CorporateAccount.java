package com.apruve.models;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.xml.bind.annotation.XmlElement;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

public class CorporateAccount {
	static final String CORPORATE_ACCOUNTS_PATH = Merchant.MERCHANT_PATH + "/corporate_accounts/";
	static final String CORPORATE_ACCOUNT_PATH = CORPORATE_ACCOUNTS_PATH + "%accountId";

	private String id;
	@XmlElement(name="customer_uuid")
	private String customerUuid;
	@XmlElement(name="merchant_uuid")
	private String merchantUuid;
	private String type;
	@XmlElement(name="payment_term_strategy_name")
	private String paymentTermStrategyName;
	private String name;
	@XmlElement(name="merchant_account_id")
	private String merchantAccountId;
	@XmlElement(name="credit_available_cents")
	private Integer creditAvailableCents;
	@XmlElement(name="credit_balance_cents")
	private Integer creditBalanceCents;
	@XmlElement(name="credit_amount_cents")
	private Integer creditAmountCents;
	@XmlElement(name="authorized_buyers")
	private List<User> authorizedBuyers;

	public static String getCorporateAccountsPath(String merchantId) {
		return CORPORATE_ACCOUNTS_PATH.replace("%merchantId", merchantId);
	}

	public static String getCorporateAccountPath(String merchantId, String accountId) {
		return CORPORATE_ACCOUNT_PATH.replace("%merchantId", merchantId).replace("%accountId", accountId);
	}

	public static ApruveResponse<CorporateAccount> get(ApruveClient client, String merchantId, String accountId) {
		return client.get(getCorporateAccountPath(merchantId, accountId), CorporateAccount.class);
	}

	public static ApruveResponse<List<CorporateAccount>> getAll(ApruveClient client, String merchantId) {
		return client.index(getCorporateAccountsPath(merchantId), new GenericType<List<CorporateAccount>>() {});
	}

	public static ApruveResponse<List<CorporateAccount>> findByEmail(ApruveClient client, String merchantId, String email) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		if (email != null) params.put("email", email);
		return client.index(getCorporateAccountsPath(merchantId), new GenericType<List<CorporateAccount>>() {}, params);
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerUuid() {
		return customerUuid;
	}

	public void setCustomerUuid(String customerUuid) {
		this.customerUuid = customerUuid;
	}

	public String getMerchantUuid() {
		return merchantUuid;
	}

	public void setMerchantUuid(String merchantUuid) {
		this.merchantUuid = merchantUuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPaymentTermStrategyName() {
		return paymentTermStrategyName;
	}

	public void setPaymentTermStrategyName(String paymentTermStrategyName) {
		this.paymentTermStrategyName = paymentTermStrategyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerchantAccountId() {
		return merchantAccountId;
	}

	public void setMerchantAccountId(String merchantAccountId) {
		this.merchantAccountId = merchantAccountId;
	}

	public Integer getCreditAvailableCents() {
		return creditAvailableCents;
	}

	public void setCreditAvailableCents(Integer creditAvailableCents) {
		this.creditAvailableCents = creditAvailableCents;
	}

	public Integer getCreditBalanceCents() {
		return creditBalanceCents;
	}

	public void setCreditBalanceCents(Integer creditBalanceCents) {
		this.creditBalanceCents = creditBalanceCents;
	}

	public Integer getCreditAmountCents() {
		return creditAmountCents;
	}

	public void setCreditAmountCents(Integer creditAmountCents) {
		this.creditAmountCents = creditAmountCents;
	}

	public List<User> getAuthorizedBuyers() {
		return authorizedBuyers;
	}

	public void setAuthorizedBuyers(List<User> authorizedBuyers) {
		this.authorizedBuyers = authorizedBuyers;
	}
}