package com.osm2xp.gui.views.panels.xplane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.osm2xp.gui.views.panels.Osm2xpPanel;
import com.osm2xp.utils.helpers.XplaneOptionsHelper;

/**
 * SceneryOptionsPanel.
 * 
 * @author Benjamin Blanchet
 * 
 */
public class SceneryOptionsPanel extends Osm2xpPanel {
	private Button btnPackageFacades;
	private Spinner spinnerMinSegment;
	private Label lblMinSegment;
	private Spinner spinnerMaxSegment;
	private Label lblMaxSegment;
	private Spinner spinnerMinArea;
	private Label lblMinArea;
	private Label lblMeters;
	private Label lblMaxMeters;
	private Label lblSquareMeters;

	public SceneryOptionsPanel(final Composite parent, final int style) {
		super(parent, style);

	}

	@Override
	protected void initLayout() {
		final GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.horizontalSpacing = 15;
		gridLayout.verticalSpacing = 15;
		setLayout(gridLayout);
		btnPackageFacades.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
				false, 1, 1));

		GridData gd_lblMinSegment = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblMinSegment.widthHint = 279;
		lblMinSegment.setLayoutData(gd_lblMinSegment);

		GridData gd_lblMaxSegment = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblMaxSegment.widthHint = 279;
		lblMaxSegment.setLayoutData(gd_lblMinSegment);

		GridData gd_lblMinArea = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblMinArea.widthHint = 279;
		lblMinArea.setLayoutData(gd_lblMinArea);

	}

	@Override
	protected void initComponents() {

		btnPackageFacades = new Button(this, SWT.CHECK);
		btnPackageFacades.setText("package facades");
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		new Label(this, SWT.NONE);
		lblMinSegment = new Label(this, SWT.NONE);
		lblMinSegment.setText("exclude buildings which largest wall is under");
		spinnerMinSegment = new Spinner(this, SWT.BORDER);
		lblMeters = new Label(this, SWT.NONE);
		lblMeters.setText("meters");

		new Label(this, SWT.NONE);

		lblMaxSegment = new Label(this, SWT.NONE);
		lblMaxSegment.setText("exclude buildings which largest wall is over");
		spinnerMaxSegment = new Spinner(this, SWT.BORDER);
		spinnerMaxSegment.setMaximum(1000);
		lblMaxMeters = new Label(this, SWT.NONE);
		lblMaxMeters.setText("meters");

		new Label(this, SWT.NONE);

		lblMinArea = new Label(this, SWT.NONE);
		lblMinArea.setText("exclude buildings which area is under");
		spinnerMinArea = new Spinner(this, SWT.BORDER);
		spinnerMinArea.setMaximum(1000);
		lblSquareMeters = new Label(this, SWT.NONE);
		lblSquareMeters.setText("square meters");

	}

	@Override
	protected void bindComponents() {
		bindComponent(btnPackageFacades, XplaneOptionsHelper.getOptions(),
				"packageFacades");
		bindComponent(spinnerMinSegment, XplaneOptionsHelper.getOptions(),
				"minHouseSegment");
		bindComponent(spinnerMaxSegment, XplaneOptionsHelper.getOptions(),
				"maxHouseSegment");
		bindComponent(spinnerMinArea, XplaneOptionsHelper.getOptions(),
				"minHouseArea");

	}

	@Override
	protected void addComponentsListeners() {

	}

}
