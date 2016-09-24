package com.bistro.sagg.products.ui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.bistro.sagg.core.services.ProductServices;
import com.bistro.sagg.core.services.SaggServiceLocator;

public class NewProductCategoryDialog extends Dialog {
	private Text nameText;

	private ProductServices productService = (ProductServices) SaggServiceLocator.getInstance()
			.lookup(ProductServices.class.getName());
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public NewProductCategoryDialog(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.MIN | SWT.MAX | SWT.TITLE);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.getShell().setText("Nueva Categoría de Productos");
		
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.marginHeight = 14;
		
		Group basicInfoGroup = new Group(container, SWT.NONE);
		basicInfoGroup.setLayout(new GridLayout(2, false));
		GridData gd_basicInfoGroup = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_basicInfoGroup.heightHint = 42;
		gd_basicInfoGroup.widthHint = 441;
		basicInfoGroup.setLayoutData(gd_basicInfoGroup);
		basicInfoGroup.setText("Informaci\u00F3n B\u00E1sica");
		
		Label nameLabel = new Label(basicInfoGroup, SWT.NONE);
		nameLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		nameLabel.setAlignment(SWT.RIGHT);
		nameLabel.setText("Nombre");
		
		nameText = new Text(basicInfoGroup, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, "Guardar", true);
		createButton(parent, IDialogConstants.CANCEL_ID, "Cancelar", false);
	}

	@Override
	protected void okPressed() {
		productService.createCategory(nameText.getText());
		super.okPressed();
	}
	
	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(481, 194);
	}
}
