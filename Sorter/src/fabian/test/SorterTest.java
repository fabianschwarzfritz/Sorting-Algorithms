package fabian.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import fabian.sorter.BubbleSorterImpl;
import fabian.sorter.Sorter;

public class SorterTest extends TestCase {

	private static final long VALUECOUNT = 1000000;
	private static final long MAXVALUE = 100000000;
	private static final long MINVALUE = 1;

	@Test
	public void testSorter() {
		List<Long> list = new ArrayList<Long>();
		for (int i = 0; i < VALUECOUNT; i++) {
			list.add(new Long((long) ((Math.random() * MAXVALUE) + MINVALUE)));
		}
		Sorter sorter = new BubbleSorterImpl(list);
		List<? extends Comparable> sort = sorter.sort();
		Comparable old = null;
		for (int i = 0; i < sort.size(); i++) {
			Comparable current = sort.get(i);
			if (old != null) {
				int compareTo = old.compareTo(current);
				assertEquals(-1, compareTo);
			}
			old = sort.get(i);
		}
	}
}
