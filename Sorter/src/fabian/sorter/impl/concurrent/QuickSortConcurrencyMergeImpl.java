package fabian.sorter.impl.concurrent;

import java.util.List;

import fabian.sorter.impl.SorterImpl;

public class QuickSortConcurrencyMergeImpl<T extends Comparable<T>> extends
		SorterImpl<T> {

	public QuickSortConcurrencyMergeImpl() {
	}

	@Override
	public List<T> sort() {
		QuickSortStarter<T> starter = new QuickSortStarter<T>(list);
		return starter.startSorting();
	}
}
