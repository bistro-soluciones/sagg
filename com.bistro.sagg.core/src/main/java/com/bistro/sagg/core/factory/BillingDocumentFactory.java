package com.bistro.sagg.core.factory;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.billing.PurchaseBillingDocument;
import com.bistro.sagg.core.model.order.billing.PurchaseBillingItem;
import com.bistro.sagg.core.model.order.billing.SaleBillingDocument;
import com.bistro.sagg.core.model.order.billing.SaleBillingItem;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.model.suppliers.Supplier;

public class BillingDocumentFactory {

	private BillingDocumentFactory() {
		super();
	}

	public static PurchaseBillingDocument createPurchaseBillingDocument(FranchiseBranch branch, DocumentType documentType,
			String documentNumber, PaymentMethod paymentMethod, Supplier supplier, List<PurchaseBillingItem> items) {
		PurchaseBillingDocument document = new PurchaseBillingDocument();
		document.setDocumentType(documentType);
		document.setDocumentNumber(documentNumber);
		document.setDate(Date.from(Instant.now()));
		document.setPaymentMethod(paymentMethod);
		document.setSupplier(supplier);
		document.addItems(items);
		document.setBranch(branch);
		return document;
	}

	public static SaleBillingDocument createSaleBillingDocument(FranchiseBranch branch, DocumentType documentType,
			String documentNumber, PaymentMethod paymentMethod, Employee seller, List<SaleBillingItem> items) {
		SaleBillingDocument document = new SaleBillingDocument();
		document.setDocumentType(documentType);
		document.setDocumentNumber(documentNumber);
		document.setDate(Date.from(Instant.now()));
		document.setPaymentMethod(paymentMethod);
		document.setSeller(seller);
		document.addItems(items);
		document.setBranch(branch);
		return document;
	}

}
