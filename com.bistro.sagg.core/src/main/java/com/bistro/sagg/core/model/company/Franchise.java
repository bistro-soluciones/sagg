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

import com.bistro.sagg.core.model.location.Country;

@Entity
@Table(name = "FANCHISES")
public class Franchise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@OneToMany(mappedBy = "franchise")
	private List<Franchised> franchiseds;
	@ManyToMany
	@JoinTable(name = "FRANCHISES_BY_COUNTIES", 
	joinColumns = @JoinColumn(name = "FRANCHISE_ID", referencedColumnName = "ID"), 
	inverseJoinColumns = @JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID"))
	private List<Country> cuontries;

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

}
