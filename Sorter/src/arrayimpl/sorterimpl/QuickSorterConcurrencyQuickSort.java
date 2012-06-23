package arrayimpl.sorterimpl;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import arrayimpl.abstractalg.Sorter;

public class QuickSorterConcurrencyQuickSort implements Sorter {

	private static Logger logger;
	private int ELEMENTCOUNT_SEQUENTIALSORTING;

	private static ConcurrentLinkedQueue<Future<?>> running = new ConcurrentLinkedQueue<Future<?>>();
	private static ExecutorService service;

	private int[] arr;
	private boolean calc = false;

	public QuickSorterConcurrencyQuickSort(Logger logger) {
		this.logger = logger;
		calc = true;
	}

	@Override
	public int[] sort() {
		if (calc) {
			int processors = Runtime.getRuntime().availableProcessors();
			service = Executors.newFixedThreadPool(processors);
			ELEMENTCOUNT_SEQUENTIALSORTING = arr.length / processors;
		}
		// logger.log(Level.INFO, "Length: " + arr.length + " | elementsingle: "
		// + ELEMENTCOUNT_SEQUENTIALSORTING);
		quickSortParallel(arr, 0, arr.length - 1);
		// quickSortSequential(arr, 0, arr.length - 1);
		return arr;
	}

	/**
	 * Startet sequentiell den algorithmus
	 */
	private void quickSortSequential(int[] arr, int left, int right) {
		if (right > left) {
			int pivot = divide(arr, left, right);
			quickSortSequential(arr, left, pivot - 1);
			quickSortSequential(arr, pivot + 1, right);
		}
	}

	public void quickSortParallel(final int[] arr, final int left,
			final int right) {
		quickSortParallelStart(arr, left, right);
		// Waiting for good news
		for (Future<?> f; (f = running.poll()) != null;) {
			try {
				// Erfolgreich ein Element aus der queue geholt, und ab geht der
				// thread
				// System.out.println("waiting for queuehead");
				f.get();
				// System.out.println("Got ready thread: queue size: "
				// + running.size());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		// Good news!
	}

	public void quickSortParallelStart(final int[] arr, final int left,
			final int right) {
		// System.out.println("Groeße : " + running.size());
		if (right > left) {
			final int pivot = divide(arr, left, right);
			// Sortiere sequenitell
			if (right - left < ELEMENTCOUNT_SEQUENTIALSORTING) {
				quickSortSequential(arr, left, right);
			} else {
				// Parallelisiere weiter, da elementanzahl groß
				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						// System.out.println("Starting thread: " + " from "
						// + left + " to " + (pivot - 1));
						quickSortParallelStart(arr, left, pivot - 1);
					}
				};
				Runnable r2 = new Runnable() {
					@Override
					public void run() {
						// System.out.println("Starting thread: " + " from "
						// + (pivot + 1) + " to " + right);
						quickSortParallelStart(arr, pivot + 1, right);
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
		int pivotelement = arr[rechts];
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

	@Override
	public void setArr(int[] arr) {
		this.arr = arr;
	}
}
