package com.bistro.sagg.suppliers.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.services.SupplierServices;

public class NewSupplierDialog extends Dialog {
	private Text businessNameText;
	private Text supplierIdText;
	private Text firstnameText;
	private Text lastnameText;
	private Text emailText;
	private Text phoneText;
	private Text cellphoneText;

	private SupplierServices supplierService = (SupplierServices) SaggServiceLocator.getInstance()
			.lookup(SupplierServices.class.getName());
	
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
		
		businessNameText = new Text(grpInformacinBsica, SWT.BORDER);
		businessNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRut = new Label(grpInformacinBsica, SWT.RIGHT);
		lblRut.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRut.setText("RUT");
		
		supplierIdText = new Text(grpInformacinBsica, SWT.BORDER);
		GridData gd_supplierIdText = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_supplierIdText.widthHint = 145;
		supplierIdText.setLayoutData(gd_supplierIdText);
		
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
		
		Composite compositeInformacionBsica = new Composite(grpInformacinDeStock, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(3, false);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		compositeInformacionBsica.setLayout(gl_composite_1);
		compositeInformacionBsica.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		firstnameText = new Text(compositeInformacionBsica, SWT.BORDER);
		firstnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblApellidos = new Label(compositeInformacionBsica, SWT.NONE);
		lblApellidos.setText("Apellidos");
		
		lastnameText = new Text(compositeInformacionBsica, SWT.BORDER);
		lastnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblTelfono = new Label(grpInformacinDeStock, SWT.NONE);
		lblTelfono.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTelfono.setText("Tel\u00E9fono");
		
		Composite composite = new Composite(grpInformacinDeStock, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		phoneText = new Text(composite, SWT.BORDER);
		phoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCelular = new Label(composite, SWT.RIGHT);
		GridData gd_lblCelular = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblCelular.widthHint = 141;
		lblCelular.setLayoutData(gd_lblCelular);
		lblCelular.setText("Celular");
		
		cellphoneText = new Text(composite, SWT.BORDER);
		cellphoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCorreoElectrnico = new Label(grpInformacinDeStock, SWT.NONE);
		lblCorreoElectrnico.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorreoElectrnico.setText("Correo Electr\u00F3nico");
		
		emailText = new Text(grpInformacinDeStock, SWT.BORDER);
		emailText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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

	@Override
	protected void okPressed() {
		supplierService.createSupplier(businessNameText.getText(), supplierIdText.getText(), firstnameText.getText(),
				lastnameText.getText(), emailText.getText(), phoneText.getText(), cellphoneText.getText());
		super.okPressed();
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(644, 610);
	}
}
