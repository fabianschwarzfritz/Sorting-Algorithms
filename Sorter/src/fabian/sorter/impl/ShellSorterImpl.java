package fabian.sorter.impl;

import java.util.ArrayList;
import java.util.List;

public class ShellSorterImpl<T extends Comparable<T>> extends SorterImpl<T> {

	private int generateSchreittweite(int k) {
		if (k == 0) {
			return 1;
		} else {
			return generateSchreittweite(k - 1) * 3 + 1;
		}
	}

	@Override
	public List<T> sort() {
		// Schrittweiten generieren
		List<Integer> schrittweiten = new ArrayList<Integer>();
		schrittweiten.add(new Integer(0));
		int max = (int) (Math.log(list.size()) / Math.log(3.0) - 1);
		for (int i = 1; i <= max; i++) {
			// h[k+1]=3*h[k]+1
			schrittweiten.add(new Integer(schrittweiten.get(i - 1) * 3 + 1));
		}
		// Sortieren
		T x = null;
		int h;
		int j;
		for (int indexWeite = 0; indexWeite < schrittweiten.size(); indexWeite++) {
			h = schrittweiten.get(indexWeite);
			for (int i = h; i < list.size(); i++) {
				x = list.get(i);
				j = i;
				while (j >= h && x.compareTo(list.get(j - h)) < 0) {
					list.set(j, list.get(j - h));
					j = j - h;
				}
				list.set(j, x);
			}
		}
		return list;
	}
}
