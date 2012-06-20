package fabian.sorter;

import java.util.List;

public interface Sorter<T extends Comparable<T>> {
	List<T> sort();

	void setList(List<T> list);

	List<T> getList();
}
