package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.billing.BillingDocument;
import com.bistro.sagg.core.model.billing.DocumentType;

public interface BillingServices extends ISaggService {

	List<DocumentType> getBillingDocumentTypes();

	List<BillingDocument> getBillingDocuments();

}
