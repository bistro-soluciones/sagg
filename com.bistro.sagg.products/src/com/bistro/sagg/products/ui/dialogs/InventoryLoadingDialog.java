package com.bistro.sagg.products.ui.dialogs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.bistro.sagg.core.factory.BillingItemFactory;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.order.billing.BillingItem;
import com.bistro.sagg.core.model.order.billing.DocumentType;
import com.bistro.sagg.core.model.order.billing.PurchaseBillingItem;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.osgi.ui.viewers.FromListContentProvider;
import com.bistro.sagg.core.services.BillingServices;
import com.bistro.sagg.core.services.FranchiseServices;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.services.SupplierServices;
import com.bistro.sagg.products.ui.viewers.BillingDocumentTypeComboContentProvider;
import com.bistro.sagg.products.ui.viewers.BillingDocumentTypeComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.BillingItemListLabelProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.ProductComboContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.SupplierComboContentProvider;
import com.bistro.sagg.products.ui.viewers.SupplierComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.SupplierProductCategoryComboContentProvider;

public class InventoryLoadingDialog extends Dialog {
	
	private Table resumeTable;
	private Text billingNumberText;
	private Text quantityText;
	private Text unitPriceText;
	
	private Combo supplierCombo;
	private Combo productCategoryCombo;
	private Combo productCombo;
	
	private Button addProductButton;
	
	private BillingServices billingServices = (BillingServices) SaggServiceLocator.getInstance()
			.lookup(BillingServices.class.getName());
	private FranchiseServices franchiseService = (FranchiseServices) SaggServiceLocator.getInstance()
			.lookup(FranchiseServices.class.getName());
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	private SupplierServices supplierServices = (SupplierServices) SaggServiceLocator.getInstance()
			.lookup(SupplierServices.class.getName());

	private DocumentType selectedBillingDocumentType;
	private Supplier selectedSupplier = new Supplier();
	private Product selectedProduct;
	private List<BillingItem> items = new ArrayList<BillingItem>();
	private FranchiseBranch franchiseBranch;

	private BigDecimal subtotal = BigDecimal.ZERO;
	private BigDecimal iva = BigDecimal.ZERO;
	private BigDecimal total = BigDecimal.ZERO;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public InventoryLoadingDialog(Shell parentShell) {
		super(parentShell);
		this.franchiseBranch = franchiseService.getById(1L);
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
		billingDocumentCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				billingNumberText.setEnabled(true);
				supplierCombo.setEnabled(true);
			}
		});
		billingDocumentCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		billingDocumentComboViewer.setContentProvider(new BillingDocumentTypeComboContentProvider());
		billingDocumentComboViewer.setLabelProvider(new BillingDocumentTypeComboLabelProvider());
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
		billingNumberText.setEnabled(false);
		billingNumberText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label supplierLabel = new Label(basicInfoGroup, SWT.NONE);
		supplierLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		supplierLabel.setText("Proveedor");
		
		ComboViewer supplierComboViewer = new ComboViewer(basicInfoGroup, SWT.NONE);
		supplierComboViewer.setContentProvider(new SupplierComboContentProvider());
		supplierComboViewer.setLabelProvider(new SupplierComboLabelProvider());
		supplierComboViewer.setInput(supplierServices);
		supplierCombo = supplierComboViewer.getCombo();
		supplierCombo.setEnabled(false);
		supplierCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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
		productCategoryComboViewer.setContentProvider(new SupplierProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(selectedSupplier);
		productCategoryCombo = productCategoryComboViewer.getCombo();
		productCategoryCombo.setEnabled(false);
		productCategoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		ComboViewer productComboViewer = new ComboViewer(productComposite, SWT.NONE);
		productComboViewer.setContentProvider(new ProductComboContentProvider());
		productComboViewer.setLabelProvider(new ProductComboLabelProvider());
		productComboViewer.setInput(productServices);
		productCombo = productComboViewer.getCombo();
		productCombo.setEnabled(false);
		productCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		productComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedProduct = (Product) ((StructuredSelection) event.getSelection()).getFirstElement();
				quantityText.setEnabled(true);
				unitPriceText.setEnabled(true);
				addProductButton.setEnabled(true);
			}
		});
		
		supplierComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedSupplier = (Supplier) ((StructuredSelection) event.getSelection()).getFirstElement();
				productCategoryComboViewer.setInput(selectedSupplier);
				productCategoryComboViewer.refresh();
				productCategoryCombo.setEnabled(true);
			}
		});
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ProductCategory category = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
				ProductComboContentProvider provider = (ProductComboContentProvider) productComboViewer.getContentProvider();
				provider.setCategory(category);
				productComboViewer.refresh();
				productCombo.setEnabled(true);
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
		quantityText.setText("0");
		quantityText.setEnabled(false);
		quantityText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label unitPriceLabel = new Label(productInfoComposite, SWT.NONE);
		unitPriceLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		unitPriceLabel.setText("Precio Unitario");
		
		unitPriceText = new Text(productInfoComposite, SWT.BORDER);
		unitPriceText.setText("0");
		unitPriceText.setEnabled(false);
		unitPriceText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button ivaCheckButton = new Button(productInfoComposite, SWT.CHECK);
		ivaCheckButton.setEnabled(false);
		ivaCheckButton.setText("Incluye IVA");
		new Label(inventoryInfoGroup, SWT.NONE);
		
		addProductButton = new Button(inventoryInfoGroup, SWT.NONE);
		addProductButton.setEnabled(false);
		addProductButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		addProductButton.setText("Agregar");
		
		Group resumeGroup = new Group(container, SWT.NONE);
		GridData gd_resumeGroup = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_resumeGroup.heightHint = 358;
		resumeGroup.setLayoutData(gd_resumeGroup);
		resumeGroup.setText("Resumen");
		resumeGroup.setLayout(new GridLayout(1, false));
		
		TableViewer resumeTableViewer = new TableViewer(resumeGroup, SWT.BORDER | SWT.FULL_SELECTION);
		resumeTableViewer.setContentProvider(new FromListContentProvider());
		resumeTableViewer.setLabelProvider(new BillingItemListLabelProvider());
