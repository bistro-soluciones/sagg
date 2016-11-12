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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboLabelProvider;

public class NewMarketableProductDialog extends Dialog {

	private Text nameText;

	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());

	private ProductCategory selectedCategory;
	private Text minStockText;
	private Text unitSalesPriceText;
	
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public NewMarketableProductDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MIN | SWT.MAX | SWT.TITLE);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.getShell().setText("Nuevo Proveedor");

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
		gd_basicInfoGroup.heightHint = 75;
		gd_basicInfoGroup.widthHint = 507;
		basicInfoGroup.setLayoutData(gd_basicInfoGroup);
		basicInfoGroup.setText("Informaci\u00F3n B\u00E1sica");

		Label nameLabel = new Label(basicInfoGroup, SWT.NONE);
		nameLabel.setAlignment(SWT.RIGHT);
		GridData gd_nameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_nameLabel.widthHint = 124;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("Nombre");

		nameText = new Text(basicInfoGroup, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label categoryLabel = new Label(basicInfoGroup, SWT.RIGHT);
		categoryLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		categoryLabel.setText("Categor\u00EDa");

		ComboViewer productCategoryComboViewer = new ComboViewer(basicInfoGroup, SWT.NONE);
//		productCategoryComboViewer.setContentProvider(new ProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(productServices);
		Combo productCategoryCombo = productCategoryComboViewer.getCombo();
		productCategoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedCategory = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});

		Group additionalInfoGroup = new Group(container, SWT.NONE);
		additionalInfoGroup.setLayout(new GridLayout(2, false));
		GridData gd_additionalInfoGroup = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_additionalInfoGroup.widthHint = 540;
		gd_additionalInfoGroup.heightHint = 43;
		additionalInfoGroup.setLayoutData(gd_additionalInfoGroup);
		additionalInfoGroup.setText("Informaci\u00F3n Adicional");
		
		Label minStockLabel = new Label(additionalInfoGroup, SWT.RIGHT);
		GridData gd_minStockLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_minStockLabel.widthHint = 124;
		minStockLabel.setLayoutData(gd_minStockLabel);
		minStockLabel.setToolTipText("");
		minStockLabel.setText("Stock M\u00EDnimo");
		
		Composite composite = new Composite(additionalInfoGroup, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		composite.setLayout(gl_composite);
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_composite.widthHint = 400;
		composite.setLayoutData(gd_composite);
		
		minStockText = new Text(composite, SWT.BORDER);
		GridData gd_minStockText = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_minStockText.widthHint = 100;
		minStockText.setLayoutData(gd_minStockText);
		minStockText.setText("0");
		
		Label unitSalesPriceLabel = new Label(composite, SWT.NONE);
		unitSalesPriceLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		unitSalesPriceLabel.setText("Precio Unitario de Venta");
		
		unitSalesPriceText = new Text(composite, SWT.BORDER);
		GridData gd_unitSalesPriceText = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1);
		gd_unitSalesPriceText.widthHint = 100;
		unitSalesPriceText.setLayoutData(gd_unitSalesPriceText);
		unitSalesPriceText.setText("0");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Guardar", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "Cancelar", false);
	}

	@Override
	protected void okPressed() {
//		productServices.createMarketableProduct(nameText.getText(), selectedCategory,
//				Integer.parseInt(minStockText.getText()), new BigDecimal(unitSalesPriceText.getText()));
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(580, 303);
	}
}
