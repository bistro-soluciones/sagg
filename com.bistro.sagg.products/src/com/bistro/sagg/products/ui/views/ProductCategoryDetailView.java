package com.bistro.sagg.products.ui.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.osgi.ui.utils.ErrorMessageUtils;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.core.validation.processor.ListValidatorProcessor;
import com.bistro.sagg.core.validation.validator.EmptyOrNullValidator;
import com.bistro.sagg.core.validation.validator.SaggValidator;
import com.bistro.sagg.products.ui.utils.ProductsCommunicationConstants;

/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ProductCategoryDetailView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.products.ui.views.ProductCategoryDetailView";
	
	private Text nameText;
	private Button forSaleCheckButton;
	
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());

	private FranchiseBranch franchiseBranch;
	
	private ListValidatorProcessor processor;
	
	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public ProductCategoryDetailView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(ProductCategoryDetailView.class).getBundleContext();
		ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
		this.eventAdmin = bundleContext.getService(ref);
		this.franchiseBranch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		this.processor = setupProductCategoryValidatorProcessor();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group basicInfoGroup = new Group(parent, SWT.NONE);
		basicInfoGroup.setLayout(new GridLayout(3, false));
		basicInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		basicInfoGroup.setText("Informaci\u00F3n B\u00E1sica");
		
		Label nameLabel = new Label(basicInfoGroup, SWT.RIGHT);
		GridData gd_nameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_nameLabel.widthHint = 124;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("Nombre *");
		nameLabel.setAlignment(SWT.RIGHT);
		
		nameText = new Text(basicInfoGroup, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		forSaleCheckButton = new Button(basicInfoGroup, SWT.CHECK);
		forSaleCheckButton.setText("Venta");
		
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		buttonsComposite.setLayout(new GridLayout(2, false));
		
		Button cancelButton = new Button(buttonsComposite, SWT.NONE);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetDefaultValues();
			}
		});
		cancelButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		cancelButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		cancelButton.setText("Cancelar");
		
		Button saveButton = new Button(buttonsComposite, SWT.NONE);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (validateFields(parent.getShell())) {
					productServices.createCategory(franchiseBranch, nameText.getText(), forSaleCheckButton.getSelection());
					Map<String,Object> properties = new HashMap<String, Object>();
					Event event = new Event(ProductsCommunicationConstants.ADD_PRODUCT_CATEGORY_EVENT, properties);
					eventAdmin.sendEvent(event);
					resetDefaultValues();
				}
			}
		});
		saveButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		saveButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		saveButton.setText("Guardar");
		
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private boolean validateFields(Shell shell) {
		boolean result = processor.processValidation();
		if (!result) {
			MessageDialog.openError(shell, "Error", processor.getErrorMessage());
		}
		return result;
	}

	private ListValidatorProcessor setupProductCategoryValidatorProcessor() {
		java.util.List<SaggValidator> validators = new ArrayList<>();
		validators.add(
				new EmptyOrNullValidator(nameText.getText(), ErrorMessageUtils.createMandatoryFieldErrorMsg("Nombre")));
		ListValidatorProcessor processor = new ListValidatorProcessor(validators);
		return processor;
	}
	
	private void resetDefaultValues() {
		nameText.setText("");
		forSaleCheckButton.setSelection(false);
	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ProductCategoryDetailView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
	}

	private void fillContextMenu(IMenuManager manager) {
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
	}

	private void makeActions() {
//		doubleClickAction = new Action() {
//			public void run() {
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection)selection).getFirstElement();
//				showMessage("Double-click detected on "+obj.toString());
//			}
//		};
	}

	private void hookDoubleClickAction() {
	}
	private void showMessage(String message) {
//		MessageDialog.openInformation(
//			viewer.getControl().getShell(),
//			"Product View",
//			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		//viewer.getControl().setFocus();
	}
}
