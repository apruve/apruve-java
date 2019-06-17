package com.apruve.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.xml.bind.annotation.XmlElement;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

public class Shipment {
	static final String SHIPMENTS_PATH = Invoice.INVOICE_PATH + "/shipments/";
	static final String SHIPMENT_PATH = SHIPMENTS_PATH + "%shipmentId";
	
	private String id;
	@XmlElement(name="invoice_id")
	private String invoiceId; 
	@XmlElement(name="amount_cents")
	private Integer amountCents; 
	@XmlElement(name="tax_cents")
	private Integer taxCents; 
	@XmlElement(name="shipping_cents")
	private Integer shippingCents; 
	private String currency; 
	private String shipper; 
	@XmlElement(name="tracking_number")
	private String trackingNumber;
	@XmlElement(name="shipped_at")
	private Date shippedAt; 
	@XmlElement(name="delivered_at")
	private Date deliveredAt; 
	@XmlElement(name="merchant_notes")
    private String merchantNotes; 
    private String status; 
	@XmlElement(name="merchant_shipment_id")
    private String merchantShipmentId; 
	@XmlElement(name="shipment_items")
    private List<ShipmentItem> shipmentItems;
	
    protected Shipment() {
    	this.shipmentItems = new ArrayList<ShipmentItem>();
    }
    
    public Shipment(String invoiceId) {
    	this();
    	this.invoiceId = invoiceId;
    }

    public static String getShipmentPath(String invoiceId, String shipmentId) {
    	return SHIPMENT_PATH.replace("%invoiceId", invoiceId).replace("%shipmentId", shipmentId);
    }
    
    public static String getShipmentsPath(String invoiceId) {
    	return SHIPMENTS_PATH.replace("%invoiceId", invoiceId);
    }
    
	public static ApruveResponse<List<Shipment>> getForInvoice(ApruveClient client, String invoiceId) {
		return client.index(getShipmentsPath(invoiceId), new GenericType<List<Shipment>>() {});
	}

	public static ApruveResponse<Shipment> get(ApruveClient client, String invoiceId, String shipmentId) {
		return client.get(getShipmentPath(invoiceId, shipmentId), Shipment.class);
	}
	
	public ApruveResponse<Shipment> create(ApruveClient client) {
		return client.post(getShipmentsPath(this.invoiceId), this, Shipment.class);
	}
	
	public ApruveResponse<Shipment> update(ApruveClient client) {
		return client.put(getShipmentPath(this.invoiceId, this.id), this, Shipment.class);		
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

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
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

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Date getShippedAt() {
		return shippedAt;
	}

	public void setShippedAt(Date shippedAt) {
		this.shippedAt = shippedAt;
	}

	public Date getDeliveredAt() {
		return deliveredAt;
	}

	public void setDeliveredAt(Date deliveredAt) {
		this.deliveredAt = deliveredAt;
	}

	public String getMerchantNotes() {
		return merchantNotes;
	}

	public void setMerchantNotes(String merchantNotes) {
		this.merchantNotes = merchantNotes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMerchantShipmentId() {
		return merchantShipmentId;
	}

	public void setMerchantShipmentId(String merchantShipmentId) {
		this.merchantShipmentId = merchantShipmentId;
	}

	public List<ShipmentItem> getShipmentItems() {
		return shipmentItems;
	}

	public void setShipmentItems(List<ShipmentItem> shipmentItems) {
		this.shipmentItems = shipmentItems;
	}
}
