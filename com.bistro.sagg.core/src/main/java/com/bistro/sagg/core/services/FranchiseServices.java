package com.bistro.sagg.core.services;

import com.bistro.sagg.core.model.company.FranchiseBranch;

public interface FranchiseServices extends ISaggService {

	FranchiseBranch getById(Long franchiseBranchId);

}
