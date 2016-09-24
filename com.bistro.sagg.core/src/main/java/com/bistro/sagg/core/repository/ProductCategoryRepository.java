package com.bistro.sagg.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.products.ProductCategory;

public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

}
