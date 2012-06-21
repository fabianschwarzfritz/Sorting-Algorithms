package fabian.sorter.impl.concurrent;

import java.util.List;

public class QuickSort<T extends Comparable<T>> {

	protected List<T> list;

	public QuickSort(List<T> list) {
		super();
		this.list = list;
	}

	public void quicksort(final int l, final int r) throws Exception {
		if (l < r) {
			final int teiler = divide(l, r);
			quicksort(l, teiler - 1);
			quicksort(teiler, r);
		}
	}

	protected int divide(int links, int rechts) {
		T pivotelement = list.get(rechts);
		int l = links;
		int r = rechts;
		do {
			// linken rand bis kleiner pivot und l kleiner gleich als der von
			// diesem rekursionablauf zu bearabeitende, maximale rechte rand
			while (list.get(l).compareTo(pivotelement) < 1 & l < rechts) {
				l++;
			}
			// rechter rand bis kleiner pivot und größer gleich als der von
			// diesem rekursionablauf zu bearabeitende, maximale linke rand
			while (pivotelement.compareTo(list.get(r)) < 1 & r > links) {
				r--;
			}
			// Hier gibt es ein element links und eine element recht vom pivot
			// element, welche beide auf der falschen seite liegen. Die indizes
			// dieser element liegen bei l und r
			if (l < r) {
				// Dreieckstausch
				tauschen(l, r);
			}
		} while (l < r);
		// Übriges element tauschen
		if (pivotelement.compareTo(list.get(l)) < 0) {
			tauschen(l, rechts);
		}
		return l;
	}

	private void tauschen(int a, int b) {
		T tmp = list.get(a);
		list.set(a, list.get(b));
		list.set(b, tmp);
	}

}
