package com.bistro.sagg.sales.ui.views;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
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
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.billing.SaleBillingDocument;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.services.BillingServices;
import com.bistro.sagg.core.services.OrderServices;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.core.util.TransactionUtils;
import com.bistro.sagg.sales.ui.utils.SalesCommunicationConstants;
import com.bistro.sagg.sales.ui.viewers.BillingDocumentTypeComboContentProvider;
import com.bistro.sagg.sales.ui.viewers.BillingDocumentTypeComboLabelProvider;
import com.bistro.sagg.sales.ui.viewers.PaymentMethodComboContentProvider;
import com.bistro.sagg.sales.ui.viewers.PaymentMethodComboLabelProvider;

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

public class SalesConfirmationDetailView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.sales.ui.views.SalesConfirmationDetailView";
	
	private Text orderNumberText;
	private Text orderDateText;
	private Text sellerText;
	private Text couponNumberText;
	private Text cashAmountText;
	private Combo documentTypeCombo;
	private Combo couponCombo;
//	private Combo cardTypeCombo;
	private Combo saleTypeCombo;
	private Button calculateCashReturnAmountButton;
	private Button cancelTransactionButton;
	private Button confirmTransactionButton;
	private Label subtotalAmountTextLabel;
	private Label discountsAmountTextLabel;
	private Label totalDiscountsAmountTextLabel;
	private Label promotionsAmountTextLabel;
	private Label totalAmountTextLabel;
	private Label cashAmountTextLabel;
	private Label cashReturnAmountTextLabel;

	private BillingServices billingServices = (BillingServices) SaggServiceLocator.getInstance()
			.lookup(BillingServices.class.getName());
	private OrderServices orderServices = (OrderServices) SaggServiceLocator.getInstance()
			.lookup(OrderServices.class.getName());
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	
	private SaleOrder order;
	private BigDecimal subtotalAmount = BigDecimal.ZERO;
	private PaymentMethod selectedPaymentMethod;
	private DocumentType selectedDocumentType;
	
	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public SalesConfirmationDetailView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(SalesConfirmationDetailView.class).getBundleContext();
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
		
		Label orderNumberLabel = new Label(paymentDetailComposite, SWT.NONE);
		orderNumberLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		orderNumberLabel.setText("N\u00FAmero");
		
		orderNumberText = new Text(paymentDetailComposite, SWT.BORDER | SWT.RIGHT);
		orderNumberText.setEditable(false);
		orderNumberText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label documentTypeLabel = new Label(paymentDetailComposite, SWT.NONE);
		documentTypeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		documentTypeLabel.setText("Tipo de Documento");
		
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
			}
		});
		
		Label orderDateLabel = new Label(paymentDetailComposite, SWT.NONE);
		orderDateLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		orderDateLabel.setText("Fecha");
		
		orderDateText = new Text(paymentDetailComposite, SWT.BORDER | SWT.RIGHT);
		orderDateText.setEditable(false);
		orderDateText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label sellerLabel = new Label(paymentDetailComposite, SWT.NONE);
		sellerLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		sellerLabel.setText("Vendedor");
		
		sellerText = new Text(paymentDetailComposite, SWT.BORDER | SWT.RIGHT);
		sellerText.setEditable(false);
		sellerText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
//		ComboViewer sellerComboViewer = new ComboViewer(paymentDetailComposite, SWT.NONE);
//		sellerComboViewer.setContentProvider(new EmployeeComboContentProvider());
//		sellerComboViewer.setLabelProvider(new EmployeeComboLabelProvider());
//		sellerComboViewer.setInput(employeeServices);
//		Combo sellerCombo = sellerComboViewer.getCombo();
//		sellerCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label couponLabel = new Label(paymentDetailComposite, SWT.NONE);
		couponLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		couponLabel.setText("Cup\u00F3n de Descuento");
		
		couponCombo = new Combo(paymentDetailComposite, SWT.NONE);
		couponCombo.setEnabled(false);
		couponCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label couponNumberLabel = new Label(paymentDetailComposite, SWT.NONE);
		couponNumberLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		couponNumberLabel.setText("N\u00FAmero de Cup\u00F3n");
		
		Composite composite_2 = new Composite(paymentDetailComposite, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);
		
		couponNumberText = new Text(composite_2, SWT.BORDER | SWT.RIGHT);
		couponNumberText.setEditable(false);
		couponNumberText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button applyCouponButton = new Button(composite_2, SWT.NONE);
		applyCouponButton.setEnabled(false);
		applyCouponButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		applyCouponButton.setText("Aplicar");
		
		Label saleTypeLabel = new Label(paymentDetailComposite, SWT.NONE);
		saleTypeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		saleTypeLabel.setText("Tipo de Venta");
		
		ComboViewer saleTypeComboViewer = new ComboViewer(paymentDetailComposite, SWT.NONE);
		saleTypeComboViewer.setContentProvider(new PaymentMethodComboContentProvider());
		saleTypeComboViewer.setLabelProvider(new PaymentMethodComboLabelProvider());
		saleTypeComboViewer.setInput(billingServices);
		saleTypeCombo = saleTypeComboViewer.getCombo();
		saleTypeCombo.setEnabled(false);
		saleTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		saleTypeComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedPaymentMethod = (PaymentMethod) ((StructuredSelection) event.getSelection()).getFirstElement();
				if(selectedPaymentMethod.isCashPayment()) {
					cashAmountText.setEditable(true);
					calculateCashReturnAmountButton.setEnabled(true);
//					cardTypeCombo.setEnabled(false);
				}
				if(selectedPaymentMethod.isCreditCardPayment() || selectedPaymentMethod.isDebitCardPayment()) {
					cashAmountText.setEditable(false);
					calculateCashReturnAmountButton.setEnabled(false);
//					cardTypeCombo.setEnabled(true);
				}
			}
		});
		
