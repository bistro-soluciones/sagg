package com.bistro.sagg.reports.ui.views;

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.services.ReportServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.reports.ui.utils.ReportsCommunicationConstants;
import com.bistro.sagg.reports.ui.utils.SuppliesBySupplierReportColumnIndex;
import com.bistro.sagg.reports.ui.viewers.GenericReportsSorter;
import com.bistro.sagg.reports.ui.viewers.GenerigReportsLabelProvider;
import com.bistro.sagg.reports.ui.viewers.SuppliesBySupplierContentProvider;

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

public class SuppliesBySupplierListView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.reports.ui.views.SuppliesBySupplierListView";

	private TableViewer suppliesBySupplierTableViewer;
	private Table suppliesBySupplierTable;
	
	private ReportServices reportServices = (ReportServices) SaggServiceLocator.getInstance()
			.lookup(ReportServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public SuppliesBySupplierListView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(SuppliesBySupplierListView.class).getBundleContext();
        ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
        this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		createGenerateReportViewEventHandler(parent);
		
		suppliesBySupplierTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		suppliesBySupplierTableViewer.setLabelProvider(new GenerigReportsLabelProvider());
		suppliesBySupplierTableViewer.setSorter(new GenericReportsSorter());
		
		suppliesBySupplierTable = suppliesBySupplierTableViewer.getTable();
		suppliesBySupplierTable.setLinesVisible(true);
		suppliesBySupplierTable.setHeaderVisible(true);
		
		TableColumn supplierColumn = new TableColumn(suppliesBySupplierTable, SWT.NONE);
		supplierColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) suppliesBySupplierTableViewer.getSorter()).doSort(SuppliesBySupplierReportColumnIndex.SUPPLIER_COLUMN_IDX);
				suppliesBySupplierTableViewer.refresh();
			}
		});
		supplierColumn.setWidth(250);
		supplierColumn.setText("Proveedor");
		
		TableColumn supplierIdColumn = new TableColumn(suppliesBySupplierTable, SWT.NONE);
		supplierIdColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) suppliesBySupplierTableViewer.getSorter()).doSort(SuppliesBySupplierReportColumnIndex.SUPPLIER_ID_COLUMN_IDX);
				suppliesBySupplierTableViewer.refresh();
			}
		});
		supplierIdColumn.setWidth(100);
		supplierIdColumn.setText("RUT");
		
		TableColumn productColumn = new TableColumn(suppliesBySupplierTable, SWT.NONE);
		productColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) suppliesBySupplierTableViewer.getSorter()).doSort(SuppliesBySupplierReportColumnIndex.PRODUCT_COLUMN_IDX);
				suppliesBySupplierTableViewer.refresh();
			}
		});
		productColumn.setWidth(194);
		productColumn.setText("Producto");
		
		TableColumn productCategoryColumn = new TableColumn(suppliesBySupplierTable, SWT.NONE);
		productCategoryColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) suppliesBySupplierTableViewer.getSorter()).doSort(SuppliesBySupplierReportColumnIndex.CATEGORY_COLUMN_IDX);
				suppliesBySupplierTableViewer.refresh();
			}
		});
		productCategoryColumn.setWidth(280);
		productCategoryColumn.setText("Categor\u00EDa de Producto");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}
	
	private void createGenerateReportViewEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				SuppliesBySupplierContentProvider provider = new SuppliesBySupplierContentProvider();
				provider.setSupplier((Supplier) event.getProperty(ReportsCommunicationConstants.SUPPLIER_DATA));
				provider.setProduct((Product) event.getProperty(ReportsCommunicationConstants.PRODUCT_DATA));
				provider.setProductCategory((ProductCategory) event.getProperty(ReportsCommunicationConstants.PRODUCT_CATEGORY_DATA));
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					suppliesBySupplierTableViewer.setContentProvider(provider);
					suppliesBySupplierTableViewer.setInput(reportServices);
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							suppliesBySupplierTableViewer.setContentProvider(provider);
							suppliesBySupplierTableViewer.setInput(reportServices);
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, ReportsCommunicationConstants.GENERATE_SUPPLIES_BY_SUPPLIER_REPORT_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}
	
//	private void createAddEmplouyeeEventHandler(Composite parent) {
//		EventHandler handler = new EventHandler() {
//			public void handleEvent(final Event event) {
//				if (parent.getDisplay().getThread() == Thread.currentThread()) {
//					employeesTableViewer.refresh();
//				} else {
//					parent.getDisplay().syncExec(new Runnable() {
//						public void run() {
//							employeesTableViewer.refresh();
//						}
//					});
//				}
//			}
//	    };
//	    Dictionary<String,String> properties = new Hashtable<String, String>();
//	    properties.put(EventConstants.EVENT_TOPIC, EmployeesCommunicationConstants.ADD_EMPLOYEE_EVENT);
//	    bundleContext.registerService(EventHandler.class, handler, properties);
//	}

	private void hookContextMenu() {
//		MenuManager menuMgr = new MenuManager("#PopupMenu");
//		menuMgr.setRemoveAllWhenShown(true);
//		menuMgr.addMenuListener(new IMenuListener() {
//			public void menuAboutToShow(IMenuManager manager) {
//				EmployeeListView.this.fillContextMenu(manager);
//			}
//		});
	}

	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalPullDown(bars.getMenuManager());
//		fillLocalToolBar(bars.getToolBarManager());
	}

//	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(openNewEmployeeDialogAction);
//		manager.add(new Separator());
//	}

//	private void fillContextMenu(IMenuManager manager) {
//		manager.add(openNewEmployeeDialogAction);
//		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//	}
	
//	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(openNewEmployeeDialogAction);
//	}

	private void makeActions() {
//		openNewEmployeeDialogAction = new OpenNewEmployeeDialogAction("Nuevo Empleado",PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//		openNewEmployeeDialogAction.setText("Nuevo Empleado");
//		openNewEmployeeDialogAction.setToolTipText("Nuevo Empleado");
//		openNewEmployeeDialogAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
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
