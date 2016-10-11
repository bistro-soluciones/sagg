package com.bistro.sagg.core.osgi;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.services.EmployeeServices;
import com.bistro.sagg.core.services.FranchiseServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.core.spring.SaggApplicationContextInitializer;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.bistro.sagg.core.osgi"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
//	private FranchiseServices franchiseService = (FranchiseServices) SaggServiceLocator.getInstance()
//			.lookup(FranchiseServices.class.getName());
//	private RefdataServices refdataService = (RefdataServices) SaggServiceLocator.getInstance()
//			.lookup(RefdataServices.class.getName());
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		SaggApplicationContextInitializer.start();
		initializeSaggSession();
		plugin = this;
	}

	private void initializeSaggSession() {
		FranchiseServices franchiseService = (FranchiseServices) SaggServiceLocator.getInstance()
				.lookup(FranchiseServices.class.getName());
		EmployeeServices employeeService = (EmployeeServices) SaggServiceLocator.getInstance()
				.lookup(EmployeeServices.class.getName());
		// TODO Get correct session objects
		SaggSession.getCurrentSession().addSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH, franchiseService.getById(1L));
		SaggSession.getCurrentSession().addSessionObject(SaggSessionConstants.ACTIVE_USER, employeeService.getById(1L));
		SimpleDateFormat formatter = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy HH:mm", new Locale("es","CL"));
		SaggSession.getCurrentSession().addSessionObject(SaggSessionConstants.DATE_FORMATTER, formatter);
		SaggSession.getCurrentSession().addSessionObject(SaggSessionConstants.CURRENCY, "CLP");
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
