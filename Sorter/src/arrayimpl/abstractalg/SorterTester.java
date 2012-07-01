package arrayimpl.abstractalg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import junit.framework.TestCase;

public class SorterTester extends TestCase {
	protected List<Integer> valueCounts = new ArrayList<Integer>();
	protected static long MAXVALUE = 100;
	protected static long MINVALUE = 1;
	protected static long PROBES = 5;

	private static final Logger logger = Logger.getLogger("logger");

	protected void executeSorter(Map<String, Sorter> entry, int[] integerarr) {
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

	protected int[] generateList(int valuecount) {
		System.out.println("Generating value list with count " + valuecount);
		int[] arr = new int[valuecount];
		for (int i = 0; i < valuecount; i++) {
			arr[i] = (int) ((Math.random() * (MAXVALUE - MINVALUE)) + MINVALUE);
		}
		return arr;
	}

	protected int[] generateSortedList(int valuecount) {
		int[] generateList = generateList(valuecount);
		Arrays.sort(generateList);
		return generateList;
	}

	public int[] generateInverseSortedList(int valuecount) {
		int[] generateList = generateList(valuecount);
		Arrays.sort(generateList);
		int[] result = new int[valuecount];
		for (int i = 0; i < generateList.length; i++) {
			result[i] = generateList[generateList.length - i - 1];
		}
		Arrays.toString(result);
		return result;
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
//		 System.out.println("\n--");
//		 System.out.println("solution: " + Arrays.toString(sorted));
//		 System.out.println("sorted  : " + Arrays.toString(sort));
//		 assertTrue(Arrays.equals(sorted, sort));
		 int old = Integer.MIN_VALUE;
		 for (int i = 0; i < sort.length; i++) {
		 int long1 = sort[i];
		 // System.out.println(sorter.getClass().getName());
		 // System.out.println(long1 + " - " + reference.get(i));
		 assertTrue(old + " doesn't seem to be smaller than " + long1,
		 old <= long1);
		 old = long1;
		 }
		return sortstop - sortstart;
	}
}
