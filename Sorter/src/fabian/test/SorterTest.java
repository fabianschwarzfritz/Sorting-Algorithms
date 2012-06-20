package fabian.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

import fabian.sorter.Sorter;
import fabian.sorter.impl.BubbleSortImpl;
import fabian.sorter.impl.QuickSortConcurrencyMergeImpl;
import fabian.sorter.impl.QuickSortImpl;
import fabian.sorter.impl.ShellSorterImpl;
import fabian.sorter.impl.SortierenDurchAuswaehlenImpl;
import fabian.sorter.impl.SortierenDurchEinfuegenImpl;

public class SorterTest extends TestCase {

	private List<Integer> valueCounts = new ArrayList<Integer>();
	private static final long MAXVALUE = 100000;
	private static final long MINVALUE = 1;
	private static final long PROBES = 1;

	public SorterTest() {
		valueCounts = new ArrayList<Integer>();
		valueCounts.add(new Integer(1000));
		valueCounts.add(new Integer(10000));
		valueCounts.add(new Integer(100000));
		valueCounts.add(new Integer(1000000));
		valueCounts.add(new Integer(10000000));
	}

	@Test
	public void testSorter() {
		Map<String, Sorter<Long>> sortermap = new HashMap<String, Sorter<Long>>();
		List<List<Long>> generateRandomLists = generateRandomLists();
		// sortermap.put("Sortieren durch einfuegen",
		// new SortierenDurchEinfuegenImpl<Long>());
		// sortermap.put("Sortieren Durch Auswaehlen",
		// new SortierenDurchAuswaehlenImpl<Long>());
		// // sortermap.put("Bubble Sort", new BubbleSortImpl<Long>());
		// sortermap.put("ShellSort", new ShellSorterImpl<Long>());
		sortermap.put("Quicksort\t", new QuickSortImpl<Long>());
		sortermap.put("Quicksortparallel",
				new QuickSortConcurrencyMergeImpl<Long>());
		executeSorter(sortermap, generateRandomLists);
	}

	private List<List<Long>> generateRandomLists() {
		List<List<Long>> result = new ArrayList<List<Long>>();
		for (Integer integer : valueCounts) {
			result.add(generateList(integer));
		}
		return result;
	}

	private void executeSorter(Map<String, Sorter<Long>> entry,
			List<List<Long>> generateList) {
		for (List<Long> list : generateList) {
			System.out.print("Listsize " + list.size() + " -- Werte von "
					+ MINVALUE + " bis " + MAXVALUE + ": \n");
			for (Map.Entry<String, Sorter<Long>> sorterentry : entry.entrySet()) {
				System.out.print("\t" + sorterentry.getKey() + ": \t");
				for (int i = 0; i < PROBES; i++) {
					long milliseconds = sortAndGetMillis(
							sorterentry.getValue(), list);
					System.out.print(milliseconds + "ms\t");
				}
				System.out.println();
			}
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
		List<Long> reference = new ArrayList<Long>(shuffledList);
		Collections.sort(reference);
		assertEquals(reference, sort);
		Long old = null;
		for (int i = 0; i < sort.size(); i++) {
			Long long1 = sort.get(i);
			assertNotNull(long1);
			// System.out.println(long1 + " - " + reference.get(i));
			if (old != null) {
				assertTrue(old + " doesn't seem to be smaller than " + long1,
						old <= long1);
			}
			old = new Long(long1);
		}
		return sortstop - sortstart;
	}
}
