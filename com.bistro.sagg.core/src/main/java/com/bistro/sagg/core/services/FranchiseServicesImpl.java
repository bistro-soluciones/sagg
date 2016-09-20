package com.bistro.sagg.core.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.repository.FranchiseBranchRepository;

public class FranchiseServicesImpl implements FranchiseServices {

	@Autowired
	private FranchiseBranchRepository franchiseBranchRepository;

	public FranchiseBranch getById(Long franchiseBranchId) {
		return franchiseBranchRepository.findOne(franchiseBranchId);
	}

}
