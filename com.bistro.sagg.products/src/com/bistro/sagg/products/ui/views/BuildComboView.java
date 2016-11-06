package com.bistro.sagg.products.ui.views;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import com.bistro.sagg.core.factory.RecipeFactory;
import com.bistro.sagg.core.model.company.FranchiseBranch;
import com.bistro.sagg.core.model.products.ComboItem;
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.SalableProduct;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.products.ui.utils.ProductsCommunicationConstants;
import com.bistro.sagg.products.ui.viewers.ComboItemTableLabelProvider;
import com.bistro.sagg.products.ui.viewers.MarketableProductListContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.ProductListLabelProvider;
import com.bistro.sagg.products.ui.viewers.RecipeListContentProvider;
import com.bistro.sagg.products.ui.viewers.SuppliesByCategoryListContentProvider;

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

public class BuildComboView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.products.ui.views.BuildComboView";

	private static final String PRODUCT = "Producto";
	private static final String RECIPE = "Receta";
	
	private Text nameText;
	private ComboViewer productCategoryComboViewer;
	private Combo categoryCombo;
	private ListViewer productListViewer;
	private List productList;
	private Spinner quantitySpinner;
	private Button addItemButton;
	private Text descriptionText;
	private Text salesPriceText;
	
	private TableViewer recipeLinesTableViewer;
	private Table comboItemsTable;
	private Spinner updateComboItemQuantitySpinner;
	private Button updateComboItemButton;
	private Button removeComboItemButton;
	private Button clearComboItemListButton;
	private Button cancelComboButton;
	private Button saveComboButton;
	
	private MarketableProductListContentProvider productProvider = new MarketableProductListContentProvider();
	private RecipeListContentProvider recipeProvider = new RecipeListContentProvider();
	
	private ProductCategory selectedCategory;
	private SalableProduct selectedProduct;
	private int selectedProductQuantity;
	
	private ComboItem comboItem;
	private int selectedQuantity;
	
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;
	private Label productTypeLabel;

	public BuildComboView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(BuildComboView.class).getBundleContext();
		ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
		this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout(SWT.VERTICAL));
		
		createResetViewEventHandler(parent);
		
		Composite basicInfoComposite = new Composite(parent, SWT.NONE);
		basicInfoComposite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(basicInfoComposite, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		
		Label nameLabel = new Label(composite, SWT.RIGHT);
		GridData gd_nameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_nameLabel.widthHint = 80;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("Nombre");
		nameLabel.setAlignment(SWT.RIGHT);
		
		nameText = new Text(composite, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label descriptionLabel = new Label(composite, SWT.RIGHT);
		GridData gd_descriptionLabel = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_descriptionLabel.widthHint = 80;
		descriptionLabel.setLayoutData(gd_descriptionLabel);
		descriptionLabel.setText("Descripci\u00F3n");
		
		descriptionText = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		descriptionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label priceLabel = new Label(composite, SWT.RIGHT);
		GridData gd_priceLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_priceLabel.widthHint = 80;
		priceLabel.setLayoutData(gd_priceLabel);
		priceLabel.setText("Precio");
		
		salesPriceText = new Text(composite, SWT.BORDER | SWT.RIGHT);
		GridData gd_salesPriceText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_salesPriceText.widthHint = 50;
		salesPriceText.setLayoutData(gd_salesPriceText);
		salesPriceText.setText("0");
		
		Composite composite_1 = new Composite(basicInfoComposite, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		
		productTypeLabel = new Label(composite_1, SWT.RIGHT);
		GridData gd_productTypeLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_productTypeLabel.widthHint = 70;
		productTypeLabel.setLayoutData(gd_productTypeLabel);
		productTypeLabel.setText("Tipo");
		
//		productCategoryComboViewer = new ComboViewer(composite_1, SWT.NONE);
//		productCategoryComboViewer.setContentProvider(new ProductCategoryComboContentProvider());
//		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
//		productCategoryComboViewer.setInput(productServices);
//		categoryCombo = productCategoryComboViewer.getCombo();
		categoryCombo = new Combo(composite_1, SWT.NONE);
		categoryCombo.add(RECIPE);
		categoryCombo.add(PRODUCT);
		categoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		categoryCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(RECIPE.equals(categoryCombo.getText())) {
					productListViewer.setContentProvider(recipeProvider);
				}
				if(PRODUCT.equals(categoryCombo.getText())) {
					productListViewer.setContentProvider(productProvider);
				}
				productListViewer.setInput(productServices);
				productListViewer.refresh();
				productList.setEnabled(true);
				quantitySpinner.setSelection(0);
				quantitySpinner.setEnabled(false);
				addItemButton.setEnabled(false);
			}
		});
