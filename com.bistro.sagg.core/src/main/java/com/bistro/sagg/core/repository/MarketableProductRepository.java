package com.bistro.sagg.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.products.MarketableProduct;

public interface MarketableProductRepository extends CrudRepository<MarketableProduct, Long> {

}
