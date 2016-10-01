package com.bistro.sagg.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.model.billing.BillingDocument;
import com.bistro.sagg.core.model.billing.DocumentType;
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

}
