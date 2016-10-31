package com.bistro.sagg.reports.ui.views;

import java.util.Date;
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
import com.bistro.sagg.core.services.ReportServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.reports.ui.utils.DetailedPurchaseOrdersReportColumnIndex;
import com.bistro.sagg.reports.ui.utils.ReportsCommunicationConstants;
import com.bistro.sagg.reports.ui.viewers.DetailedPurchaseOrdersReportViewContentProvider;
import com.bistro.sagg.reports.ui.viewers.GenericReportsSorter;
import com.bistro.sagg.reports.ui.viewers.GenerigReportsLabelProvider;

public class DetailedPurchaseOrdersReportView extends ViewPart {

	public static final String ID = "com.bistro.sagg.reports.ui.views.DetailedPurchaseOrdersReportView"; //$NON-NLS-1$

	private TableViewer detailedPurchaseOrdersTableViewer;
	private Table detailedPurchaseOrdersTable;
	
	private ReportServices reportServices = (ReportServices) SaggServiceLocator.getInstance()
			.lookup(ReportServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;
	
	public DetailedPurchaseOrdersReportView() {
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
		
		detailedPurchaseOrdersTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		detailedPurchaseOrdersTableViewer.setLabelProvider(new GenerigReportsLabelProvider());
		detailedPurchaseOrdersTableViewer.setSorter(new GenericReportsSorter());
		
		detailedPurchaseOrdersTable = detailedPurchaseOrdersTableViewer.getTable();
		detailedPurchaseOrdersTable.setLinesVisible(true);
		detailedPurchaseOrdersTable.setHeaderVisible(true);
		
		TableColumn orderNumberColumn = new TableColumn(detailedPurchaseOrdersTable, SWT.NONE);
		orderNumberColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) detailedPurchaseOrdersTableViewer.getSorter()).doSort(DetailedPurchaseOrdersReportColumnIndex.ORDER_NUMBER_COLUMN_IDX);
				detailedPurchaseOrdersTableViewer.refresh();
			}
		});
		orderNumberColumn.setWidth(150);
		orderNumberColumn.setText("Nro. de Orden");
		
		TableColumn dateColumn = new TableColumn(detailedPurchaseOrdersTable, SWT.NONE);
		dateColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) detailedPurchaseOrdersTableViewer.getSorter()).doSort(DetailedPurchaseOrdersReportColumnIndex.DATE_COLUMN_IDX);
				detailedPurchaseOrdersTableViewer.refresh();
			}
		});
		dateColumn.setWidth(130);
		dateColumn.setText("Fecha");
		
		TableColumn productColumn = new TableColumn(detailedPurchaseOrdersTable, SWT.NONE);
		productColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) detailedPurchaseOrdersTableViewer.getSorter()).doSort(DetailedPurchaseOrdersReportColumnIndex.PRODUCT_COLUMN_IDX);
				detailedPurchaseOrdersTableViewer.refresh();
			}
		});
		productColumn.setWidth(300);
		productColumn.setText("Producto");
		
		TableColumn productCategoryColumn = new TableColumn(detailedPurchaseOrdersTable, SWT.NONE);
		productCategoryColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) detailedPurchaseOrdersTableViewer.getSorter()).doSort(DetailedPurchaseOrdersReportColumnIndex.PRODUCT_CATEGORY_COLUMN_IDX);
				detailedPurchaseOrdersTableViewer.refresh();
			}
		});
		productCategoryColumn.setWidth(300);
		productCategoryColumn.setText("Categor\u00EDa");
		
		TableColumn unitPriceColumn = new TableColumn(detailedPurchaseOrdersTable, SWT.NONE);
		unitPriceColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) detailedPurchaseOrdersTableViewer.getSorter()).doSort(DetailedPurchaseOrdersReportColumnIndex.UNIT_PRICE_COLUMN_IDX);
				detailedPurchaseOrdersTableViewer.refresh();
			}
		});
		unitPriceColumn.setWidth(130);
		unitPriceColumn.setText("Precio Unitario");
		
		TableColumn quantityColumn = new TableColumn(detailedPurchaseOrdersTable, SWT.NONE);
		quantityColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) detailedPurchaseOrdersTableViewer.getSorter()).doSort(DetailedPurchaseOrdersReportColumnIndex.QUANTITY_COLUMN_IDX);
				detailedPurchaseOrdersTableViewer.refresh();
			}
		});
		quantityColumn.setWidth(80);
		quantityColumn.setText("Cantidad");
		
		TableColumn totalAmountColumn = new TableColumn(detailedPurchaseOrdersTable, SWT.NONE);
		totalAmountColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) detailedPurchaseOrdersTableViewer.getSorter()).doSort(DetailedPurchaseOrdersReportColumnIndex.TOTAL_AMOUNT_COLUMN_IDX);
				detailedPurchaseOrdersTableViewer.refresh();
			}
		});
		totalAmountColumn.setWidth(130);
		totalAmountColumn.setText("Total");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}
	
	private void createGenerateReportViewEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				DetailedPurchaseOrdersReportViewContentProvider provider = new DetailedPurchaseOrdersReportViewContentProvider();
				provider.setFromDate((Date) event.getProperty(ReportsCommunicationConstants.FROM_DATE_DATA));
				provider.setToDate((Date) event.getProperty(ReportsCommunicationConstants.TO_DATE_DATA));
				provider.setProductCategory((ProductCategory) event.getProperty(ReportsCommunicationConstants.PRODUCT_CATEGORY_DATA));
				provider.setProduct((Product) event.getProperty(ReportsCommunicationConstants.PRODUCT_DATA));
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					detailedPurchaseOrdersTableViewer.setContentProvider(provider);
					detailedPurchaseOrdersTableViewer.setInput(reportServices);
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							detailedPurchaseOrdersTableViewer.setContentProvider(provider);
							detailedPurchaseOrdersTableViewer.setInput(reportServices);
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, ReportsCommunicationConstants.GENERATE_DTAILED_PURCHASE_ORDERS_REPORT_EVENT);
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