//		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
//			@Override
//			public void selectionChanged(SelectionChangedEvent event) {
//				selectedCategory = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
//				SuppliesByCategoryListContentProvider provider = (SuppliesByCategoryListContentProvider) productListViewer.getContentProvider();
//				provider.setCategory(selectedCategory);
//				productListViewer.refresh();
//				productList.setEnabled(true);
//				quantitySpinner.setSelection(0);
//				quantitySpinner.setEnabled(false);
//				addItemButton.setEnabled(false);
//			}
//		});
		
		Label lblProducto = new Label(composite_1, SWT.RIGHT);
		GridData gd_lblProducto = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_lblProducto.widthHint = 70;
		lblProducto.setLayoutData(gd_lblProducto);
		lblProducto.setText(PRODUCT);
		
		productListViewer = new ListViewer(composite_1, SWT.BORDER);
		productListViewer.setLabelProvider(new ProductListLabelProvider());
//		productListViewer.setInput(productServices);
		productList = productListViewer.getList();
		productList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		productList.setEnabled(false);
		productList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				org.eclipse.swt.widgets.List source = (org.eclipse.swt.widgets.List) e.getSource();
				if(source.getSelectionCount() > 0) {
					quantitySpinner.setEnabled(true);
					quantitySpinner.setSelection(0);
					selectedProduct = (SalableProduct) ((StructuredSelection) productListViewer.getSelection()).getFirstElement();
				} else {
					quantitySpinner.setEnabled(false);
					selectedProduct = null;
				}
			}
		});
		
		Label quantityLabel = new Label(composite_1, SWT.RIGHT);
		GridData gd_quantityLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_quantityLabel.widthHint = 70;
		quantityLabel.setLayoutData(gd_quantityLabel);
		quantityLabel.setText("Cantidad");
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.marginHeight = 0;
		gl_composite_2.marginWidth = 0;
		composite_2.setLayout(gl_composite_2);
		
		quantitySpinner = new Spinner(composite_2, SWT.BORDER);
		quantitySpinner.setMaximum(10);
		quantitySpinner.setEnabled(false);
		quantitySpinner.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Spinner source = (Spinner) e.getSource();
				if(source.getSelection() > 0) {
					addItemButton.setEnabled(true);
				} else {
					addItemButton.setEnabled(false);
				}
				selectedProductQuantity = source.getSelection();
			}
		});
		
		addItemButton = new Button(composite_2, SWT.NONE);
		addItemButton.setEnabled(false);
		addItemButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		addItemButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addItemButton.setText("Agregar");
		addItemButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ComboItem item = RecipeFactory.createComboItem(selectedProduct, selectedProductQuantity);
				recipeLinesTableViewer.add(item);
				clearComboItemListButton.setEnabled(true);
				cancelComboButton.setEnabled(true);
				saveComboButton.setEnabled(true);
			}
		});
		
		Composite detailComposite = new Composite(parent, SWT.NONE);
		detailComposite.setLayout(new GridLayout(1, false));
		
		recipeLinesTableViewer = new TableViewer(detailComposite, SWT.BORDER | SWT.FULL_SELECTION);
		recipeLinesTableViewer.setLabelProvider(new ComboItemTableLabelProvider());
		
		comboItemsTable = recipeLinesTableViewer.getTable();
		comboItemsTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Table source = (Table) e.getSource();
				if(source.getSelectionCount() > 0) {
					removeComboItemButton.setEnabled(true);
					updateComboItemQuantitySpinner.setEnabled(true);
					comboItem = (ComboItem) ((StructuredSelection)recipeLinesTableViewer.getSelection()).getFirstElement();
					updateComboItemQuantitySpinner.setSelection(comboItem.getQuantity());
				} else {
					removeComboItemButton.setEnabled(false);
					updateComboItemQuantitySpinner.setEnabled(false);
					comboItem = null;
				}
			}
		});
		comboItemsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		comboItemsTable.setHeaderVisible(true);
		comboItemsTable.setLinesVisible(true);
		
		TableColumn itemColumn = new TableColumn(comboItemsTable, SWT.NONE);
		itemColumn.setWidth(400);
		itemColumn.setText(PRODUCT);
		
		TableColumn quantityColumn = new TableColumn(comboItemsTable, SWT.NONE);
		quantityColumn.setWidth(100);
		quantityColumn.setText("Cantidad");
		
		Composite composite_3 = new Composite(detailComposite, SWT.NONE);
		GridLayout gl_composite = new GridLayout(6, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite_3.setLayout(gl_composite);
		composite_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		updateComboItemQuantitySpinner = new Spinner(composite_3, SWT.BORDER);
		updateComboItemQuantitySpinner.setMaximum(9999);
		updateComboItemQuantitySpinner.setEnabled(false);
		updateComboItemQuantitySpinner.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Spinner source = (Spinner) e.getSource();
				if(source.getSelection() > 0) {
					updateComboItemButton.setEnabled(true);
				}
				selectedQuantity = source.getSelection();
			}
		});
		
		updateComboItemButton = new Button(composite_3, SWT.NONE);
		updateComboItemButton.setEnabled(false);
		GridData gd_updateComboItemButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_updateComboItemButton.widthHint = 100;
		updateComboItemButton.setLayoutData(gd_updateComboItemButton);
		updateComboItemButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		updateComboItemButton.setText("Modificar");
		updateComboItemButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(selectedQuantity > 0) {
					comboItem.setQuantity(selectedQuantity);
					recipeLinesTableViewer.refresh(comboItem, true);
				} else {
					recipeLinesTableViewer.remove(comboItem);
					updateComboItemButton.setEnabled(false);
					updateComboItemQuantitySpinner.setSelection(0);
					updateComboItemQuantitySpinner.setEnabled(false);
					removeComboItemButton.setEnabled(false);
					if(comboItemsTable.getItemCount() == 0) {
						clearComboItemListButton.setEnabled(false);
						saveComboButton.setEnabled(false);
					}
					comboItem = null;
				}
			}
		});
		
		removeComboItemButton = new Button(composite_3, SWT.NONE);
		removeComboItemButton.setEnabled(false);
		GridData gd_removeComboItemButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_removeComboItemButton.widthHint = 100;
		removeComboItemButton.setLayoutData(gd_removeComboItemButton);
		removeComboItemButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		removeComboItemButton.setText("Eliminar");
		removeComboItemButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				recipeLinesTableViewer.remove(comboItem);
				updateComboItemButton.setEnabled(false);
				updateComboItemQuantitySpinner.setSelection(0);
				updateComboItemQuantitySpinner.setEnabled(false);
				removeComboItemButton.setEnabled(false);
				if(comboItemsTable.getItemCount() == 0) {
					clearComboItemListButton.setEnabled(false);
					saveComboButton.setEnabled(false);
				}
				comboItem = null;
			}
		});
		
		clearComboItemListButton = new Button(composite_3, SWT.NONE);
		clearComboItemListButton.setEnabled(false);
		GridData gd_clearComboItemListButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_clearComboItemListButton.widthHint = 100;
		clearComboItemListButton.setLayoutData(gd_clearComboItemListButton);
		clearComboItemListButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		clearComboItemListButton.setText("Limpiar");
		clearComboItemListButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comboItemsTable.removeAll();
				updateComboItemButton.setEnabled(false);
				updateComboItemQuantitySpinner.setSelection(0);
				updateComboItemQuantitySpinner.setEnabled(false);
				removeComboItemButton.setEnabled(false);
				clearComboItemListButton.setEnabled(false);
				saveComboButton.setEnabled(false);
				comboItem = null;
			}
		});
		
		cancelComboButton = new Button(composite_3, SWT.NONE);
		cancelComboButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetDefaultValues();
			}
		});
		cancelComboButton.setEnabled(false);
		GridData gd_cancelComboButton = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_cancelComboButton.widthHint = 100;
		cancelComboButton.setLayoutData(gd_cancelComboButton);
		cancelComboButton.setText("Cancelar");
		cancelComboButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		
		saveComboButton = new Button(composite_3, SWT.NONE);
		saveComboButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				java.util.List<ComboItem> items = new ArrayList<ComboItem>();
				for (TableItem item : comboItemsTable.getItems()) {
					items.add((ComboItem) item.getData());
				}
				clearComboItemListButton.setEnabled(false);
				cancelComboButton.setEnabled(false);
				saveComboButton.setEnabled(false);
				
				FranchiseBranch branch = SaggSession.getCurrentSession()
						.getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
				productServices.createCombo(nameText.getText(), descriptionText.getText(), items,
						new BigDecimal(salesPriceText.getText()), branch);

				Map<String,Object> properties = new HashMap<String, Object>();
				Event event = new Event(ProductsCommunicationConstants.ADD_RECIPE_EVENT, properties);
				eventAdmin.sendEvent(event);
				
				resetDefaultValues();
			}
		});
		saveComboButton.setEnabled(false);
		GridData gd_saveComboButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_saveComboButton.widthHint = 100;
		saveComboButton.setLayoutData(gd_saveComboButton);
		saveComboButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		saveComboButton.setText("Guardar");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
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
	    properties.put(EventConstants.EVENT_TOPIC, ProductsCommunicationConstants.RESET_RECIPE_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}
	
	private void resetDefaultValues() {
		nameText.setText("");
		descriptionText.setText("");
		salesPriceText.setText("0");
		categoryCombo.setText("");
		productList.removeAll();
		quantitySpinner.setEnabled(false);
		quantitySpinner.setSelection(0);
		selectedCategory = null;
		selectedProduct = null;
		selectedProductQuantity = 0;
		
		comboItemsTable.removeAll();
		updateComboItemButton.setEnabled(false);
		updateComboItemQuantitySpinner.setSelection(0);
		updateComboItemQuantitySpinner.setEnabled(false);
		removeComboItemButton.setEnabled(false);
		clearComboItemListButton.setEnabled(false);
		cancelComboButton.setEnabled(false);
		saveComboButton.setEnabled(false);
		comboItem = null;
		selectedQuantity = 0;
	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				BuildComboView.this.fillContextMenu(manager);
			}
		});
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
	}

	private void fillContextMenu(IMenuManager manager) {
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
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
