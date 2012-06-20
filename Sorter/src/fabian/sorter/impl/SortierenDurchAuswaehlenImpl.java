package fabian.sorter.impl;

import java.util.List;

public class SortierenDurchAuswaehlenImpl<T extends Comparable<T>> extends
		SortImpl<T> {

	@Override
	public List<T> sort() {
		int k = 0;
		T x = null;
		for (int i = 0; i < list.size() - 1; i++) {
			k = i;
			// Element j, welches immer das naechste linke repraesentiert...
			for (int j = i + 1; j < list.size(); j++) {
				// ... ist kleiner, als das kleineste im restlichen listenteil
				// von k
				if (list.get(j).compareTo(list.get(k)) < 0) {
					k = j;
				}
			}
			// tauschen
			x = list.get(k);
			list.set(k, list.get(i));
			list.set(i, x);
		}
		return list;
	}
}
