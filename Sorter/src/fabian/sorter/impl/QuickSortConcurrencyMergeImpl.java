package fabian.sorter.impl;

import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class QuickSortConcurrencyMergeImpl<T extends Comparable<T>> extends
		SorterImpl<T> {

	private int corepoolsize;
	private int threadcount;
	private ExecutorService executor;

	public QuickSortConcurrencyMergeImpl() {
		corepoolsize = Runtime.getRuntime().availableProcessors() * 2;
		System.out.println("COREPO: " + corepoolsize);
		// int maxthreadcount = corepoolsize;
		// executor = new ThreadPoolExecutor(corepoolsize, maxthreadcount, 0,
		// TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
		executor = Executors.newFixedThreadPool(corepoolsize);
	}

	@Override
	public List<T> sort() {
		try {
			quicksort(0, list.size() - 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private void quicksort(final int l, final int r) throws Exception {
		if (l < r) {
			final int teiler = divide(l, r);
			Callable<Void> runnable = new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					quicksort(l, teiler - 1);
					return null;
				}
			};
			Callable<Void> runnable2 = new Callable<Void>() {
				@Override
				public Void call() throws Exception {
					quicksort(teiler, r);
					return null;
				}
			};
			if (threadcount <= corepoolsize - 2) {
				threadcount += 2;
				Future<Void> submit = executor.submit(runnable);
				Future<Void> submit2 = executor.submit(runnable2);
				submit.get();
				submit2.get();
				threadcount -= 2;
			} else {
				runnable.call();
				runnable2.call();
			}
		}
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
