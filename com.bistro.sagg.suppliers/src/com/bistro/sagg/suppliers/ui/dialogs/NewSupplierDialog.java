package com.bistro.sagg.suppliers.ui.dialogs;

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

public class NewSupplierDialog extends Dialog {
	private Text text;
	private Text text_5;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewSupplierDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MIN | SWT.MAX | SWT.TITLE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.getShell().setText("Nuevo Proveedor");
		
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
		gd_grpInformacinBsica.heightHint = 75;
		gd_grpInformacinBsica.widthHint = 604;
		grpInformacinBsica.setLayoutData(gd_grpInformacinBsica);
		grpInformacinBsica.setText("Informaci\u00F3n B\u00E1sica");
		
		Label lblNombre = new Label(grpInformacinBsica, SWT.NONE);
		lblNombre.setAlignment(SWT.RIGHT);
		GridData gd_lblNombre = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNombre.widthHint = 124;
		lblNombre.setLayoutData(gd_lblNombre);
		lblNombre.setText("Raz\u00F3n Social");
		
		text = new Text(grpInformacinBsica, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRut = new Label(grpInformacinBsica, SWT.RIGHT);
		lblRut.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRut.setText("RUT");
		
		text_5 = new Text(grpInformacinBsica, SWT.BORDER);
		GridData gd_text_5 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_5.widthHint = 145;
		text_5.setLayoutData(gd_text_5);
		
		Group grpInformacinDeStock = new Group(container, SWT.NONE);
		grpInformacinDeStock.setLayout(new GridLayout(2, false));
		GridData gd_grpInformacinDeStock = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpInformacinDeStock.heightHint = 112;
		grpInformacinDeStock.setLayoutData(gd_grpInformacinDeStock);
		grpInformacinDeStock.setText("Informaci\u00F3n de Contacto");
		
		Label lblDatosDeContacto = new Label(grpInformacinDeStock, SWT.NONE);
		GridData gd_lblDatosDeContacto = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblDatosDeContacto.widthHint = 124;
		lblDatosDeContacto.setLayoutData(gd_lblDatosDeContacto);
		lblDatosDeContacto.setText("Datos de Contacto");
		
		text_1 = new Text(grpInformacinDeStock, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblTelfono = new Label(grpInformacinDeStock, SWT.NONE);
		lblTelfono.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTelfono.setText("Tel\u00E9fono");
		
		Composite composite = new Composite(grpInformacinDeStock, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCelular = new Label(composite, SWT.RIGHT);
		GridData gd_lblCelular = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblCelular.widthHint = 141;
		lblCelular.setLayoutData(gd_lblCelular);
		lblCelular.setText("Celular");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCorreoElectrnico = new Label(grpInformacinDeStock, SWT.NONE);
		lblCorreoElectrnico.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorreoElectrnico.setText("Correo Electr\u00F3nico");
		
		text_2 = new Text(grpInformacinDeStock, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group grpInformacinDeInsumos = new Group(container, SWT.NONE);
		grpInformacinDeInsumos.setLayout(new GridLayout(2, false));
		GridData gd_grpInformacinDeInsumos = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpInformacinDeInsumos.heightHint = 205;
		grpInformacinDeInsumos.setLayoutData(gd_grpInformacinDeInsumos);
		grpInformacinDeInsumos.setText("Informaci\u00F3n de Insumos");
		
		Label lblInsumos = new Label(grpInformacinDeInsumos, SWT.RIGHT);
		GridData gd_lblInsumos = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblInsumos.widthHint = 124;
		lblInsumos.setLayoutData(gd_lblInsumos);
		lblInsumos.setText("Insumos");
		
		Combo combo = new Combo(grpInformacinDeInsumos, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpInformacinDeInsumos, SWT.NONE);
		
		List list_1 = new List(grpInformacinDeInsumos, SWT.BORDER);
		GridData gd_list_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_list_1.heightHint = 155;
		list_1.setLayoutData(gd_list_1);

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
		return new Point(644, 610);
	}
}
