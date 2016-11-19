package com.bistro.sagg.suppliers.ui.views;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
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
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.osgi.ui.utils.ErrorMessageUtils;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.services.SupplierServices;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.core.validation.processor.ListValidatorProcessor;
import com.bistro.sagg.core.validation.validator.AndValidator;
import com.bistro.sagg.core.validation.validator.EmailValidator;
import com.bistro.sagg.core.validation.validator.EmptyOrNullValidator;
import com.bistro.sagg.core.validation.validator.RUTValidator;
import com.bistro.sagg.core.validation.validator.SaggValidator;
import com.bistro.sagg.suppliers.ui.utils.SuppliersCommunicationConstants;
import com.bistro.sagg.suppliers.ui.viewers.InventoryProductCategoryComboContentProvider;
import com.bistro.sagg.suppliers.ui.viewers.ProductCategoryComboLabelProvider;
import com.bistro.sagg.suppliers.ui.viewers.ProductCategoryListContentProvider;

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

public class SupplierDetailView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.suppliers.ui.views.SupplierDetailView";

	private Text businessNameText;
	private Text supplierIdText;
	private Text contactFirstnameText;
	private Text contactLastnameText;
	private Text contactEmailText;
	private Text contactPhoneText;
	private Text contactCellphoneText;
	
	private ComboViewer productCategoryComboViewer;
	private Combo productCategoryCombo;
	
	private ListViewer productsCategoryListViewer;
	private List productsCategoryList;
	
	private Button addProductsCategoryButton;
	
	private ProductCategory selectedProductCategory;
	private java.util.List<ProductCategory> selectedProductCategories = new ArrayList<ProductCategory>();
	
	private SupplierServices supplierServices = (SupplierServices) SaggServiceLocator.getInstance()
			.lookup(SupplierServices.class.getName());
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	
	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public SupplierDetailView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(SupplierDetailView.class).getBundleContext();
		ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
		this.eventAdmin = bundleContext.getService(ref);
