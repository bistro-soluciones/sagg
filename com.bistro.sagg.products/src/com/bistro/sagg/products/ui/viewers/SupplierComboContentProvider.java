package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.SupplierServices;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;

public class SupplierComboContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		SupplierServices supplierServices = (SupplierServices) inputElement;
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		return supplierServices.getSuppliers(branch).toArray();
	}

}
