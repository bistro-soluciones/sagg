package com.bistro.sagg.employees.ui.dialogs;

import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Position;
import com.bistro.sagg.core.model.location.City;
import com.bistro.sagg.core.model.location.Country;
import com.bistro.sagg.core.model.location.State;
import com.bistro.sagg.core.services.EmployeeServices;
import com.bistro.sagg.core.services.FranchiseServices;
import com.bistro.sagg.core.services.RefdataServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.employees.ui.viewers.CityComboContentProvider;
import com.bistro.sagg.employees.ui.viewers.CityComboLabelProvider;
import com.bistro.sagg.employees.ui.viewers.PositionComboContentProvider;
import com.bistro.sagg.employees.ui.viewers.PositionComboLabelProvider;
import com.bistro.sagg.employees.ui.viewers.StateComboContentProvider;
import com.bistro.sagg.employees.ui.viewers.StateComboLabelProvider;

public class NewEmployeeDialog extends Dialog {
	
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.employees.ui.dialogs.NewEmployeeDialog";
	
	private Text textNombres;
	private Text textApellidos;
	private Text textRut;
	private DateTime dateTimeFechaDeIngreso;
	private Text textCorreoElectronico;
	private Text textTelefono;
	private Text textCelular;
	private Text textDireccionL1;
	private Text textDireccionL2;

	private Position selectedPosition;
	private City selectedCity;
	
	private Country country;
	private FranchiseBranch franchiseBranch;
	
