package arrayimpl.sorterimpl;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import arrayimpl.abstractalg.PivotCalculator;
import arrayimpl.abstractalg.Sorter;

public class QuickSorterConcurrencyQuickSortImpl implements Sorter {

	private PivotCalculator pivot;

	/*
	 * Elementzahl, ab welcher noch parrallel sortier werden soll. Ist diese
	 * Elementzahl keiner, wird sequentiell sortiert
	 */
	private int elementcount_sequentialQuicksort;

	/*
	 * Queue für threads in der (synchronisierten) Schlagne. Die Elemente der
	 * Schlange sind vom type Future. (doc:
	 * "A Future represents the result of an asynchronous computation")
	 */
	private static ConcurrentLinkedQueue<Future<?>> running = new ConcurrentLinkedQueue<Future<?>>();
	/* Executor service, welcher den threadpool enhält */
	private static ExecutorService service;

	private int[] arr;

	public QuickSorterConcurrencyQuickSortImpl(PivotCalculator pivot) {
		this.pivot = pivot;
	}

	public QuickSorterConcurrencyQuickSortImpl(int poolcount,
			int eLEMENTCOUNT_SEQUENTIALSORTING, PivotCalculator pivot) {
		super();
		elementcount_sequentialQuicksort = eLEMENTCOUNT_SEQUENTIALSORTING;
		this.pivot = pivot;
		// Threadpool mit fixer anzahl
		service = Executors.newFixedThreadPool(poolcount);
	}

	@Override
	public int[] sort() {
		quickSortParallel(arr, 0, arr.length - 1);
		// Front element der queue holen, falls eines da.
		for (Future<?> f; (f = running.poll()) != null;) {
			try {
				// Erfolgreich ein Element aus der queue geholt, und ab geht der
				// System.out.println("waiting for queuehead");
				f.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		// Good news! - alle runnables abgearbeitet - die liste muss hier
		// sortiert sein
		// FIXME terminierung checken
		// B.S. Mail:
		// "Die threads müssen dann aber terminieren, nachdem sie verzweigt haben (das könnte etwas tricky werden)."
		return arr;
	}

	/**
	 * Startet den Sequentiellen quicksort (wie gewohnt). Nach Auführung sind
	 * die Elemente von <code>left</code> bis <code>right</code> auf dem
	 * gegebenen array <code>arr</code> sortiert.
	 * 
	 * @param arr
	 *            Das array auf dem der quicksort ausgeführt werden soll
	 * @param left
	 *            Das linkeste Element zwischen dem gesucht werden soll
	 * @param right
	 *            Das rechteste Element zwischen dem gesucht werden soll
	 */
	private void quickSortSequential(int[] arr, int left, int right) {
		if (right > left) {
			int pivot = divide(arr, left, right);
			quickSortSequential(arr, left, pivot - 1);
			quickSortSequential(arr, pivot + 1, right);
		}
	}

	/**
	 * Startet den Sequentiellen quicksort (wie gewohnt). Nach Auführung sind
	 * die Elemente von <code>left</code> bis <code>right</code> auf dem
	 * gegebenen array <code>arr</code> sortiert.
	 * 
	 * @param arr
	 *            Das array auf dem der quicksort ausgeführt werden soll
	 * @param left
	 *            Das linkeste Element zwischen dem gesucht werden soll
	 * @param right
	 *            Das rechteste Element zwischen dem gesucht werden soll
	 */
	public void quickSortParallel(final int[] arr, final int left,
			final int right) {
		// System.out.println("Groeße : " + running.size());
		if (right > left) {
			final int pivot = divide(arr, left, right);
			// Sortiere sequenitell
			if (right - left < elementcount_sequentialQuicksort) {
				quickSortSequential(arr, left, right);
			} else {
				// Parallelisiere weiter, da elementanzahl groß
				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						// System.out.println("Starting thread: " + " from "
						// + left + " to " + (pivot - 1));
						quickSortParallel(arr, left, pivot - 1);
					}
				};
				Runnable r2 = new Runnable() {
					@Override
					public void run() {
						// System.out.println("Starting thread: " + " from "
						// + (pivot + 1) + " to " + right);
						quickSortParallel(arr, pivot + 1, right);
					}
				};
				// Future representation der runnables zur queue hinzufuegen
				running.offer(service.submit(r1));
				running.offer(service.submit(r2));
				// System.out.println("offered");
			}
		}

	}

	private int divide(int[] arr, int links, int rechts) {
		int pivotelement = pivot.getPivot(arr, links, rechts);
		int l = links;
		int r = rechts;
		do {
			// linken rand bis kleiner pivot und l kleiner gleich als der von
			// diesem rekursionablauf zu bearabeitende, maximale rechte rand
			while (arr[l] <= pivotelement & l < rechts) {
				l++;
			}
			// rechter rand bis kleiner pivot und größer gleich als der von
			// diesem rekursionablauf zu bearabeitende, maximale linke rand
			while (pivotelement <= arr[r] & r > links) {
				r--;
			}
			// Hier gibt es ein element links und eine element recht vom pivot
			// element, welche beide auf der falschen seite liegen. Die indizes
			// dieser element liegen bei l und r
			if (l < r) {
				// Dreieckstausch
				swap(arr, l, r);
			}
		} while (l < r);
		// Übriges element tauschen
		if (pivotelement < arr[l]) {
			swap(arr, l, rechts);
		}
		return l;
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * @param arr
	 *            the arr to set
	 */
	public void setArr(int[] arr) {
		this.arr = arr;
	}

}
