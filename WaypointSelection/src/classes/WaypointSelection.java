package classes;

import org.eclipse.swt.widgets.Composite;

public class WaypointSelection extends Composite {

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public WaypointSelection(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
