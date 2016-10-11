package com.bistro.sagg.sales.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.part.ViewPart;

import com.bistro.sagg.core.model.products.MarketableProduct;
import com.bistro.sagg.core.model.products.Product;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;

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

public class PromotionSelectionView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.sales.ui.views.ProductSelectionView";

	private ProductServices productService = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	
	private List<ProductCategory> categories;
	private List<MarketableProduct> products = new ArrayList<MarketableProduct>();

	public PromotionSelectionView() {
		super();
//		this.categories = productService.getProductCategories();
		this.products = productService.getMarketableProducts();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ScrolledComposite productCategoriesScrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL);
		GridData gd_scrolledComposite = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_scrolledComposite.heightHint = 105;
		productCategoriesScrolledComposite.setLayoutData(gd_scrolledComposite);
		productCategoriesScrolledComposite.setExpandHorizontal(true);
		productCategoriesScrolledComposite.setExpandVertical(true);
		
		Composite productCategoriesComposite = new Composite(productCategoriesScrolledComposite, SWT.NONE);
		productCategoriesComposite.setLayout(new RowLayout(SWT.HORIZONTAL));
		fillProductCategories(productCategoriesComposite);
		productCategoriesScrolledComposite.setContent(productCategoriesComposite);
		productCategoriesScrolledComposite.setMinSize(productCategoriesComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		ScrolledComposite productsScrolledComposite = new ScrolledComposite(parent, SWT.H_SCROLL);
		productsScrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		productsScrolledComposite.setExpandHorizontal(true);
		productsScrolledComposite.setExpandVertical(true);
		
		Composite productsComposite = new Composite(productsScrolledComposite, SWT.NONE);
		RowLayout rl_productsComposite = new RowLayout(SWT.HORIZONTAL);
		productsComposite.setLayout(rl_productsComposite);
		fillProducts(productsComposite);
		productsScrolledComposite.setContent(productsComposite);
		productsScrolledComposite.setMinSize(productsComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
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
				PromotionSelectionView.this.fillContextMenu(manager);
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
