package com.bistro.sagg.products.ui.views;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.suppliers.Supplier;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.services.SupplierServices;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.products.ui.utils.ProductsCommunicationConstants;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.ProductListContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductListLabelProvider;
import com.bistro.sagg.products.ui.viewers.SaleProductCategoryComboContentProvider;
import com.bistro.sagg.products.ui.viewers.SupplierComboContentProvider;
import com.bistro.sagg.products.ui.viewers.SupplierComboLabelProvider;

/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ProductSelectionView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.products.ui.views.ProductSelectionView";

	private Combo supplierCombo;
	private ComboViewer productCategoryComboViewer;
	private Combo productCategoryCombo;
	private ListViewer productListViewer;
	private org.eclipse.swt.widgets.List productList;
	private Spinner productQuantitySpinner;
	private Button addProductButton;
	
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	private SupplierServices supplierServices = (SupplierServices) SaggServiceLocator.getInstance()
			.lookup(SupplierServices.class.getName());
	
	private List<ProductCategory> categories;
	private List<MarketableProduct> products = new ArrayList<MarketableProduct>();
	private Supplier selectedSupplier;
	private Product selectedProduct;
	private int selectedProductQuantity;
	private BigDecimal selectedProductUnitPrice;
	
    private BundleContext bundleContext;
	private EventAdmin eventAdmin;
	private Text unitPriceText;

	public ProductSelectionView() {
		super();
//		this.categories = productService.getProductCategories();
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
		this.products = productServices.getMarketableProducts(branch);
		
		this.bundleContext = FrameworkUtil.getBundle(ProductSelectionView.class).getBundleContext();
        ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
        this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		
		createConfirmSaleOrderEventHandler(parent);
		createResetViewEventHandler(parent);
		parent.setLayout(new GridLayout(2, false));
		
		Label lblProveedor = new Label(parent, SWT.RIGHT);
		GridData gd_lblProveedor = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblProveedor.widthHint = 120;
		lblProveedor.setLayoutData(gd_lblProveedor);
		lblProveedor.setText("Proveedor");
		
		ComboViewer supplierComboViewer = new ComboViewer(parent, SWT.NONE);
		supplierComboViewer.setContentProvider(new SupplierComboContentProvider());
		supplierComboViewer.setLabelProvider(new SupplierComboLabelProvider());
		supplierComboViewer.setInput(supplierServices);
		supplierCombo = supplierComboViewer.getCombo();
		supplierCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		supplierComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedSupplier = (Supplier) ((StructuredSelection) event.getSelection()).getFirstElement();
				SaleProductCategoryComboContentProvider provider = (SaleProductCategoryComboContentProvider) productCategoryComboViewer.getContentProvider();
				provider.setSupplier(selectedSupplier);
				productCategoryComboViewer.refresh();
				productCategoryCombo.setEnabled(true);
				productList.setEnabled(false);
				productQuantitySpinner.setEnabled(false);
				unitPriceText.setEnabled(false);
			}
		});
		
		Label productCategoryLabel = new Label(parent, SWT.RIGHT);
		GridData gd_productCategoryLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_productCategoryLabel.widthHint = 120;
		productCategoryLabel.setLayoutData(gd_productCategoryLabel);
		productCategoryLabel.setText("Categor\u00EDa");
		
		productCategoryComboViewer = new ComboViewer(parent, SWT.NONE);
		productCategoryComboViewer.setContentProvider(new SaleProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(productServices);
		productCategoryCombo = productCategoryComboViewer.getCombo();
		productCategoryCombo.setEnabled(false);
		productCategoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ProductCategory category = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
				ProductListContentProvider provider = (ProductListContentProvider) productListViewer.getContentProvider();
				provider.setCategory(category);
				productListViewer.refresh();
				productList.setEnabled(true);
				productQuantitySpinner.setEnabled(false);
				unitPriceText.setEnabled(false);
			}
		});
		
		Label productLabel = new Label(parent, SWT.RIGHT);
		GridData gd_productLabel = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_productLabel.widthHint = 120;
		productLabel.setLayoutData(gd_productLabel);
		productLabel.setText("Producto");
		
		productListViewer = new ListViewer(parent, SWT.BORDER);
		productListViewer.setContentProvider(new ProductListContentProvider());
		productListViewer.setLabelProvider(new ProductListLabelProvider());
		productListViewer.setInput(productServices);
		productList = productListViewer.getList();
		productList.setEnabled(false);
		productList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		productList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				org.eclipse.swt.widgets.List source = (org.eclipse.swt.widgets.List) e.getSource();
				if(source.getSelectionCount() > 0) {
					productQuantitySpinner.setEnabled(true);
					unitPriceText.setEnabled(true);
					productQuantitySpinner.setSelection(0);
					unitPriceText.setText("0");
					selectedProduct = (Product) ((StructuredSelection)productListViewer.getSelection()).getFirstElement();
				} else {
					productQuantitySpinner.setEnabled(false);
					unitPriceText.setEnabled(false);
					selectedProduct = null;
				}
			}
		});
		
		Label quantityLabel = new Label(parent, SWT.RIGHT);
		GridData gd_quantityLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_quantityLabel.widthHint = 120;
		quantityLabel.setLayoutData(gd_quantityLabel);
		quantityLabel.setText("Cantidad");
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		GridLayout gl_composite = new GridLayout(4, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		
		productQuantitySpinner = new Spinner(composite, SWT.BORDER);
		productQuantitySpinner.setEnabled(false);
		productQuantitySpinner.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Spinner source = (Spinner) e.getSource();
				if(source.getSelection() > 0) {
					addProductButton.setEnabled(true);
				} else {
					addProductButton.setEnabled(false);
				}
				selectedProductQuantity = source.getSelection();
			}
		});
		
		Label lblPrecioUnitario = new Label(composite, SWT.NONE);
		lblPrecioUnitario.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPrecioUnitario.setText("Precio Unitario");
		
		unitPriceText = new Text(composite, SWT.BORDER | SWT.RIGHT);
		unitPriceText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String unitPrice = ((Text) e.getSource()).getText();
				try {
					setSelectedProductUnitPrice(unitPrice);
				} catch (NumberFormatException e1) {
					setSelectedProductUnitPrice("");
				}
			}

			private void setSelectedProductUnitPrice(String unitPrice) {
				if("".equals(unitPrice)) {
					unitPrice = "0";
					unitPriceText.setText(unitPrice);
				}
				selectedProductUnitPrice = new BigDecimal(unitPrice);
			}
		});
		unitPriceText.setText("0");
		unitPriceText.setEnabled(false);
		GridData gd_unitPriceText = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_unitPriceText.widthHint = 50;
		unitPriceText.setLayoutData(gd_unitPriceText);
		
		addProductButton = new Button(composite, SWT.NONE);
		addProductButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Map<String,Object> properties = new HashMap<String, Object>();
		        properties.put(ProductsCommunicationConstants.ADD_PRODUCT_DATA, selectedProduct);
		        properties.put(ProductsCommunicationConstants.ADD_PRODUCT_SUPPLIER_DATA, selectedSupplier);
		        properties.put(ProductsCommunicationConstants.ADD_PRODUCT_QUANTITY_DATA, selectedProductQuantity);
		        properties.put(ProductsCommunicationConstants.ADD_PRODUCT_UNIT_PRICE_DATA, selectedProductUnitPrice);
				Event event = new Event(ProductsCommunicationConstants.ADD_PRODUCT_EVENT, properties);
				eventAdmin.sendEvent(event);
			}
		});
		addProductButton.setEnabled(false);
		addProductButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		addProductButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addProductButton.setText("Agregar");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void createConfirmSaleOrderEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					productQuantitySpinner.setEnabled(false);
					unitPriceText.setEnabled(false);
					addProductButton.setEnabled(false);
					resetDefaultValues();
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							productQuantitySpinner.setEnabled(false);
							unitPriceText.setEnabled(false);
							addProductButton.setEnabled(false);
							resetDefaultValues();
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, ProductsCommunicationConstants.CONFIRM_PURCHASE_ORDER_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}
	
	private void createResetViewEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					resetDefaultValues();
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							resetDefaultValues();
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, ProductsCommunicationConstants.RESET_PURCHASE_ORDER_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}
	
	protected void resetDefaultValues() {
		productCategoryCombo.setText("");
		productList.removeAll();
		selectedProduct = null;
		productQuantitySpinner.setSelection(0);
		unitPriceText.setText("0");
//		productQuantitySpinner.setEnabled(false);
//		addProductButton.setEnabled(false);
	}

	private void fillProductCategories(Composite productCategoriesComposite) {
		for (ProductCategory category : categories) {
			Button btnCateg = new Button(productCategoriesComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(category.getName()));
			btnCateg.setToolTipText(category.getName());
		}
		for (ProductCategory category : categories) {
			Button btnCateg = new Button(productCategoriesComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(category.getName()));
			btnCateg.setToolTipText(category.getName());
		}
		for (ProductCategory category : categories) {
			Button btnCateg = new Button(productCategoriesComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(category.getName()));
			btnCateg.setToolTipText(category.getName());
		}
		for (ProductCategory category : categories) {
			Button btnCateg = new Button(productCategoriesComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(category.getName()));
			btnCateg.setToolTipText(category.getName());
		}
	}
	
	private void fillProducts(Composite productsComposite) {
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
		for (Product product : products) {
			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
			btnCateg.setLayoutData(new RowData(100, 100));
			btnCateg.setText(getLabel(product.getName()));
			btnCateg.setToolTipText(product.getName());
		}
	}
	
	private String getLabel(String text) {
		String[] split = text.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < split.length; i++) {
			sb.append(split[i]);
			sb.append("\n");
		}
		return sb.toString();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ProductSelectionView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(openNewEmployeeDialogAction);
//		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
//		manager.add(openNewEmployeeDialogAction);
		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(openNewEmployeeDialogAction);
	}

	private void makeActions() {
//		doubleClickAction = new Action() {
//			public void run() {
//				ISelection selection = viewer.getSelection();
//				Object obj = ((IStructuredSelection)selection).getFirstElement();
//				showMessage("Double-click detected on "+obj.toString());
//			}
//		};
	}

	private void hookDoubleClickAction() {
	}
	private void showMessage(String message) {
//		MessageDialog.openInformation(
//			viewer.getControl().getShell(),
//			"Product View",
//			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		//viewer.getControl().setFocus();
	}
}
