package fabian.sorter.impl;

import java.util.List;

public class BubbleSortImpl<T extends Comparable<T>> extends SortImpl<T> {

	@Override
	public List<T> sort() {
		T x = null;
		// Linker rand in dem schon sortier ist
		for (int i = 0; i < list.size() - 1; i++) {
			// j geht jeweils von links nach rechts durch das array und tauscht
			for (int j = list.size() - 1; j > i; j++) {
				if (list.get(j - 1).compareTo(list.get(j)) < 0) {
					// tauschen
					x = list.get(j - 1);
					list.set(j - 1, list.get(j));
					list.set(j, x);
				}
			}
		}
		return list;
	}
}
