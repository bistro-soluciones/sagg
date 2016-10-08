package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.order.billing.BillingDocument;
import com.bistro.sagg.core.model.order.billing.BillingItem;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;

public interface BillingServices extends ISaggService {

	List<DocumentType> getBillingDocumentTypes();

	List<BillingDocument> getBillingDocuments();

	void createBillingDocument(DocumentType documentType, String documentNumber, PaymentMethod paymentMethod,
			List<BillingItem> items);

}
