package com.bistro.sagg.products.ui.viewers;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;

public class SaleProductCategoryComboContentProvider extends SaggStructuredContentProvider {

	private Supplier supplier;
	
	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (supplier != null) {
			return supplier.getCategories().toArray();
		}
		ProductServices productServices = (ProductServices) inputElement;
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		return productServices.getSaleProductCategories(branch).toArray();
	}

}
