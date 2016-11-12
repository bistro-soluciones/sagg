package com.bistro.sagg.sales.ui.views;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
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

import com.bistro.sagg.core.factory.OrderItemFactory;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.SaleOrder;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.products.SalableProduct;
import com.bistro.sagg.core.services.OrderServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.sales.ui.utils.SalesCommunicationConstants;
import com.bistro.sagg.sales.ui.viewers.OrderItemTableLabelProvider;

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

public class SalesDetailView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.sales.ui.views.SalesDetailView";
	
	private Table productsTable;
	private Spinner updateItemCountSpinner;
	private Button updateItemButton;
	private Button removeItemButton;
	private Button clearItemListButton;
	private Button confirmItemListButton;
	
//	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
//			.lookup(ProductServices.class.getName());
	private OrderServices orderServices = (OrderServices) SaggServiceLocator.getInstance()
			.lookup(OrderServices.class.getName());
	
	private SaleOrderItem selectedItem;
	private int selectedItemQuantity;
	
	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public SalesDetailView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(SalesDetailView.class).getBundleContext();
        ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
        this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		createResetViewEventHandler(parent);
		
		TableViewer productsTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
//		productsTableViewer.setContentProvider(new MarketableProductListContentProvider());
		productsTableViewer.setLabelProvider(new OrderItemTableLabelProvider());
