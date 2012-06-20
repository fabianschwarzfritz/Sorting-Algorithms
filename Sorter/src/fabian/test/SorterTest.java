package fabian.test;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import fabian.sorter.Sorter;
import fabian.sorter.SortierenDurchEinfuegenImpl;

public class SorterTest extends TestCase {

	private static final long VALUECOUNT = 100000;
	private static final long MAXVALUE = 100000000;
	private static final long MINVALUE = 1;

	@Test
	public void testSorter() {
		List<Long> generateList = generateList();
		long millisSortierenDurchEinfuegen = sortAndGetMillis(
				new SortierenDurchEinfuegenImpl<Long>(), generateList);
		System.out.println("Sortieren durch Einfuegen: "
				+ millisSortierenDurchEinfuegen + "\tbei Elementen von \t"
				+ MINVALUE + "\t bis \t" + MAXVALUE
				+ "\t und einer listenlaenge von \t" + VALUECOUNT);
	}

	private List<Long> generateList() {
		List<Long> list = new ArrayList<Long>();
		for (int i = 0; i < VALUECOUNT; i++) {
			list.add(new Long((long) ((Math.random() * MAXVALUE) + MINVALUE)));
		}
		return list;
	}

	private long sortAndGetMillis(Sorter<Long> sorter, List<Long> shuffledList) {
		sorter.setList(shuffledList);
		long sortstart = System.currentTimeMillis();
		List<Long> sort = sorter.sort();
		long sortstop = System.currentTimeMillis();
		Long old = null;
		for (Long long1 : sort) {
			assertNotNull(long1);
			if (old != null) {
				assertTrue(old <= long1);
			}
			old = new Long(long1);
		}
		return sortstop - sortstart;
	}
}
