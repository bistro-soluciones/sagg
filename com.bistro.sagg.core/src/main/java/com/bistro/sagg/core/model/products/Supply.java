package com.bistro.sagg.core.model.products;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SUPPLY")
public class Supply extends Product {

	public Supply() {
		super();
	}

	public void decreaseStock(int portion) {
		setStock(getStock() - portion);
	}

}
