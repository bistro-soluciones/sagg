package com.bistro.sagg.products.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class InventoryLoadingDialog extends Dialog {
	private Table table;
	private Text text_3;
	private Text text_4;
	private Text text_5;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public InventoryLoadingDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.marginHeight = 14;
		
		Group grpInformacinBsica = new Group(container, SWT.NONE);
		GridLayout gl_grpInformacinBsica = new GridLayout(2, false);
		gl_grpInformacinBsica.marginTop = 10;
		gl_grpInformacinBsica.marginLeft = 5;
		gl_grpInformacinBsica.marginRight = 5;
		gl_grpInformacinBsica.marginHeight = 0;
		gl_grpInformacinBsica.marginWidth = 0;
		grpInformacinBsica.setLayout(gl_grpInformacinBsica);
		GridData gd_grpInformacinBsica = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpInformacinBsica.heightHint = 80;
		gd_grpInformacinBsica.widthHint = 604;
		grpInformacinBsica.setLayoutData(gd_grpInformacinBsica);
		grpInformacinBsica.setText("Informaci\u00F3n B\u00E1sica");
		
		Label lblUnidadDeMedida = new Label(grpInformacinBsica, SWT.NONE);
		lblUnidadDeMedida.setAlignment(SWT.RIGHT);
		GridData gd_lblUnidadDeMedida = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblUnidadDeMedida.widthHint = 124;
		lblUnidadDeMedida.setLayoutData(gd_lblUnidadDeMedida);
		lblUnidadDeMedida.setText("Documento");
		
		Composite composite_4 = new Composite(grpInformacinBsica, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_4 = new GridLayout(3, false);
		gl_composite_4.marginHeight = 0;
		gl_composite_4.marginWidth = 0;
		composite_4.setLayout(gl_composite_4);
		
		Combo combo = new Combo(composite_4, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNro = new Label(composite_4, SWT.NONE);
		lblNro.setText("Nro.");
		
		text_3 = new Text(composite_4, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblProveedor = new Label(grpInformacinBsica, SWT.NONE);
		lblProveedor.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProveedor.setText("Proveedor");
		
		Combo combo_2 = new Combo(grpInformacinBsica, SWT.NONE);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group grpInformacinDeInventario = new Group(container, SWT.NONE);
		grpInformacinDeInventario.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpInformacinDeInventario.setText("Informaci\u00F3n de Inventario");
		grpInformacinDeInventario.setLayout(new GridLayout(2, false));
		
		Label lblStockLocal = new Label(grpInformacinDeInventario, SWT.NONE);
		GridData gd_lblStockLocal = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblStockLocal.widthHint = 124;
		lblStockLocal.setLayoutData(gd_lblStockLocal);
		lblStockLocal.setAlignment(SWT.RIGHT);
		lblStockLocal.setText("Insumo");
		
		Composite composite_6 = new Composite(grpInformacinDeInventario, SWT.NONE);
		GridLayout gl_composite_6 = new GridLayout(3, false);
		gl_composite_6.marginHeight = 0;
		gl_composite_6.marginWidth = 0;
		composite_6.setLayout(gl_composite_6);
		GridData gd_composite_6 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite_6.widthHint = 465;
		composite_6.setLayoutData(gd_composite_6);
		
		Combo combo_1 = new Combo(composite_6, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Combo combo_3 = new Combo(composite_6, SWT.NONE);
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNuevo = new Button(composite_6, SWT.NONE);
		btnNuevo.setSize(55, 30);
		btnNuevo.setText("Nuevo");
		
		Label lblCantidadLocal = new Label(grpInformacinDeInventario, SWT.NONE);
		lblCantidadLocal.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblCantidadLocal.setAlignment(SWT.RIGHT);
		lblCantidadLocal.setText("Cantidad");
		
		Composite composite_7 = new Composite(grpInformacinDeInventario, SWT.NONE);
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_7 = new GridLayout(4, false);
		gl_composite_7.marginWidth = 0;
		gl_composite_7.marginHeight = 0;
		composite_7.setLayout(gl_composite_7);
		
		text_4 = new Text(composite_7, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPrecioUnitario = new Label(composite_7, SWT.NONE);
		lblPrecioUnitario.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPrecioUnitario.setText("Precio Unitario");
		
		text_5 = new Text(composite_7, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnCheckButton_1 = new Button(composite_7, SWT.CHECK);
		btnCheckButton_1.setText("Incluye IVA");
		
		Group grpResumen = new Group(container, SWT.NONE);
		GridData gd_grpResumen = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpResumen.heightHint = 358;
		grpResumen.setLayoutData(gd_grpResumen);
		grpResumen.setText("Resumen");
		grpResumen.setLayout(new GridLayout(1, false));
		
		table = new Table(grpResumen, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_table.heightHint = 198;
		gd_table.widthHint = 572;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNombre = new TableColumn(table, SWT.NONE);
		tblclmnNombre.setWidth(170);
		tblclmnNombre.setText("Nombre");
		
		TableColumn tblclmnCategora = new TableColumn(table, SWT.NONE);
		tblclmnCategora.setWidth(170);
		tblclmnCategora.setText("Categor\u00EDa");
		
		TableColumn tblclmnCantidad = new TableColumn(table, SWT.NONE);
		tblclmnCantidad.setWidth(80);
		tblclmnCantidad.setText("Cantidad");
		
		TableColumn tblclmnPrecioUnitario = new TableColumn(table, SWT.NONE);
		tblclmnPrecioUnitario.setWidth(115);
		tblclmnPrecioUnitario.setText("Precio Unitario");
		
		TableColumn tblclmnTotal = new TableColumn(table, SWT.NONE);
		tblclmnTotal.setWidth(58);
		tblclmnTotal.setText("Total");
		
		Composite composite_5 = new Composite(grpResumen, SWT.NONE);
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_5.setLayout(new GridLayout(18, false));
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
		Label lblSubtotal = new Label(composite_5, SWT.RIGHT);
		GridData gd_lblSubtotal = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblSubtotal.widthHint = 75;
		lblSubtotal.setLayoutData(gd_lblSubtotal);
		lblSubtotal.setText("Subtotal");
		
		Label label_2 = new Label(composite_5, SWT.RIGHT);
		GridData gd_label_2 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_2.widthHint = 40;
		label_2.setLayoutData(gd_label_2);
		label_2.setText("0");
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
		Label lblIva = new Label(composite_5, SWT.RIGHT);
		GridData gd_lblIva = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblIva.widthHint = 75;
		lblIva.setLayoutData(gd_lblIva);
		lblIva.setText("IVA 19%");
		
		Label label = new Label(composite_5, SWT.RIGHT);
		GridData gd_label = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label.widthHint = 40;
		label.setLayoutData(gd_label);
		label.setText("0");
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		new Label(composite_5, SWT.NONE);
		
		Label lblTotal_1 = new Label(composite_5, SWT.RIGHT);
		GridData gd_lblTotal_1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblTotal_1.widthHint = 75;
		lblTotal_1.setLayoutData(gd_lblTotal_1);
		lblTotal_1.setText("Total");
		
		Label label_1 = new Label(composite_5, SWT.RIGHT);
		GridData gd_label_1 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_label_1.widthHint = 40;
		label_1.setLayoutData(gd_label_1);
		label_1.setText("0");
		
		Label lblClp = new Label(composite_5, SWT.NONE);
		lblClp.setText("CLP");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Guardar", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "Cancelar", false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(647, 727);
	}
}
