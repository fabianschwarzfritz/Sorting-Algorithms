package fabian.sorter;

import java.util.List;

public class BubbleSorterImpl implements Sorter {

	private List<? extends Comparable> list;

	public BubbleSorterImpl(List<? extends Comparable> list) {
		super();
		this.list = list;
	}

	@Override
	public List<? extends Comparable> sort() {
		return list;
	}

}
