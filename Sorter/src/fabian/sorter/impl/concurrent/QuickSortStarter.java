package fabian.sorter.impl.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class QuickSortStarter<T extends Comparable<T>> {

	private List<T> list;
	private ExecutorService service;
	private int corepoolsize;

	private int tiefe;

	private List<Future<List<T>>> quickSorterList;
	private List<List<T>> listsForQuickSort;

	public QuickSortStarter(List<T> list) {
		this.list = list;
		quickSorterList = new LinkedList<Future<List<T>>>();
		listsForQuickSort = new LinkedList<List<T>>();
		// Verbesserungwuerdig
		corepoolsize = Runtime.getRuntime().availableProcessors() * 2;
		this.service = Executors.newFixedThreadPool(corepoolsize);
		tiefe = (int) (Math.log(corepoolsize) / Math.log(2.0));
	}

	public List<T> startSorting() {
		divideAndStart(list, 0);
		List<T> finalResult = new LinkedList<T>();
		// Wait for every thread to be ready
		long currentTimeMillis = System.currentTimeMillis();
		for (Future<List<T>> future : quickSorterList) {
			try {
				List<T> result = future.get();
				finalResult.addAll(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		long currentTimeMillis2 = System.currentTimeMillis();
		return finalResult;
	}

	private void divideAndStart(List<T> list, int currenttiefe) {
		if (currenttiefe < tiefe) {
			// List inclusive l, (now, because of +1) inclusive r
			QuickSortTask<T> quickSorter1 = new QuickSortTask<T>(list);
			int pivot = quickSorter1.divide(0, list.size() - 1);
			int newcurrenttiefe = currenttiefe + 1;
			List<T> list1 = new LinkedList<T>(list.subList(0, pivot));
			List<T> list2 = new LinkedList<T>(list.subList(pivot, list.size()));
			divideAndStart(list1, newcurrenttiefe);
			divideAndStart(list2, newcurrenttiefe);
		} else {
			QuickSortTask<T> quickSorter1 = new QuickSortTask<T>(list);
			Future<List<T>> submit = service.submit(quickSorter1);
			quickSorterList.add(submit);
		}
	}
}
