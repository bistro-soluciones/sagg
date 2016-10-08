package com.bistro.sagg.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.order.billing.BillingDocument;

public interface BillingDocumentRepository extends CrudRepository<BillingDocument, Long> {

}
