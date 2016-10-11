package com.bistro.sagg.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.builders.BillingDocumentBuilder;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.billing.BillingDocument;
import com.bistro.sagg.core.model.order.billing.BillingItem;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.repository.BillingDocumentRepository;
import com.bistro.sagg.core.repository.DocumentTypeRepository;
import com.bistro.sagg.core.repository.PaymentMethodRepository;
import com.bistro.sagg.core.transformer.SaleOrderToBillindDocumentTransformer;

public class BillingServicesImpl implements BillingServices {

	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	@Autowired
	private BillingDocumentRepository billilngDocumentRepository;
	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	public List<DocumentType> getBillingDocumentTypes() {
		return (List<DocumentType>) documentTypeRepository.findAll();
	}

	public List<BillingDocument> getBillingDocuments() {
		return (List<BillingDocument>) billilngDocumentRepository.findAll();
	}
	
	public BillingDocument createBillingDocument(SaleOrder order, DocumentType documentType, PaymentMethod paymentMethod) {
		BillingDocument document = new SaleOrderToBillindDocumentTransformer().transform(order);
		document.setPaymentMethod(paymentMethod);
		document.setDocumentType(documentType);
		billilngDocumentRepository.save(document);
		return document;
	}
	
	public BillingDocument createBillingDocument(DocumentType documentType, String documentNumber, PaymentMethod paymentMethod,
			List<BillingItem> items) {
		// Create billing document object
		BillingDocumentBuilder builder = new BillingDocumentBuilder();
		builder.build(documentType, documentNumber, paymentMethod, items);
		BillingDocument document = builder.getDocument();
		// Save billing document
		billilngDocumentRepository.save(document);
		return document;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return (List<PaymentMethod>) paymentMethodRepository.findAll();
	}

}
