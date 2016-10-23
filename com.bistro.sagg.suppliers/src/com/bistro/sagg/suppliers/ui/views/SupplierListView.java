package com.bistro.sagg.suppliers.ui.views;


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

import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.services.SupplierServices;
import com.bistro.sagg.suppliers.ui.utils.SupplierColumnIndex;
import com.bistro.sagg.suppliers.ui.utils.SuppliersCommunicationConstants;
import com.bistro.sagg.suppliers.ui.viewers.SupplierListContentProvider;
import com.bistro.sagg.suppliers.ui.viewers.SupplierListLabelProvider;
import com.bistro.sagg.suppliers.ui.viewers.SupplierListSorter;


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

public class SupplierListView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.suppliers.ui.views.SupplierListView";

//	private OpenNewSupplierDialogAction openNewSupplierDialogAction;
	private TableViewer suppliersTableViewer;
	private Table suppliersTable;
	
	private SupplierServices supplierServices = (SupplierServices) SaggServiceLocator.getInstance()
			.lookup(SupplierServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public SupplierListView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(SupplierListView.class).getBundleContext();
		ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
		this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		createAddEmplouyeeEventHandler(parent);
		
		suppliersTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		suppliersTableViewer.setContentProvider(new SupplierListContentProvider());
		suppliersTableViewer.setLabelProvider(new SupplierListLabelProvider());
		suppliersTableViewer.setSorter(new SupplierListSorter());
		
		suppliersTable = suppliersTableViewer.getTable();
		suppliersTable.setLinesVisible(true);
		suppliersTable.setHeaderVisible(true);
		
		TableColumn tblclmnNombre = new TableColumn(suppliersTable, SWT.NONE);
		tblclmnNombre.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((SupplierListSorter) suppliersTableViewer.getSorter()).doSort(SupplierColumnIndex.BUSINESS_NAME_COLUMN_IDX);
				suppliersTableViewer.refresh();
			}
		});
		tblclmnNombre.setWidth(182);
		tblclmnNombre.setText("Raz\u00F3n Social");
		
		TableColumn tblclmnRut = new TableColumn(suppliersTable, SWT.NONE);
		tblclmnRut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((SupplierListSorter) suppliersTableViewer.getSorter()).doSort(SupplierColumnIndex.SUPPLIER_ID_COLUMN_IDX);
				suppliersTableViewer.refresh();
			}
		});
		tblclmnRut.setWidth(88);
		tblclmnRut.setText("RUT");
		
		TableColumn tblclmnNewColumn = new TableColumn(suppliersTable, SWT.NONE);
		tblclmnNewColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((SupplierListSorter) suppliersTableViewer.getSorter()).doSort(SupplierColumnIndex.SUPPLIES_COLUMN_IDX);
				suppliersTableViewer.refresh();
			}
		});
		tblclmnNewColumn.setWidth(386);
		tblclmnNewColumn.setText("Insumos");
		
		TableColumn tblclmnUnidadDeMedida = new TableColumn(suppliersTable, SWT.NONE);
		tblclmnUnidadDeMedida.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((SupplierListSorter) suppliersTableViewer.getSorter()).doSort(SupplierColumnIndex.CONTACT_COLUMN_IDX);
				suppliersTableViewer.refresh();
			}
		});
		tblclmnUnidadDeMedida.setWidth(190);
		tblclmnUnidadDeMedida.setText("Datos de Contacto");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(suppliersTable, SWT.NONE);
		tblclmnNewColumn_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((SupplierListSorter) suppliersTableViewer.getSorter()).doSort(SupplierColumnIndex.PHONE_COLUMN_IDX);
				suppliersTableViewer.refresh();
			}
		});
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("Tel\u00E9fono");
		
		TableColumn tblclmnStockLocal = new TableColumn(suppliersTable, SWT.NONE);
		tblclmnStockLocal.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((SupplierListSorter) suppliersTableViewer.getSorter()).doSort(SupplierColumnIndex.CELLPHONE_COLUMN_IDX);
				suppliersTableViewer.refresh();
			}
		});
		tblclmnStockLocal.setWidth(100);
		tblclmnStockLocal.setText("Celular");
		
		TableColumn tblclmnStockBodega = new TableColumn(suppliersTable, SWT.NONE);
		tblclmnStockBodega.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((SupplierListSorter) suppliersTableViewer.getSorter()).doSort(SupplierColumnIndex.EMAIL_COLUMN_IDX);
				suppliersTableViewer.refresh();
			}
		});
		tblclmnStockBodega.setWidth(190);
		tblclmnStockBodega.setText("Correo Electr\u00F3nico");
		
		suppliersTableViewer.setInput(supplierServices);
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}
	
	private void createAddEmplouyeeEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					suppliersTableViewer.refresh();
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							suppliersTableViewer.refresh();
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, SuppliersCommunicationConstants.ADD_SUPPLIER_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}

	private void hookContextMenu() {
//		MenuManager menuMgr = new MenuManager("#PopupMenu");
//		menuMgr.setRemoveAllWhenShown(true);
//		menuMgr.addMenuListener(new IMenuListener() {
//			public void menuAboutToShow(IMenuManager manager) {
//				SupplierListView.this.fillContextMenu(manager);
//			}
//		});
	}

	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalPullDown(bars.getMenuManager());
//		fillLocalToolBar(bars.getToolBarManager());
	}

//	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(openNewSupplierDialogAction);
//		manager.add(new Separator());
//	}
//
//	private void fillContextMenu(IMenuManager manager) {
//		manager.add(openNewSupplierDialogAction);
//		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//	}
//	
//	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(openNewSupplierDialogAction);
//	}

	private void makeActions() {
//		openNewSupplierDialogAction = new OpenNewSupplierDialogAction("Nuevo Proveedor",PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//		openNewSupplierDialogAction.setText("Nuevo Proveedor");
//		openNewSupplierDialogAction.setToolTipText("Nuevo Proveedor");
//		openNewSupplierDialogAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
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
