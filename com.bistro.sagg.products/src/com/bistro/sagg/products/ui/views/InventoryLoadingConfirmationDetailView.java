package com.bistro.sagg.products.ui.views;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.PurchaseOrder;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.billing.PurchaseBillingDocument;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.utils.ErrorMessageUtils;
import com.bistro.sagg.core.services.BillingServices;
import com.bistro.sagg.core.services.OrderServices;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.core.util.TransactionUtils;
import com.bistro.sagg.core.validation.processor.ListValidatorProcessor;
import com.bistro.sagg.core.validation.validator.EmptyOrNullValidator;
import com.bistro.sagg.core.validation.validator.SaggValidator;
import com.bistro.sagg.products.ui.utils.ProductsCommunicationConstants;
import com.bistro.sagg.products.ui.viewers.BillingDocumentTypeComboContentProvider;
import com.bistro.sagg.products.ui.viewers.BillingDocumentTypeComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.PaymentMethodComboContentProvider;
import com.bistro.sagg.products.ui.viewers.PaymentMethodComboLabelProvider;

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

public class InventoryLoadingConfirmationDetailView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.products.ui.views.InventoryLoadingConfirmationDetailView";
	
	private Text orderNumberText;
	private Text orderDateText;
	private Text receiverText;
	private Combo documentTypeCombo;
	private Combo paymentMethodCombo;
	private Text documentNumberText;
	private Text supplierText;
	private Button cancelTransactionButton;
	private Button confirmTransactionButton;
	private Label subtotalAmountTextLabel;
	private Label taxAmountTextLabel;
	private Label totalAmountTextLabel;

	private BillingServices billingServices = (BillingServices) SaggServiceLocator.getInstance()
			.lookup(BillingServices.class.getName());
	private OrderServices orderServices = (OrderServices) SaggServiceLocator.getInstance()
			.lookup(OrderServices.class.getName());
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	
	private PurchaseOrder order;
	private Supplier supplier;
	private Employee receiver;
	private BigDecimal subtotalAmount = BigDecimal.ZERO;
	private PaymentMethod selectedPaymentMethod;
	private DocumentType selectedDocumentType;
	
	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public InventoryLoadingConfirmationDetailView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(InventoryLoadingConfirmationDetailView.class).getBundleContext();
        ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
        this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
	    createConfirmSaleOrderEventHandler(parent);
	    
		Composite paymentDetailComposite = new Composite(parent, SWT.NONE);
		paymentDetailComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		paymentDetailComposite.setLayout(new GridLayout(2, false));
		
		Label orderNumberLabel = new Label(paymentDetailComposite, SWT.RIGHT);
		GridData gd_orderNumberLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_orderNumberLabel.widthHint = 145;
		orderNumberLabel.setLayoutData(gd_orderNumberLabel);
		orderNumberLabel.setText("N\u00FAmero de Orden *");
		
		orderNumberText = new Text(paymentDetailComposite, SWT.BORDER | SWT.RIGHT);
		orderNumberText.setEditable(false);
		orderNumberText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label documentTypeLabel = new Label(paymentDetailComposite, SWT.RIGHT);
		GridData gd_documentTypeLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_documentTypeLabel.widthHint = 145;
		documentTypeLabel.setLayoutData(gd_documentTypeLabel);
		documentTypeLabel.setText("Tipo de Documento *");
		
		ComboViewer documentTypeComboViewer = new ComboViewer(paymentDetailComposite, SWT.NONE);
		documentTypeComboViewer.setContentProvider(new BillingDocumentTypeComboContentProvider());
		documentTypeComboViewer.setLabelProvider(new BillingDocumentTypeComboLabelProvider());
		documentTypeComboViewer.setInput(billingServices);
		documentTypeCombo = documentTypeComboViewer.getCombo();
		documentTypeCombo.setEnabled(false);
		documentTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		documentTypeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedDocumentType = (DocumentType) ((StructuredSelection) event.getSelection()).getFirstElement();
				documentNumberText.setEnabled(true);
			}
		});
		
		Label documentNumberLabel = new Label(paymentDetailComposite, SWT.RIGHT);
		GridData gd_lblNewLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 145;
		documentNumberLabel.setLayoutData(gd_lblNewLabel);
		documentNumberLabel.setText("N\u00FAmero *");
		
		documentNumberText = new Text(paymentDetailComposite, SWT.BORDER | SWT.RIGHT);
		documentNumberText.setEnabled(false);
		documentNumberText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label orderDateLabel = new Label(paymentDetailComposite, SWT.RIGHT);
		GridData gd_orderDateLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_orderDateLabel.widthHint = 135;
		orderDateLabel.setLayoutData(gd_orderDateLabel);
		orderDateLabel.setText("Fecha *");
		
		orderDateText = new Text(paymentDetailComposite, SWT.BORDER | SWT.RIGHT);
		orderDateText.setEditable(false);
		orderDateText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label supplierLabel = new Label(paymentDetailComposite, SWT.RIGHT);
		GridData gd_supplierLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_supplierLabel.widthHint = 135;
		supplierLabel.setLayoutData(gd_supplierLabel);
		supplierLabel.setText("Proveedor *");
		
		supplierText = new Text(paymentDetailComposite, SWT.BORDER | SWT.RIGHT);
		supplierText.setEnabled(false);
		supplierText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label sellerLabel = new Label(paymentDetailComposite, SWT.RIGHT);
		GridData gd_sellerLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_sellerLabel.widthHint = 135;
		sellerLabel.setLayoutData(gd_sellerLabel);
		sellerLabel.setText("Recibe *");
		
		receiverText = new Text(paymentDetailComposite, SWT.BORDER | SWT.RIGHT);
		receiverText.setEditable(false);
		receiverText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label saleTypeLabel = new Label(paymentDetailComposite, SWT.NONE);
		saleTypeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		saleTypeLabel.setText("Tipo de Pago *");
		
		ComboViewer paymentTypeComboViewer = new ComboViewer(paymentDetailComposite, SWT.NONE);
		paymentTypeComboViewer.setContentProvider(new PaymentMethodComboContentProvider());
		paymentTypeComboViewer.setLabelProvider(new PaymentMethodComboLabelProvider());
		paymentTypeComboViewer.setInput(billingServices);
		paymentMethodCombo = paymentTypeComboViewer.getCombo();
		paymentMethodCombo.setEnabled(false);
		paymentMethodCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		paymentTypeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedPaymentMethod = (PaymentMethod) ((StructuredSelection) event.getSelection()).getFirstElement();
