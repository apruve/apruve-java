package com.apruve;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.PropertyNameProcessor;
import net.sf.json.util.PropertyFilter;

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
	private String merchantId;
	private Integer amountCents = null;
	private String currency = null;
	private Boolean isRecurring = null;
	private List<LineItem> lineItems = new ArrayList<LineItem>();

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
		JSONObject json = toJsonObject();
		String shaInput = apiKey + toValueString(json);

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
		return isRecurring;
	}

	public void setRecurring(Boolean isRecurring) {
		this.isRecurring = isRecurring;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	private JSONObject toJsonObject() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(PaymentRequest.class);
		jsonConfig.registerJsonPropertyNameProcessor(PaymentRequest.class,
				new PropertyNameProcessor() {
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
						}
						return jsonName;
					}
				});
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

	private String toValueString(JSONObject jsonObject) {
		StringBuffer buf = new StringBuffer();

		for (Object value : jsonObject.values()) {
			if (value instanceof JSONArray) {
				JSONArray array = (JSONArray) value;
				buf.append(toValueString(array));
			} else {
				buf.append(value.toString());
			}
		}
		return buf.toString();
	}

	private String toValueString(JSONArray jsonArray) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < jsonArray.size(); i++) {
			Object item = jsonArray.get(i);
			if (item instanceof JSONObject) {
				buf.append(toValueString((JSONObject) item));
			} else if (item instanceof JSONArray) {
				buf.append(toValueString((JSONArray) item));
			}
		}
		return buf.toString();
	}

}
