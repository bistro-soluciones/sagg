package com.bistro.sagg.reports.ui.viewers;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.osgi.ui.viewers.SaggStructuredContentProvider;
import com.bistro.sagg.core.services.EmployeeServices;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;

public class EmployeeComboContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		EmployeeServices employeeServices = (EmployeeServices) inputElement;
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		return employeeServices.getEmployees(branch).toArray();
	}

}
