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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

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

public class SalesConfirmationDetailView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.sales.ui.views.SalesConfirmationDetailView";
	private Text text;
	private Text text_2;
	private Text text_3;
	private Text text_1;


	public SalesConfirmationDetailView() {
		super();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite paymentDetailComposite = new Composite(parent, SWT.NONE);
		paymentDetailComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		paymentDetailComposite.setLayout(new GridLayout(2, false));
		
		Label lblNmero = new Label(paymentDetailComposite, SWT.NONE);
		lblNmero.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNmero.setText("N\u00FAmero");
		
		text = new Text(paymentDetailComposite, SWT.BORDER);
		text.setEditable(false);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblFecha = new Label(paymentDetailComposite, SWT.NONE);
		lblFecha.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFecha.setText("Fecha");
		
		text_2 = new Text(paymentDetailComposite, SWT.BORDER);
		text_2.setEditable(false);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblVendedor = new Label(paymentDetailComposite, SWT.NONE);
		lblVendedor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVendedor.setText("Vendedor");
		
		Combo combo = new Combo(paymentDetailComposite, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblDescuento = new Label(paymentDetailComposite, SWT.NONE);
		lblDescuento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescuento.setText("Cup\u00F3n de Descuento");
		
		Combo combo_3 = new Combo(paymentDetailComposite, SWT.NONE);
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCupnDeDescuento = new Label(paymentDetailComposite, SWT.NONE);
		lblCupnDeDescuento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCupnDeDescuento.setText("N\u00FAmero de Cup\u00F3n");
		
		Composite composite_2 = new Composite(paymentDetailComposite, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);
		
		text_3 = new Text(composite_2, SWT.BORDER);
		text_3.setEditable(false);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnAplicar = new Button(composite_2, SWT.NONE);
		btnAplicar.setEnabled(false);
		btnAplicar.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnAplicar.setText("Aplicar");
		
		Label lblTipoDeVenta = new Label(paymentDetailComposite, SWT.NONE);
		lblTipoDeVenta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTipoDeVenta.setText("Tipo de Venta");
		
		Combo combo_1 = new Combo(paymentDetailComposite, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblTarjeta = new Label(paymentDetailComposite, SWT.NONE);
		lblTarjeta.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTarjeta.setText("Tarjeta");
		
		Combo combo_2 = new Combo(paymentDetailComposite, SWT.NONE);
		combo_2.setEnabled(false);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblEfectivo = new Label(paymentDetailComposite, SWT.NONE);
		lblEfectivo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEfectivo.setText("Efectivo");
		
		Composite composite_1 = new Composite(paymentDetailComposite, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_1 = new GridLayout(2, false);
		gl_composite_1.marginHeight = 0;
		gl_composite_1.marginWidth = 0;
		composite_1.setLayout(gl_composite_1);
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setEditable(false);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnCalcularVuelto = new Button(composite_1, SWT.NONE);
		btnCalcularVuelto.setEnabled(false);
		btnCalcularVuelto.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnCalcularVuelto.setText("Calcular Vuelto");
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridData gd_composite = new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1);
		gd_composite.widthHint = 250;
		composite.setLayoutData(gd_composite);
		composite.setLayout(null);
		
		Label lblSubtotal = new Label(composite, SWT.RIGHT);
		lblSubtotal.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblSubtotal.setBounds(-19, 0, 155, 20);
		lblSubtotal.setText("Subtotal");
		
		Label lblDescuentos = new Label(composite, SWT.RIGHT);
		lblDescuentos.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblDescuentos.setBounds(-19, 26, 155, 20);
		lblDescuentos.setText("Promociones");
		
		Label lblTotal = new Label(composite, SWT.RIGHT);
		lblTotal.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblTotal.setBounds(-19, 78, 155, 20);
		lblTotal.setText("Total Descuentos");
		
		Label label = new Label(composite, SWT.RIGHT);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		label.setBounds(140, 0, 100, 20);
		label.setText("0");
		
		Label label_1 = new Label(composite, SWT.RIGHT);
		label_1.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		label_1.setText("0");
		label_1.setBounds(140, 26, 100, 20);
		
		Label label_2 = new Label(composite, SWT.RIGHT);
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		label_2.setText("0");
		label_2.setBounds(140, 52, 100, 20);
		
		Label lblCambio = new Label(composite, SWT.RIGHT);
		lblCambio.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblCambio.setBounds(-19, 52, 155, 20);
		lblCambio.setText("Descuentos");
		
		Label label_3 = new Label(composite, SWT.RIGHT);
		label_3.setText("0");
		label_3.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		label_3.setBounds(140, 78, 100, 20);
		
		Label label_4 = new Label(composite, SWT.RIGHT);
		label_4.setText("0");
		label_4.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		label_4.setBounds(140, 104, 100, 30);
		
		Label lblTotal_1 = new Label(composite, SWT.RIGHT);
		lblTotal_1.setText("Vuelto");
		lblTotal_1.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblTotal_1.setBounds(-19, 134, 155, 30);
		
		Label label_5 = new Label(composite, SWT.RIGHT);
		label_5.setText("Total");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		label_5.setBounds(-19, 104, 155, 30);
		
		Label label_6 = new Label(composite, SWT.RIGHT);
		label_6.setText("0");
		label_6.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		label_6.setBounds(140, 134, 100, 30);
		
		Button btnNewButton = new Button(parent, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		btnNewButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnNewButton.setText("Confirmar Venta");
		
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
				SalesConfirmationDetailView.this.fillContextMenu(manager);
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