	private EmployeeServices employeeService = (EmployeeServices) SaggServiceLocator.getInstance()
			.lookup(EmployeeServices.class.getName());
	private FranchiseServices franchiseService = (FranchiseServices) SaggServiceLocator.getInstance()
			.lookup(FranchiseServices.class.getName());
	private RefdataServices refdataService = (RefdataServices) SaggServiceLocator.getInstance()
			.lookup(RefdataServices.class.getName());

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewEmployeeDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MIN | SWT.MAX | SWT.TITLE);
		
		// TODO replace by session franchised information
		this.country = new Country();
		this.country.setId(1L);
		this.country.setName("Chile");
		this.franchiseBranch = franchiseService.getById(1L);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.getShell().setText("Nuevo Empleado");
		
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.marginHeight = 14;
		
		Group grpInformacionBasica = new Group(container, SWT.NONE);
		GridLayout gl_grpInformacionBasica = new GridLayout(2, false);
		gl_grpInformacionBasica.marginTop = 10;
		gl_grpInformacionBasica.marginLeft = 5;
		gl_grpInformacionBasica.marginRight = 5;
		gl_grpInformacionBasica.marginHeight = 0;
		gl_grpInformacionBasica.marginWidth = 0;
		grpInformacionBasica.setLayout(gl_grpInformacionBasica);
		GridData gd_grpInformacionBasica = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpInformacionBasica.heightHint = 75;
		gd_grpInformacionBasica.widthHint = 604;
		grpInformacionBasica.setLayoutData(gd_grpInformacionBasica);
		grpInformacionBasica.setText("Informaci\u00F3n B\u00E1sica");
		
		Label lblNombres = new Label(grpInformacionBasica, SWT.NONE);
		lblNombres.setAlignment(SWT.RIGHT);
		GridData gd_lblNombres = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNombres.widthHint = 124;
		lblNombres.setLayoutData(gd_lblNombres);
		lblNombres.setText("Nombres");
		
		Composite composite = new Composite(grpInformacionBasica, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		textNombres = new Text(composite, SWT.BORDER);
		textNombres.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblApellidos = new Label(composite, SWT.NONE);
		lblApellidos.setText("Apellidos");
		
		textApellidos = new Text(composite, SWT.BORDER);
		textApellidos.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRut = new Label(grpInformacionBasica, SWT.RIGHT);
		lblRut.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRut.setText("RUT");
		
		textRut = new Text(grpInformacionBasica, SWT.BORDER);
		GridData gd_textRut = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_textRut.widthHint = 145;
		textRut.setLayoutData(gd_textRut);
		
		Group grpInformacionLaboral = new Group(container, SWT.NONE);
		grpInformacionLaboral.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpInformacionLaboral.setText("Informaci\u00F3n Laboral");
		grpInformacionLaboral.setLayout(new GridLayout(2, false));
		
		Label lblCargo = new Label(grpInformacionLaboral, SWT.RIGHT);
		GridData gd_lblCargo = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblCargo.widthHint = 124;
		lblCargo.setLayoutData(gd_lblCargo);
		lblCargo.setText("Cargo");
		
		ComboViewer comboCargoViewer = new ComboViewer(grpInformacionLaboral, SWT.NONE);
		comboCargoViewer.setContentProvider(new PositionComboContentProvider());
		comboCargoViewer.setLabelProvider(new PositionComboLabelProvider());
		comboCargoViewer.setInput(refdataService);
		Combo comboCargo = comboCargoViewer.getCombo();
		comboCargo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCargoViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedPosition = (Position) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Label lblFechaDeIngreso = new Label(grpInformacionLaboral, SWT.NONE);
		lblFechaDeIngreso.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFechaDeIngreso.setText("Fecha de Ingreso");
		
		dateTimeFechaDeIngreso = new DateTime(grpInformacionLaboral, SWT.DATE | SWT.DROP_DOWN);
		
		Group grpInformacionDeStock = new Group(container, SWT.NONE);
		grpInformacionDeStock.setLayout(new GridLayout(2, false));
		GridData gd_grpInformacionDeStock = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_grpInformacionDeStock.heightHint = 81;
		grpInformacionDeStock.setLayoutData(gd_grpInformacionDeStock);
		grpInformacionDeStock.setText("Informaci\u00F3n de Contacto");
		
		Label lblTelefono = new Label(grpInformacionDeStock, SWT.RIGHT);
		GridData gd_lblTelefono = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblTelefono.widthHint = 124;
		lblTelefono.setLayoutData(gd_lblTelefono);
		lblTelefono.setText("Tel\u00E9fono");
		
		Composite compositeTelefonos = new Composite(grpInformacionDeStock, SWT.NONE);
		GridLayout gl_compositeTelefonos = new GridLayout(3, false);
		gl_compositeTelefonos.marginHeight = 0;
		gl_compositeTelefonos.marginWidth = 0;
		compositeTelefonos.setLayout(gl_compositeTelefonos);
		compositeTelefonos.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		textTelefono = new Text(compositeTelefonos, SWT.BORDER);
		textTelefono.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCelular = new Label(compositeTelefonos, SWT.RIGHT);
		GridData gd_lblCelular = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblCelular.widthHint = 141;
		lblCelular.setLayoutData(gd_lblCelular);
		lblCelular.setText("Celular");
		
		textCelular = new Text(compositeTelefonos, SWT.BORDER);
		textCelular.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCorreoElectronico = new Label(grpInformacionDeStock, SWT.NONE);
		lblCorreoElectronico.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorreoElectronico.setText("Correo Electr\u00F3nico");
		
		textCorreoElectronico = new Text(grpInformacionDeStock, SWT.BORDER);
		textCorreoElectronico.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Group grpInformacionDeDomicililo = new Group(container, SWT.NONE);
		grpInformacionDeDomicililo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		grpInformacionDeDomicililo.setText("Informaci\u00F3n de Domicililo");
		grpInformacionDeDomicililo.setLayout(new GridLayout(2, false));
		
		Label lblDireccion = new Label(grpInformacionDeDomicililo, SWT.RIGHT);
		GridData gd_lblDireccion = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblDireccion.widthHint = 124;
		lblDireccion.setLayoutData(gd_lblDireccion);
		lblDireccion.setText("Direcci\u00F3n");
		
		textDireccionL1 = new Text(grpInformacionDeDomicililo, SWT.BORDER);
		textDireccionL1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(grpInformacionDeDomicililo, SWT.NONE);
		
		textDireccionL2 = new Text(grpInformacionDeDomicililo, SWT.BORDER);
		textDireccionL2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblRegion = new Label(grpInformacionDeDomicililo, SWT.NONE);
		lblRegion.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRegion.setText("Regi\u00F3n");
		
		Composite composite_1 = new Composite(grpInformacionDeDomicililo, SWT.NONE);
		GridLayout gl_composite_1 = new GridLayout(3, false);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.marginHeight = 0;
		composite_1.setLayout(gl_composite_1);
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		ComboViewer comboRegionViewer = new ComboViewer(composite_1, SWT.NONE);
		comboRegionViewer.setContentProvider(new StateComboContentProvider(country));
		comboRegionViewer.setLabelProvider(new StateComboLabelProvider());
		comboRegionViewer.setInput(refdataService);
		Combo comboRegion = comboRegionViewer.getCombo();
		comboRegion.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblComuna = new Label(composite_1, SWT.NONE);
		lblComuna.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblComuna.setText("Comuna");
		
		ComboViewer comboComunaViewer = new ComboViewer(composite_1, SWT.NONE);
		comboComunaViewer.setContentProvider(new CityComboContentProvider());
		comboComunaViewer.setLabelProvider(new CityComboLabelProvider());
		comboComunaViewer.setInput(refdataService);
		Combo comboComuna = comboComunaViewer.getCombo();
		comboComuna.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		comboRegionViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				State state = (State) ((StructuredSelection) event.getSelection()).getFirstElement();
				CityComboContentProvider provider = (CityComboContentProvider) comboComunaViewer.getContentProvider();
				provider.setState(state);
				comboComunaViewer.refresh();
			}
		});
		comboComunaViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedCity = (City) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});

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
		int day = dateTimeFechaDeIngreso.getDay();
		int month = dateTimeFechaDeIngreso.getMonth();
		int year = dateTimeFechaDeIngreso.getYear();
		Date startDate = new GregorianCalendar(year, month, day).getTime();
		
		employeeService.createEmployee(textNombres.getText(), textApellidos.getText(), textRut.getText(), selectedPosition,
				startDate, franchiseBranch, textCorreoElectronico.getText(), textTelefono.getText(),
				textCelular.getText(), textDireccionL1.getText(), textDireccionL2.getText(), selectedCity);
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(644, 578);
	}
}
