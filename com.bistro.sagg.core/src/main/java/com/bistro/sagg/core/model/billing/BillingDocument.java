package com.bistro.sagg.core.model.billing;

import java.util.ArrayList;
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

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.suppliers.Supplier;

@Entity
@Table(name = "BILLING_DOCUMENTS")
public class BillingDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOCUMENT_TYPE_ID")
	private DocumentType document;
	@Column(name = "DOCUMENT_NUMBER")
	private String documentNumber;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SUPPLIER_ID")
	private Supplier supplier;
	@OneToMany(mappedBy = "billingDocument", cascade = CascadeType.PERSIST)
	private List<BillingItem> items = new ArrayList<BillingItem>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRANCHISE_BRANCH_ID")
	private FranchiseBranch branch;

	public BillingDocument() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DocumentType getDocument() {
		return document;
	}

	public void setDocument(DocumentType document) {
		this.document = document;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<BillingItem> getItems() {
		return items;
	}

	public void setItems(List<BillingItem> items) {
		this.items = items;
	}

	public FranchiseBranch getBranch() {
		return branch;
	}

	public void setBranch(FranchiseBranch branch) {
		this.branch = branch;
	}
	
	public void addItem(BillingItem item) {
		items.add(item);
		item.setBillingDocument(this);
	}
	
	public void addItems(List<BillingItem> items) {
		for (BillingItem item : items) {
			addItem(item);
		}
	}

}
