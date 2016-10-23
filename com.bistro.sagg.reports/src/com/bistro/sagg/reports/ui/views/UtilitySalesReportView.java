package com.bistro.sagg.reports.ui.views;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.DateTime;

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
public class UtilitySalesReportView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.reports.views.UtilitySalesReportView";

	private Action action1;
	private Action action2;
	private Table table;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

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
	public UtilitySalesReportView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Group grpBsquedaDeProductos = new Group(parent, SWT.NONE);
		grpBsquedaDeProductos.setLayout(new FillLayout(SWT.HORIZONTAL));
		grpBsquedaDeProductos.setLayoutData(new RowData(1262, 140));
		grpBsquedaDeProductos.setText("Filtrar Reporte");
		
		Composite composite_4 = new Composite(grpBsquedaDeProductos, SWT.NONE);
		composite_4.setLayout(new GridLayout(1, false));
		
		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayout(new GridLayout(4, false));
		GridData gd_composite_5 = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_composite_5.heightHint = 84;
		gd_composite_5.widthHint = 1248;
		composite_5.setLayoutData(gd_composite_5);
		
		Label lblRaznSocial = new Label(composite_5, SWT.NONE);
		lblRaznSocial.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRaznSocial.setText("Nro. de Venta");
		
		text = new Text(composite_5, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblInsumos = new Label(composite_5, SWT.RIGHT);
		GridData gd_lblInsumos = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblInsumos.widthHint = 200;
		lblInsumos.setLayoutData(gd_lblInsumos);
		lblInsumos.setText("Cantidad desde");
		
		Composite composite_6 = new Composite(composite_5, SWT.NONE);
		GridLayout gl_composite_6 = new GridLayout(3, false);
		gl_composite_6.marginHeight = 0;
		composite_6.setLayout(gl_composite_6);
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		text_4 = new Text(composite_6, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblHasta_2 = new Label(composite_6, SWT.NONE);
		lblHasta_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHasta_2.setText("hasta");
		
		text_3 = new Text(composite_6, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblFechaDesde = new Label(composite_5, SWT.NONE);
		lblFechaDesde.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFechaDesde.setText("Fecha desde");
		
		Composite composite_1 = new Composite(composite_5, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(3, false);
		gl_composite_1.marginWidth = 0;
		composite_1.setLayout(gl_composite_1);
		GridData gd_composite_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite_1.widthHint = 400;
		composite_1.setLayoutData(gd_composite_1);
		
		DateTime dateTime = new DateTime(composite_1, SWT.BORDER);
		
		Label lblHasta = new Label(composite_1, SWT.NONE);
		lblHasta.setText("hasta");
		
		DateTime dateTime_1 = new DateTime(composite_1, SWT.BORDER);
		
		Label lblUtilidadDesde = new Label(composite_5, SWT.NONE);
		lblUtilidadDesde.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUtilidadDesde.setText("Utilidad desde");
		
		Composite composite_2 = new Composite(composite_5, SWT.NONE);
		composite_2.setLayout(new GridLayout(3, false));
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite_2.widthHint = 400;
		composite_2.setLayoutData(gd_composite_2);
		
		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblHasta_1 = new Label(composite_2, SWT.NONE);
		lblHasta_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblHasta_1.setText("hasta");
		
		text_2 = new Text(composite_2, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_3 = new Composite(composite_4, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		composite_3.setLayout(new GridLayout(2, false));
		
		Button btnFiltrar = new Button(composite_3, SWT.NONE);
		GridData gd_btnFiltrar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnFiltrar.widthHint = 80;
		btnFiltrar.setLayoutData(gd_btnFiltrar);
		btnFiltrar.setText("Filtrar");
		
		Button btnLimpiar = new Button(composite_3, SWT.NONE);
		GridData gd_btnLimpiar = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLimpiar.widthHint = 80;
		btnLimpiar.setLayoutData(gd_btnLimpiar);
		btnLimpiar.setText("Limpiar");
		
		Group grpListadoDeProductos = new Group(parent, SWT.NONE);
		grpListadoDeProductos.setLayout(new GridLayout(1, false));
		grpListadoDeProductos.setLayoutData(new RowData(1262, 540));
		grpListadoDeProductos.setText("Listado de Ventas");
		
		table = new Table(grpListadoDeProductos, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_table.heightHint = 450;
		gd_table.widthHint = 1225;
		table.setLayoutData(gd_table);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tblclmnNombre = new TableColumn(table, SWT.NONE);
		tblclmnNombre.setWidth(130);
		tblclmnNombre.setText("Nro. de Venta");
		
		TableColumn tblclmnRut = new TableColumn(table, SWT.NONE);
		tblclmnRut.setWidth(130);
		tblclmnRut.setText("Fecha");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(470);
		tblclmnNewColumn.setText("Descripci\u00F3n");
		
		TableColumn tblclmnUnidadDeMedida = new TableColumn(table, SWT.NONE);
		tblclmnUnidadDeMedida.setWidth(130);
		tblclmnUnidadDeMedida.setText("Cantidad");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(130);
		tblclmnNewColumn_1.setText("Precio Unitario");
		
		TableColumn tblclmnStockLocal = new TableColumn(table, SWT.NONE);
		tblclmnStockLocal.setWidth(130);
		tblclmnStockLocal.setText("Total");
		
		TableColumn tblclmnStockBodega = new TableColumn(table, SWT.NONE);
		tblclmnStockBodega.setWidth(130);
		tblclmnStockBodega.setText("Utilidad");
		
		Composite composite = new Composite(grpListadoDeProductos, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblTotal = new Label(composite, SWT.RIGHT);
		GridData gd_lblTotal = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblTotal.widthHint = 1110;
		lblTotal.setLayoutData(gd_lblTotal);
		lblTotal.setText("Total");
		
		Label label_1 = new Label(composite, SWT.NONE);
		GridData gd_label_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label_1.widthHint = 130;
		label_1.setLayoutData(gd_label_1);
		label_1.setText("0");
		
		Label lblTotalUtilidades = new Label(composite, SWT.NONE);
		lblTotalUtilidades.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTotalUtilidades.setText("Total Utilidades");
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("0");
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
				UtilitySalesReportView.this.fillContextMenu(manager);
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
