package com.bistro.sagg.products.ui.dialogs;

import java.math.BigDecimal;

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
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboLabelProvider;

public class NewMarketableProductDialog extends Dialog {

	private Text nameText;
	private Text stockText;
	private Text minStockText;

	private ProductServices productService = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	private Text unitPriceText;
	private Text unitSalesPriceText;
	private GridLayout gl_stockComposite;
	private GridLayout gl_pricesComposite;

	private ProductCategory selectedCategory;
	
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
		productCategoryComboViewer.setContentProvider(new ProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(productService);
		Combo productCategoryCombo = productCategoryComboViewer.getCombo();
		productCategoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedCategory = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});

		Group stockInfoGroup = new Group(container, SWT.NONE);
		stockInfoGroup.setLayout(new GridLayout(2, false));
		GridData gd_stockInfoGroup = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_stockInfoGroup.widthHint = 540;
		gd_stockInfoGroup.heightHint = 43;
		stockInfoGroup.setLayoutData(gd_stockInfoGroup);
		stockInfoGroup.setText("Informaci\u00F3n de Stock");

		Label stockLabel = new Label(stockInfoGroup, SWT.RIGHT);
		GridData gd_stockLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_stockLabel.widthHint = 124;
		stockLabel.setLayoutData(gd_stockLabel);
		stockLabel.setText("Stock inicial");

		Composite stockComposite = new Composite(stockInfoGroup, SWT.NONE);
		GridLayout gl_composite_1;
		gl_stockComposite = new GridLayout(3, false);
		gl_stockComposite.marginWidth = 0;
		gl_stockComposite.marginHeight = 0;
		stockComposite.setLayout(gl_stockComposite);
		GridData gd_stockComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_stockComposite.widthHint = 400;
		stockComposite.setLayoutData(gd_stockComposite);

		stockText = new Text(stockComposite, SWT.BORDER);
		stockText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblApellidos = new Label(stockComposite, SWT.NONE);
		lblApellidos.setToolTipText("");
		lblApellidos.setText("Stock M\u00EDnimo Requerido");

		minStockText = new Text(stockComposite, SWT.BORDER);
		minStockText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Group pricesInfoGroup = new Group(container, SWT.NONE);
		pricesInfoGroup.setLayout(new GridLayout(2, false));
		GridData gd_pricesInfoGroup = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_pricesInfoGroup.heightHint = 43;
		pricesInfoGroup.setLayoutData(gd_pricesInfoGroup);
		pricesInfoGroup.setText("Informaci\u00F3n de Mercado");

		Label unitPriceLabel = new Label(pricesInfoGroup, SWT.RIGHT);
		GridData gd_unitPriceLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_unitPriceLabel.widthHint = 124;
		unitPriceLabel.setLayoutData(gd_unitPriceLabel);
		unitPriceLabel.setText("Precio Unitario");

		Composite pricesComposite = new Composite(pricesInfoGroup, SWT.NONE);
		GridData gd_pricesComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_pricesComposite.widthHint = 400;
		pricesComposite.setLayoutData(gd_pricesComposite);
		gl_pricesComposite = new GridLayout(3, false);
		gl_pricesComposite.marginWidth = 0;
		gl_pricesComposite.marginHeight = 0;
		pricesComposite.setLayout(gl_pricesComposite);

		unitPriceText = new Text(pricesComposite, SWT.BORDER);
		unitPriceText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label unitSalesPriceLabel = new Label(pricesComposite, SWT.NONE);
		unitSalesPriceLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		unitSalesPriceLabel.setToolTipText("");
		unitSalesPriceLabel.setText("Precio Unitario de Venta");

		unitSalesPriceText = new Text(pricesComposite, SWT.BORDER);
		unitSalesPriceText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

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
		productService.createMarketableProduct(nameText.getText(), selectedCategory,
				Integer.parseInt(stockText.getText()), Integer.parseInt(minStockText.getText()),
				new BigDecimal(unitPriceText.getText()), new BigDecimal(unitSalesPriceText.getText()));
		super.okPressed();
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(580, 379);
	}
}