//				confirmTransactionButton.setEnabled(true);
			}
		});

		Composite composite = new Composite(parent, SWT.NONE);
		GridData gd_composite = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_composite.widthHint = 250;
		composite.setLayoutData(gd_composite);
		composite.setLayout(null);
		
		Label subtotalLabel = new Label(composite, SWT.RIGHT);
		subtotalLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		subtotalLabel.setBounds(-19, 78, 155, 20);
		subtotalLabel.setText("Subtotal");
		
		Label taxAmountLabel = new Label(composite, SWT.RIGHT);
		taxAmountLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		taxAmountLabel.setBounds(-19, 104, 155, 20);
		taxAmountLabel.setText("IVA 19%");
		
		subtotalAmountTextLabel = new Label(composite, SWT.RIGHT);
		subtotalAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		subtotalAmountTextLabel.setBounds(140, 78, 100, 20);
		subtotalAmountTextLabel.setText("0");
		
		taxAmountTextLabel= new Label(composite, SWT.RIGHT);
		taxAmountTextLabel.setText("0");
		taxAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		taxAmountTextLabel.setBounds(140, 104, 100, 20);
		
		totalAmountTextLabel= new Label(composite, SWT.RIGHT);
		totalAmountTextLabel.setText("0");
		totalAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		totalAmountTextLabel.setBounds(140, 130, 100, 30);
		
		Label totalLabel = new Label(composite, SWT.RIGHT);
		totalLabel.setText("Total");
		totalLabel.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		totalLabel.setBounds(-19, 130, 155, 30);
		
		Composite composite_3 = new Composite(parent, SWT.NONE);
		GridLayout gl_composite_3 = new GridLayout(2, false);
		gl_composite_3.marginHeight = 0;
		gl_composite_3.marginWidth = 0;
		composite_3.setLayout(gl_composite_3);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		cancelTransactionButton = new Button(composite_3, SWT.NONE);
		cancelTransactionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				orderServices.cancelPurchaseOrder(order);
				resetDefaultValues();
				Event event = new Event(ProductsCommunicationConstants.RESET_PURCHASE_ORDER_EVENT, new HashMap<String, Object>());
				eventAdmin.sendEvent(event);
			}
		});
		cancelTransactionButton.setEnabled(false);
		cancelTransactionButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cancelTransactionButton.setText("Cancelar Carga");
		cancelTransactionButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		
		confirmTransactionButton = new Button(composite_3, SWT.NONE);
		confirmTransactionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (validateFields(parent.getShell())) {
					PurchaseBillingDocument document = billingServices.createBillingDocument(order, selectedDocumentType,
							documentNumberText.getText(), selectedPaymentMethod);
					orderServices.receivePurchaseOrder(order, document);
					productServices.increaseProductStock(document.getItems());
					resetDefaultValues();
					Event event = new Event(ProductsCommunicationConstants.RESET_PURCHASE_ORDER_EVENT, new HashMap<String, Object>());
					eventAdmin.sendEvent(event);
				}
			}
		});
		confirmTransactionButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		confirmTransactionButton.setEnabled(false);
		confirmTransactionButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		confirmTransactionButton.setText("Confirmar Carga");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private boolean validateFields(Shell shell) {
		ListValidatorProcessor processor = setupProductValidatorProcessor();
		boolean result = processor.processValidation();
		if (!result) {
			MessageDialog.openError(shell, "Error", processor.getErrorMessage());
		}
		return result;
	}

	private ListValidatorProcessor setupProductValidatorProcessor() {
		java.util.List<SaggValidator> validators = new ArrayList<>();
		validators.add(
				new EmptyOrNullValidator(orderNumberText.getText(), ErrorMessageUtils.createMandatoryFieldErrorMsg("N\u00FAmero de Orden")));
		validators.add(
				new EmptyOrNullValidator(selectedDocumentType, ErrorMessageUtils.createMandatoryFieldErrorMsg("Tipo de Documento")));
		validators.add(
				new EmptyOrNullValidator(documentNumberText.getText(), ErrorMessageUtils.createMandatoryFieldErrorMsg("N\u00FAmero de Documento")));
		validators.add(
				new EmptyOrNullValidator(orderDateText.getText(), ErrorMessageUtils.createMandatoryFieldErrorMsg("Fecha")));
		validators.add(
				new EmptyOrNullValidator(supplierText.getText(), ErrorMessageUtils.createMandatoryFieldErrorMsg("Proveedor")));
		validators.add(
				new EmptyOrNullValidator(receiverText.getText(), ErrorMessageUtils.createMandatoryFieldErrorMsg("Recibe")));
		validators.add(
				new EmptyOrNullValidator(selectedPaymentMethod, ErrorMessageUtils.createMandatoryFieldErrorMsg("Tipo de Pago")));
		ListValidatorProcessor processor = new ListValidatorProcessor(validators);
		return processor;
	}

	private void createConfirmSaleOrderEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				order = (PurchaseOrder) event.getProperty(ProductsCommunicationConstants.PURCHASE_ORDER_DATA);
				supplier = (Supplier) event.getProperty(ProductsCommunicationConstants.ADD_PRODUCT_SUPPLIER_DATA);
				SimpleDateFormat formatter = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.DATE_FORMATTER);
				receiver = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.ACTIVE_USER);
				subtotalAmount = TransactionUtils.addItemAmounts(order.getItems());
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					orderNumberText.setText(order.getOrderNumber());
					documentNumberText.setText(order.getOrderNumber());
					orderDateText.setText(formatter.format(order.getDate()));
					supplierText.setText(supplier.getBusinessName());
					receiverText.setText(receiver.getFullName());
					subtotalAmountTextLabel.setText(subtotalAmount.toString());
					taxAmountTextLabel.setText(TransactionUtils.calculateWithTaxes(subtotalAmount).toString());
					totalAmountTextLabel.setText(subtotalAmount.toString());
					documentTypeCombo.setEnabled(true);
					paymentMethodCombo.setEnabled(true);
					cancelTransactionButton.setEnabled(true);
					confirmTransactionButton.setEnabled(true);
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							orderNumberText.setText(order.getOrderNumber());
							documentNumberText.setText(order.getOrderNumber());
							orderDateText.setText(formatter.format(order.getDate()));
							supplierText.setText(supplier.getBusinessName());
							receiverText.setText(receiver.getFullName());
							subtotalAmountTextLabel.setText(subtotalAmount.toString());
							taxAmountTextLabel.setText(TransactionUtils.calculateWithTaxes(subtotalAmount).toString());
							totalAmountTextLabel.setText(subtotalAmount.toString());
							documentTypeCombo.setEnabled(true);
							paymentMethodCombo.setEnabled(true);
							cancelTransactionButton.setEnabled(true);
							confirmTransactionButton.setEnabled(true);
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, ProductsCommunicationConstants.CONFIRM_PURCHASE_ORDER_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}

	private void resetDefaultValues() {
		orderNumberText.setText("");
		documentTypeCombo.setText("");
		documentTypeCombo.setEnabled(false);
		documentNumberText.setText("");
		documentNumberText.setEnabled(false);
		orderDateText.setText("");
		supplierText.setText("");
		receiverText.setText("");
		paymentMethodCombo.setText("");
		paymentMethodCombo.setEnabled(false);
		
		subtotalAmountTextLabel.setText("0");
		taxAmountTextLabel.setText("0");
		totalAmountTextLabel.setText("0");
		
		cancelTransactionButton.setEnabled(false);
		confirmTransactionButton.setEnabled(false);

		order = null;
		supplier = null;
		selectedPaymentMethod = null;
		subtotalAmount = BigDecimal.ZERO;
		selectedDocumentType = null;
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				InventoryLoadingConfirmationDetailView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(openNewEmployeeDialogAction);
//		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
//		manager.add(openNewEmployeeDialogAction);
		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(openNewEmployeeDialogAction);
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
