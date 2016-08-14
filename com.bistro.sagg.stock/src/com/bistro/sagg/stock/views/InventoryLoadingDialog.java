package com.bistro.sagg.stock.views;

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
	private Text text;
	private Text text_3;
	private Text text_1;
	private Text text_2;
	private Table table;

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
		GridLayout gl_grpInformacinBsica = new GridLayout(3, false);
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
		
		Label lblNombre = new Label(grpInformacinBsica, SWT.NONE);
		lblNombre.setAlignment(SWT.RIGHT);
		GridData gd_lblNombre = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNombre.widthHint = 120;
		lblNombre.setLayoutData(gd_lblNombre);
		lblNombre.setText("Proveedor");
		
		Combo combo_2 = new Combo(grpInformacinBsica, SWT.NONE);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNuevo_1 = new Button(grpInformacinBsica, SWT.NONE);
		btnNuevo_1.setText("Nuevo");
		
		Label lblUnidadDeMedida = new Label(grpInformacinBsica, SWT.NONE);
		lblUnidadDeMedida.setAlignment(SWT.RIGHT);
		lblUnidadDeMedida.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUnidadDeMedida.setText("Tipo de Documento");
		
		Composite composite_4 = new Composite(grpInformacinBsica, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_4 = new GridLayout(3, false);
		gl_composite_4.marginHeight = 0;
		gl_composite_4.marginWidth = 0;
		composite_4.setLayout(gl_composite_4);
		
		Combo combo = new Combo(composite_4, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCategora = new Label(composite_4, SWT.NONE);
		lblCategora.setAlignment(SWT.RIGHT);
		lblCategora.setText("Fecha de Ingreso");
		
		DateTime dateTime = new DateTime(composite_4, SWT.BORDER);
		new Label(grpInformacinBsica, SWT.NONE);
		
		Group grpInformacinDeStock = new Group(container, SWT.NONE);
		GridLayout gl_grpInformacinDeStock = new GridLayout(4, false);
		gl_grpInformacinDeStock.marginTop = 10;
		gl_grpInformacinDeStock.marginRight = 5;
		gl_grpInformacinDeStock.marginLeft = 5;
		grpInformacinDeStock.setLayout(gl_grpInformacinDeStock);
		GridData gd_grpInformacinDeStock = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpInformacinDeStock.heightHint = 141;
		grpInformacinDeStock.setLayoutData(gd_grpInformacinDeStock);
		grpInformacinDeStock.setText("Informaci\u00F3n de Stock Inicial");
		
		Label lblStockLocal = new Label(grpInformacinDeStock, SWT.NONE);
		lblStockLocal.setAlignment(SWT.RIGHT);
		GridData gd_lblStockLocal = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblStockLocal.widthHint = 120;
		lblStockLocal.setLayoutData(gd_lblStockLocal);
		lblStockLocal.setText("Insumo");
		
		Combo combo_1 = new Combo(grpInformacinDeStock, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Combo combo_3 = new Combo(grpInformacinDeStock, SWT.NONE);
		combo_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNuevo = new Button(grpInformacinDeStock, SWT.NONE);
		btnNuevo.setText("Nuevo");
		
		Label lblCantidadLocal = new Label(grpInformacinDeStock, SWT.NONE);
		lblCantidadLocal.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCantidadLocal.setAlignment(SWT.RIGHT);
		lblCantidadLocal.setText("Cantidad");
		
		Composite composite = new Composite(grpInformacinDeStock, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		
		Label lblLocal = new Label(composite, SWT.NONE);
		GridData gd_lblLocal = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblLocal.widthHint = 60;
		gd_lblLocal.horizontalIndent = 5;
		lblLocal.setLayoutData(gd_lblLocal);
		lblLocal.setText("Local");
		
		text = new Text(composite, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_1 = new Composite(grpInformacinDeStock, SWT.NONE);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_1 = new GridLayout(2, false);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		composite_1.setLayout(gl_composite_1);
		
		Label lblBodega = new Label(composite_1, SWT.NONE);
		GridData gd_lblBodega = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblBodega.widthHint = 60;
		gd_lblBodega.horizontalIndent = 5;
		lblBodega.setLayoutData(gd_lblBodega);
		lblBodega.setText("Bodega");
		
		text_3 = new Text(composite_1, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpInformacinDeStock, SWT.NONE);
		
		Label lblCantidadBodega = new Label(grpInformacinDeStock, SWT.NONE);
		lblCantidadBodega.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCantidadBodega.setAlignment(SWT.RIGHT);
		lblCantidadBodega.setText("Precio de Compra");
		
		Composite composite_2 = new Composite(grpInformacinDeStock, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.marginWidth = 0;
		gl_composite_2.marginHeight = 0;
		composite_2.setLayout(gl_composite_2);
		
		Label lblUnitario = new Label(composite_2, SWT.NONE);
		GridData gd_lblUnitario = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblUnitario.horizontalIndent = 5;
		gd_lblUnitario.widthHint = 60;
		lblUnitario.setLayoutData(gd_lblUnitario);
		lblUnitario.setText("Unitario");
		
		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite composite_3 = new Composite(grpInformacinDeStock, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite_3 = new GridLayout(2, false);
		gl_composite_3.marginWidth = 0;
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		
		Label lblTotal = new Label(composite_3, SWT.NONE);
		GridData gd_lblTotal = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblTotal.horizontalIndent = 5;
		gd_lblTotal.widthHint = 60;
		lblTotal.setLayoutData(gd_lblTotal);
		lblTotal.setText("Total");
		
		text_2 = new Text(composite_3, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpInformacinDeStock, SWT.NONE);
		
		Label lblIncluyeIva = new Label(grpInformacinDeStock, SWT.NONE);
		lblIncluyeIva.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblIncluyeIva.setText("Incluye IVA");
		
		Button btnCheckButton = new Button(grpInformacinDeStock, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		new Label(grpInformacinDeStock, SWT.NONE);
		new Label(grpInformacinDeStock, SWT.NONE);
		
		Group grpResumen = new Group(container, SWT.NONE);
		GridData gd_grpResumen = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpResumen.heightHint = 225;
		grpResumen.setLayoutData(gd_grpResumen);
		grpResumen.setText("Resumen");
		grpResumen.setLayout(new GridLayout(1, false));
		
		table = new Table(grpResumen, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNombre = new TableColumn(table, SWT.NONE);
		tblclmnNombre.setWidth(100);
		tblclmnNombre.setText("Nombre");
		
		TableColumn tblclmnCategora = new TableColumn(table, SWT.NONE);
		tblclmnCategora.setWidth(100);
		tblclmnCategora.setText("Categor\u00EDa");
		
		TableColumn tblclmnCantidad = new TableColumn(table, SWT.NONE);
		tblclmnCantidad.setWidth(100);
		tblclmnCantidad.setText("Cantidad");
		
		TableColumn tblclmnPrecioUnitario = new TableColumn(table, SWT.NONE);
		tblclmnPrecioUnitario.setWidth(100);
		tblclmnPrecioUnitario.setText("Precio Unitario");
		
		TableColumn tblclmnTotal = new TableColumn(table, SWT.NONE);
		tblclmnTotal.setWidth(100);
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
		return new Point(645, 660);
	}
}
