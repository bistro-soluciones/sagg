package com.bistro.sagg.employees.ui.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.company.employees.Position;
import com.bistro.sagg.core.model.location.City;
import com.bistro.sagg.core.model.location.Country;
import com.bistro.sagg.core.model.location.State;
import com.bistro.sagg.core.osgi.ui.utils.ErrorMessageUtils;
import com.bistro.sagg.core.services.EmployeeServices;
import com.bistro.sagg.core.services.RefdataServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.core.validation.processor.ListValidatorProcessor;
import com.bistro.sagg.core.validation.validator.AndValidator;
import com.bistro.sagg.core.validation.validator.EmailValidator;
import com.bistro.sagg.core.validation.validator.EmptyOrNullValidator;
import com.bistro.sagg.core.validation.validator.RUTValidator;
import com.bistro.sagg.core.validation.validator.SaggValidator;
import com.bistro.sagg.employees.ui.utils.EmployeesCommunicationConstants;
import com.bistro.sagg.employees.ui.viewers.CityComboContentProvider;
import com.bistro.sagg.employees.ui.viewers.CityComboLabelProvider;
import com.bistro.sagg.employees.ui.viewers.PositionComboContentProvider;
import com.bistro.sagg.employees.ui.viewers.PositionComboLabelProvider;
import com.bistro.sagg.employees.ui.viewers.StateComboContentProvider;
import com.bistro.sagg.employees.ui.viewers.StateComboLabelProvider;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class EmployeeDetailView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.employees.ui.views.EmployeeDetailView";

	private Text firstnameText;
	private Text lastnameText;
	private Text personIdText;
	private Text emailText;
	private Text cellphoneText;
	private Text phoneText;
	private Text addressL1Text;
	private Text addressL2Text;

	private Combo positionCombo;
	private ComboViewer positionComboViewer;
	private Combo stateCombo;
	private ComboViewer stateComboViewer;
	private Combo cityCombo;
	private ComboViewer cityComboViewer;

	private Position selectedPosition;
	private City selectedCity;
	private Country country;
	private FranchiseBranch franchiseBranch;

	private EmployeeServices employeeService = (EmployeeServices) SaggServiceLocator.getInstance()
			.lookup(EmployeeServices.class.getName());
	private RefdataServices refdataService = (RefdataServices) SaggServiceLocator.getInstance()
			.lookup(RefdataServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public EmployeeDetailView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(EmployeeDetailView.class).getBundleContext();
		ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
		this.eventAdmin = bundleContext.getService(ref);
		this.franchiseBranch = SaggSession.getCurrentSession()
				.getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		this.country = SaggSession.getCurrentSession()
				.getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH_COUNTRY);
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Group basicInfoGroup = new Group(parent, SWT.NONE);
		basicInfoGroup.setLayout(new GridLayout(2, false));
		basicInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		basicInfoGroup.setText("Informaci\u00F3n B\u00E1sica");

		Label firstnameLabel = new Label(basicInfoGroup, SWT.RIGHT);
		GridData gd_firstnameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_firstnameLabel.widthHint = 130;
		firstnameLabel.setLayoutData(gd_firstnameLabel);
		firstnameLabel.setText("Nombres *");
		firstnameLabel.setAlignment(SWT.RIGHT);

		firstnameText = new Text(basicInfoGroup, SWT.BORDER);
		firstnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lastnameLabel = new Label(basicInfoGroup, SWT.RIGHT);
		GridData gd_lastnameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lastnameLabel.widthHint = 130;
		lastnameLabel.setLayoutData(gd_lastnameLabel);
		lastnameLabel.setText("Apellidos *");

		lastnameText = new Text(basicInfoGroup, SWT.BORDER);
		lastnameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label personIdLabel = new Label(basicInfoGroup, SWT.RIGHT);
		GridData gd_personIdLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_personIdLabel.widthHint = 130;
		personIdLabel.setLayoutData(gd_personIdLabel);
		personIdLabel.setText("RUT *");

		personIdText = new Text(basicInfoGroup, SWT.BORDER);
		// personIdText.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent e) {
		// String string = e.text;
		// Matcher matcher = Pattern.compile("[0-9]*+$").matcher(string);
		// if (!matcher.matches()) {
		// e.doit = false;
		// return;
		// }
		// }
		// });
		personIdText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group workingInfoGroup = new Group(parent, SWT.NONE);
		workingInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		workingInfoGroup.setText("Informaci\u00F3n laboral");
		workingInfoGroup.setLayout(new GridLayout(2, false));

		Label positionLabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_positionLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_positionLabel.widthHint = 130;
		positionLabel.setLayoutData(gd_positionLabel);
		positionLabel.setText("Cargo *");

		positionComboViewer = new ComboViewer(workingInfoGroup, SWT.NONE);
		positionComboViewer.setContentProvider(new PositionComboContentProvider());
		positionComboViewer.setLabelProvider(new PositionComboLabelProvider());
		positionComboViewer.setInput(refdataService);
		positionCombo = positionComboViewer.getCombo();
		positionCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		positionComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedPosition = (Position) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});

		Label startDateLabel = new Label(workingInfoGroup, SWT.RIGHT);
		GridData gd_startDateLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_startDateLabel.widthHint = 130;
		startDateLabel.setLayoutData(gd_startDateLabel);
		startDateLabel.setText("Fecha de Ingreso *");

		DateTime startDateDateTime = new DateTime(workingInfoGroup, SWT.DROP_DOWN);

		Group contactInfoGroup = new Group(parent, SWT.NONE);
		contactInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		contactInfoGroup.setText("Informaci\u00F3n de Contacto");
		contactInfoGroup.setLayout(new GridLayout(2, false));

		Label phoneLabel = new Label(contactInfoGroup, SWT.RIGHT);
		GridData gd_phoneLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_phoneLabel.widthHint = 130;
		phoneLabel.setLayoutData(gd_phoneLabel);
		phoneLabel.setText("Tel\u00E9fono");

		phoneText = new Text(contactInfoGroup, SWT.BORDER);
		phoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label cellphoneLabel = new Label(contactInfoGroup, SWT.RIGHT);
		GridData gd_cellphoneLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_cellphoneLabel.widthHint = 130;
		cellphoneLabel.setLayoutData(gd_cellphoneLabel);
		cellphoneLabel.setText("Celular");

		cellphoneText = new Text(contactInfoGroup, SWT.BORDER);
		cellphoneText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label emailLabel = new Label(contactInfoGroup, SWT.RIGHT);
		GridData gd_emailLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_emailLabel.widthHint = 130;
		emailLabel.setLayoutData(gd_emailLabel);
		emailLabel.setText("Correo Electr\u00F3nico");

		emailText = new Text(contactInfoGroup, SWT.BORDER);
		emailText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		// emailText.addVerifyListener(new VerifyListener() {
		// public void verifyText(VerifyEvent e) {
		// String string = e.text;
		// Matcher matcher =
		// Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
		// Pattern.CASE_INSENSITIVE).matcher(string);
		// if (!matcher.matches()) {
		// e.doit = false;
		// return;
		// }
		// }
		// });

		Group addressInfoGroup = new Group(parent, SWT.NONE);
		addressInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addressInfoGroup.setText("Informaci\u00F3n de Domicilio");
		addressInfoGroup.setLayout(new GridLayout(2, false));

		Label addressLabel = new Label(addressInfoGroup, SWT.RIGHT);
		GridData gd_addressLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_addressLabel.widthHint = 130;
		addressLabel.setLayoutData(gd_addressLabel);
		addressLabel.setText("Direcci\u00F3n");

		addressL1Text = new Text(addressInfoGroup, SWT.BORDER);
		addressL1Text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(addressInfoGroup, SWT.NONE);

		addressL2Text = new Text(addressInfoGroup, SWT.BORDER);
		addressL2Text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label stateLabel = new Label(addressInfoGroup, SWT.RIGHT);
		GridData gd_stateLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_stateLabel.widthHint = 130;
		stateLabel.setLayoutData(gd_stateLabel);
		stateLabel.setText("Regi\u00F3n");

		stateComboViewer = new ComboViewer(addressInfoGroup, SWT.NONE);
		stateComboViewer.setContentProvider(new StateComboContentProvider(country));
		stateComboViewer.setLabelProvider(new StateComboLabelProvider());
		stateComboViewer.setInput(refdataService);
		stateComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				State state = (State) ((StructuredSelection) event.getSelection()).getFirstElement();
				CityComboContentProvider provider = (CityComboContentProvider) cityComboViewer.getContentProvider();
				provider.setState(state);
				cityComboViewer.refresh();
				cityCombo.setEnabled(true);
			}
		});
		stateCombo = stateComboViewer.getCombo();
		stateCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label cityLabel = new Label(addressInfoGroup, SWT.RIGHT);
		GridData gd_cityLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_cityLabel.widthHint = 130;
		cityLabel.setLayoutData(gd_cityLabel);
		cityLabel.setText("Comuna");

		cityComboViewer = new ComboViewer(addressInfoGroup, SWT.NONE);
		cityComboViewer.setContentProvider(new CityComboContentProvider());
		cityComboViewer.setLabelProvider(new CityComboLabelProvider());
		cityComboViewer.setInput(refdataService);
		cityComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedCity = (City) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		cityCombo = cityComboViewer.getCombo();
		cityCombo.setEnabled(false);
		cityCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		composite.setLayout(new GridLayout(2, false));

		Button cancelButton = new Button(composite, SWT.NONE);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetDefaultValues();
			}
		});
		cancelButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		cancelButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		cancelButton.setText("Cancelar");

		Button saveButton = new Button(composite, SWT.NONE);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (validateFields(parent.getShell())) {
					int day = startDateDateTime.getDay();
					int month = startDateDateTime.getMonth();
					int year = startDateDateTime.getYear();
					Date startDate = new GregorianCalendar(year, month, day).getTime();

					employeeService.createEmployee(firstnameText.getText(), lastnameText.getText(),
							personIdText.getText(), selectedPosition, startDate, franchiseBranch, emailText.getText(),
							phoneText.getText(), cellphoneText.getText(), addressL1Text.getText(),
							addressL2Text.getText(), selectedCity);
					Event event = new Event(EmployeesCommunicationConstants.ADD_EMPLOYEE_EVENT,
							new HashMap<String, Object>());
					eventAdmin.sendEvent(event);
					resetDefaultValues();
				}
			}
		});
		saveButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		saveButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		saveButton.setText("Guardar");

		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private boolean validateFields(Shell shell) {
		ListValidatorProcessor processor = getEmployeeValidatorProcessor();
		boolean result = processor.processValidation();
		if (!result) {
			MessageDialog.openError(shell, "Error", processor.getErrorMessage());
		}
		return result;
	}

	private ListValidatorProcessor getEmployeeValidatorProcessor() {
		List<SaggValidator> validators = new ArrayList<>();
		validators.add(new EmptyOrNullValidator(firstnameText.getText(),
				ErrorMessageUtils.createMandatoryFieldErrorMsg("Nombres")));
		validators.add(new EmptyOrNullValidator(lastnameText.getText(),
				ErrorMessageUtils.createMandatoryFieldErrorMsg("Apellidos")));
		validators.add(new AndValidator(personIdText.getText(),
				new EmptyOrNullValidator(personIdText.getText(), ErrorMessageUtils.createMandatoryFieldErrorMsg("RUT")),
				new RUTValidator(personIdText.getText(), ErrorMessageUtils.createWrongFieldValueErrorMsg("RUT"))));
		validators.add(new EmptyOrNullValidator(positionCombo.getText(),
				ErrorMessageUtils.createMandatoryFieldErrorMsg("Cargo")));
		if (!"".equals(emailText.getText())) {
			validators.add(new EmailValidator(emailText.getText(),
					ErrorMessageUtils.createWrongFieldValueErrorMsg("Correo Electr\u00F3nico")));
		}
		ListValidatorProcessor processor = new ListValidatorProcessor(validators);
		return processor;
	}

	private void resetDefaultValues() {
		firstnameText.setText("");
		lastnameText.setText("");
		personIdText.setText("");
		emailText.setText("");
		phoneText.setText("");
		cellphoneText.setText("");
		addressL1Text.setText("");
		addressL2Text.setText("");
		positionCombo.setText("");
		stateCombo.setText("");
		cityCombo.setText("");
		cityCombo.setEnabled(false);
		selectedPosition = null;
		selectedCity = null;
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				EmployeeDetailView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
	}

	private void fillContextMenu(IMenuManager manager) {
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
	}

	private void makeActions() {
		// doubleClickAction = new Action() {
		// public void run() {
		// ISelection selection = viewer.getSelection();
		// Object obj = ((IStructuredSelection)selection).getFirstElement();
		// showMessage("Double-click detected on "+obj.toString());
		// }
		// };
	}

	private void hookDoubleClickAction() {
	}

	private void showMessage(String message) {
		// MessageDialog.openInformation(
		// viewer.getControl().getShell(),
		// "Product View",
		// message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		// viewer.getControl().setFocus();
	}
}
