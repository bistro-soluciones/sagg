package com.bistro.sagg.sales.ui.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.bistro.sagg.core.services.EmployeeServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;

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

public class SalesView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.employees.ui.views.EmployeeListView";

	private Action openNewEmployeeDialogAction;
	
	private EmployeeServices employeeService = (EmployeeServices) SaggServiceLocator.getInstance()
			.lookup(EmployeeServices.class.getName());
	private Text text;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object parent) {
			return new String[] { "One", "Two", "Three" };
		}
	}
	class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().
					getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}
	class NameSorter extends ViewerSorter {
	}
	
	/**
	 * The constructor.
	 */
	public SalesView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		
		Group grpInformacinDeLa = new Group(parent, SWT.NONE);
		grpInformacinDeLa.setLayout(new GridLayout(2, false));
		GridData gd_grpInformacinDeLa = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpInformacinDeLa.widthHint = 289;
		grpInformacinDeLa.setLayoutData(gd_grpInformacinDeLa);
		grpInformacinDeLa.setText("Informaci\u00F3n de la venta");
		
		Label lblNmeroDeBoleta = new Label(grpInformacinDeLa, SWT.NONE);
		lblNmeroDeBoleta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmeroDeBoleta.setText("N\u00FAmero de Boleta");
		
		text = new Text(grpInformacinDeLa, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group grpDetalleDeVenta = new Group(parent, SWT.NONE);
		grpDetalleDeVenta.setLayout(new GridLayout(1, false));
		grpDetalleDeVenta.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		grpDetalleDeVenta.setText("Detalle de venta");
		
		Group grpPromociones = new Group(parent, SWT.NONE);
		grpPromociones.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_grpPromociones = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_grpPromociones.widthHint = 658;
		gd_grpPromociones.heightHint = 121;
		grpPromociones.setLayoutData(gd_grpPromociones);
		grpPromociones.setText("Promociones");
		
		Composite composite = new Composite(grpPromociones, SWT.V_SCROLL);
		composite.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Button btnCombo = new Button(composite, SWT.NONE);
		btnCombo.setLayoutData(new RowData(100, 100));
		btnCombo.setText("Combo 1");
		
		Button button = new Button(composite, SWT.NONE);
		button.setLayoutData(new RowData(100, 100));
		button.setText("Combo 1");
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setLayoutData(new RowData(100, 100));
		button_1.setText("Combo 1");
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setLayoutData(new RowData(100, 100));
		button_2.setText("Combo 1");
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.setLayoutData(new RowData(100, 100));
		button_3.setText("Combo 1");
		
		Button button_4 = new Button(composite, SWT.NONE);
		button_4.setLayoutData(new RowData(100, 100));
		button_4.setText("Combo 1");
		new Label(parent, SWT.NONE);
		
		Group grpProductos = new Group(parent, SWT.NONE);
		GridData gd_grpProductos = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_grpProductos.heightHint = 348;
		grpProductos.setLayoutData(gd_grpProductos);
		grpProductos.setText("Productos");
		new Label(parent, SWT.NONE);
		
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
				SalesView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(openNewEmployeeDialogAction);
		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(openNewEmployeeDialogAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(openNewEmployeeDialogAction);
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
