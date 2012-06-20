package fabian.sorter.impl;

import java.util.List;

import fabian.sorter.Sorter;

public abstract class SortImpl<T extends Comparable<T>> implements Sorter<T> {
	protected List<T> list;

	@Override
	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public List<T> getList() {
		return list;
	}

}
