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
import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.widgets.Shell;
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
import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.RecipeLine;
import com.bistro.sagg.core.model.products.Supply;
import com.bistro.sagg.core.osgi.ui.utils.ErrorMessageUtils;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.core.session.SaggSession;
import com.bistro.sagg.core.session.SaggSessionConstants;
import com.bistro.sagg.core.validation.processor.ListValidatorProcessor;
import com.bistro.sagg.core.validation.validator.AmountValidator;
import com.bistro.sagg.core.validation.validator.AndValidator;
import com.bistro.sagg.core.validation.validator.EmptyOrNullValidator;
import com.bistro.sagg.core.validation.validator.SaggValidator;
import com.bistro.sagg.products.ui.utils.ProductsCommunicationConstants;
import com.bistro.sagg.products.ui.viewers.InventoryProductCategoryComboContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.ProductListLabelProvider;
import com.bistro.sagg.products.ui.viewers.RecipeLineTableLabelProvider;
import com.bistro.sagg.products.ui.viewers.SaleProductCategoryComboContentProvider;
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

public class BuildRecipeView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.products.ui.views.BuildRecipeView";
	
	private Text nameText;
	private ComboViewer recipeCategoryComboViewer;
	private Combo recipeCategoryCombo;
	private ComboViewer productCategoryComboViewer;
	private Combo categoryCombo;
	private ListViewer productListViewer;
	private List productList;
	private Spinner portionSpinner;
	private Button addSupplyButton;
	private Text descriptionText;
	private Text salesPriceText;
	
	private TableViewer recipeLinesTableViewer;
	private Table recipeLinesTable;
	private Spinner updateRecipeLinePortionSpinner;
	private Button updateRecipeLineButton;
	private Button removeRecipeLineButton;
	private Button clearRecipeLineListButton;
	private Button cancelRecipeButton;
	private Button saveRecipeButton;
	
	private ProductCategory selectedRecipeCategory;
	private ProductCategory selectedCategory;
	private Supply selectedSupply;
	private int selectedSupplyPortion;
	
	private RecipeLine recipeLine;
	private int selectecPortion;
	
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public BuildRecipeView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(BuildRecipeView.class).getBundleContext();
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
		gd_nameLabel.widthHint = 90;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("Nombre *");
		nameLabel.setAlignment(SWT.RIGHT);
		
		nameText = new Text(composite, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label descriptionLabel = new Label(composite, SWT.RIGHT);
		GridData gd_descriptionLabel = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_descriptionLabel.widthHint = 90;
		descriptionLabel.setLayoutData(gd_descriptionLabel);
		descriptionLabel.setText("Descripci\u00F3n *");
		
		descriptionText = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		descriptionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Label recipeCategoryLabel = new Label(composite, SWT.RIGHT);
		GridData gd_recipeCategoryLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_recipeCategoryLabel.widthHint = 90;
		recipeCategoryLabel.setLayoutData(gd_recipeCategoryLabel);
		recipeCategoryLabel.setText("Categor\u00EDa *");
		
		recipeCategoryComboViewer = new ComboViewer(composite, SWT.NONE);
		recipeCategoryComboViewer.setContentProvider(new SaleProductCategoryComboContentProvider());
		recipeCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		recipeCategoryComboViewer.setInput(productServices);
		recipeCategoryCombo = recipeCategoryComboViewer.getCombo();
		recipeCategoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		recipeCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedRecipeCategory = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Label priceLabel = new Label(composite, SWT.RIGHT);
		GridData gd_priceLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_priceLabel.widthHint = 90;
		priceLabel.setLayoutData(gd_priceLabel);
		priceLabel.setText("Precio *");
		
		salesPriceText = new Text(composite, SWT.BORDER | SWT.RIGHT);
		GridData gd_salesPriceText = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_salesPriceText.widthHint = 50;
		salesPriceText.setLayoutData(gd_salesPriceText);
		salesPriceText.setText("0");
		
		Composite composite_1 = new Composite(basicInfoComposite, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, false));
		
		Label categoryLabel = new Label(composite_1, SWT.RIGHT);
		GridData gd_categoryLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_categoryLabel.widthHint = 70;
		categoryLabel.setLayoutData(gd_categoryLabel);
		categoryLabel.setText("Categor\u00EDa");
		
		productCategoryComboViewer = new ComboViewer(composite_1, SWT.NONE);
		productCategoryComboViewer.setContentProvider(new InventoryProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(productServices);
		categoryCombo = productCategoryComboViewer.getCombo();
		categoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedCategory = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
				SuppliesByCategoryListContentProvider provider = (SuppliesByCategoryListContentProvider) productListViewer.getContentProvider();
				provider.setCategory(selectedCategory);
				productListViewer.refresh();
				productList.setEnabled(true);
				portionSpinner.setSelection(0);
				portionSpinner.setEnabled(false);
				addSupplyButton.setEnabled(false);
			}
		});
		
		Label lblProducto = new Label(composite_1, SWT.RIGHT);
		GridData gd_lblProducto = new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1);
		gd_lblProducto.widthHint = 70;
		lblProducto.setLayoutData(gd_lblProducto);
		lblProducto.setText("Producto");
		
		productListViewer = new ListViewer(composite_1, SWT.BORDER);
		productListViewer.setContentProvider(new SuppliesByCategoryListContentProvider());
		productListViewer.setLabelProvider(new ProductListLabelProvider());
		productListViewer.setInput(productServices);
		productList = productListViewer.getList();
		productList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		productList.setEnabled(false);
		productList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				org.eclipse.swt.widgets.List source = (org.eclipse.swt.widgets.List) e.getSource();
				if(source.getSelectionCount() > 0) {
					portionSpinner.setEnabled(true);
					portionSpinner.setSelection(0);
					selectedSupply = (Supply) ((StructuredSelection) productListViewer.getSelection()).getFirstElement();
				} else {
					portionSpinner.setEnabled(false);
					selectedSupply = null;
				}
			}
		});
		
		Label lblGramaje = new Label(composite_1, SWT.RIGHT);
		GridData gd_lblGramaje = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblGramaje.widthHint = 70;
		lblGramaje.setLayoutData(gd_lblGramaje);
		lblGramaje.setText("Gramaje");
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		GridLayout gl_composite_2 = new GridLayout(2, false);
		gl_composite_2.marginHeight = 0;
		gl_composite_2.marginWidth = 0;
		composite_2.setLayout(gl_composite_2);
		
		portionSpinner = new Spinner(composite_2, SWT.BORDER);
		portionSpinner.setMaximum(9999);
		portionSpinner.setEnabled(false);
		portionSpinner.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Spinner source = (Spinner) e.getSource();
				if(source.getSelection() > 0) {
					addSupplyButton.setEnabled(true);
				} else {
					addSupplyButton.setEnabled(false);
				}
				selectedSupplyPortion = source.getSelection();
			}
		});
		
		addSupplyButton = new Button(composite_2, SWT.NONE);
		addSupplyButton.setEnabled(false);
		addSupplyButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		addSupplyButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addSupplyButton.setText("Agregar");
		addSupplyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RecipeLine line = RecipeFactory.createRecipeLine(selectedSupply, selectedSupplyPortion);
				recipeLinesTableViewer.add(line);
				clearRecipeLineListButton.setEnabled(true);
				cancelRecipeButton.setEnabled(true);
				saveRecipeButton.setEnabled(true);
			}
		});
		
		Composite detailComposite = new Composite(parent, SWT.NONE);
		detailComposite.setLayout(new GridLayout(1, false));
		
		recipeLinesTableViewer = new TableViewer(detailComposite, SWT.BORDER | SWT.FULL_SELECTION);
		recipeLinesTableViewer.setLabelProvider(new RecipeLineTableLabelProvider());
		
		recipeLinesTable = recipeLinesTableViewer.getTable();
		recipeLinesTable.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Table source = (Table) e.getSource();
				if(source.getSelectionCount() > 0) {
					removeRecipeLineButton.setEnabled(true);
					updateRecipeLinePortionSpinner.setEnabled(true);
					recipeLine = (RecipeLine) ((StructuredSelection)recipeLinesTableViewer.getSelection()).getFirstElement();
					updateRecipeLinePortionSpinner.setSelection(recipeLine.getPortion());
				} else {
					removeRecipeLineButton.setEnabled(false);
					updateRecipeLinePortionSpinner.setEnabled(false);
					recipeLine = null;
				}
			}
		});
		recipeLinesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		recipeLinesTable.setHeaderVisible(true);
		recipeLinesTable.setLinesVisible(true);
		
		TableColumn productColumn = new TableColumn(recipeLinesTable, SWT.NONE);
		productColumn.setWidth(400);
		productColumn.setText("Insumo");
		
		TableColumn quantityColumn = new TableColumn(recipeLinesTable, SWT.NONE);
		quantityColumn.setWidth(100);
		quantityColumn.setText("Gramaje");
		
		Composite composite_3 = new Composite(detailComposite, SWT.NONE);
		GridLayout gl_composite = new GridLayout(6, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite_3.setLayout(gl_composite);
		composite_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 1, 1));
		
		updateRecipeLinePortionSpinner = new Spinner(composite_3, SWT.BORDER);
		updateRecipeLinePortionSpinner.setMaximum(9999);
		updateRecipeLinePortionSpinner.setEnabled(false);
		updateRecipeLinePortionSpinner.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Spinner source = (Spinner) e.getSource();
				if(source.getSelection() > 0) {
					updateRecipeLineButton.setEnabled(true);
				}
				selectecPortion = source.getSelection();
			}
		});
		
		updateRecipeLineButton = new Button(composite_3, SWT.NONE);
		updateRecipeLineButton.setEnabled(false);
		GridData gd_updateItemButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_updateItemButton.widthHint = 100;
		updateRecipeLineButton.setLayoutData(gd_updateItemButton);
		updateRecipeLineButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		updateRecipeLineButton.setText("Modificar");
		updateRecipeLineButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(selectecPortion > 0) {
					recipeLine.setPortion(selectecPortion);
					recipeLinesTableViewer.refresh(recipeLine, true);
				} else {
					recipeLinesTableViewer.remove(recipeLine);
					updateRecipeLineButton.setEnabled(false);
					updateRecipeLinePortionSpinner.setSelection(0);
					updateRecipeLinePortionSpinner.setEnabled(false);
					removeRecipeLineButton.setEnabled(false);
					if(recipeLinesTable.getItemCount() == 0) {
						clearRecipeLineListButton.setEnabled(false);
						saveRecipeButton.setEnabled(false);
					}
					recipeLine = null;
				}
			}
		});
		
		removeRecipeLineButton = new Button(composite_3, SWT.NONE);
		removeRecipeLineButton.setEnabled(false);
		GridData gd_removeItemButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_removeItemButton.widthHint = 100;
		removeRecipeLineButton.setLayoutData(gd_removeItemButton);
		removeRecipeLineButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		removeRecipeLineButton.setText("Eliminar");
		removeRecipeLineButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				recipeLinesTableViewer.remove(recipeLine);
				updateRecipeLineButton.setEnabled(false);
				updateRecipeLinePortionSpinner.setSelection(0);
				updateRecipeLinePortionSpinner.setEnabled(false);
				removeRecipeLineButton.setEnabled(false);
				if(recipeLinesTable.getItemCount() == 0) {
					clearRecipeLineListButton.setEnabled(false);
					saveRecipeButton.setEnabled(false);
				}
				recipeLine = null;
			}
		});
		
		clearRecipeLineListButton = new Button(composite_3, SWT.NONE);
		clearRecipeLineListButton.setEnabled(false);
		GridData gd_clearItemListButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_clearItemListButton.widthHint = 100;
		clearRecipeLineListButton.setLayoutData(gd_clearItemListButton);
		clearRecipeLineListButton.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		clearRecipeLineListButton.setText("Limpiar");
		clearRecipeLineListButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				recipeLinesTable.removeAll();
				updateRecipeLineButton.setEnabled(false);
				updateRecipeLinePortionSpinner.setSelection(0);
				updateRecipeLinePortionSpinner.setEnabled(false);
				removeRecipeLineButton.setEnabled(false);
				clearRecipeLineListButton.setEnabled(false);
				saveRecipeButton.setEnabled(false);
				recipeLine = null;
			}
		});
		
		cancelRecipeButton = new Button(composite_3, SWT.NONE);
		cancelRecipeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetDefaultValues();
			}
		});
		cancelRecipeButton.setEnabled(false);
		GridData gd_cancelRecipeButton = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_cancelRecipeButton.widthHint = 100;
		cancelRecipeButton.setLayoutData(gd_cancelRecipeButton);
		cancelRecipeButton.setText("Cancelar");
		cancelRecipeButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		
		saveRecipeButton = new Button(composite_3, SWT.NONE);
		saveRecipeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (validateFields(parent.getShell())) {
					java.util.List<RecipeLine> lines = new ArrayList<RecipeLine>();
					for (TableItem item : recipeLinesTable.getItems()) {
						lines.add((RecipeLine) item.getData());
					}
					clearRecipeLineListButton.setEnabled(false);
					cancelRecipeButton.setEnabled(false);
					saveRecipeButton.setEnabled(false);
					
					FranchiseBranch branch = SaggSession.getCurrentSession()
							.getSessionObject(SaggSessionConstants.CURRENT_FRANCHISE_BANCH);
					productServices.createRecipe(nameText.getText(), descriptionText.getText(), selectedRecipeCategory,
							lines, new BigDecimal(salesPriceText.getText()), branch);
					
					Map<String,Object> properties = new HashMap<String, Object>();
					Event event = new Event(ProductsCommunicationConstants.ADD_RECIPE_EVENT, properties);
					eventAdmin.sendEvent(event);
					
					resetDefaultValues();
				}
			}
		});
		saveRecipeButton.setEnabled(false);
		GridData gd_saveRecipeLineListButton = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_saveRecipeLineListButton.widthHint = 100;
		saveRecipeButton.setLayoutData(gd_saveRecipeLineListButton);
		saveRecipeButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		saveRecipeButton.setText("Guardar");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private boolean validateFields(Shell shell) {
		ListValidatorProcessor processor = setupProductValidatorProcessor();
		boolean result = processor.processValidation();
		if (!result) {
			MessageDialog.openError(shell, "Error", processor.getErrorMessage());
		}
		return result;
	}

	private ListValidatorProcessor setupProductValidatorProcessor() {
		java.util.List<SaggValidator> validators = new ArrayList<>();
		validators.add(
				new EmptyOrNullValidator(nameText.getText(), ErrorMessageUtils.createMandatoryFieldErrorMsg("Nombre")));
		validators.add(new EmptyOrNullValidator(descriptionText.getText(),
				ErrorMessageUtils.createMandatoryFieldErrorMsg("Descripci\u00F3n")));
		validators.add(new EmptyOrNullValidator(selectedRecipeCategory,
				ErrorMessageUtils.createMandatoryFieldErrorMsg("Categor\u00EDa")));
		validators.add(new AndValidator(salesPriceText.getText(),
				new EmptyOrNullValidator(salesPriceText.getText(),
						ErrorMessageUtils.createMandatoryFieldErrorMsg("Precio")),
				new AmountValidator(salesPriceText.getText(),
						ErrorMessageUtils.createWrongAmountFieldValueErrorMsg("Precio"))));
		ListValidatorProcessor processor = new ListValidatorProcessor(validators);
		return processor;
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
		recipeCategoryCombo.setText("");
		salesPriceText.setText("0");
		categoryCombo.setText("");
		productList.removeAll();
		portionSpinner.setEnabled(false);
		portionSpinner.setSelection(0);
		selectedRecipeCategory = null;
		selectedCategory = null;
		selectedSupply = null;
		selectedSupplyPortion = 0;
		
		
		recipeLinesTable.removeAll();
		updateRecipeLineButton.setEnabled(false);
		updateRecipeLinePortionSpinner.setSelection(0);
		updateRecipeLinePortionSpinner.setEnabled(false);
		removeRecipeLineButton.setEnabled(false);
		clearRecipeLineListButton.setEnabled(false);
		cancelRecipeButton.setEnabled(false);
		saveRecipeButton.setEnabled(false);
		recipeLine = null;
		selectecPortion = 0;
	}
	
//	private void createAddCategoryEventHandler(Composite parent) {
//		EventHandler handler = new EventHandler() {
//			public void handleEvent(final Event event) {
//				if (parent.getDisplay().getThread() == Thread.currentThread()) {
//					productCategoryComboViewer.refresh();
//				} else {
//					parent.getDisplay().syncExec(new Runnable() {
//						public void run() {
//							productCategoryComboViewer.refresh();
//						}
//					});
//				}
//			}
//	    };
//	    Dictionary<String,String> properties = new Hashtable<String, String>();
//	    properties.put(EventConstants.EVENT_TOPIC, ProductsCommunicationConstants.ADD_PRODUCT_CATEGORY_EVENT);
//	    bundleContext.registerService(EventHandler.class, handler, properties);
//	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				BuildRecipeView.this.fillContextMenu(manager);
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