//		Label cardTypeLabel = new Label(paymentDetailComposite, SWT.NONE);
//		cardTypeLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
//		cardTypeLabel.setText("Tarjeta");
//		
//		cardTypeCombo = new Combo(paymentDetailComposite, SWT.NONE);
//		cardTypeCombo.setEnabled(false);
//		cardTypeCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label cashAmountLabel = new Label(paymentDetailComposite, SWT.NONE);
		cashAmountLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		cashAmountLabel.setText("Efectivo");
		
		Composite composite_1 = new Composite(paymentDetailComposite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_1 = new GridLayout(2, false);
		gl_composite_1.marginHeight = 0;
		gl_composite_1.marginWidth = 0;
		composite_1.setLayout(gl_composite_1);
		
		cashAmountText = new Text(composite_1, SWT.BORDER | SWT.RIGHT);
		cashAmountText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				cashAmountTextLabel.setText(cashAmountText.getText());
			}
		});
		cashAmountText.setEditable(false);
		cashAmountText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		calculateCashReturnAmountButton = new Button(composite_1, SWT.NONE);
		calculateCashReturnAmountButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				BigDecimal cashAmount = new BigDecimal(cashAmountText.getText());
				BigDecimal cashReturnAmount = cashAmount.subtract(subtotalAmount);
				cashReturnAmountTextLabel.setText(cashReturnAmount.toString());
			}
		});
		calculateCashReturnAmountButton.setEnabled(false);
		calculateCashReturnAmountButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		calculateCashReturnAmountButton.setText("Calcular Vuelto");
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridData gd_composite = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_composite.widthHint = 250;
		composite.setLayoutData(gd_composite);
		composite.setLayout(null);
		
		Label subtotalLabel = new Label(composite, SWT.RIGHT);
		subtotalLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		subtotalLabel.setBounds(-19, 0, 155, 20);
		subtotalLabel.setText("Subtotal");
		
		Label promotionsLabel = new Label(composite, SWT.RIGHT);
		promotionsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		promotionsLabel.setBounds(-19, 26, 155, 20);
		promotionsLabel.setText("Promociones");
		
		Label totalDiscountsLabel = new Label(composite, SWT.RIGHT);
		totalDiscountsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		totalDiscountsLabel.setBounds(-19, 78, 155, 20);
		totalDiscountsLabel.setText("Total Descuentos");
		
		subtotalAmountTextLabel = new Label(composite, SWT.RIGHT);
		subtotalAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		subtotalAmountTextLabel.setBounds(140, 0, 100, 20);
		subtotalAmountTextLabel.setText("0");
		
		promotionsAmountTextLabel= new Label(composite, SWT.RIGHT);
		promotionsAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		promotionsAmountTextLabel.setText("0");
		promotionsAmountTextLabel.setBounds(140, 26, 100, 20);
		
		discountsAmountTextLabel = new Label(composite, SWT.RIGHT);
		discountsAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		discountsAmountTextLabel.setText("0");
		discountsAmountTextLabel.setBounds(140, 52, 100, 20);
		
		Label discountsLabel = new Label(composite, SWT.RIGHT);
		discountsLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		discountsLabel.setBounds(-19, 52, 155, 20);
		discountsLabel.setText("Descuentos");
		
		totalDiscountsAmountTextLabel= new Label(composite, SWT.RIGHT);
		totalDiscountsAmountTextLabel.setText("0");
		totalDiscountsAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		totalDiscountsAmountTextLabel.setBounds(140, 78, 100, 20);
		
		totalAmountTextLabel= new Label(composite, SWT.RIGHT);
		totalAmountTextLabel.setText("0");
		totalAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		totalAmountTextLabel.setBounds(140, 104, 100, 30);
		
		Label cashLabel = new Label(composite, SWT.RIGHT);
		cashLabel.setText("Efectivo");
		cashLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		cashLabel.setBounds(-19, 134, 155, 30);
		
		Label cashReturnLabel = new Label(composite, SWT.RIGHT);
		cashReturnLabel.setText("Vuelto");
		cashReturnLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		cashReturnLabel.setBounds(-19, 162, 155, 30);
		
		Label totalLabel = new Label(composite, SWT.RIGHT);
		totalLabel.setText("Total");
		totalLabel.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		totalLabel.setBounds(-19, 104, 155, 30);
		
		cashAmountTextLabel= new Label(composite, SWT.RIGHT);
		cashAmountTextLabel.setText("0");
		cashAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		cashAmountTextLabel.setBounds(140, 134, 100, 30);
		
		cashReturnAmountTextLabel = new Label(composite, SWT.RIGHT);
		cashReturnAmountTextLabel.setText("0");
		cashReturnAmountTextLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		cashReturnAmountTextLabel.setBounds(140, 162, 100, 30);
		
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
				orderServices.cancelSaleOrder(order);
				resetDefaultValues();
				Event event = new Event(SalesCommunicationConstants.RESET_SALE_ORDER_EVENT, new HashMap<String, Object>());
				eventAdmin.sendEvent(event);
			}
		});
		cancelTransactionButton.setEnabled(false);
		cancelTransactionButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cancelTransactionButton.setText("Cancelar Venta");
		cancelTransactionButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		
		confirmTransactionButton = new Button(composite_3, SWT.NONE);
		confirmTransactionButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				SaleBillingDocument document = billingServices.createBillingDocument(order, selectedDocumentType, selectedPaymentMethod);
				orderServices.deliverSaleOrder(order, document);
				for (SaleOrderItem item : order.getItems()) {
					productServices.decreaseProductStock(item.getSalableProduct(), item.getQuantity());
					orderServices.decreasePurchasedItemStock(item.getSalableProduct(), item.getQuantity());
					// TODO track de ganancias netas
				}
				resetDefaultValues();
				Event event = new Event(SalesCommunicationConstants.RESET_SALE_ORDER_EVENT, new HashMap<String, Object>());
				eventAdmin.sendEvent(event);
			}
		});
		confirmTransactionButton.setEnabled(false);
		confirmTransactionButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		confirmTransactionButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		confirmTransactionButton.setText("Confirmar Venta");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void createConfirmSaleOrderEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				order = (SaleOrder) event.getProperty(SalesCommunicationConstants.SALE_ORDER_DATA);
				SimpleDateFormat formatter = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.DATE_FORMATTER);
				Employee seller = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.ACTIVE_USER);
				subtotalAmount = TransactionUtils.addItemAmounts(order.getItems());
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					orderNumberText.setText(order.getOrderNumber());
					orderDateText.setText(formatter.format(order.getDate()));
					sellerText.setText(seller.getFullName());
					subtotalAmountTextLabel.setText(subtotalAmount.toString());
					totalAmountTextLabel.setText(subtotalAmount.toString());
					documentTypeCombo.setEnabled(true);
					saleTypeCombo.setEnabled(true);
					cancelTransactionButton.setEnabled(true);
					confirmTransactionButton.setEnabled(true);
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							orderNumberText.setText(order.getOrderNumber());
							orderDateText.setText(formatter.format(order.getDate()));
							sellerText.setText(seller.getFullName());
							subtotalAmountTextLabel.setText(subtotalAmount.toString());
							totalAmountTextLabel.setText(subtotalAmount.toString());
							documentTypeCombo.setEnabled(true);
							saleTypeCombo.setEnabled(true);
							cancelTransactionButton.setEnabled(true);
							confirmTransactionButton.setEnabled(true);
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, SalesCommunicationConstants.CONFIRM_SALE_ORDER_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}

	private void resetDefaultValues() {
		orderNumberText.setText("");
		documentTypeCombo.setText("");
		documentTypeCombo.setEnabled(false);
		orderDateText.setText("");
		sellerText.setText("");
		couponCombo.redraw();
		couponNumberText.setText("");
		saleTypeCombo.setText("");
		saleTypeCombo.setEnabled(false);
		cashAmountText.setText("");
		cashAmountText.setEditable(false);
		calculateCashReturnAmountButton.setEnabled(false);
		
		subtotalAmountTextLabel.setText("0");
		promotionsAmountTextLabel.setText("0");
		discountsAmountTextLabel.setText("0");
		totalDiscountsAmountTextLabel.setText("0");
		totalAmountTextLabel.setText("0");
		cashAmountTextLabel.setText("0");
		cashReturnAmountTextLabel.setText("0");
		
		cancelTransactionButton.setEnabled(false);
		confirmTransactionButton.setEnabled(false);

		order = null;
		selectedPaymentMethod = null;
		subtotalAmount = BigDecimal.ZERO;
		selectedDocumentType = null;
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				SalesConfirmationDetailView.this.fillContextMenu(manager);
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
