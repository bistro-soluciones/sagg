package com.bistro.sagg.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.builders.BillingDocumentBuilder;
import com.bistro.sagg.core.model.billing.BillingDocument;
import com.bistro.sagg.core.model.billing.BillingItem;
import com.bistro.sagg.core.model.billing.DocumentType;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.repository.BillingDocumentRepository;
import com.bistro.sagg.core.repository.DocumentTypeRepository;

public class BillingServicesImpl implements BillingServices {

	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	@Autowired
	private BillingDocumentRepository billilngDocumentRepository;

	public List<DocumentType> getBillingDocumentTypes() {
		return (List<DocumentType>) documentTypeRepository.findAll();
	}

	public List<BillingDocument> getBillingDocuments() {
		return (List<BillingDocument>) billilngDocumentRepository.findAll();
	}
	
	public void createBillingDocument(DocumentType documentType, String documentNumber, Supplier supplier,
			List<BillingItem> items, FranchiseBranch branch) {
		// Create billing document object
		BillingDocumentBuilder builder = new BillingDocumentBuilder();
		builder.build(documentType, documentNumber, supplier, items, branch);
		BillingDocument document = builder.getDocument();
		// Save billing document
		billilngDocumentRepository.save(document);
	}

}
