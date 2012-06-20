package fabian.sorter.impl;

import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QuickSortImpl<T extends Comparable<T>> extends
		SorterImpl<T> {

	private int corepoolsize;
	private ThreadPoolExecutor executor;

	public QuickSortImpl() {
		corepoolsize = Runtime.getRuntime().availableProcessors() * 2;
		int maxthreadcount = corepoolsize;
		executor = new ThreadPoolExecutor(corepoolsize, maxthreadcount, 0,
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	}

	@Override
	public List<T> sort() {
		return quicksort(0, list.size() - 1);
	}

	private List<T> quicksort(int l, int r) {
		if (l < r) {
			int teiler = divide(l, r);
			quicksort(l, teiler - 1);
			quicksort(teiler, r);
		}
		return list;
	}

	private int divide(int links, int rechts) {
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
