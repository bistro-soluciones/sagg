package com.bistro.sagg.core.model.company;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.location.City;
import com.bistro.sagg.core.model.order.PurchaseOrder;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.products.ProductCategory;

@Entity
@Table(name = "FRANCHISE_BRANCHES")
public class FranchiseBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CODE")
	private String code;
	// Address information
	@Column(name = "ADDRESS_L1")
	private String addressL1;
	@Column(name = "ADDRESS_L2")
	private String addressL2;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID")
	private City city;
	// Employees information
	@OneToMany(mappedBy = "franchiseBranch")
	private List<Employee> employees;

	@OneToMany(mappedBy = "branch", cascade = CascadeType.PERSIST)
	private List<ProductCategory> productCategories;
	@OneToMany(mappedBy = "branch", cascade = CascadeType.PERSIST)
	private List<PurchaseOrder> purchaseOrders;
	@OneToMany(mappedBy = "branch", cascade = CascadeType.PERSIST)
	private List<SaleOrder> saleOrders;

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "branch")
	private Franchised franchised;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FRANCHISE_ID")
	private Franchise franchise;
	// Suppliers information
	// private List<Supplier> suppliers;
	public FranchiseBranch() {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAddressL1() {
		return addressL1;
	}

	public void setAddressL1(String addressL1) {
		this.addressL1 = addressL1;
	}

	public String getAddressL2() {
		return addressL2;
	}

	public void setAddressL2(String addressL2) {
		this.addressL2 = addressL2;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

	public List<PurchaseOrder> getPurchaseOrders() {
		return purchaseOrders;
	}

	public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	public List<SaleOrder> getSaleOrders() {
		return saleOrders;
	}

	public void setSaleOrders(List<SaleOrder> saleOrders) {
		this.saleOrders = saleOrders;
	}

	public Franchised getFranchised() {
		return franchised;
	}

	public void setFranchised(Franchised franchised) {
		this.franchised = franchised;
	}

	public Franchise getFranchise() {
		return franchise;
	}

	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
	}

}
