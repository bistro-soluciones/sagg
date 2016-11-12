package com.bistro.sagg.sales.ui.views;

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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
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
import com.bistro.sagg.core.model.products.SalableProduct;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.sales.ui.utils.SalesCommunicationConstants;
import com.bistro.sagg.sales.ui.viewers.ProductCategoryComboContentProvider;
import com.bistro.sagg.sales.ui.viewers.ProductCategoryComboLabelProvider;
import com.bistro.sagg.sales.ui.viewers.SalableProductListContentProvider;
import com.bistro.sagg.sales.ui.viewers.SalableProductListLabelProvider;

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
	public static final String ID = "com.bistro.sagg.sales.ui.views.ProductSelectionView";

	private Combo productCategoryCombo;
	private ListViewer productListViewer;
	private org.eclipse.swt.widgets.List productList;
	private Spinner productQuantitySpinner;
	private Button addProductButton;
	
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	
//	private List<ProductCategory> categories;
//	private List<MarketableProduct> products = new ArrayList<MarketableProduct>();
	private SalableProduct selectedProduct;
	private int selectedProductQuantity;
	
    private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public ProductSelectionView() {
		super();
//		this.categories = productService.getProductCategories();
		FranchiseBranch branch = SaggSession.getCurrentSession().getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
//		this.products = productServices.getMarketableProducts(branch);
		
		this.bundleContext = FrameworkUtil.getBundle(ProductSelectionView.class).getBundleContext();
        ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
        this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		createConfirmSaleOrderEventHandler(parent);
		createResetViewEventHandler(parent);
	    
		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		
		Label productCategoryLabel = new Label(composite_1, SWT.NONE);
		productCategoryLabel.setText("Categor\u00EDa");
		
		ComboViewer productCategoryComboViewer = new ComboViewer(composite_1, SWT.NONE);
		productCategoryComboViewer.setContentProvider(new ProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(productServices);
		productCategoryCombo = productCategoryComboViewer.getCombo();
		productCategoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ProductCategory category = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
				SalableProductListContentProvider provider = (SalableProductListContentProvider) productListViewer.getContentProvider();
				provider.setCategory(category);
				productListViewer.refresh();
				productList.setEnabled(true);
				productQuantitySpinner.setEnabled(false);
			}
		});
		
		Label productLabel = new Label(composite_1, SWT.NONE);
		productLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		productLabel.setText("Producto");
		
		productListViewer = new ListViewer(composite_1, SWT.BORDER);
		productListViewer.setContentProvider(new SalableProductListContentProvider());
		productListViewer.setLabelProvider(new SalableProductListLabelProvider());
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
					productQuantitySpinner.setSelection(0);
					selectedProduct = (SalableProduct) ((StructuredSelection) productListViewer.getSelection())
							.getFirstElement();
				} else {
					productQuantitySpinner.setEnabled(false);
					selectedProduct = null;
				}
			}
		});
		
		Label quantityLabel = new Label(composite_1, SWT.NONE);
		quantityLabel.setText("Cantidad");
		
		Composite composite = new Composite(composite_1, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite = new GridLayout(2, false);
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
		
		addProductButton = new Button(composite, SWT.NONE);
		addProductButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Map<String,Object> properties = new HashMap<String, Object>();
		        properties.put(SalesCommunicationConstants.ADD_PRODUCT_DATA, selectedProduct);
		        properties.put(SalesCommunicationConstants.ADD_PRODUCT_QUANTITY_DATA, selectedProductQuantity);
				Event event = new Event(SalesCommunicationConstants.ADD_PRODUCT_EVENT, properties);
				eventAdmin.sendEvent(event);
			}
		});
		addProductButton.setEnabled(false);
		addProductButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		addProductButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addProductButton.setText("Agregar");
		
		Composite composite_2 = new Composite(parent, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_3.setBounds(0, 0, 64, 64);
		GridLayout gl_composite_3 = new GridLayout(2, true);
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		
		Button btnCombo = new Button(composite_3, SWT.NONE);
		btnCombo.setEnabled(false);
		btnCombo.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnCombo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnCombo.setText("Promo 1");
		
		Button btnNewButton_2 = new Button(composite_3, SWT.NONE);
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnNewButton_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnNewButton_2.setText("Promo 2");
		
		Composite composite_4 = new Composite(composite_2, SWT.NONE);
		composite_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_4.setBounds(0, 0, 178, 40);
		GridLayout gl_composite_4 = new GridLayout(2, true);
		gl_composite_4.marginHeight = 0;
		composite_4.setLayout(gl_composite_4);
		
		Button btnPromo = new Button(composite_4, SWT.NONE);
		btnPromo.setEnabled(false);
		btnPromo.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnPromo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnPromo.setText("Promo 3");
		
		Button btnPromo_1 = new Button(composite_4, SWT.NONE);
		btnPromo_1.setEnabled(false);
		btnPromo_1.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnPromo_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnPromo_1.setText("Promo 4");
		
		Composite composite_5 = new Composite(composite_2, SWT.NONE);
		composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_5.setBounds(0, 0, 178, 40);
		GridLayout gl_composite_5 = new GridLayout(2, true);
		gl_composite_5.marginHeight = 0;
		composite_5.setLayout(gl_composite_5);
		
		Button btnPromo_2 = new Button(composite_5, SWT.NONE);
		btnPromo_2.setEnabled(false);
		btnPromo_2.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnPromo_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnPromo_2.setText("Promo 5");
		
		Button btnPromo_3 = new Button(composite_5, SWT.NONE);
		btnPromo_3.setEnabled(false);
		btnPromo_3.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnPromo_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnPromo_3.setText("Promo 6");
		
		Composite composite_6 = new Composite(composite_2, SWT.NONE);
		composite_6.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_6.setBounds(0, 0, 287, 38);
		GridLayout gl_composite_6 = new GridLayout(2, true);
		gl_composite_6.marginHeight = 0;
		composite_6.setLayout(gl_composite_6);
		
		Button btnPromo_4 = new Button(composite_6, SWT.NONE);
		btnPromo_4.setEnabled(false);
		btnPromo_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnPromo_4.setText("Promo 7");
		btnPromo_4.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		
		Button btnPromo_5 = new Button(composite_6, SWT.NONE);
		btnPromo_5.setEnabled(false);
		btnPromo_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnPromo_5.setText("Promo 8");
		btnPromo_5.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		
		Composite composite_7 = new Composite(composite_2, SWT.NONE);
		composite_7.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite_7.setBounds(0, 0, 64, 64);
		GridLayout gl_composite_7 = new GridLayout(3, false);
		gl_composite_7.marginHeight = 0;
		composite_7.setLayout(gl_composite_7);
		
		Label lblOtros = new Label(composite_7, SWT.NONE);
		lblOtros.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblOtros.setText("Otros");
		
		Combo combo_1 = new Combo(composite_7, SWT.NONE);
		combo_1.setEnabled(false);
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnAgregar = new Button(composite_7, SWT.NONE);
		btnAgregar.setEnabled(false);
		btnAgregar.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		btnAgregar.setText("Agregar");
		
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
					addProductButton.setEnabled(false);
					resetDefaultValues();
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							productQuantitySpinner.setEnabled(false);
							addProductButton.setEnabled(false);
							resetDefaultValues();
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, SalesCommunicationConstants.CONFIRM_SALE_ORDER_EVENT);
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
	    properties.put(EventConstants.EVENT_TOPIC, SalesCommunicationConstants.RESET_SALE_ORDER_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}
	
	protected void resetDefaultValues() {
		productCategoryCombo.setText("");
		productList.removeAll();
		selectedProduct = null;
		productQuantitySpinner.setSelection(0);
//		productQuantitySpinner.setEnabled(false);
//		addProductButton.setEnabled(false);
	}

//	private void fillProductCategories(Composite productCategoriesComposite) {
//		for (ProductCategory category : categories) {
//			Button btnCateg = new Button(productCategoriesComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(category.getName()));
//			btnCateg.setToolTipText(category.getName());
//		}
//		for (ProductCategory category : categories) {
//			Button btnCateg = new Button(productCategoriesComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(category.getName()));
//			btnCateg.setToolTipText(category.getName());
//		}
//		for (ProductCategory category : categories) {
//			Button btnCateg = new Button(productCategoriesComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(category.getName()));
//			btnCateg.setToolTipText(category.getName());
//		}
//		for (ProductCategory category : categories) {
//			Button btnCateg = new Button(productCategoriesComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(category.getName()));
//			btnCateg.setToolTipText(category.getName());
//		}
//	}
//	
//	private void fillProducts(Composite productsComposite) {
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//		for (Product product : products) {
//			Button btnCateg = new Button(productsComposite, SWT.WRAP | SWT.PUSH);
//			btnCateg.setLayoutData(new RowData(100, 100));
//			btnCateg.setText(getLabel(product.getName()));
//			btnCateg.setToolTipText(product.getName());
//		}
//	}
	
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
