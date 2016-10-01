package com.bistro.sagg.products.ui.dialogs;

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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.bistro.sagg.core.model.billing.DocumentType;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.services.BillingServices;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.services.SupplierServices;
import com.bistro.sagg.products.ui.viewers.BillilngDocumentTypeComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.BillingDocumentTypeComboContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.ProductComboContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.SupplierComboContentProvider;
import com.bistro.sagg.products.ui.viewers.SupplierComboLabelProvider;

public class InventoryLoadingDialog extends Dialog {
	
	private Table resumeTable;
	private Text billingNumberText;
	private Text quantityText;
	private Text unitPriceText;
	
	private BillingServices billingServices = (BillingServices) SaggServiceLocator.getInstance()
			.lookup(BillingServices.class.getName());
	private SupplierServices supplierServices = (SupplierServices) SaggServiceLocator.getInstance()
			.lookup(SupplierServices.class.getName());
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());

	private DocumentType selectedBillingDocumentType;
	private Supplier selectedSupplier;
	private Product selectedProduct;

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
		
		Group basicInfoGroup = new Group(container, SWT.NONE);
		GridLayout gl_basicInfoGroup = new GridLayout(2, false);
		gl_basicInfoGroup.marginTop = 10;
		gl_basicInfoGroup.marginLeft = 5;
		gl_basicInfoGroup.marginRight = 5;
		gl_basicInfoGroup.marginHeight = 0;
		gl_basicInfoGroup.marginWidth = 0;
		basicInfoGroup.setLayout(gl_basicInfoGroup);
		GridData gd_basicInfoGroup = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_basicInfoGroup.heightHint = 80;
		gd_basicInfoGroup.widthHint = 604;
		basicInfoGroup.setLayoutData(gd_basicInfoGroup);
		basicInfoGroup.setText("Informaci\u00F3n B\u00E1sica");
		
		Label billingDocumentLabel = new Label(basicInfoGroup, SWT.NONE);
		billingDocumentLabel.setAlignment(SWT.RIGHT);
		GridData gd_billingDocumentLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_billingDocumentLabel.widthHint = 124;
		billingDocumentLabel.setLayoutData(gd_billingDocumentLabel);
		billingDocumentLabel.setText("Documento");
		
		Composite billingInfoComposite = new Composite(basicInfoGroup, SWT.NONE);
		billingInfoComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_billingInfoComposite = new GridLayout(3, false);
		gl_billingInfoComposite.marginHeight = 0;
		gl_billingInfoComposite.marginWidth = 0;
		billingInfoComposite.setLayout(gl_billingInfoComposite);
		
		ComboViewer billingDocumentComboViewer = new ComboViewer(billingInfoComposite, SWT.NONE);
		Combo billingDocumentCombo = billingDocumentComboViewer.getCombo();
		billingDocumentCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		billingDocumentComboViewer.setContentProvider(new BillingDocumentTypeComboContentProvider());
		billingDocumentComboViewer.setLabelProvider(new BillilngDocumentTypeComboLabelProvider());
		billingDocumentComboViewer.setInput(billingServices);
		billingDocumentComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedBillingDocumentType = (DocumentType) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Label billingNrLabel = new Label(billingInfoComposite, SWT.NONE);
		billingNrLabel.setText("Nro.");
		
		billingNumberText = new Text(billingInfoComposite, SWT.BORDER);
		billingNumberText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label supplierLabel = new Label(basicInfoGroup, SWT.NONE);
		supplierLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		supplierLabel.setText("Proveedor");
		
		ComboViewer supplierComboViewer = new ComboViewer(basicInfoGroup, SWT.NONE);
		supplierComboViewer.setContentProvider(new SupplierComboContentProvider());
		supplierComboViewer.setLabelProvider(new SupplierComboLabelProvider());
		supplierComboViewer.setInput(supplierServices);
		Combo supplierCombo = supplierComboViewer.getCombo();
		supplierCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		supplierComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedSupplier = (Supplier) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Group inventoryInfoGroup = new Group(container, SWT.NONE);
		inventoryInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		inventoryInfoGroup.setText("Informaci\u00F3n de Inventario");
		inventoryInfoGroup.setLayout(new GridLayout(2, false));
		
		Label productLabel = new Label(inventoryInfoGroup, SWT.NONE);
		GridData gd_productLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_productLabel.widthHint = 124;
		productLabel.setLayoutData(gd_productLabel);
		productLabel.setAlignment(SWT.RIGHT);
		productLabel.setText("Insumo");
		
		Composite productComposite = new Composite(inventoryInfoGroup, SWT.NONE);
		GridLayout gl_productComposite = new GridLayout(2, false);
		gl_productComposite.marginHeight = 0;
		gl_productComposite.marginWidth = 0;
		productComposite.setLayout(gl_productComposite);
		GridData gd_productComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_productComposite.widthHint = 465;
		productComposite.setLayoutData(gd_productComposite);
		
		ComboViewer productCategoryComboViewer = new ComboViewer(productComposite, SWT.NONE);
		productCategoryComboViewer.setContentProvider(new ProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(productServices);
		Combo productCategoryCombo = productCategoryComboViewer.getCombo();
		productCategoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		ComboViewer productComboViewer = new ComboViewer(productComposite, SWT.NONE);
		productComboViewer.setContentProvider(new ProductComboContentProvider());
		productComboViewer.setLabelProvider(new ProductComboLabelProvider());
		productComboViewer.setInput(productServices);
		Combo productCombo = productComboViewer.getCombo();
		productCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ProductCategory category = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
				ProductComboContentProvider provider = (ProductComboContentProvider) productComboViewer.getContentProvider();
				provider.setCategory(category);
				productComboViewer.refresh();
			}
		});
		productComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedProduct = (Product) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Label quantityLabel = new Label(inventoryInfoGroup, SWT.NONE);
		quantityLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		quantityLabel.setAlignment(SWT.RIGHT);
		quantityLabel.setText("Cantidad");
		
		Composite productInfoComposite = new Composite(inventoryInfoGroup, SWT.NONE);
		productInfoComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_productInfoComposite = new GridLayout(4, false);
		gl_productInfoComposite.marginWidth = 0;
		gl_productInfoComposite.marginHeight = 0;
		productInfoComposite.setLayout(gl_productInfoComposite);
		
		quantityText = new Text(productInfoComposite, SWT.BORDER);
		quantityText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label unitPriceLabel = new Label(productInfoComposite, SWT.NONE);
		unitPriceLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		unitPriceLabel.setText("Precio Unitario");
		
		unitPriceText = new Text(productInfoComposite, SWT.BORDER);
		unitPriceText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button ivaCheckButton = new Button(productInfoComposite, SWT.CHECK);
		ivaCheckButton.setText("Incluye IVA");
		
		Group resumeGroup = new Group(container, SWT.NONE);
		GridData gd_resumeGroup = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_resumeGroup.heightHint = 358;
		resumeGroup.setLayoutData(gd_resumeGroup);
		resumeGroup.setText("Resumen");
		resumeGroup.setLayout(new GridLayout(1, false));
		
		resumeTable = new Table(resumeGroup, SWT.BORDER | SWT.FULL_SELECTION);
		GridData gd_resumeTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_resumeTable.heightHint = 198;
		gd_resumeTable.widthHint = 572;
		resumeTable.setLayoutData(gd_resumeTable);
		resumeTable.setHeaderVisible(true);
		resumeTable.setLinesVisible(true);
		
		TableColumn tblclmnNombre = new TableColumn(resumeTable, SWT.NONE);
		tblclmnNombre.setWidth(170);
		tblclmnNombre.setText("Nombre");
		
		TableColumn tblclmnCategora = new TableColumn(resumeTable, SWT.NONE);
		tblclmnCategora.setWidth(170);
		tblclmnCategora.setText("Categor\u00EDa");
		
		TableColumn tblclmnCantidad = new TableColumn(resumeTable, SWT.NONE);
		tblclmnCantidad.setWidth(80);
		tblclmnCantidad.setText("Cantidad");
		
		TableColumn tblclmnPrecioUnitario = new TableColumn(resumeTable, SWT.NONE);
		tblclmnPrecioUnitario.setWidth(115);
		tblclmnPrecioUnitario.setText("Precio Unitario");
		
		TableColumn tblclmnTotal = new TableColumn(resumeTable, SWT.NONE);
		tblclmnTotal.setWidth(58);
		tblclmnTotal.setText("Total");
		
		Composite composite_5 = new Composite(resumeGroup, SWT.NONE);
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
		
		Label subtotalAmountLabel = new Label(composite_5, SWT.RIGHT);
		GridData gd_subtotalAmountLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_subtotalAmountLabel.widthHint = 75;
		subtotalAmountLabel.setLayoutData(gd_subtotalAmountLabel);
		subtotalAmountLabel.setText("Subtotal");
		
		Label subtotalAmountNrLabel = new Label(composite_5, SWT.RIGHT);
		GridData gd_subtotalAmountNrLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_subtotalAmountNrLabel.widthHint = 40;
		subtotalAmountNrLabel.setLayoutData(gd_subtotalAmountNrLabel);
		subtotalAmountNrLabel.setText("0");
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
		
		Label ivaLabel = new Label(composite_5, SWT.RIGHT);
		GridData gd_ivaLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_ivaLabel.widthHint = 75;
		ivaLabel.setLayoutData(gd_ivaLabel);
		ivaLabel.setText("IVA 19%");
		
		Label ivaPercentageLabel = new Label(composite_5, SWT.RIGHT);
		GridData gd_ivaPercentageLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_ivaPercentageLabel.widthHint = 40;
		ivaPercentageLabel.setLayoutData(gd_ivaPercentageLabel);
		ivaPercentageLabel.setText("0");
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
		
		Label totalAmountLabel = new Label(composite_5, SWT.RIGHT);
		GridData gd_totalAmountLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_totalAmountLabel.widthHint = 75;
		totalAmountLabel.setLayoutData(gd_totalAmountLabel);
		totalAmountLabel.setText("Total");
		
		Label totalAmountNrLabel = new Label(composite_5, SWT.RIGHT);
		GridData gd_totalAmountNrLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_totalAmountNrLabel.widthHint = 40;
		totalAmountNrLabel.setLayoutData(gd_totalAmountNrLabel);
		totalAmountNrLabel.setText("0");
		
		Label currencyLabel = new Label(composite_5, SWT.NONE);
		currencyLabel.setText("CLP");

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
//		int day = dateTimeFechaDeIngreso.getDay();
//		int month = dateTimeFechaDeIngreso.getMonth();
//		int year = dateTimeFechaDeIngreso.getYear();
//		Date startDate = new GregorianCalendar(year, month, day).getTime();
//		
//		employeeService.createEmployee(textNombres.getText(), textApellidos.getText(), textRut.getText(), selectedPosition,
//				startDate, franchiseBranch, textCorreoElectronico.getText(), textTelefono.getText(),
//				textCelular.getText(), textDireccionL1.getText(), textDireccionL2.getText(), selectedCity);
		super.okPressed();
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(647, 727);
	}
}