//		productsTableViewer.setSorter(new MarketableProductListSorter());
		
		productsTable = productsTableViewer.getTable();
		productsTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Table source = (Table) e.getSource();
				if(source.getSelectionCount() > 0) {
					removeItemButton.setEnabled(true);
					updateItemCountSpinner.setEnabled(true);
					selectedItem = (SaleOrderItem) ((StructuredSelection)productsTableViewer.getSelection()).getFirstElement();
					updateItemCountSpinner.setSelection(selectedItem.getQuantity());
				} else {
					removeItemButton.setEnabled(false);
					updateItemCountSpinner.setEnabled(false);
					selectedItem = null;
				}
			}
		});
		productsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		productsTable.setHeaderVisible(true);
		productsTable.setLinesVisible(true);
		
		TableColumn productColumn = new TableColumn(productsTable, SWT.NONE);
		productColumn.setWidth(500);
		productColumn.setText("Producto");
		
		TableColumn quantityColumn = new TableColumn(productsTable, SWT.NONE);
		quantityColumn.setWidth(100);
		quantityColumn.setText("Cantidad");
		
		TableColumn amountColumn = new TableColumn(productsTable, SWT.NONE);
		amountColumn.setWidth(100);
		amountColumn.setText("Monto");
		
	    createAddProductEventHandler(parent, productsTableViewer);
	    
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(5, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		updateItemCountSpinner = new Spinner(composite, SWT.BORDER);
		updateItemCountSpinner.setEnabled(false);
		updateItemCountSpinner.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Spinner source = (Spinner) e.getSource();
				if(source.getSelection() > 0) {
					updateItemButton.setEnabled(true);
				}
				selectedItemQuantity = source.getSelection();
			}
		});
		
		updateItemButton = new Button(composite, SWT.NONE);
		updateItemButton.setEnabled(false);
		GridData gd_updateItemButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_updateItemButton.widthHint = 100;
		updateItemButton.setLayoutData(gd_updateItemButton);
		updateItemButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		updateItemButton.setText("Modificar");
		updateItemButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(selectedItemQuantity > 0) {
					selectedItem.setQuantity(selectedItemQuantity);
					selectedItem.recalculateAmount();
					productsTableViewer.refresh(selectedItem, true);
				} else {
					productsTableViewer.remove(selectedItem);
					updateItemButton.setEnabled(false);
					updateItemCountSpinner.setSelection(0);
					updateItemCountSpinner.setEnabled(false);
					removeItemButton.setEnabled(false);
					if(productsTable.getItemCount() == 0) {
						clearItemListButton.setEnabled(false);
						confirmItemListButton.setEnabled(false);
					}
					selectedItem = null;
				}
			}
		});
		
		removeItemButton = new Button(composite, SWT.NONE);
		removeItemButton.setEnabled(false);
		GridData gd_removeItemButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_removeItemButton.widthHint = 100;
		removeItemButton.setLayoutData(gd_removeItemButton);
		removeItemButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		removeItemButton.setText("Eliminar");
		removeItemButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				productsTableViewer.remove(selectedItem);
				updateItemButton.setEnabled(false);
				updateItemCountSpinner.setSelection(0);
				updateItemCountSpinner.setEnabled(false);
				removeItemButton.setEnabled(false);
				if(productsTable.getItemCount() == 0) {
					clearItemListButton.setEnabled(false);
					confirmItemListButton.setEnabled(false);
				}
				selectedItem = null;
			}
		});
		
		clearItemListButton = new Button(composite, SWT.NONE);
		clearItemListButton.setEnabled(false);
		GridData gd_clearItemListButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_clearItemListButton.widthHint = 100;
		clearItemListButton.setLayoutData(gd_clearItemListButton);
		clearItemListButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		clearItemListButton.setText("Limpiar");
		clearItemListButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				productsTable.removeAll();
				updateItemButton.setEnabled(false);
				updateItemCountSpinner.setSelection(0);
				updateItemCountSpinner.setEnabled(false);
				removeItemButton.setEnabled(false);
				clearItemListButton.setEnabled(false);
				confirmItemListButton.setEnabled(false);
				selectedItem = null;
			}
		});
		
		confirmItemListButton = new Button(composite, SWT.NONE);
		confirmItemListButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
				Employee seller = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.ACTIVE_USER);
				List<SaleOrderItem> items = new ArrayList<SaleOrderItem>();
				for (TableItem item : productsTable.getItems()) {
					items.add((SaleOrderItem) item.getData());
				}
				SaleOrder order = orderServices.createSaleOrder(branch, seller, items);
				
				clearItemListButton.setEnabled(false);
				confirmItemListButton.setEnabled(false);
				
				Map<String,Object> properties = new HashMap<String, Object>();
		        properties.put(SalesCommunicationConstants.SALE_ORDER_DATA, order);
				Event event = new Event(SalesCommunicationConstants.CONFIRM_SALE_ORDER_EVENT, properties);
				eventAdmin.sendEvent(event);
			}
		});
		confirmItemListButton.setEnabled(false);
		GridData gd_confirmItemListButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_confirmItemListButton.widthHint = 100;
		confirmItemListButton.setLayoutData(gd_confirmItemListButton);
		confirmItemListButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		confirmItemListButton.setText("Confirmar");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void createResetViewEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					resetDefaultValues();
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							resetDefaultValues();
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, SalesCommunicationConstants.RESET_SALE_ORDER_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}

	protected void resetDefaultValues() {
		productsTable.removeAll();;
//		updateItemCountSpinner.setSelection(0);
//		updateItemCountSpinner.setEnabled(false);
//		updateItemButton.setEnabled(false);
//		removeItemButton.setEnabled(false);
//		clearItemListButton.setEnabled(false);
//		confirmItemListButton.setEnabled(false);
		selectedItem = null;
		selectedItemQuantity = 0;
	}
	
	private void createAddProductEventHandler(Composite parent, TableViewer productsTableViewer) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				SaleOrderItem item = OrderItemFactory.createSaleOrderItem(
						(SalableProduct) event.getProperty(SalesCommunicationConstants.ADD_PRODUCT_DATA),
						(int) event.getProperty(SalesCommunicationConstants.ADD_PRODUCT_QUANTITY_DATA));
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					productsTableViewer.add(item);
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							productsTableViewer.add(item);
						}
					});
				}
				clearItemListButton.setEnabled(true);
				confirmItemListButton.setEnabled(true);
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, SalesCommunicationConstants.ADD_PRODUCT_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				SalesDetailView.this.fillContextMenu(manager);
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
