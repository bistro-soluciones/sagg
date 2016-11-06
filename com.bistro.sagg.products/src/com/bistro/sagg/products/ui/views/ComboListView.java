package com.bistro.sagg.products.ui.views;


import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.products.ui.utils.ComboColumnIndex;
import com.bistro.sagg.products.ui.utils.ProductsCommunicationConstants;
import com.bistro.sagg.products.ui.viewers.ComboListContentProvider;
import com.bistro.sagg.products.ui.viewers.ComboListLabelProvider;
import com.bistro.sagg.products.ui.viewers.ComboListSorter;
import com.bistro.sagg.products.ui.viewers.RecipeListSorter;


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

public class ComboListView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.products.ui.views.ComboListView";

	private TableViewer combosTableViewer;
	private Table combosTable;
	
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;
	
	public ComboListView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(ComboListView.class).getBundleContext();
		ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
		this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		createAddRecipeEventHandler(parent);
		
		combosTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		combosTableViewer.setContentProvider(new ComboListContentProvider());
		combosTableViewer.setLabelProvider(new ComboListLabelProvider());
		combosTableViewer.setSorter(new ComboListSorter());
		
		combosTable = combosTableViewer.getTable();
		combosTable.setLinesVisible(true);
		combosTable.setHeaderVisible(true);
		
		TableColumn nameColumn = new TableColumn(combosTable, SWT.NONE);
		nameColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((RecipeListSorter) combosTableViewer.getSorter()).doSort(ComboColumnIndex.NAME_COLUMN_IDX);
				combosTableViewer.refresh();
			}
		});
		nameColumn.setWidth(200);
		nameColumn.setText("Nombre");
		
		TableColumn descriptionColumn = new TableColumn(combosTable, SWT.NONE);
		descriptionColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((RecipeListSorter) combosTableViewer.getSorter()).doSort(ComboColumnIndex.DESCRIPTION_COLUMN_IDX);
				combosTableViewer.refresh();
			}
		});
		descriptionColumn.setWidth(350);
		descriptionColumn.setText("Descripci\u00F3n");

		TableColumn salesPriceColumn = new TableColumn(combosTable, SWT.NONE);
		salesPriceColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((RecipeListSorter) combosTableViewer.getSorter()).doSort(ComboColumnIndex.SALES_PRICE_COLUMN_IDX);
				combosTableViewer.refresh();
			}
		});
		salesPriceColumn.setWidth(100);
		salesPriceColumn.setText("Precio de Venta");
		
		combosTableViewer.setInput(productServices);
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}
	
	private void createAddRecipeEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					combosTableViewer.refresh();
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							combosTableViewer.refresh();
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, ProductsCommunicationConstants.ADD_RECIPE_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}

	private void hookContextMenu() {
//		MenuManager menuMgr = new MenuManager("#PopupMenu");
//		menuMgr.setRemoveAllWhenShown(true);
//		menuMgr.addMenuListener(new IMenuListener() {
//			public void menuAboutToShow(IMenuManager manager) {
//				MarketableProductListView.this.fillContextMenu(manager);
//			}
//		});
	}

	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalPullDown(bars.getMenuManager());
//		fillLocalToolBar(bars.getToolBarManager());
	}

//	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(openNewMarketableProductDialogAction);
//		manager.add(openInventoryLoadingDialogAction);
//		manager.add(new Separator());
//	}
//
//	private void fillContextMenu(IMenuManager manager) {
//		manager.add(openNewMarketableProductDialogAction);
//		manager.add(openInventoryLoadingDialogAction);
//		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//	}
	
//	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(openNewMarketableProductDialogAction);
//		manager.add(openInventoryLoadingDialogAction);
//	}

	private void makeActions() {
//		openNewMarketableProductDialogAction = new OpenNewMarketableProductDialogAction("Nuevo Producto",PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//		openNewMarketableProductDialogAction.setText("Nuevo Producto");
//		openNewMarketableProductDialogAction.setToolTipText("Nuevo Producto");
//		openNewMarketableProductDialogAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
//		
//		openInventoryLoadingDialogAction = new OpenInventoryLoadingDialogAction("Carga de Inventario",PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//		openInventoryLoadingDialogAction.setText("Carga de Inventario");
//		openInventoryLoadingDialogAction.setToolTipText("Carga de Inventario");
//		openInventoryLoadingDialogAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
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
