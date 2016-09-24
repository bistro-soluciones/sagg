package com.bistro.sagg.core.osgi.ui.viewers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

public abstract class SaggViewerSorter extends ViewerSorter {

	private static final int ASCENDING = 0;
	private static final int DESCENDING = 1;

	private int column;
	private int direction;

	protected int getColumn() {
		return column;
	}

	/**
	 * Does the sort. If it's a different column from the previous sort, do an
	 * ascending sort. If it's the same column as the last sort, toggle the sort
	 * direction.
	 * 
	 * @param column
	 */
	public void doSort(int column) {
		if (column == this.column) {
			// Same column as last sort; toggle the direction
			direction = 1 - direction;
		} else {
			// New column; do an ascending sort
			this.column = column;
			direction = ASCENDING;
		}
	}

	/**
	 * Compares the object for sorting
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		int result = compare(e1,e2);

		// If descending order, flip the direction
		if (direction == DESCENDING) {
			result = -result;
		}

		return result;
	}

	protected abstract int compare(Object e1, Object e2);

}
