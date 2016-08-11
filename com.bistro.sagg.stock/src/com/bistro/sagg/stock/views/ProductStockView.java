package com.bistro.sagg.stock.views;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;


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

public class ProductStockView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.stock.views.ProductStockView";
	private Action action1;
	private Action action2;
	private Action doubleClickAction;
	private Table table;
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
	public ProductStockView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Group grpBsquedaDeProductos = new Group(parent, SWT.NONE);
		grpBsquedaDeProductos.setLayout(new FillLayout(SWT.HORIZONTAL));
		grpBsquedaDeProductos.setLayoutData(new RowData(1262, 180));
		grpBsquedaDeProductos.setText("Filtro de Productos");
		
		Composite composite = new Composite(grpBsquedaDeProductos, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		Label lblNombre = new Label(composite, SWT.NONE);
		lblNombre.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblNombre.setText("Nombre");
		
		text = new Text(composite, SWT.BORDER);
		GridData gd_text = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_text.widthHint = 535;
		text.setLayoutData(gd_text);
		
		Label lblCtegora = new Label(composite, SWT.NONE);
		lblCtegora.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		lblCtegora.setText("Categor\u00EDa");
		
		List list = new List(composite, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_list.heightHint = 136;
		list.setLayoutData(gd_list);
		
		Composite composite_1 = new Composite(grpBsquedaDeProductos, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		
		Label lblUnidadDeMedida = new Label(composite_1, SWT.NONE);
		lblUnidadDeMedida.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUnidadDeMedida.setText("Unidad de Medida");
		
		Combo combo = new Combo(composite_1, SWT.NONE);
		GridData gd_combo = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_combo.widthHint = 191;
		combo.setLayoutData(gd_combo);
		
		Label lblStock = new Label(composite_1, SWT.NONE);
		lblStock.setText("Stock");
		
		Button btnConStockLocal = new Button(composite_1, SWT.CHECK);
		btnConStockLocal.setText("Con Stock Local");
		
		Group grpListadoDeProductos = new Group(parent, SWT.NONE);
		grpListadoDeProductos.setLayout(null);
		grpListadoDeProductos.setLayoutData(new RowData(1262, 569));
		grpListadoDeProductos.setText("Listado de Productos");
		
		table = new Table(grpListadoDeProductos, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(10, 67, 1248, 516);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tblclmnNombre = new TableColumn(table, SWT.NONE);
		tblclmnNombre.setWidth(230);
		tblclmnNombre.setText("Nombre");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(396);
		tblclmnNewColumn.setText("Categor\u00EDa");
		
		TableColumn tblclmnUnidadDeMedida = new TableColumn(table, SWT.NONE);
		tblclmnUnidadDeMedida.setWidth(138);
		tblclmnUnidadDeMedida.setText("Unidad de Medida");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(120);
		tblclmnNewColumn_1.setText("Precio Unitario");
		
		TableColumn tblclmnStockLocal = new TableColumn(table, SWT.NONE);
		tblclmnStockLocal.setWidth(120);
		tblclmnStockLocal.setText("Stock Local");
		
		TableColumn tblclmnStockBodega = new TableColumn(table, SWT.NONE);
		tblclmnStockBodega.setWidth(120);
		tblclmnStockBodega.setText("Stock Bodega");
		
		TableColumn tblclmnStockMnimo = new TableColumn(table, SWT.NONE);
		tblclmnStockMnimo.setWidth(120);
		tblclmnStockMnimo.setText("Stock M\u00EDnimo");
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
				ProductStockView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
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