//		resumeTableViewer.setSorter(new ProductCategoryListSorter());
		resumeTable = resumeTableViewer.getTable();
//		resumeTable = new Table(resumeGroup, SWT.BORDER | SWT.FULL_SELECTION);
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
		
		Composite composite = new Composite(resumeGroup, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 78;
		composite.setLayoutData(gd_composite);
		
		Label subtotalAmountNrLabel = new Label(composite, SWT.RIGHT);
		subtotalAmountNrLabel.setBounds(512, 10, 75, 20);
		subtotalAmountNrLabel.setText("0");
		
		Label ivaPercentageLabel = new Label(composite, SWT.RIGHT);
		ivaPercentageLabel.setBounds(512, 30, 75, 20);
		ivaPercentageLabel.setText("0");
		
		Label subtotalAmountLabel = new Label(composite, SWT.RIGHT);
		subtotalAmountLabel.setBounds(430, 10, 75, 20);
		subtotalAmountLabel.setText("Subtotal");
		
		Label ivaLabel = new Label(composite, SWT.RIGHT);
		ivaLabel.setBounds(430, 30, 75, 20);
		ivaLabel.setText("IVA 19%");
		
		Label totalAmountLabel = new Label(composite, SWT.RIGHT);
		totalAmountLabel.setBounds(430, 48, 75, 20);
		totalAmountLabel.setText("Total");
		
		Label totalAmountNrLabel = new Label(composite, SWT.RIGHT);
		totalAmountNrLabel.setBounds(547, 48, 40, 20);
		totalAmountNrLabel.setText("0");

		resumeTableViewer.setInput(items);
		addProductButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				addItem();
				recalculateAmounts();
				resetWidgets(productComboViewer, resumeTableViewer);
			}
			
			private void addItem() {
				PurchaseBillingItem item = BillingItemFactory.createPurchaseBillingItem(selectedProduct,
						Integer.parseInt(quantityText.getText()), new BigDecimal(unitPriceText.getText()), false);
				items.add(item);
			}

			private void recalculateAmounts() {
				quantityText.setText("0");
				unitPriceText.setText("0");
				subtotal = BigDecimal.ZERO;
				for (BillingItem item : items) {
					subtotal = subtotal.add(item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
				}
				total = subtotal.multiply(new BigDecimal("1.19"));
				iva = total.subtract(subtotal);
				subtotalAmountNrLabel.setText(String.valueOf(subtotal.intValue()));
				ivaPercentageLabel.setText(String.valueOf(iva.intValue()));
				totalAmountNrLabel.setText(String.valueOf(total.intValue()));
			}
			
			private void resetWidgets(ComboViewer productComboViewer, TableViewer resumeTableViewer) {
				quantityText.setEnabled(false);
				unitPriceText.setEnabled(false);
				supplierCombo.setEnabled(false);
				resumeTableViewer.refresh();
				productComboViewer.setSelection(null);
				productComboViewer.refresh();
				addProductButton.setEnabled(false);
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
//		billingServices.createBillingDocument(selectedBillingDocumentType, billingNumberText.getText(),
//				selectedSupplier, items, franchiseBranch);
//		productServices.increaseProductStock(items);
		super.okPressed();
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(647, 760);
	}
}
