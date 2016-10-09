package com.bistro.sagg.sales.ui.viewers;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.session.SaggSession;

public class ProductCategoryComboContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		ProductServices productServices = (ProductServices) inputElement;
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(FranchiseBranch.class.getName());
		return productServices.getProductCategories(branch).toArray();
	}

}
