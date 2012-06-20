package fabian.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import fabian.sorter.Sorter;
import fabian.sorter.impl.SortierenDurchAuswaehlenImpl;
import fabian.sorter.impl.SortierenDurchEinfuegenImpl;

public class SorterTest extends TestCase {

	private List<Integer> valueCounts = new ArrayList<Integer>();
	private static final long MAXVALUE = 100000;
	private static final long MINVALUE = 1;

	@Override
	protected void setUp() throws Exception {
		valueCounts = new ArrayList<Integer>();
		valueCounts.add(new Integer(1000));
		valueCounts.add(new Integer(10000));
		valueCounts.add(new Integer(100000));
	}

	@Test
	public void testSorter() {
		Map<Integer, List<Long>> generateRandomLists = generateRandomLists();
		for (Map.Entry<Integer, List<Long>> entry : generateRandomLists
				.entrySet()) {
			do5Times("Sortieren durch einfuegen", entry.getValue(),
					new SortierenDurchEinfuegenImpl<Long>());
			do5Times("Sortieren durch auswaehlen", entry.getValue(),
					new SortierenDurchAuswaehlenImpl<Long>());
		}

	}

	private Map<Integer, List<Long>> generateRandomLists() {
		Map<Integer, List<Long>> map = new HashMap<Integer, List<Long>>();
		for (Integer integer : valueCounts) {
			map.put(integer, generateList(integer));
		}
		return map;
	}

	private void do5Times(String name, List<Long> generatedList,
			Sorter<Long> sorter) {
		for (int i = 0; i < 5; i++) {
			long millisSortierenDurchEinfuegen = sortAndGetMillis(sorter,
					generatedList);
			System.out.println(name + ": \t" + millisSortierenDurchEinfuegen
					+ "\tms - Listenlaenge:\t" + generatedList.size()
					+ " \t von " + MINVALUE + " bis " + MAXVALUE);
		}
	}

	private List<Long> generateList(int valuecount) {
		List<Long> list = new ArrayList<Long>();
		for (int i = 0; i < valuecount; i++) {
			list.add(new Long((long) ((Math.random() * MAXVALUE) + MINVALUE)));
		}
		return list;
	}

	private long sortAndGetMillis(Sorter<Long> sorter, List<Long> shuffledList) {
		List<Long> listForSorter = new ArrayList<Long>(shuffledList);
		sorter.setList(listForSorter);
		long sortstart = System.currentTimeMillis();
		List<Long> sort = sorter.sort();
		long sortstop = System.currentTimeMillis();
		Long old = null;
		for (Long long1 : sort) {
			assertNotNull(long1);
			if (old != null) {
				assertTrue(old + " doesn't seem to be smaller than " + long1,
						old <= long1);
			}
			old = new Long(long1);
		}
		return sortstop - sortstart;
	}
}
