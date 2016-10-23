package com.bistro.sagg.employees.ui.views;

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

import com.bistro.sagg.core.services.EmployeeServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.employees.ui.utils.EmployeeColumnIndex;
import com.bistro.sagg.employees.ui.utils.EmployeesCommunicationConstants;
import com.bistro.sagg.employees.ui.viewers.EmployeeListContentProvider;
import com.bistro.sagg.employees.ui.viewers.EmployeeListLabelProvider;
import com.bistro.sagg.employees.ui.viewers.EmployeeListSorter;

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

public class EmployeeListView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.employees.ui.views.EmployeeListView";

//	private Action openNewEmployeeDialogAction;
	private TableViewer employeesTableViewer;
	private Table employeesTable;
	
	private EmployeeServices employeeService = (EmployeeServices) SaggServiceLocator.getInstance()
			.lookup(EmployeeServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public EmployeeListView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(EmployeeListView.class).getBundleContext();
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
		
		employeesTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		employeesTableViewer.setContentProvider(new EmployeeListContentProvider());
		employeesTableViewer.setLabelProvider(new EmployeeListLabelProvider());
		employeesTableViewer.setSorter(new EmployeeListSorter());
		
		employeesTable = employeesTableViewer.getTable();
		employeesTable.setLinesVisible(true);
		employeesTable.setHeaderVisible(true);
		
		TableColumn tblclmnNombre = new TableColumn(employeesTable, SWT.NONE);
		tblclmnNombre.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((EmployeeListSorter) employeesTableViewer.getSorter()).doSort(EmployeeColumnIndex.NAME_COLUMN_IDX);
				employeesTableViewer.refresh();
			}
		});
		tblclmnNombre.setWidth(250);
		tblclmnNombre.setText("Nombre");
//		TableViewerColumn tblclmnNombreViewer = new TableViewerColumn(employeesTableViewer, tblclmnNombre);
		
		TableColumn tblclmnRut = new TableColumn(employeesTable, SWT.NONE);
		tblclmnRut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((EmployeeListSorter) employeesTableViewer.getSorter()).doSort(EmployeeColumnIndex.PERSON_ID_COLUMN_IDX);
				employeesTableViewer.refresh();
			}
		});
		tblclmnRut.setWidth(100);
		tblclmnRut.setText("RUT");
		
		TableColumn tblclmnCargo = new TableColumn(employeesTable, SWT.NONE);
		tblclmnCargo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((EmployeeListSorter) employeesTableViewer.getSorter()).doSort(EmployeeColumnIndex.POSITION_COLUMN_IDX);
				employeesTableViewer.refresh();
			}
		});
		tblclmnCargo.setWidth(194);
		tblclmnCargo.setText("Cargo");
		
		TableColumn tblclmnDirección = new TableColumn(employeesTable, SWT.NONE);
		tblclmnDirección.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((EmployeeListSorter) employeesTableViewer.getSorter()).doSort(EmployeeColumnIndex.ADDRESS_COLUMN_IDX);
				employeesTableViewer.refresh();
			}
		});
		tblclmnDirección.setWidth(280);
		tblclmnDirección.setText("Direcci\u00F3n");
		
		TableColumn tblclmnTelefono = new TableColumn(employeesTable, SWT.NONE);
		tblclmnTelefono.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((EmployeeListSorter) employeesTableViewer.getSorter()).doSort(EmployeeColumnIndex.PHONE_COLUMN_IDX);
				employeesTableViewer.refresh();
			}
		});
		tblclmnTelefono.setWidth(100);
		tblclmnTelefono.setText("Tel\u00E9fono");
		
		TableColumn tblclmnCelular = new TableColumn(employeesTable, SWT.NONE);
		tblclmnCelular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((EmployeeListSorter) employeesTableViewer.getSorter()).doSort(EmployeeColumnIndex.CELLPHONE_COLUMN_IDX);
				employeesTableViewer.refresh();
			}
		});
		tblclmnCelular.setWidth(100);
		tblclmnCelular.setText("Celular");
		
		TableColumn tblclmnCorreoElectronico = new TableColumn(employeesTable, SWT.NONE);
		tblclmnCorreoElectronico.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((EmployeeListSorter) employeesTableViewer.getSorter()).doSort(EmployeeColumnIndex.EMAIL_COLUMN_IDX);
				employeesTableViewer.refresh();
			}
		});
		tblclmnCorreoElectronico.setWidth(220);
		tblclmnCorreoElectronico.setText("Correo Electr\u00F3nico");
		
		TableColumn tblclmnFechaDeIngreso = new TableColumn(employeesTable, SWT.NONE);
		tblclmnFechaDeIngreso.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((EmployeeListSorter) employeesTableViewer.getSorter()).doSort(EmployeeColumnIndex.START_DATE_COLUMN_IDX);
				employeesTableViewer.refresh();
			}
		});
		tblclmnFechaDeIngreso.setWidth(135);
		tblclmnFechaDeIngreso.setText("Fecha de Ingreso");
		
		employeesTableViewer.setInput(employeeService);
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}
	
	private void createAddEmplouyeeEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					employeesTableViewer.refresh();
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							employeesTableViewer.refresh();
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, EmployeesCommunicationConstants.ADD_EMPLOYEE_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}

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
