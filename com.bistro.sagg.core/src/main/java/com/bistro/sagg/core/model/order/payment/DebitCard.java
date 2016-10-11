package com.bistro.sagg.core.model.order.payment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DEBIT_CARD")
public class DebitCard extends PaymentMethod {

	@Override
	public boolean isDebitCardPayment() {
		return true;
	}

}
