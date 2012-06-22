package fabian.sorter.concurrency3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import fabian.sorter.impl.SorterImpl;

public class QuickSorterConcurrency3<T extends Comparable<T>> extends
		SorterImpl<T> {

	private ConcurrentLinkedQueue<Future<?>> running;
	private ExecutorService es;

	public QuickSorterConcurrency3() {
		running = new ConcurrentLinkedQueue<Future<?>>();
		es = Executors.newFixedThreadPool(8);
	}

	@Override
	public List<T> sort() {
		try {
			quickSortSequential(list, 0, list.size() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public <T extends Comparable<T>> void quickSortSequential(List<T> list,
			int l, int r) throws Exception {
		if (l < r) {
			final int teiler = divide(list, l, r);
			quickSortSequential(list, l, teiler - 1);
			quickSortSequential(list, teiler + 1, r);
		}
	}

	public <T extends Comparable<T>> void quickSortParallel(
			final ArrayList<T> list, final int left, final int right)
			throws Exception {
		quickSortParallelHelper(list, left, right);
		for (Future<?> f; (f = running.poll()) != null;) {
			try {
				f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	public <T extends Comparable<T>> void quickSortParallelHelper(
			final ArrayList<T> list, final int left, final int right)
			throws Exception {
		if (right > left) {
			final int pivot = divide(list, left, right);
			if (right - left > 10000) {
				quickSortSequential(list, left, right);
			} else {
				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						try {
							quickSortParallelHelper(list, left, pivot - 1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};
				Runnable r2 = new Runnable() {
					@Override
					public void run() {
						try {
							quickSortParallelHelper(list, pivot + 1, right);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				};

				// This Future blocks until all the children Future is offered
				// to the queue
				running.offer(es.submit(r1));
				running.offer(es.submit(r2));
			}
		}
	}

	protected <T extends Comparable<T>> int divide(List<T> list, int links,
			int rechts) {
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
				tauschen(list, l, r);
			}
		} while (l < r);
		// Übriges element tauschen
		if (pivotelement.compareTo(list.get(l)) < 0) {
			tauschen(list, l, rechts);
		}
		return l;
	}

	private <T extends Comparable<T>> void tauschen(List<T> list, int a, int b) {
		T tmp = list.get(a);
		list.set(a, list.get(b));
		list.set(b, tmp);
	}

}
