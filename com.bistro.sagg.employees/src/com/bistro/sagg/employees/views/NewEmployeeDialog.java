package com.bistro.sagg.employees.views;

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
import org.eclipse.swt.widgets.DateTime;

public class NewEmployeeDialog extends Dialog {
	private Text text;
	private Text text_5;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_1;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewEmployeeDialog(Shell parentShell) {
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
		gd_grpInformacinBsica.heightHint = 75;
		gd_grpInformacinBsica.widthHint = 604;
		grpInformacinBsica.setLayoutData(gd_grpInformacinBsica);
		grpInformacinBsica.setText("Informaci\u00F3n B\u00E1sica");
		
		Label lblNombre = new Label(grpInformacinBsica, SWT.NONE);
		lblNombre.setAlignment(SWT.RIGHT);
		GridData gd_lblNombre = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNombre.widthHint = 124;
		lblNombre.setLayoutData(gd_lblNombre);
		lblNombre.setText("Nombre");
		
		text = new Text(grpInformacinBsica, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRut = new Label(grpInformacinBsica, SWT.RIGHT);
		lblRut.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRut.setText("RUT");
		
		text_5 = new Text(grpInformacinBsica, SWT.BORDER);
		GridData gd_text_5 = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_text_5.widthHint = 145;
		text_5.setLayoutData(gd_text_5);
		
		Group grpInformacinLaboral = new Group(container, SWT.NONE);
		grpInformacinLaboral.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpInformacinLaboral.setText("Informaci\u00F3n Laboral");
		grpInformacinLaboral.setLayout(new GridLayout(2, false));
		
		Label lblCargo = new Label(grpInformacinLaboral, SWT.RIGHT);
		GridData gd_lblCargo = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblCargo.widthHint = 124;
		lblCargo.setLayoutData(gd_lblCargo);
		lblCargo.setText("Cargo");
		
		Combo combo_2 = new Combo(grpInformacinLaboral, SWT.NONE);
		combo_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblFechaDeIngreso = new Label(grpInformacinLaboral, SWT.NONE);
		lblFechaDeIngreso.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFechaDeIngreso.setText("Fecha de Ingreso");
		
		DateTime dateTime = new DateTime(grpInformacinLaboral, SWT.BORDER);
		
		Group grpInformacinDeDomicililo = new Group(container, SWT.NONE);
		grpInformacinDeDomicililo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpInformacinDeDomicililo.setText("Informaci\u00F3n de Domicililo");
		grpInformacinDeDomicililo.setLayout(new GridLayout(2, false));
		
		Label lblDatosDeContacto = new Label(grpInformacinDeDomicililo, SWT.RIGHT);
		GridData gd_lblDatosDeContacto = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblDatosDeContacto.widthHint = 124;
		lblDatosDeContacto.setLayoutData(gd_lblDatosDeContacto);
		lblDatosDeContacto.setText("Calle");
		
		text_1 = new Text(grpInformacinDeDomicililo, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRegin = new Label(grpInformacinDeDomicililo, SWT.NONE);
		lblRegin.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRegin.setText("Regi\u00F3n");
		
		Composite composite_1 = new Composite(grpInformacinDeDomicililo, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(3, false);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblComuna = new Label(composite_1, SWT.NONE);
		lblComuna.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComuna.setText("Comuna");
		
		Combo combo_1 = new Combo(composite_1, SWT.NONE);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group grpInformacinDeStock = new Group(container, SWT.NONE);
		grpInformacinDeStock.setLayout(new GridLayout(2, false));
		GridData gd_grpInformacinDeStock = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpInformacinDeStock.heightHint = 81;
		grpInformacinDeStock.setLayoutData(gd_grpInformacinDeStock);
		grpInformacinDeStock.setText("Informaci\u00F3n de Contacto");
		
		Label lblTelfono = new Label(grpInformacinDeStock, SWT.RIGHT);
		GridData gd_lblTelfono = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblTelfono.widthHint = 124;
		lblTelfono.setLayoutData(gd_lblTelfono);
		lblTelfono.setText("Tel\u00E9fono");
		
		Composite composite = new Composite(grpInformacinDeStock, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginHeight = 0;
		gl_composite.marginWidth = 0;
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
		return new Point(645, 525);
	}
}