//		this.franchiseBranch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
//		this.country = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH_COUNTRY);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group basicInfoGroup = new Group(parent, SWT.NONE);
		basicInfoGroup.setLayout(new GridLayout(2, false));
		basicInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		basicInfoGroup.setText("Informaci\u00F3n B\u00E1sica");
		
		Label businessNameLabel = new Label(basicInfoGroup, SWT.RIGHT);
		GridData gd_firstnameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_firstnameLabel.widthHint = 135;
		businessNameLabel.setLayoutData(gd_firstnameLabel);
		businessNameLabel.setText("Raz\u00F3n Social *");
		businessNameLabel.setAlignment(SWT.RIGHT);
		
		businessNameText = new Text(basicInfoGroup, SWT.BORDER);
		businessNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label personIdLabel = new Label(basicInfoGroup, SWT.RIGHT);
		GridData gd_personIdLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_personIdLabel.widthHint = 135;
		personIdLabel.setLayoutData(gd_personIdLabel);
		personIdLabel.setText("RUT *");
		
		supplierIdText = new Text(basicInfoGroup, SWT.BORDER);
		supplierIdText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group contactInfoGroup = new Group(parent, SWT.NONE);
		contactInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		contactInfoGroup.setText("Informaci\u00F3n de Contacto");
		contactInfoGroup.setLayout(new GridLayout(2, false));
		
		Label contactFirstnameLabel = new Label(contactInfoGroup, SWT.RIGHT);
		gd_firstnameLabel.widthHint = 130;
		GridData gd_contactFirstnameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_contactFirstnameLabel.widthHint = 135;
		contactFirstnameLabel.setLayoutData(gd_contactFirstnameLabel);
		contactFirstnameLabel.setText("Nombres *");
		contactFirstnameLabel.setAlignment(SWT.RIGHT);
		
		contactFirstnameText = new Text(contactInfoGroup, SWT.BORDER);
		contactFirstnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label contactLastnameLabel = new Label(contactInfoGroup, SWT.RIGHT);
		GridData gd_contactLastnameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_contactLastnameLabel.widthHint = 135;
		contactLastnameLabel.setLayoutData(gd_contactLastnameLabel);
		contactLastnameLabel.setText("Apellidos *");
		
		contactLastnameText = new Text(contactInfoGroup, SWT.BORDER);
		contactLastnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label contactPhoneLabel = new Label(contactInfoGroup, SWT.RIGHT);
		GridData gd_contactPhoneLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_contactPhoneLabel.widthHint = 135;
		contactPhoneLabel.setLayoutData(gd_contactPhoneLabel);
		contactPhoneLabel.setText("Tel\u00E9fono");
		
		contactPhoneText = new Text(contactInfoGroup, SWT.BORDER);
		contactPhoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label contactCellphoneLabel = new Label(contactInfoGroup, SWT.RIGHT);
		GridData gd_contactCellphoneLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_contactCellphoneLabel.widthHint = 135;
		contactCellphoneLabel.setLayoutData(gd_contactCellphoneLabel);
		contactCellphoneLabel.setText("Celular");
		
		contactCellphoneText = new Text(contactInfoGroup, SWT.BORDER);
		contactCellphoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label contactEmailLabel = new Label(contactInfoGroup, SWT.RIGHT);
		GridData gd_contactEmailLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_contactEmailLabel.widthHint = 135;
		contactEmailLabel.setLayoutData(gd_contactEmailLabel);
		contactEmailLabel.setText("Correo Electr\u00F3nico *");
		
		contactEmailText = new Text(contactInfoGroup, SWT.BORDER);
		contactEmailText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group suppliesInfoGroup = new Group(parent, SWT.NONE);
		suppliesInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		suppliesInfoGroup.setText("Informaci\u00F3n de Insumos");
		suppliesInfoGroup.setLayout(new GridLayout(2, false));
		
		Label addressLabel = new Label(suppliesInfoGroup, SWT.RIGHT);
		GridData gd_addressLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_addressLabel.widthHint = 135;
		addressLabel.setLayoutData(gd_addressLabel);
		addressLabel.setText("Insumos *");

		Composite productCategoryComposite = new Composite(suppliesInfoGroup, SWT.NONE);
		productCategoryComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_productCategoryComposite = new GridLayout(2, false);
		gl_productCategoryComposite.marginHeight = 0;
		gl_productCategoryComposite.marginWidth = 0;
		productCategoryComposite.setLayout(gl_productCategoryComposite);
		
		productCategoryComboViewer = new ComboViewer(productCategoryComposite, SWT.NONE);
		productCategoryComboViewer.setContentProvider(new InventoryProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(productServices);
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if(event.getSelection() != null){
					addProductsCategoryButton.setEnabled(true);
				}
				selectedProductCategory = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		productCategoryCombo = productCategoryComboViewer.getCombo();
		productCategoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		addProductsCategoryButton = new Button(productCategoryComposite, SWT.NONE);
		addProductsCategoryButton.setText("Agregar");
		addProductsCategoryButton.setEnabled(false);
		addProductsCategoryButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!selectedProductCategories.contains(selectedProductCategory)) {
					selectedProductCategories.add(selectedProductCategory);
				}
				productsCategoryListViewer.refresh();
			}
		});
		new Label(suppliesInfoGroup, SWT.NONE);
		
		productsCategoryListViewer = new ListViewer(suppliesInfoGroup, SWT.BORDER | SWT.V_SCROLL);
		productsCategoryListViewer.setContentProvider(new ProductCategoryListContentProvider());
		productsCategoryListViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productsCategoryListViewer.setInput(selectedProductCategories);
		productsCategoryList = productsCategoryListViewer.getList();
		GridData gd_productsCategoryList = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_productsCategoryList.heightHint = 155;
		productsCategoryList.setLayoutData(gd_productsCategoryList);
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		composite.setLayout(new GridLayout(2, false));
		
		Button cancelButton = new Button(composite, SWT.NONE);
		cancelButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetDefaultValues();
			}
		});
		cancelButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cancelButton.setText("Cancelar");
		
		Button saveButton = new Button(composite, SWT.NONE);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (validateFields(parent.getShell())) {
					FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
					supplierServices.createSupplier(businessNameText.getText(), supplierIdText.getText(), contactFirstnameText.getText(),
							contactLastnameText.getText(), contactEmailText.getText(), contactPhoneText.getText(), contactCellphoneText.getText(),
							selectedProductCategories, branch);
					Event event = new Event(SuppliersCommunicationConstants.ADD_SUPPLIER_EVENT, new HashMap<String, Object>());
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
		ListValidatorProcessor processor = getSupplierValidatorProcessor();
		boolean result = processor.processValidation();
		if (!result) {
			MessageDialog.openError(shell, "Error", processor.getErrorMessage());
		}
		return result;
	}

	private ListValidatorProcessor getSupplierValidatorProcessor() {
		java.util.List<SaggValidator> validators = new ArrayList<>();
		validators.add(new EmptyOrNullValidator(businessNameText.getText(),
				ErrorMessageUtils.createMandatoryFieldErrorMsg("Raz\u00F3n Social")));
		validators.add(new AndValidator(supplierIdText.getText(),
				new EmptyOrNullValidator(supplierIdText.getText(),
						ErrorMessageUtils.createMandatoryFieldErrorMsg("RUT")),
				new RUTValidator(supplierIdText.getText(), ErrorMessageUtils.createWrongFieldValueErrorMsg("RUT"))));
		validators.add(new EmptyOrNullValidator(contactFirstnameText.getText(),
				ErrorMessageUtils.createMandatoryFieldErrorMsg("Nombres de contacto")));
		validators.add(new EmptyOrNullValidator(contactLastnameText.getText(),
				ErrorMessageUtils.createMandatoryFieldErrorMsg("Apellidos de contacto")));
//		validators.add(new OrValidator(supplierIdText.getText(),
//				ErrorMessageUtils.createAtLeastOneMandatoryFieldErrorMsg("Tel\u00E9fono", "Celular",
//						"Correo Electr\u00F3nico de contacto"),
//				new EmptyOrNullValidator(contactPhoneText.getText(),
//						ErrorMessageUtils.createMandatoryFieldErrorMsg("Tel\u00E9fono")),	
//				new EmptyOrNullValidator(contactCellphoneText.getText(),
//						ErrorMessageUtils.createMandatoryFieldErrorMsg("Celular")),
//				new EmptyOrNullValidator(contactEmailText.getText(),
//						ErrorMessageUtils.createMandatoryFieldErrorMsg("Correo Electr\u00F3nico de contacto"))));
		validators.add(new EmptyOrNullValidator(contactEmailText.getText(),
				ErrorMessageUtils.createMandatoryFieldErrorMsg("Correo Electr\u00F3nico de contacto")));
		if (!"".equals(contactEmailText.getText())) {
			validators.add(new EmailValidator(contactEmailText.getText(),
					ErrorMessageUtils.createWrongFieldValueErrorMsg("Correo Electr\u00F3nico de contacto")));
		}
		validators.add(new EmptyOrNullValidator(selectedProductCategories,
				ErrorMessageUtils.createOneMandatoryListElementErrorMsg("Insumo")));
		ListValidatorProcessor processor = new ListValidatorProcessor(validators);
		return processor;
	}

	private void resetDefaultValues() {
		businessNameText.setText("");
		supplierIdText.setText("");
		contactFirstnameText.setText("");
		contactLastnameText.setText("");
		contactEmailText.setText("");
		contactPhoneText.setText("");
		contactCellphoneText.setText("");
		productCategoryCombo.setText("");
		selectedProductCategory = null;
		selectedProductCategories.clear();
		productsCategoryListViewer.refresh();
	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				SupplierDetailView.this.fillContextMenu(manager);
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
