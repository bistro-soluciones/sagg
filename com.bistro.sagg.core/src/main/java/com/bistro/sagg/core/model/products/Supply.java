package com.bistro.sagg.core.model.products;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("SUPPLY")
public class Supply extends Product {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_FORMAT_ID")
	private ProductFormat format;
	
	public Supply() {
		super();
	}

	public ProductFormat getFormat() {
		return format;
	}

	public void setFormat(ProductFormat format) {
		this.format = format;
	}

}
