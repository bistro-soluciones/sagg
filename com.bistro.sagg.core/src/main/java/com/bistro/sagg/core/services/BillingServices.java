package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.billing.BillingDocument;
import com.bistro.sagg.core.model.billing.BillingItem;
import com.bistro.sagg.core.model.billing.DocumentType;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.suppliers.Supplier;

public interface BillingServices extends ISaggService {

	List<DocumentType> getBillingDocumentTypes();

	List<BillingDocument> getBillingDocuments();

	void createBillingDocument(DocumentType documentType, String documentNumber, Supplier supplier,
			List<BillingItem> items, FranchiseBranch branch);
	
}
