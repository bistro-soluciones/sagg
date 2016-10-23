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

import com.bistro.sagg.core.model.company.employees.Employee;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.payment.PaymentMethod;
import com.bistro.sagg.core.services.ReportServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.reports.ui.utils.ReportsCommunicationConstants;
import com.bistro.sagg.reports.ui.utils.SalesReportColumnIndex;
import com.bistro.sagg.reports.ui.viewers.GenericReportsSorter;
import com.bistro.sagg.reports.ui.viewers.GenerigReportsLabelProvider;
import com.bistro.sagg.reports.ui.viewers.SalesReportViewContentProvider;

public class SalesReportView extends ViewPart {

	public static final String ID = "com.bistro.sagg.reports.ui.views.SalesReportView"; //$NON-NLS-1$

	private TableViewer salesTableViewer;
	private Table salesTable;
	
	private ReportServices reportServices = (ReportServices) SaggServiceLocator.getInstance()
			.lookup(ReportServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;
	
	public SalesReportView() {
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
		
		salesTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		salesTableViewer.setLabelProvider(new GenerigReportsLabelProvider());
		salesTableViewer.setSorter(new GenericReportsSorter());
		
		salesTable = salesTableViewer.getTable();
		salesTable.setLinesVisible(true);
		salesTable.setHeaderVisible(true);
		
		TableColumn documentNumberColumn = new TableColumn(salesTable, SWT.NONE);
		documentNumberColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) salesTableViewer.getSorter()).doSort(SalesReportColumnIndex.DOCUMENT_NUMBER_COLUMN_IDX);
				salesTableViewer.refresh();
			}
		});
		documentNumberColumn.setWidth(130);
		documentNumberColumn.setText("Nro. de Venta");
		
		TableColumn documentTypeColumn = new TableColumn(salesTable, SWT.NONE);
		documentTypeColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) salesTableViewer.getSorter()).doSort(SalesReportColumnIndex.DOCUMENT_TYPE_COLUMN_IDX);
				salesTableViewer.refresh();
			}
		});
		documentTypeColumn.setWidth(130);
		documentTypeColumn.setText("Documento");
		
		TableColumn paymentMethodColumn = new TableColumn(salesTable, SWT.NONE);
		paymentMethodColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) salesTableViewer.getSorter()).doSort(SalesReportColumnIndex.PAYMENT_METHOD_COLUMN_IDX);
				salesTableViewer.refresh();
			}
		});
		paymentMethodColumn.setWidth(130);
		paymentMethodColumn.setText("Forma de Pago");
		
		TableColumn dateColumn = new TableColumn(salesTable, SWT.NONE);
		dateColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) salesTableViewer.getSorter()).doSort(SalesReportColumnIndex.DATE_COLUMN_IDX);
				salesTableViewer.refresh();
			}
		});
		dateColumn.setWidth(130);
		dateColumn.setText("Fecha");
		
		TableColumn employeeColumn = new TableColumn(salesTable, SWT.NONE);
		employeeColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) salesTableViewer.getSorter()).doSort(SalesReportColumnIndex.EMPLOYEE_COLUMN_IDX);
				salesTableViewer.refresh();
			}
		});
		employeeColumn.setWidth(340);
		employeeColumn.setText("Vendedor");
		
		TableColumn orderStateColumn = new TableColumn(salesTable, SWT.NONE);
		orderStateColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) salesTableViewer.getSorter()).doSort(SalesReportColumnIndex.ORDER_STATE_COLUMN_IDX);
				salesTableViewer.refresh();
			}
		});
		orderStateColumn.setWidth(130);
		orderStateColumn.setText("Estado");
		
		TableColumn totalAmount = new TableColumn(salesTable, SWT.NONE);
		totalAmount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((GenericReportsSorter) salesTableViewer.getSorter()).doSort(SalesReportColumnIndex.TOTAL_AMOUNT_COLUMN_IDX);
				salesTableViewer.refresh();
			}
		});
		totalAmount.setWidth(130);
		totalAmount.setText("Total");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}
	
	private void createGenerateReportViewEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				SalesReportViewContentProvider provider = new SalesReportViewContentProvider();
				provider.setFromDate((Date) event.getProperty(ReportsCommunicationConstants.FROM_DATE_DATA));
				provider.setToDate((Date) event.getProperty(ReportsCommunicationConstants.TO_DATE_DATA));
				provider.setDocumentType((DocumentType) event.getProperty(ReportsCommunicationConstants.DOCUMENT_TYPE_DATA));;
				provider.setPaymentMethod((PaymentMethod) event.getProperty(ReportsCommunicationConstants.PAYMENT_METHOD_DATA));
				provider.setEmployee((Employee) event.getProperty(ReportsCommunicationConstants.EMPLOYEE_DATA));
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					salesTableViewer.setContentProvider(provider);
					salesTableViewer.setInput(reportServices);
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							salesTableViewer.setContentProvider(provider);
							salesTableViewer.setInput(reportServices);
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, ReportsCommunicationConstants.GENERATE_SALES_REPORT_EVENT);
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
