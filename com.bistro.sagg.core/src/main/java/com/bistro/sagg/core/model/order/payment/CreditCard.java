package com.bistro.sagg.core.model.order.payment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CREDIT_CARD")
public class CreditCard extends PaymentMethod {

	@Override
	public boolean isCreditCardPayment() {
		return true;
	}

}
