package com.bistro.sagg.core.model.order.payment;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CASH")
public class Cash extends PaymentMethod {

	@Override
	public boolean isCashPayment() {
		return true;
	}

}
