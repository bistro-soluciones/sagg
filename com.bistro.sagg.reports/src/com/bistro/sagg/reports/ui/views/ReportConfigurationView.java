package com.bistro.sagg.reports.ui.views;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
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
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.services.BillingServices;
import com.bistro.sagg.core.services.EmployeeServices;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.services.SupplierServices;
import com.bistro.sagg.reports.ui.commands.DetailedPurchaseOrdersReportCommand;
import com.bistro.sagg.reports.ui.commands.DetailedSalesReportCommand;
import com.bistro.sagg.reports.ui.commands.IReportCommand;
import com.bistro.sagg.reports.ui.commands.PurchaseOrdersReportCommand;
import com.bistro.sagg.reports.ui.commands.SalesReportCommand;
import com.bistro.sagg.reports.ui.commands.SuppliesBySupplierReportCommand;
import com.bistro.sagg.reports.ui.utils.ReportsCommunicationConstants;
import com.bistro.sagg.reports.ui.viewers.BillingDocumentTypeComboContentProvider;
import com.bistro.sagg.reports.ui.viewers.BillingDocumentTypeComboLabelProvider;
import com.bistro.sagg.reports.ui.viewers.EmployeeComboContentProvider;
import com.bistro.sagg.reports.ui.viewers.EmployeeComboLabelProvider;
import com.bistro.sagg.reports.ui.viewers.PaymentMethodComboContentProvider;
import com.bistro.sagg.reports.ui.viewers.PaymentMethodComboLabelProvider;
import com.bistro.sagg.reports.ui.viewers.ProductCategoryComboContentProvider;
import com.bistro.sagg.reports.ui.viewers.ProductCategoryComboLabelProvider;
import com.bistro.sagg.reports.ui.viewers.ProductComboContentProvider;
import com.bistro.sagg.reports.ui.viewers.ProductComboLabelProvider;
import com.bistro.sagg.reports.ui.viewers.SupplierComboContentProvider;
import com.bistro.sagg.reports.ui.viewers.SupplierComboLabelProvider;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;

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

