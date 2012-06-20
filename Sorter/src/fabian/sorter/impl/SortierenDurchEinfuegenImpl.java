package fabian.sorter.impl;

import java.util.List;

public class SortierenDurchEinfuegenImpl<T extends Comparable<T>> extends
		SortImpl<T> {

	public SortierenDurchEinfuegenImpl(List<T> list) {
		super.list = list;
	}

	public SortierenDurchEinfuegenImpl() {
	}

	@Override
	public List<T> sort() {
		int j = 0;
		T x = null;
		for (int i = 1; i < list.size(); i++) {
			x = list.get(i);
			j = i;
			while (j > 0 && x.compareTo(list.get(j - 1)) < 0) {
				list.set(j, list.get(j - 1));
				j--;
			}
			list.set(j, x);
		}
		return list;
	}
}