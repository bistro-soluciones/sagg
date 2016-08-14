package com.bistro.sagg.stock.views;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;

public class NewProductDialog extends Dialog {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewProductDialog(Shell parentShell) {
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
		gd_grpInformacinBsica.heightHint = 232;
		gd_grpInformacinBsica.widthHint = 604;
		grpInformacinBsica.setLayoutData(gd_grpInformacinBsica);
		grpInformacinBsica.setText("Informaci\u00F3n B\u00E1sica");
		
		Label lblNombre = new Label(grpInformacinBsica, SWT.NONE);
		lblNombre.setAlignment(SWT.RIGHT);
		GridData gd_lblNombre = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNombre.widthHint = 120;
		lblNombre.setLayoutData(gd_lblNombre);
		lblNombre.setText("Nombre");
		
		text = new Text(grpInformacinBsica, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCategora = new Label(grpInformacinBsica, SWT.NONE);
		lblCategora.setAlignment(SWT.RIGHT);
		lblCategora.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblCategora.setText("Categor\u00EDa");
		
		List list = new List(grpInformacinBsica, SWT.BORDER);
		GridData gd_list = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_list.heightHint = 112;
		list.setLayoutData(gd_list);
		
		Label lblUnidadDeMedida = new Label(grpInformacinBsica, SWT.NONE);
		lblUnidadDeMedida.setAlignment(SWT.RIGHT);
		lblUnidadDeMedida.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUnidadDeMedida.setText("Unidad de Medida");
		
		Combo combo = new Combo(grpInformacinBsica, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPrecioUnitario = new Label(grpInformacinBsica, SWT.NONE);
		lblPrecioUnitario.setAlignment(SWT.RIGHT);
		lblPrecioUnitario.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPrecioUnitario.setText("Precio Unitario");
		
		Composite composite = new Composite(grpInformacinBsica, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginHeight = 0;
		gl_composite.marginWidth = 0;
		composite.setLayout(gl_composite);
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 28;
		composite.setLayoutData(gd_composite);
		
		text_1 = new Text(composite, SWT.BORDER);
		GridData gd_text_1 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_1.widthHint = 100;
		text_1.setLayoutData(gd_text_1);
		
		Label lblMoneda = new Label(composite, SWT.NONE);
		lblMoneda.setAlignment(SWT.RIGHT);
		GridData gd_lblMoneda = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblMoneda.widthHint = 123;
		lblMoneda.setLayoutData(gd_lblMoneda);
		lblMoneda.setText("Moneda");
		
		Combo combo_1 = new Combo(composite, SWT.NONE);
		GridData gd_combo_1 = new GridData(SWT.LEFT, SWT.TOP, true, false, 1, 1);
		gd_combo_1.widthHint = 80;
		gd_combo_1.heightHint = 28;
		combo_1.setLayoutData(gd_combo_1);
		new Label(grpInformacinBsica, SWT.NONE);
		new Label(grpInformacinBsica, SWT.NONE);
		
		Group grpInformacinDeStock = new Group(container, SWT.NONE);
		GridLayout gl_grpInformacinDeStock = new GridLayout(4, false);
		gl_grpInformacinDeStock.marginTop = 10;
		gl_grpInformacinDeStock.marginRight = 5;
		gl_grpInformacinDeStock.marginLeft = 5;
		grpInformacinDeStock.setLayout(gl_grpInformacinDeStock);
		GridData gd_grpInformacinDeStock = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpInformacinDeStock.heightHint = 83;
		grpInformacinDeStock.setLayoutData(gd_grpInformacinDeStock);
		grpInformacinDeStock.setText("Informaci\u00F3n de Stock Inicial");
		
		Label lblStockLocal = new Label(grpInformacinDeStock, SWT.NONE);
		lblStockLocal.setAlignment(SWT.RIGHT);
		GridData gd_lblStockLocal = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblStockLocal.widthHint = 120;
		lblStockLocal.setLayoutData(gd_lblStockLocal);
		lblStockLocal.setText("Stock Local");
		
		text_2 = new Text(grpInformacinDeStock, SWT.BORDER);
		GridData gd_text_2 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_2.widthHint = 100;
		text_2.setLayoutData(gd_text_2);
		
		Label lblStockBodega = new Label(grpInformacinDeStock, SWT.NONE);
		lblStockBodega.setAlignment(SWT.RIGHT);
		GridData gd_lblStockBodega = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblStockBodega.widthHint = 127;
		lblStockBodega.setLayoutData(gd_lblStockBodega);
		lblStockBodega.setText("Stock Bodega");
		
		text_3 = new Text(grpInformacinDeStock, SWT.BORDER);
		GridData gd_text_3 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_3.widthHint = 100;
		text_3.setLayoutData(gd_text_3);
		
		Label lblStockMnimo = new Label(grpInformacinDeStock, SWT.NONE);
		lblStockMnimo.setAlignment(SWT.RIGHT);
		lblStockMnimo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStockMnimo.setText("Stock M\u00EDnimo");
		
		text_4 = new Text(grpInformacinDeStock, SWT.BORDER);
		GridData gd_text_4 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_4.widthHint = 100;
		text_4.setLayoutData(gd_text_4);
		new Label(grpInformacinDeStock, SWT.NONE);
		new Label(grpInformacinDeStock, SWT.NONE);

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
		return new Point(640, 490);
	}
}
