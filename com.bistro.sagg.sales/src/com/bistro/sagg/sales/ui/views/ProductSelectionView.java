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

	private Text quantityText;

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
		parent.setLayout(new GridLayout(2, false));
		
		Label productCategoryLabel = new Label(parent, SWT.NONE);
		productCategoryLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		productCategoryLabel.setText("Categor\u00EDa");
		
		org.eclipse.swt.widgets.List productCategoryList = new org.eclipse.swt.widgets.List(parent, SWT.BORDER);
		productCategoryList.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label productLabel = new Label(parent, SWT.NONE);
		productLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		productLabel.setText("Producto");
		
		org.eclipse.swt.widgets.List productList = new org.eclipse.swt.widgets.List(parent, SWT.BORDER);
		productList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label quantityLabel = new Label(parent, SWT.NONE);
		quantityLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		quantityLabel.setText("Cantidad");
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		quantityText = new Text(composite, SWT.BORDER);
		
		Button addButton = new Button(composite, SWT.NONE);
		addButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addButton.setText("Agregar");
		
		Button removeButton = new Button(composite, SWT.NONE);
		removeButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		removeButton.setText("Quitar");
		
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
