package fabian.sorter.impl.concurrent;

import java.util.List;
import java.util.concurrent.Callable;

public class QuickSortTask<T extends Comparable<T>> extends QuickSort<T>
		implements Callable<List<T>> {

	public QuickSortTask(List<T> list) {
		super(list);
	}

	@Override
	public List<T> call() throws Exception {
		quicksort(0, list.size() - 1);
		return list;
	}
}
