package com.apruve.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.apruve.ApruveClient;
import com.apruve.ApruveResponse;
import com.apruve.JsonUtil;

@XmlRootElement(name = "invoice")
public class Invoice {
	static final String INVOICE_PATH = "/invoices/";
	static final String INVOICES_PATH = Order.ORDER_PATH + INVOICE_PATH;

	private String id;
	@XmlElement(name = "order_id")
	private String orderId;
	@XmlElement(name = "amount_cents")
	private Integer amountCents;
	private String currency;
	private String status;
	@XmlElement(name = "issue_on_create")
	private Boolean issueOnCreate;
	@XmlElement(name = "created_at")
	private Date createdAt;
	@XmlElement(name = "opened_at")
	private Date openedAt;
	@XmlElement(name = "issued_at")
	private Date issuedAt;
	@XmlElement(name = "due_at")
	private Date dueAt;
	@XmlElement(name = "final_state_at")
	private Date finalStateAt;
	@XmlElement(name = "merchant_notes")
	private String merchantNotes;
	@XmlElement(name = "merchant_invoice_id")
	private String merchantInvoiceId;
	@XmlElement(name = "tax_cents")
	private Integer taxCents;
	@XmlElement(name = "shipping_cents")
	private Integer shippingCents;
	@XmlElement(name = "amount_due")
	private Integer amountDue;
	private OrderChildLinks links;
	@XmlElement(name = "company_code")
	private String companyCode;
	@XmlElement(name = "gst_exchange_percentage")
	private BigDecimal gstExchangePercentage;
	@XmlElement(name = "gst_exchange_rate_usd")
	private BigDecimal gstExchangeRcateUsd;
	@XmlElement(name = "terms_of_delivery")
	private String termsOfDelivery;
	private String footer;
	private List<Payment> payments;
	@XmlElement(name = "invoice_items")
	private List<InvoiceItem> invoiceItems;
	@XmlElement(name = "funding_details")
	private List<FundingDetail> fundingDetails;
	@XmlElement(name = "ship_to_address")
	private Address shipToAddress;
	@XmlElement(name = "sold_to_address")
	private Address soldToAddress;
	@XmlElement(name = "remittance_address")
	private Address remittanceAddress;
	@XmlElement(name = "fiscal_representative")
	private Address fiscalRepresentative;

	protected Invoice() {
		this.payments = new ArrayList<Payment>();
		this.invoiceItems = new ArrayList<InvoiceItem>();
		this.fundingDetails = new ArrayList<FundingDetail>();
	}

	public Invoice(String orderId) {
		this();
		this.orderId = orderId;
	}

	public static String getInvoicesPath(String orderId) {
		return INVOICES_PATH.replace("%orderId", orderId);
	}

	public static String getInvoicePath(String invoiceId) {
		return INVOICE_PATH + invoiceId;
	}

	public static ApruveResponse<List<Invoice>> getAllForOrder(ApruveClient client, String orderId) {
		return client.index(getInvoicesPath(orderId), new GenericType<List<Invoice>>() {
		});
	}

	public static ApruveResponse<Invoice> get(ApruveClient client, String invoiceId) {
		return client.get(getInvoicePath(invoiceId), Invoice.class);
	}

	public static ApruveResponse<Invoice> close(ApruveClient client, String invoiceId) {
		return client.post(getInvoicePath(invoiceId) + "/close", "", Invoice.class);
	}

	public static ApruveResponse<Invoice> cancel(ApruveClient client, String invoiceId) {
		return client.post(getInvoicePath(invoiceId) + "/cancel", "", Invoice.class);
	}

	public static ApruveResponse<Invoice> issue(ApruveClient client, String invoiceId) {
		return client.post(getInvoicePath(invoiceId) + "/issue", "", Invoice.class);
	}

	public ApruveResponse<Invoice> create(ApruveClient client) {
		return client.post(Invoice.getInvoicesPath(this.orderId), this, Invoice.class);
	}

	public ApruveResponse<Invoice> update(ApruveClient client) {
		return client.put(Invoice.getInvoicePath(this.id), this, Invoice.class);
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

	public void addInvoiceItem(InvoiceItem item) {
		this.invoiceItems.add(item);
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIssueOnCreate() {
		return issueOnCreate;
	}

	public void setIssueOnCreate(Boolean issueOnCreate) {
		this.issueOnCreate = issueOnCreate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getOpenedAt() {
		return openedAt;
	}

	public void setOpenedAt(Date openedAt) {
		this.openedAt = openedAt;
	}

	public Date getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Date getDueAt() {
		return dueAt;
	}

	public void setDueAt(Date dueAt) {
		this.dueAt = dueAt;
	}

	public Date getFinalStateAt() {
		return finalStateAt;
	}

	public void setFinalStateAt(Date finalStateAt) {
		this.finalStateAt = finalStateAt;
	}

	public String getMerchantNotes() {
		return merchantNotes;
	}

	public void setMerchantNotes(String merchantNotes) {
		this.merchantNotes = merchantNotes;
	}

	public String getMerchantInvoiceId() {
		return merchantInvoiceId;
	}

	public void setMerchantInvoiceId(String merchantInvoiceId) {
		this.merchantInvoiceId = merchantInvoiceId;
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

	public Integer getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(Integer amountDue) {
		this.amountDue = amountDue;
	}

	public OrderChildLinks getLinks() {
		return links;
	}

	public void setLinks(OrderChildLinks links) {
		this.links = links;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public BigDecimal getGstExchangePercentage() {
		return gstExchangePercentage;
	}

	public void setGstExchangePercentage(BigDecimal gstExchangePercentage) {
		this.gstExchangePercentage = gstExchangePercentage;
	}

	public BigDecimal getGstExchangeRcateUsd() {
		return gstExchangeRcateUsd;
	}

	public void setGstExchangeRcateUsd(BigDecimal gstExchangeRcateUsd) {
		this.gstExchangeRcateUsd = gstExchangeRcateUsd;
	}

	public String getTermsOfDelivery() {
		return termsOfDelivery;
	}

	public void setTermsOfDelivery(String termsOfDelivery) {
		this.termsOfDelivery = termsOfDelivery;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public List<FundingDetail> getFundingDetails() {
		return fundingDetails;
	}

	public void setFundingDetails(List<FundingDetail> fundingDetails) {
		this.fundingDetails = fundingDetails;
	}

	public Address getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(Address shipToAddress) {
		this.shipToAddress = shipToAddress;
	}

	public Address getSoldToAddress() {
		return soldToAddress;
	}

	public void setSoldToAddress(Address soldToAddress) {
		this.soldToAddress = soldToAddress;
	}

	public Address getRemittanceAddress() {
		return remittanceAddress;
	}

	public void setRemittanceAddress(Address remittanceAddress) {
		this.remittanceAddress = remittanceAddress;
	}

	public Address getFiscalRepresentative() {
		return fiscalRepresentative;
	}

	public void setFiscalRepresentative(Address fiscalRepresentative) {
		this.fiscalRepresentative = fiscalRepresentative;
	}
}
