package com.bistro.sagg.core.osgi.ui.viewers;

import java.util.Collections;
import java.util.List;

public class FromListContentProvider extends SaggStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof List) {
			List<? extends Object> list = (List<? extends Object>) inputElement;
			return list.toArray();
		}
		return Collections.EMPTY_LIST.toArray();
	}

}
