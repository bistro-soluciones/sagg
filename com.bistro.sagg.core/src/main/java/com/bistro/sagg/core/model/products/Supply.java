package com.bistro.sagg.core.model.products;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SUPPLIES")
public class Supply extends Product {

	public Supply() {
		super();
	}

}
