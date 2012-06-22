package arrayimpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import junit.framework.TestCase;

import org.junit.Test;

public class SorterTester extends TestCase {

	private List<Integer> valueCounts = new ArrayList<Integer>();
	private static final long MAXVALUE = 100000;
	private static final long MINVALUE = 1;
	private static final long PROBES = 1;

	private static final Logger logger = Logger.getLogger("logger");

	public SorterTester() {
		StreamHandler sh = new StreamHandler(System.out, new SimpleFormatter());
		logger.addHandler(sh);
		logger.log(Level.INFO, "alle");
		valueCounts = new ArrayList<Integer>();
		valueCounts.add(new Integer(1000));
		valueCounts.add(new Integer(10000));
		valueCounts.add(new Integer(100000));
		valueCounts.add(new Integer(1000000));
		valueCounts.add(new Integer(10000000));
		valueCounts.add(new Integer(20000000));
		valueCounts.add(new Integer(50000000));
		valueCounts.add(new Integer(70000000));
		valueCounts.add(new Integer(100000000));
		valueCounts.add(new Integer(300000000));
		valueCounts.add(new Integer(500000000));
		valueCounts.add(new Integer(1000000000));
	}

	@Test
	public void testSorter() {
		Map<String, Sorter> sortermap = new HashMap<String, Sorter>();
		sortermap.put("aQuickpasdfasdfasdf", new QuickSortImpl());
		sortermap.put("calculatedconf", new QuickSorterConcurrencyQuickSort(
				logger));
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
				// integerarr);
				System.out.print(milliseconds + "ms\t");
			}
			System.out.println();
		}
	}

	private int[] generateList(int valuecount) {
		System.out.println("Generating value list with count " + valuecount);
		int[] arr = new int[valuecount];
		for (int i = 0; i < valuecount; i++) {
			arr[i] = (int) ((Math.random() * MAXVALUE) + MINVALUE);
		}
		return arr;
	}

	private long sortAndGetMillis(Sorter sorter, int[] shuffledList) {
		sorter.setArr(shuffledList);
		long sortstart = System.currentTimeMillis();
		int[] sort = sorter.sort();
		long sortstop = System.currentTimeMillis();
		assertEquals(shuffledList.length, sort.length);
		// int[] sorted = shuffledList.clone();
		// Arrays.sort(sorted);
		// assertTrue(Arrays.equals(sorted, sort));
		// int old = Integer.MIN_VALUE;
		// for (int i = 0; i < sort.length; i++) {
		// int long1 = sort[i];
		// // System.out.println(long1 + " - " + reference.get(i));
		// assertTrue(old + " doesn't seem to be smaller than " + long1,
		// old <= long1);
		// old = long1;
		// }
		return sortstop - sortstart;
	}
}