public class ReportConfigurationView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.reports.ui.views.ReportConfigurationView";
	
	private static final String REPORTE_DE_VENTAS = "Reporte de Ventas";
	private static final String REPORTE_DE_VENTAS_DETALLADO = "Reporte de Ventas Detallado";
	private static final String REPORTE_DE_PRODUCTOS_POR_PROVEEDOR = "Reporte de Productos por Proveedor";
	private static final String REPORTE_DE_ORDENES_DE_COMPRA = "Reporte de Ordenes de Compra";
	private static final String REPORTE_DE_ORDENES_DE_COMPRA_DETALLADO = "Reporte de Ordenes de Compra Detallado";
	
	private DateTime fromDateDateTime;
	private DateTime toDateDateTime;
	private Combo reportsCombo;
	private Combo documentTypeCombo;
	private Combo paymentMethodCombo;
	private Combo employeeCombo;
	private Combo supplierCombo;
	private ComboViewer productCategoryComboViewer;
	private Combo productCategoryCombo;
	private ComboViewer productComboViewer;
	private Combo productCombo;
	
	private Button cancelButton;
	private Button generateReportButton;
	
	private BillingServices billingServices = (BillingServices) SaggServiceLocator.getInstance()
			.lookup(BillingServices.class.getName());
	private EmployeeServices employeeServices = (EmployeeServices) SaggServiceLocator.getInstance()
			.lookup(EmployeeServices.class.getName());
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	private SupplierServices supplierServices = (SupplierServices) SaggServiceLocator.getInstance()
			.lookup(SupplierServices.class.getName());
	
	private DocumentType selectedDocumentType;
	private PaymentMethod selectedPaymentMethod;
	private ProductCategory selectedProductCategory;
	private Product selectedProduct;
	private Employee selectedEmployee;
	private Supplier selectedSupplier;
	
	private BundleContext bundleContext;
	private EventAdmin eventAdmin;
	
	private IReportCommand reportCommand;

	public ReportConfigurationView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(ReportConfigurationView.class).getBundleContext();
		ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
		this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group reportsSelectionGroup = new Group(parent, SWT.NONE);
		reportsSelectionGroup.setLayout(new GridLayout(2, false));
		reportsSelectionGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		reportsSelectionGroup.setText("Reportes");
		
		Label reportsLabel = new Label(reportsSelectionGroup, SWT.RIGHT);
		GridData gd_reportsLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_reportsLabel.widthHint = 150;
		reportsLabel.setLayoutData(gd_reportsLabel);
		reportsLabel.setText("Reporte");
		reportsLabel.setAlignment(SWT.RIGHT);
		
		reportsCombo = new Combo(reportsSelectionGroup, SWT.NONE);
		reportsCombo.add(REPORTE_DE_VENTAS);
		reportsCombo.add(REPORTE_DE_VENTAS_DETALLADO);
		reportsCombo.add(REPORTE_DE_PRODUCTOS_POR_PROVEEDOR);
		reportsCombo.add(REPORTE_DE_ORDENES_DE_COMPRA);
		reportsCombo.add(REPORTE_DE_ORDENES_DE_COMPRA_DETALLADO);
		reportsCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		reportsCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				createReportCommand();
				enableReportFilters(reportsCombo.getText());
				cancelButton.setEnabled(true);
				generateReportButton.setEnabled(true);
			}
		});
		
		Group workingInfoGroup = new Group(parent, SWT.NONE);
		workingInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		workingInfoGroup.setText("Selecci\u00F3n de Filtro");
		workingInfoGroup.setLayout(new GridLayout(2, false));
		
		Label fromDateLabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_fromDateLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_fromDateLabel.widthHint = 150;
		fromDateLabel.setLayoutData(gd_fromDateLabel);
		fromDateLabel.setText("Fecha Inicio");
		
		fromDateDateTime = new DateTime(workingInfoGroup, SWT.DROP_DOWN);
		fromDateDateTime.setEnabled(false);
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MONTH, -1);
		fromDateDateTime.setDate(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DAY_OF_MONTH));
		
		Label toDateLabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_toDateLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_toDateLabel.widthHint = 150;
		toDateLabel.setLayoutData(gd_toDateLabel);
		toDateLabel.setText("Fecha Fin");
		
		toDateDateTime = new DateTime(workingInfoGroup, SWT.DROP_DOWN);
		toDateDateTime.setEnabled(false);
		
		Label documentTypeLabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_documentTypeLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_documentTypeLabel.widthHint = 150;
		documentTypeLabel.setLayoutData(gd_documentTypeLabel);
		documentTypeLabel.setText("Tipo de Documento");
		
		ComboViewer documentTypeComboViewer = new ComboViewer(workingInfoGroup, SWT.NONE);
		documentTypeComboViewer.setContentProvider(new BillingDocumentTypeComboContentProvider());
		documentTypeComboViewer.setLabelProvider(new BillingDocumentTypeComboLabelProvider());
		documentTypeComboViewer.setInput(billingServices);
		documentTypeCombo = documentTypeComboViewer.getCombo();
		documentTypeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if ("".equals(documentTypeCombo.getText())) {
					selectedDocumentType = null;
				}
			}
		});
		documentTypeCombo.setEnabled(false);
		documentTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		documentTypeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedDocumentType = (DocumentType) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Label paymentMethodLabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_paymentMethodLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_paymentMethodLabel.widthHint = 150;
		paymentMethodLabel.setLayoutData(gd_paymentMethodLabel);
		paymentMethodLabel.setText("Forma de Pago");
		
		ComboViewer paymentMethodComboViewer = new ComboViewer(workingInfoGroup, SWT.NONE);
		paymentMethodComboViewer.setContentProvider(new PaymentMethodComboContentProvider());
		paymentMethodComboViewer.setLabelProvider(new PaymentMethodComboLabelProvider());
		paymentMethodComboViewer.setInput(billingServices);
		paymentMethodCombo = paymentMethodComboViewer.getCombo();
		paymentMethodCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if ("".equals(paymentMethodCombo.getText())) {
					selectedPaymentMethod = null;
				}
			}
		});
		paymentMethodCombo.setEnabled(false);
		paymentMethodCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		paymentMethodComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedPaymentMethod = (PaymentMethod) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Label employeeLabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_employeeLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_employeeLabel.widthHint = 150;
		employeeLabel.setLayoutData(gd_employeeLabel);
		employeeLabel.setText("Vendedor");
		
		ComboViewer employeeComboViewer = new ComboViewer(workingInfoGroup, SWT.NONE);
		employeeComboViewer.setContentProvider(new EmployeeComboContentProvider());
		employeeComboViewer.setLabelProvider(new EmployeeComboLabelProvider());
		employeeComboViewer.setInput(employeeServices);
		employeeCombo = employeeComboViewer.getCombo();
		employeeCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if ("".equals(employeeCombo.getText())) {
					selectedEmployee = null;
				}
			}
		});
		employeeCombo.setEnabled(false);
		employeeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		employeeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedEmployee = (Employee) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Label supplierLabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_supplierLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_supplierLabel.widthHint = 150;
		supplierLabel.setLayoutData(gd_supplierLabel);
		supplierLabel.setText("Proveedor");
		
		ComboViewer supplierComboViewer = new ComboViewer(workingInfoGroup, SWT.NONE);
		supplierComboViewer.setContentProvider(new SupplierComboContentProvider());
		supplierComboViewer.setLabelProvider(new SupplierComboLabelProvider());
		supplierComboViewer.setInput(supplierServices);
		supplierCombo = supplierComboViewer.getCombo();
		supplierCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if ("".equals(supplierCombo.getText())) {
					selectedSupplier = null;
				}
			}
		});
		supplierCombo.setEnabled(false);
		supplierCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		supplierComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedSupplier = (Supplier) ((StructuredSelection) event.getSelection()).getFirstElement();
				productCategoryComboViewer.setInput(selectedSupplier);
				productCategoryComboViewer.refresh();
			}
		});
		
		Label productCategoryLabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_productCategoryLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_productCategoryLabel.widthHint = 150;
		productCategoryLabel.setLayoutData(gd_productCategoryLabel);
		productCategoryLabel.setText("Categor\u00EDa de Producto");
		
		productCategoryComboViewer = new ComboViewer(workingInfoGroup, SWT.NONE);
		productCategoryComboViewer.setContentProvider(new ProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(productServices);
		productCategoryCombo = productCategoryComboViewer.getCombo();
		productCategoryCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if ("".equals(productCategoryCombo.getText())) {
					selectedProductCategory = null;
				}
			}
		});
		productCategoryCombo.setEnabled(false);
		productCategoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedProductCategory = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
				ProductComboContentProvider provider = (ProductComboContentProvider) productComboViewer.getContentProvider();
				provider.setCategory(selectedProductCategory);
				productComboViewer.refresh();
				productCombo.setEnabled(true);
			}
		});
		
		Label productlabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_productlabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_productlabel.widthHint = 150;
		productlabel.setLayoutData(gd_productlabel);
		productlabel.setText("Producto");
		
		productComboViewer = new ComboViewer(workingInfoGroup, SWT.NONE);
		productComboViewer.setContentProvider(new ProductComboContentProvider());
		productComboViewer.setLabelProvider(new ProductComboLabelProvider());
		productComboViewer.setInput(productServices);
		productCombo = productComboViewer.getCombo();
		productCombo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if ("".equals(productCombo.getText())) {
					selectedProduct = null;
				}
			}
		});
		productCombo.setEnabled(false);
		productCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		productComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedProduct = (Product) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		composite.setLayout(new GridLayout(2, true));
		
		cancelButton = new Button(composite, SWT.NONE);
		cancelButton.setEnabled(false);
		cancelButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetDefaultValues();
			}
		});
		cancelButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cancelButton.setText("Cancelar");
		
		generateReportButton = new Button(composite, SWT.NONE);
		generateReportButton.setEnabled(false);
		generateReportButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		generateReportButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Map<String,Object> properties = new HashMap<String, Object>();
			        properties.put(ReportsCommunicationConstants.DOCUMENT_TYPE_DATA, selectedDocumentType);
			        properties.put(ReportsCommunicationConstants.EMPLOYEE_DATA, selectedEmployee);
			        properties.put(ReportsCommunicationConstants.FROM_DATE_DATA, getDateFor(fromDateDateTime));
			        properties.put(ReportsCommunicationConstants.PAYMENT_METHOD_DATA, selectedPaymentMethod);
			        properties.put(ReportsCommunicationConstants.PRODUCT_CATEGORY_DATA, selectedProductCategory);
			        properties.put(ReportsCommunicationConstants.PRODUCT_DATA, selectedProduct);
			        properties.put(ReportsCommunicationConstants.SUPPLIER_DATA, selectedSupplier);
			        properties.put(ReportsCommunicationConstants.TO_DATE_DATA, getDateFor(toDateDateTime));
					Event event = new Event(reportCommand.getEvent(), properties);
					reportCommand.execute();
					eventAdmin.sendEvent(event);
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				int day = startDateDateTime.getDay();
//				int month = startDateDateTime.getMonth();
//				int year = startDateDateTime.getYear();
//				Date startDate = new GregorianCalendar(year, month, day).getTime();
//				
//				employeeService.createEmployee(firstnameText.getText(), lastnameText.getText(), personIdText.getText(), selectedPosition,
//						startDate, franchiseBranch, emailText.getText(), phoneText.getText(),
//						cellphoneText.getText(), addressL1Text.getText(), addressL2Text.getText(), selectedCity);
//				Event event = new Event(EmployeesCommunicationConstants.ADD_EMPLOYEE_EVENT, new HashMap<String, Object>());
//				eventAdmin.sendEvent(event);
//				resetDefaultValues();
			}
		});
		generateReportButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		generateReportButton.setText("Generar Reporte");
		
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void createReportCommand() {
		if(REPORTE_DE_VENTAS.equals(reportsCombo.getText())) {
			reportCommand = new SalesReportCommand();
		}
		if(REPORTE_DE_VENTAS_DETALLADO.equals(reportsCombo.getText())) {
			reportCommand = new DetailedSalesReportCommand();
		}
		if(REPORTE_DE_PRODUCTOS_POR_PROVEEDOR.equals(reportsCombo.getText())) {
			reportCommand = new SuppliesBySupplierReportCommand();
		}
		if(REPORTE_DE_ORDENES_DE_COMPRA.equals(reportsCombo.getText())) {
			reportCommand = new PurchaseOrdersReportCommand();
		}
		if(REPORTE_DE_ORDENES_DE_COMPRA_DETALLADO.equals(reportsCombo.getText())) {
			reportCommand = new DetailedPurchaseOrdersReportCommand();
		}
	}
	
	private void enableReportFilters(String selectedReport) {
		resetFiltersDefaultValues();
		if(REPORTE_DE_VENTAS.equals(selectedReport)) {
			fromDateDateTime.setEnabled(true);
			toDateDateTime.setEnabled(true);
			documentTypeCombo.setEnabled(true);
			paymentMethodCombo.setEnabled(true);
			employeeCombo.setEnabled(true);
		}
		if(REPORTE_DE_VENTAS_DETALLADO.equals(selectedReport)) {
			fromDateDateTime.setEnabled(true);
			toDateDateTime.setEnabled(true);
			productCategoryCombo.setEnabled(true);
			productCombo.setEnabled(true);
		}
		if(REPORTE_DE_PRODUCTOS_POR_PROVEEDOR.equals(selectedReport)) {
			supplierCombo.setEnabled(true);
			productCategoryCombo.setEnabled(true);
			productCombo.setEnabled(true);
		}
		if(REPORTE_DE_ORDENES_DE_COMPRA.equals(selectedReport)) {
			fromDateDateTime.setEnabled(true);
			toDateDateTime.setEnabled(true);
			supplierCombo.setEnabled(true);
		}
		if(REPORTE_DE_ORDENES_DE_COMPRA_DETALLADO.equals(selectedReport)) {
			fromDateDateTime.setEnabled(true);
			toDateDateTime.setEnabled(true);
			productCategoryCombo.setEnabled(true);
			productCombo.setEnabled(true);
		}
	}
	
	private void resetDefaultValues() {
		reportsCombo.setText("");
		resetFiltersDefaultValues();
		cancelButton.setEnabled(false);
		generateReportButton.setEnabled(false);
	}
	
	private void resetFiltersDefaultValues() {
		fromDateDateTime.setEnabled(false);
		toDateDateTime.setEnabled(false);
		documentTypeCombo.setText("");
		documentTypeCombo.setEnabled(false);
		paymentMethodCombo.setText("");
		paymentMethodCombo.setEnabled(false);
		employeeCombo.setText("");
		employeeCombo.setEnabled(false);
		supplierCombo.setText("");
		supplierCombo.setEnabled(false);
		productCategoryCombo.setText("");
		productCategoryCombo.setEnabled(false);
		productCombo.setText("");
		productCombo.setEnabled(false);
		selectedDocumentType = null;
		selectedPaymentMethod = null;
		selectedProductCategory = null;
		selectedProduct = null;
		selectedEmployee = null;
		selectedSupplier = null;
		productCategoryComboViewer.setInput(productServices);
	}
	
	private Date getDateFor(DateTime dateTime) {
		Calendar instance = Calendar.getInstance();
		instance.set(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), 0, 0, 0);
		return instance.getTime();
	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ReportConfigurationView.this.fillContextMenu(manager);
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
