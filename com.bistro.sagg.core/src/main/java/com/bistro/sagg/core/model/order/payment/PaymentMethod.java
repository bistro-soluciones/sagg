package com.bistro.sagg.core.model.order.payment;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.bistro.sagg.core.model.Identificable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "METHOD_TYPE")
@Table(name = "PAYMENT_METHODS")
public abstract class PaymentMethod implements Identificable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;

	public PaymentMethod() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCashPayment() {
		return false;
	}
	
	public boolean isCreditCardPayment() {
		return false;
	}
	
	public boolean isDebitCardPayment() {
		return false;
	}
	
}
