package com.bistro.sagg.products.ui.views;


import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.products.ui.actions.OpenInventoryLoadingDialogAction;
import com.bistro.sagg.products.ui.actions.OpenNewMarketableProductDialogAction;
import com.bistro.sagg.products.ui.utils.MarketableProductColumnIndex;
import com.bistro.sagg.products.ui.viewers.MarketableProductListContentProvider;
import com.bistro.sagg.products.ui.viewers.MarketableProductListLabelProvider;
import com.bistro.sagg.products.ui.viewers.MarketableProductListSorter;


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

public class MarketableProductListView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.products.ui.views.MarketableProductListView";

	private OpenNewMarketableProductDialogAction openNewMarketableProductDialogAction;
	private OpenInventoryLoadingDialogAction openInventoryLoadingDialogAction;
	private Table productsTable;
	
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());

	public MarketableProductListView() {
		super();
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TableViewer productsTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		productsTableViewer.setContentProvider(new MarketableProductListContentProvider());
		productsTableViewer.setLabelProvider(new MarketableProductListLabelProvider());
		productsTableViewer.setSorter(new MarketableProductListSorter());
		
		productsTable = productsTableViewer.getTable();
		productsTable.setLinesVisible(true);
		productsTable.setHeaderVisible(true);
		
		TableColumn nameColumn = new TableColumn(productsTable, SWT.NONE);
		nameColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((MarketableProductListSorter) productsTableViewer.getSorter()).doSort(MarketableProductColumnIndex.NAME_COLUMN_IDX);
				productsTableViewer.refresh();
			}
		});
		nameColumn.setWidth(182);
		nameColumn.setText("Nombre");
		
		TableColumn categoryColumn = new TableColumn(productsTable, SWT.NONE);
		categoryColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((MarketableProductListSorter) productsTableViewer.getSorter()).doSort(MarketableProductColumnIndex.CATEGORY_COLUMN_IDX);
				productsTableViewer.refresh();
			}
		});
		categoryColumn.setWidth(386);
		categoryColumn.setText("Categor\u00EDa");
		
		TableColumn stockColumn = new TableColumn(productsTable, SWT.NONE);
		stockColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((MarketableProductListSorter) productsTableViewer.getSorter()).doSort(MarketableProductColumnIndex.STOCK_COLUMN_IDX);
				productsTableViewer.refresh();
			}
		});
		stockColumn.setWidth(88);
		stockColumn.setText("Stock");
		
		TableColumn minStockColumn = new TableColumn(productsTable, SWT.NONE);
		minStockColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((MarketableProductListSorter) productsTableViewer.getSorter()).doSort(MarketableProductColumnIndex.STOCK_MIN_COLUMN_IDX);
				productsTableViewer.refresh();
			}
		});
		minStockColumn.setWidth(190);
		minStockColumn.setText("Stock M\u00EDnimo Requerido");
		
		TableColumn unitSalesPriceColumn = new TableColumn(productsTable, SWT.NONE);
		unitSalesPriceColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((MarketableProductListSorter) productsTableViewer.getSorter()).doSort(MarketableProductColumnIndex.UNIT_SALES_PRICE_COLUMN_IDX);
				productsTableViewer.refresh();
			}
		});
		unitSalesPriceColumn.setWidth(100);
		unitSalesPriceColumn.setText("Precio Unitario de Venta");
		
		productsTableViewer.setInput(productServices);
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				MarketableProductListView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(openNewMarketableProductDialogAction);
		manager.add(openInventoryLoadingDialogAction);
		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(openNewMarketableProductDialogAction);
		manager.add(openInventoryLoadingDialogAction);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(openNewMarketableProductDialogAction);
		manager.add(openInventoryLoadingDialogAction);
	}

	private void makeActions() {
		openNewMarketableProductDialogAction = new OpenNewMarketableProductDialogAction("Nuevo Producto",PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		openNewMarketableProductDialogAction.setText("Nuevo Producto");
		openNewMarketableProductDialogAction.setToolTipText("Nuevo Producto");
		openNewMarketableProductDialogAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		
		openInventoryLoadingDialogAction = new OpenInventoryLoadingDialogAction("Carga de Inventario",PlatformUI.getWorkbench().getActiveWorkbenchWindow());
		openInventoryLoadingDialogAction.setText("Carga de Inventario");
		openInventoryLoadingDialogAction.setToolTipText("Carga de Inventario");
		openInventoryLoadingDialogAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}

	private void hookDoubleClickAction() {
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		//viewer.getControl().setFocus();
	}
}
