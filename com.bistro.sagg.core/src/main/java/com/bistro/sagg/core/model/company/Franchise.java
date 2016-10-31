package com.bistro.sagg.core.model.company;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bistro.sagg.core.model.Identificable;
import com.bistro.sagg.core.model.location.Country;

@Entity
@Table(name = "FRANCHISES")
public class Franchise implements Identificable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "CODE")
	private String code;
	@ManyToMany
	@JoinTable(name = "FRANCHISES_OF_FRANCHISEDS", 
	joinColumns = @JoinColumn(name = "FRANCHISE_ID", referencedColumnName = "ID"), 
	inverseJoinColumns = @JoinColumn(name = "FRANCHISED_ID", referencedColumnName = "ID"))
	private List<Franchised> franchiseds;
	@ManyToMany
	@JoinTable(name = "FRANCHISES_BY_COUNTIES", 
	joinColumns = @JoinColumn(name = "FRANCHISE_ID", referencedColumnName = "ID"), 
	inverseJoinColumns = @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID"))
	private List<Country> cuontries;
	@OneToMany(mappedBy = "franchise")
	private List<FranchiseBranch> branches;

	public Franchise() {
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

	public List<Franchised> getFranchiseds() {
		return franchiseds;
	}

	public void setFranchiseds(List<Franchised> franchiseds) {
		this.franchiseds = franchiseds;
	}

	public List<Country> getCuontries() {
		return cuontries;
	}

	public void setCuontries(List<Country> cuontries) {
		this.cuontries = cuontries;
	}

	public List<FranchiseBranch> getBranches() {
		return branches;
	}

	public void setBranches(List<FranchiseBranch> branches) {
		this.branches = branches;
	}

}
