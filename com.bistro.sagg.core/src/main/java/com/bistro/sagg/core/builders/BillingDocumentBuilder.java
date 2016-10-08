package com.bistro.sagg.core.builders;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.bistro.sagg.core.model.order.billing.BillingDocument;
import com.bistro.sagg.core.model.order.billing.BillingItem;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;

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

	public void build(DocumentType documentType, String documentNumber, PaymentMethod paymentMethod, List<BillingItem> items) {
		document.setDocumentType(documentType);
		document.setDocumentNumber(documentNumber);
		document.setDate(Date.from(Instant.now()));
		document.setPaymentMethod(paymentMethod);
		document.addItems(items);
	}

	public BillingDocument getDocument() {
		return document;
	}

}
