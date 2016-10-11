package com.bistro.sagg.core.model.order.billing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bistro.sagg.core.model.order.payment.PaymentMethod;

@Entity
@Table(name = "BILLING_DOCUMENTS")
public class BillingDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCUMENT_TYPE_ID")
	private DocumentType documentType;
	@Column(name = "DOCUMENT_NUMBER")
	private String documentNumber;
	@Column(name = "DOCUMENT_DATETIME")
	private Date date;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAYMENT_METHOD_ID")
	private PaymentMethod paymentMethod;
	@OneToMany(mappedBy = "billingDocument", cascade = CascadeType.PERSIST)
	private List<BillingItem> items = new ArrayList<BillingItem>();

	public BillingDocument() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public List<BillingItem> getItems() {
		return items;
	}

	public void setItems(List<BillingItem> items) {
		this.items = items;
	}
	
	public void addItem(BillingItem item) {
		item.setBillingDocument(this);
		this.items.add(item);
	}
	
	public void addItems(List<BillingItem> items) {
		for (BillingItem item : items) {
			item.setBillingDocument(this);
		}
		this.items.addAll(items);
	}

}
