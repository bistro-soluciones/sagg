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
import com.bistro.sagg.products.ui.utils.ProductCategoryColumnIndex;
import com.bistro.sagg.products.ui.utils.ProductsCommunicationConstants;
import com.bistro.sagg.products.ui.viewers.ProductCategoryListContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryListLabelProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryListSorter;


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

public class ProductCategoryListView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.products.ui.views.ProductCategoryListView";

	private TableViewer productCategoriesTableViewer;
	private Table productCategoriesTable;
	
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;
	
	public ProductCategoryListView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(ProductCategoryDetailView.class).getBundleContext();
		ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
		this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		createAddCategoryEventHandler(parent);
		
		productCategoriesTableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		productCategoriesTableViewer.setContentProvider(new ProductCategoryListContentProvider());
		productCategoriesTableViewer.setLabelProvider(new ProductCategoryListLabelProvider());
		productCategoriesTableViewer.setSorter(new ProductCategoryListSorter());
		
		productCategoriesTable = productCategoriesTableViewer.getTable();
		productCategoriesTable.setLinesVisible(true);
		productCategoriesTable.setHeaderVisible(true);
		
		TableColumn tblclmnNombre = new TableColumn(productCategoriesTable, SWT.NONE);
		tblclmnNombre.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				((ProductCategoryListSorter) productCategoriesTableViewer.getSorter()).doSort(ProductCategoryColumnIndex.NAME_COLUMN_IDX);
				productCategoriesTableViewer.refresh();
			}
		});
		tblclmnNombre.setWidth(182);
		tblclmnNombre.setText("Categor\u00EDa");
		
		productCategoriesTableViewer.setInput(productServices);
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}
	
	private void createAddCategoryEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					productCategoriesTableViewer.refresh();
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							productCategoriesTableViewer.refresh();
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, ProductsCommunicationConstants.ADD_PRODUCT_CATEGORY_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}

	private void hookContextMenu() {
//		MenuManager menuMgr = new MenuManager("#PopupMenu");
//		menuMgr.setRemoveAllWhenShown(true);
//		menuMgr.addMenuListener(new IMenuListener() {
//			public void menuAboutToShow(IMenuManager manager) {
//				ProductCategoryListView.this.fillContextMenu(manager);
//			}
//		});
	}

	private void contributeToActionBars() {
//		IActionBars bars = getViewSite().getActionBars();
//		fillLocalPullDown(bars.getMenuManager());
//		fillLocalToolBar(bars.getToolBarManager());
	}

//	private void fillLocalPullDown(IMenuManager manager) {
//		manager.add(openNewSupplierDialogAction);
//		manager.add(new Separator());
//	}

//	private void fillContextMenu(IMenuManager manager) {
//		manager.add(openNewSupplierDialogAction);
		// Other plug-ins can contribute there actions here
//		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
//	}
	
//	private void fillLocalToolBar(IToolBarManager manager) {
//		manager.add(openNewSupplierDialogAction);
//	}

	private void makeActions() {
//		openNewSupplierDialogAction = new OpenNewProductCategoryDialogAction("Nueva Categoría de Productos",PlatformUI.getWorkbench().getActiveWorkbenchWindow());
//		openNewSupplierDialogAction.setText("Nueva Categoría de Productos");
//		openNewSupplierDialogAction.setToolTipText("Nueva Categoría de Productos");
//		openNewSupplierDialogAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
//			getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
	}

	private void hookDoubleClickAction() {
		
	}

	@Override
	public void setFocus() {
		
	}
	
}
