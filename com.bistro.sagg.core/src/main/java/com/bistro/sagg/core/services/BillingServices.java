package com.bistro.sagg.core.services;

import java.util.List;

import com.bistro.sagg.core.model.order.PurchaseOrder;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.billing.BillingDocument;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.billing.PurchaseBillingDocument;
import com.bistro.sagg.core.model.order.billing.SaleBillingDocument;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;

public interface BillingServices extends ISaggService {

	List<DocumentType> getBillingDocumentTypes();

	List<BillingDocument> getBillingDocuments();

	SaleBillingDocument createBillingDocument(SaleOrder order, DocumentType documentType, String documentNumber,
			PaymentMethod paymentMethod);

	PurchaseBillingDocument createBillingDocument(PurchaseOrder order, DocumentType documentType, String documentNumber,
			PaymentMethod paymentMethod);

//	BillingDocument createPurchaseBillingDocument(DocumentType documentType, String documentNumber, PaymentMethod paymentMethod,
//			List<PurchaseBillingItem> items);

//	BillingDocument createSaleBillingDocument(DocumentType documentType, String documentNumber, PaymentMethod paymentMethod,
//			List<SaleBillingItem> items);
	
	List<PaymentMethod> getPaymentMethods();
	
}
