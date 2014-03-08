package com.apruve;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.PropertyNameProcessor;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;

/**
 * A request to a payer for payment on behalf of a shopper. In order to provide
 * the prospective payer with as much information as possible, and enhance the
 * likelihood for approval, as much detail should be provided as possible.
 * 
 * @author neal
 * 
 */
public class PaymentRequest implements Serializable {
	private static final long serialVersionUID = -4892365561562700036L;
	public static final PropertyNameProcessor JSON_PROP_NAME_PROCESSOR;

	private String merchantId;
	private Integer amountCents = null;
	private String currency = null;
	private Integer taxCents = null;
	private Integer shippingCents = null;
	private Boolean recurring = null;
	private List<LineItem> lineItems = new ArrayList<LineItem>();

	static {
		JSON_PROP_NAME_PROCESSOR = new PropertyNameProcessor() {
			@SuppressWarnings("rawtypes")
			@Override
			public String processPropertyName(Class parentClass,
					String propertyName) {
				String jsonName = propertyName;
				switch (propertyName) {
				case "merchantId":
					jsonName = "merchant_id";
					break;
				case "amountCents":
					jsonName = "amount_cents";
					break;
				case "lineItems":
					jsonName = "line_items";
					break;
				case "shippingCents":
					jsonName = "shipping_cents";
					break;
				case "taxCents":
					jsonName = "tax_cents";
				}
				return jsonName;
			}
		};
	}

	public PaymentRequest() {
		this.merchantId = ApruveMerchant.getInstance().getMerchantId();
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
		return toJsonObject().toString();
	}

	/**
	 * For use by merchants, and depends on proper initialization of
	 * ApruveMerchant. Returns the secure hash for a PaymentRequest, suitable
	 * for use with the property of apruve.js JavaScript library on a merchant
	 * checkout page. Use this to populate the value of apruve.secureHash.
	 * 
	 * @return
	 */
	public String toSecureHash() {
		String apiKey = ApruveMerchant.getInstance().getApiKey();
		String shaInput = apiKey + toValueString();

		return ShaUtil.getDigest(shaInput);
	}

	public String getMerchantId() {
		return merchantId;
	}

	public Integer getAmountCents() {
		return amountCents;
	}

	public void setAmountCents(Integer amountCents) {
		this.amountCents = amountCents;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Boolean getRecurring() {
		return recurring;
	}

	public void setRecurring(Boolean isRecurring) {
		this.recurring = isRecurring;
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

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	private JSONObject toJsonObject() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(PaymentRequest.class);
		jsonConfig.registerJsonPropertyNameProcessor(PaymentRequest.class,
				PaymentRequest.JSON_PROP_NAME_PROCESSOR);
		jsonConfig.registerJsonPropertyNameProcessor(LineItem.class,
				LineItem.JSON_PROP_NAME_PROCESSOR);
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (value == null) {
					return true;
				}
				return false;
			}
		});
		return JSONObject.fromObject(this, jsonConfig);
	}

	protected String toValueString() {
		StringBuffer buf = new StringBuffer();

		buf.append(StringUtils.defaultString(merchantId));
		if (amountCents != null)
			buf.append(amountCents);
		buf.append(StringUtils.defaultString(currency));
		if (taxCents != null)
			buf.append(taxCents);
		if (shippingCents != null)
			buf.append(shippingCents);
		if (recurring != null)
			buf.append(recurring);

		for (LineItem line : lineItems) {
			buf.append(line.getTitle());
			buf.append(line.getAmountCents());
			if (line.getPriceEachCents() != null)
				buf.append(line.getPriceEachCents());
			if (line.getQuantity() != null)
				buf.append(line.getQuantity());
			buf.append(StringUtils.defaultString(line.getDescription()));
			buf.append(StringUtils.defaultString(line.getVariantInfo()));
			buf.append(StringUtils.defaultString(line.getSku()));
			buf.append(StringUtils.defaultString(line.getVendor()));
			buf.append(StringUtils.defaultString(line.getViewProductUrl()));
		}
		return buf.toString();
	}
}
