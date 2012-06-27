package arrayimpl.abstractalg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.junit.Test;

import arrayimpl.sorterimpl.BubbleSorterImpl;
import arrayimpl.sorterimpl.HeapSorterImpl;
import arrayimpl.sorterimpl.PivotMedianImpl;
import arrayimpl.sorterimpl.PivotRightImpl;
import arrayimpl.sorterimpl.QuickSortImpl;
import arrayimpl.sorterimpl.QuickSorterConcurrencyQuickSortImpl;
import arrayimpl.sorterimpl.QuickSorterThreeWayImpl;
import arrayimpl.sorterimpl.SortierenDurchAuswaehlenImpl;
import arrayimpl.sorterimpl.SortierenDurchEinfuegenImpl;

public class SorterTester extends TestCase {
	private List<Integer> valueCounts = new ArrayList<Integer>();
	private static final long MAXVALUE = Integer.MAX_VALUE;
	private static final long MINVALUE = Integer.MIN_VALUE;
	private static final long PROBES = 1;

	private static final Logger logger = Logger.getLogger("logger");

	public SorterTester() {
		valueCounts = new ArrayList<Integer>();
		// valueCounts.add(new Integer(1000));
		// valueCounts.add(new Integer(10000));
		// valueCounts.add(new Integer(100000));
		// valueCounts.add(new Integer(1000000));
		// valueCounts.add(new Integer(3000000));
		// valueCounts.add(new Integer(5000000));
//		valueCounts.add(new Integer(10000000)); // Zehnmillionen
		// valueCounts.add(new Integer(20000000));
		// valueCounts.add(new Integer(50000000));
		 valueCounts.add(new Integer(100000000));
		// valueCounts.add(new Integer(50000000));
		// valueCounts.add(new Integer(70000000));
		// valueCounts.add(new Integer(90000000));
		// valueCounts.add(new Integer(100000000));
		// valueCounts.add(new Integer(200000000));
		// valueCounts.add(new Integer(300000000));
		// valueCounts.add(new Integer(500000000));
		// valueCounts.add(new Integer(1000000000));
	}

	@Test
	public void testSorter() {
		Map<String, Sorter> sortermap = new HashMap<String, Sorter>();
		sortermap.put("aquicksorter normal pivot rechts", new QuickSortImpl(
				new PivotRightImpl()));
		// sortermap.put("quicksortparalel pr",
		// new QuickSorterConcurrencyQuickSortImpl(logger,
		// new PivotRightImpl()));
		// sortermap.put("quicksorter norm pm", new QuickSortImpl(
		// new PivotMedianImpl()));
		// sortermap.put("quicksortparalel pm",
		// new QuickSorterConcurrencyQuickSortImpl(logger,
		// new PivotRightImpl()));
//		sortermap.put("quicksortparalel pm 20p 100\t",
//				new QuickSorterConcurrencyQuickSortImpl(20, 1,
//						new PivotRightImpl()));
		
		sortermap.put("quicksortparalel pm 2p 5000t\t",
				new QuickSorterConcurrencyQuickSortImpl(2, 5000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 2p 10000t\t",
				new QuickSorterConcurrencyQuickSortImpl(2, 10000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 2p 50000t\t",
				new QuickSorterConcurrencyQuickSortImpl(2, 50000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 2p 100000t\t",
				new QuickSorterConcurrencyQuickSortImpl(2, 100000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 2p 1000000t\t",
				new QuickSorterConcurrencyQuickSortImpl(2, 1000000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 5p 5000t\t",
				new QuickSorterConcurrencyQuickSortImpl(5, 5000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 5p 10000t\t",
				new QuickSorterConcurrencyQuickSortImpl(5, 10000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 5p 50000t\t",
				new QuickSorterConcurrencyQuickSortImpl(5, 50000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 5p 100000t\t",
				new QuickSorterConcurrencyQuickSortImpl(5, 100000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 5p 1000000t\t",
				new QuickSorterConcurrencyQuickSortImpl(5, 1000000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 20p 3000\t",
				new QuickSorterConcurrencyQuickSortImpl(20, 3000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 20p 5000t\t",
				new QuickSorterConcurrencyQuickSortImpl(20, 5000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 20p 10000t\t",
				new QuickSorterConcurrencyQuickSortImpl(20, 10000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 20p 50000\t",
				new QuickSorterConcurrencyQuickSortImpl(20, 50000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 20p 100000\t",
				new QuickSorterConcurrencyQuickSortImpl(20, 100000,
						new PivotRightImpl()));
		sortermap.put("quicksortparalel pm 20p 1000000\t",
				new QuickSorterConcurrencyQuickSortImpl(20, 1000000,
						new PivotRightImpl()));
		// sortermap.put("quicksorter three", new QuickSorterThreeWayImpl(
		// new PivotRightImpl()));
		// sortermap.put("heapsorter     ", new HeapSorterImpl());
		// sortermap.put("bubblesorter   ", new BubbleSorterImpl());
		// sortermap.put("sortieren d einf", new SortierenDurchEinfuegenImpl());
		// sortermap.put("sortieren d aus", new SortierenDurchAuswaehlenImpl());
		for (Integer i : valueCounts) {
			executeSorter(sortermap, generateList(i));
		}
	}

	private void executeSorter(Map<String, Sorter> entry, int[] integerarr) {
		System.out.print("Listsize " + integerarr.length + " -- Werte von "
				+ MINVALUE + " bis " + MAXVALUE + ": \n");
		for (Map.Entry<String, Sorter> sorterentry : entry.entrySet()) {
			System.out.print("\t" + sorterentry.getKey() + ": \t");
			for (int i = 0; i < PROBES; i++) {
				long milliseconds = sortAndGetMillis(sorterentry.getValue(),
						Arrays.copyOf(integerarr, integerarr.length));
//				 integerarr);
				System.out.print(milliseconds + "ms\t");
			}
			System.out.println();
		}
	}

	private int[] generateList(int valuecount) {
		System.out.println("Generating value list with count " + valuecount);
		int[] arr = new int[valuecount];
		for (int i = 0; i < valuecount; i++) {
			arr[i] = (int) ((Math.random() * (MAXVALUE - MINVALUE)) + MINVALUE);
		}
		return arr;
	}

	private long sortAndGetMillis(Sorter sorter, int[] shuffledList) {
		// System.out.println("Sorting: " + Arrays.toString(shuffledList));
		sorter.setArr(shuffledList);
		long sortstart = System.currentTimeMillis();
		int[] sort = sorter.sort();
		long sortstop = System.currentTimeMillis();
		assertEquals(shuffledList.length, sort.length);
		int[] sorted = shuffledList.clone();
		Arrays.sort(sorted);
		// System.out.println("\n--");
		// System.out.println("solution: " + Arrays.toString(sorted));
		// System.out.println("sorted  : " + Arrays.toString(sort));
		// assertTrue(Arrays.equals(sorted, sort));
		// int old = Integer.MIN_VALUE;
		// for (int i = 0; i < sort.length; i++) {
		// int long1 = sort[i];
		// // System.out.println(sorter.getClass().getName());
		// // System.out.println(long1 + " - " + reference.get(i));
		// assertTrue(old + " doesn't seem to be smaller than " + long1,
		// old <= long1);
		// old = long1;
		// }
		return sortstop - sortstart;
	}
}
