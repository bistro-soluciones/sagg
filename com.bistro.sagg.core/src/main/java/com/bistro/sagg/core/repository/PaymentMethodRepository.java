package com.bistro.sagg.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.bistro.sagg.core.model.order.payment.PaymentMethod;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod, Long> {

}
