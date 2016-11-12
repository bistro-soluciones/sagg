package com.bistro.sagg.products.ui.views;

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
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
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

import com.bistro.sagg.core.model.products.ProductCategory;
import com.bistro.sagg.core.model.products.ProductFormat;
import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;
import com.bistro.sagg.products.ui.utils.ProductsCommunicationConstants;
import com.bistro.sagg.products.ui.viewers.InventoryProductCategoryComboContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductCategoryComboLabelProvider;
import com.bistro.sagg.products.ui.viewers.ProductFormatComboContentProvider;
import com.bistro.sagg.products.ui.viewers.ProductFormatComboLabelProvider;

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

public class SupplyDetailView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "com.bistro.sagg.products.ui.views.SupplyDetailView";
	
	private Text nameText;
	private ComboViewer productCategoryComboViewer;
	private Combo categoryCombo;
	private Spinner minStockSpinner;
	private Combo productFormatCombo;
	
	private ProductCategory selectedCategory;
	private ProductFormat selectedFormat;
	
	private ProductServices productServices = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());

	private BundleContext bundleContext;
	private EventAdmin eventAdmin;

	public SupplyDetailView() {
		super();
		this.bundleContext = FrameworkUtil.getBundle(SupplyDetailView.class).getBundleContext();
		ServiceReference<EventAdmin> ref = bundleContext.getServiceReference(EventAdmin.class);
		this.eventAdmin = bundleContext.getService(ref);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		createAddCategoryEventHandler(parent);
		
		Group basicInfoGroup = new Group(parent, SWT.NONE);
		basicInfoGroup.setLayout(new GridLayout(2, false));
		basicInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		basicInfoGroup.setText("Informaci\u00F3n B\u00E1sica");
		
		Label nameLabel = new Label(basicInfoGroup, SWT.RIGHT);
		GridData gd_nameLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_nameLabel.widthHint = 124;
		nameLabel.setLayoutData(gd_nameLabel);
		nameLabel.setText("Nombre");
		nameLabel.setAlignment(SWT.RIGHT);
		
		nameText = new Text(basicInfoGroup, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label categoryLabel = new Label(basicInfoGroup, SWT.RIGHT);
		GridData gd_categoryLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_categoryLabel.widthHint = 124;
		categoryLabel.setLayoutData(gd_categoryLabel);
		categoryLabel.setText("Categor\u00EDa");
		
		productCategoryComboViewer = new ComboViewer(basicInfoGroup, SWT.NONE);
		productCategoryComboViewer.setContentProvider(new InventoryProductCategoryComboContentProvider());
		productCategoryComboViewer.setLabelProvider(new ProductCategoryComboLabelProvider());
		productCategoryComboViewer.setInput(productServices);
		categoryCombo = productCategoryComboViewer.getCombo();
		categoryCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		productCategoryComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedCategory = (ProductCategory) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Group additionalInfoGroup = new Group(parent, SWT.NONE);
		additionalInfoGroup.setLayout(new GridLayout(2, false));
		additionalInfoGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		additionalInfoGroup.setText("Informaci\u00F3n Adicional");
		
		Label minStockLabel = new Label(additionalInfoGroup, SWT.RIGHT);
		GridData gd_minStockLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_minStockLabel.widthHint = 124;
		minStockLabel.setLayoutData(gd_minStockLabel);
		minStockLabel.setToolTipText("");
		minStockLabel.setText("Stock M\u00EDnimo");
		
		Composite composite = new Composite(additionalInfoGroup, SWT.NONE);
		GridLayout gl_composite = new GridLayout(3, false);
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		minStockSpinner = new Spinner(composite, SWT.BORDER);
		
		Label productFormatLabel = new Label(composite, SWT.NONE);
		productFormatLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		productFormatLabel.setText("Formato");
		
		ComboViewer productFormatComboViewer = new ComboViewer(composite, SWT.NONE);
		productFormatComboViewer.setContentProvider(new ProductFormatComboContentProvider());
		productFormatComboViewer.setLabelProvider(new ProductFormatComboLabelProvider());
		productFormatComboViewer.setInput(productServices);
		productFormatCombo = productFormatComboViewer.getCombo();
		productFormatCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		productFormatComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				selectedFormat = (ProductFormat) ((StructuredSelection) event.getSelection()).getFirstElement();
			}
		});
		
		Composite buttonsComposite = new Composite(parent, SWT.NONE);
		buttonsComposite.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, true, 1, 1));
		buttonsComposite.setLayout(new GridLayout(2, false));
		
		Button cancelButton = new Button(buttonsComposite, SWT.NONE);
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetDefaultValues();
			}
		});
		cancelButton.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 1, 1));
		cancelButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		cancelButton.setText("Cancelar");
		
		Button saveButton = new Button(buttonsComposite, SWT.NONE);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				productServices.createSupply(nameText.getText(), selectedCategory,
						Integer.parseInt(minStockSpinner.getText()), selectedFormat);
				Map<String,Object> properties = new HashMap<String, Object>();
				Event event = new Event(ProductsCommunicationConstants.ADD_SUPPLY_EVENT, properties);
				eventAdmin.sendEvent(event);
				resetDefaultValues();
			}
		});
		saveButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		saveButton.setFont(SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD));
		saveButton.setText("Guardar");
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}
	
	private void createAddCategoryEventHandler(Composite parent) {
		EventHandler handler = new EventHandler() {
			public void handleEvent(final Event event) {
				if (parent.getDisplay().getThread() == Thread.currentThread()) {
					productCategoryComboViewer.refresh();
				} else {
					parent.getDisplay().syncExec(new Runnable() {
						public void run() {
							productCategoryComboViewer.refresh();
						}
					});
				}
			}
	    };
	    Dictionary<String,String> properties = new Hashtable<String, String>();
	    properties.put(EventConstants.EVENT_TOPIC, ProductsCommunicationConstants.ADD_PRODUCT_CATEGORY_EVENT);
	    bundleContext.registerService(EventHandler.class, handler, properties);
	}

	private void resetDefaultValues() {
		nameText.setText("");
		categoryCombo.setText("");
		minStockSpinner.setSelection(0);
		productFormatCombo.setText("");
		selectedCategory = null;
		selectedFormat = null;
	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				SupplyDetailView.this.fillContextMenu(manager);
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
