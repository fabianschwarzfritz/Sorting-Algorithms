package fabian.sorter.impl.concurrent2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import fabian.sorter.impl.SorterImpl;

public class QuickSorterConcurrency2<T extends Comparable<T>> extends
		SorterImpl<T> {

	public static ConcurrentLinkedQueue<Future<?>> running = new ConcurrentLinkedQueue<Future<?>>();
	private static ExecutorService es = Executors.newFixedThreadPool(8);

	@Override
	public List<T> sort() {
		try {
			quicksort(0, list.size() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void quicksort(final int l, final int r) throws Exception {
		Long[] arr = new Long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			arr[i] = (Long) t;
		}
		// long l1 = System.currentTimeMillis();
		quickSortSequential(arr, l, r);
		// long l2 = System.currentTimeMillis();
		// System.out.println(l2 - l1);
		List<T> asList = (List<T>) Arrays.asList(arr);
		list = asList;
	}

	private static <T extends Comparable<T>> void quickSortSequential(T[] arr,
			int left, int right) {
		if (right > left) {
			int pivot = partition(arr, left, right);
			quickSortSequential(arr, left, pivot - 1);
			quickSortSequential(arr, pivot + 1, right);
		}
	}

	public <T extends Comparable<T>> void quickSortParallel(final T[] arr,
			final int left, final int right) {
		quickSortParallelHelper(arr, left, right);
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
			final T[] arr, final int left, final int right) {

		if (right > left) {
			final int pivot = partition(arr, left, right);
			if (right - left > 10000) {
				quickSortSequential(arr, left, right);
			} else {
				Runnable r1 = new Runnable() {
					@Override
					public void run() {
						quickSortParallelHelper(arr, left, pivot - 1);
					}
				};
				Runnable r2 = new Runnable() {
					@Override
					public void run() {
						quickSortParallelHelper(arr, pivot + 1, right);
					}
				};

				// This Future blocks until all the children Future is offered
				// to the queue
				running.offer(es.submit(r1));
				running.offer(es.submit(r2));
			}
		}

	}

	private static <T extends Comparable<T>> int partition(T[] arr, int low,
			int high) {
		int pivotIndex = (low + high) / 2;
		T pivotValue = arr[pivotIndex];
		swap(arr, low, pivotIndex); // Move pivot to end
		int left = low;
		for (int i = low + 1; i <= high; i++) {
			if (arr[i].compareTo(pivotValue) < 0) {
				left++;
				swap(arr, i, left);
			}
		}
		swap(arr, low, left); // Move pivot to its final place
		return left;
	}

	private static <T> void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}
