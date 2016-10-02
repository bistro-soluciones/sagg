package com.bistro.sagg.core.builders;

import java.util.List;

import com.bistro.sagg.core.model.billing.BillingDocument;
import com.bistro.sagg.core.model.billing.BillingItem;
import com.bistro.sagg.core.model.billing.DocumentType;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.suppliers.Supplier;

public class BillingDocumentBuilder {

	private BillingDocument document;

	public BillingDocumentBuilder() {
		super();
		this.document = new BillingDocument();
	}

	public BillingDocumentBuilder(BillingDocument document) {
		super();
		this.document = document;
	}

	public void build(DocumentType documentType, String documentNumber, Supplier supplier, List<BillingItem> items, FranchiseBranch branch) {
		document.setDocument(documentType);
		document.setDocumentNumber(documentNumber);
		document.setSupplier(supplier);
		document.addItems(items);
		document.setBranch(branch);
	}

	public BillingDocument getDocument() {
		return document;
	}

}
