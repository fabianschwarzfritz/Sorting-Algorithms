package fabian.sorter.impl;

import java.util.List;

import fabian.sorter.Sorter;

public class SortierenDurchEinfuegenImpl<T extends Comparable<T>> implements
		Sorter<T> {

	private List<T> list;

	public SortierenDurchEinfuegenImpl(List<T> list) {
		super();
		this.list = list;
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

	@Override
	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public List<T> getList() {
		return list;
	}
}
