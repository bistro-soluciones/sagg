package com.bistro.sagg.core.model.products;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("INVENTORY_CATEGORY")
public class InventoryProductCategory extends ProductCategory {

}
