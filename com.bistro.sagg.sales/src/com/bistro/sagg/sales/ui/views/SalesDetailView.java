package com.bistro.sagg.sales.ui.views;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import com.bistro.sagg.core.factory.OrderItemFactory;
import com.bistro.sagg.core.model.order.SaleOrderItem;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.sales.ui.utils.SalesCommunicationConstants;
import com.bistro.sagg.sales.ui.viewers.OrderItemTableLabelProvider;
import org.eclipse.swt.widgets.TableColumn;

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


	public SalesDetailView() {
		super();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		TableViewer productsTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
//		productsTableViewer.setContentProvider(new MarketableProductListContentProvider());
		productsTableViewer.setLabelProvider(new OrderItemTableLabelProvider());
//		productsTableViewer.setSorter(new MarketableProductListSorter());
		
		productsTable = productsTableViewer.getTable();
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
		
		BundleContext ctx = FrameworkUtil.getBundle(SalesDetailView.class).getBundleContext();
	    EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				SaleOrderItem item = OrderItemFactory.createSaleOrderItem(
						(Product) event.getProperty(SalesCommunicationConstants.ADD_PRODUCT_DATA),
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
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, SalesCommunicationConstants.ADD_PRODUCT_EVENT);
	    ctx.registerService(EventHandler.class, handler, properties);
	    
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		Spinner spinner = new Spinner(composite, SWT.BORDER);
		
		Button btnModificar = new Button(composite, SWT.NONE);
		GridData gd_btnModificar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnModificar.widthHint = 100;
		btnModificar.setLayoutData(gd_btnModificar);
		btnModificar.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		btnModificar.setText("Modificar");
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.widthHint = 100;
		btnNewButton.setLayoutData(gd_btnNewButton);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		btnNewButton.setText("Eliminar");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
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
