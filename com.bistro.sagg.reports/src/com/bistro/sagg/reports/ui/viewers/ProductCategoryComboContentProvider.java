package com.bistro.sagg.reports.ui.viewers;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;

public class ProductCategoryComboContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		ProductServices productServices = (ProductServices) inputElement;
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		return productServices.getProductCategories(branch).toArray();
	}

}
