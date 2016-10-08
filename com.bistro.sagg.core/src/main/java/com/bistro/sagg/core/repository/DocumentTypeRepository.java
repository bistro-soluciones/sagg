package com.bistro.sagg.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.order.billing.DocumentType;

public interface DocumentTypeRepository extends CrudRepository<DocumentType, Long> {

}
