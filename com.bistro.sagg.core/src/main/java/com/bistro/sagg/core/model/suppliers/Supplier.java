package com.bistro.sagg.core.model.suppliers;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.ProductCategory;

@Entity
@Table(name = "SUPPLIERS")
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "BUSINESS_NAME")
	private String businessName;
	@Column(name = "SUPPLIER_ID")
	private String supplierId;
	// Contact information
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "CONTACT_ID")
	private SupplierContact contact;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "SUPPLIERS_FOR_CATEGORIES", 
		joinColumns = @JoinColumn(name = "SUPPLIER_ID", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "PRODUCT_CATEGORY_ID", referencedColumnName = "ID"))
	private List<ProductCategory> categories;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRANCHISE_BRANCH_ID")
	private FranchiseBranch franchiseBranch; 
	
	public Supplier() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public SupplierContact getContact() {
		return contact;
	}

	public void setContact(SupplierContact contact) {
		this.contact = contact;
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}

}
