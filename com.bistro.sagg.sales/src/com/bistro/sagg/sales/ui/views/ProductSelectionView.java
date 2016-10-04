package com.bistro.sagg.sales.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.services.EmployeeServices;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Spinner;

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

	private ProductServices productService = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	
	private List<ProductCategory> categories;
	private List<MarketableProduct> products = new ArrayList<MarketableProduct>();

	public ProductSelectionView() {
		super();
		this.categories = productService.getProductCategories();
		this.products = productService.getMarketableProducts();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		
		Label productCategoryLabel = new Label(composite_1, SWT.NONE);
		productCategoryLabel.setText("Categor\u00EDa");
		
		Combo combo = new Combo(composite_1, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label productLabel = new Label(composite_1, SWT.NONE);
		productLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		productLabel.setText("Producto");
		
		org.eclipse.swt.widgets.List productList = new org.eclipse.swt.widgets.List(composite_1, SWT.BORDER);
		productList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label quantityLabel = new Label(composite_1, SWT.NONE);
		quantityLabel.setText("Cantidad");
		
		Composite composite = new Composite(composite_1, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		
		Spinner spinner = new Spinner(composite, SWT.BORDER);
		
		Button addButton = new Button(composite, SWT.NONE);
		addButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		addButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addButton.setText("Agregar");
		
		Composite composite_2 = new Composite(parent, SWT.NONE);
		composite_2.setLayout(new GridLayout(1, false));
		
		Composite composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_3.setBounds(0, 0, 64, 64);
		GridLayout gl_composite_3 = new GridLayout(2, true);
		gl_composite_3.marginHeight = 0;
		composite_3.setLayout(gl_composite_3);
		
		Button btnCombo = new Button(composite_3, SWT.NONE);
		btnCombo.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnCombo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnCombo.setText("Promo 1");
		
		Button btnNewButton_2 = new Button(composite_3, SWT.NONE);
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
		btnPromo.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnPromo.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnPromo.setText("Promo 3");
		
		Button btnPromo_1 = new Button(composite_4, SWT.NONE);
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
		btnPromo_2.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		btnPromo_2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnPromo_2.setText("Promo 5");
		
		Button btnPromo_3 = new Button(composite_5, SWT.NONE);
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
		btnPromo_4.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		btnPromo_4.setText("Promo 7");
		btnPromo_4.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC));
		
		Button btnPromo_5 = new Button(composite_6, SWT.NONE);
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
		combo_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnAgregar = new Button(composite_7, SWT.NONE);
		btnAgregar.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		btnAgregar.setText("Agregar");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
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
