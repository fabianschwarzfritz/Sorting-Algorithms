package arrayimpl.abstractalg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import arrayimpl.sorterimpl.BubbleSorterImpl;
import arrayimpl.sorterimpl.HeapSorterImpl;
import arrayimpl.sorterimpl.PivotRightImpl;
import arrayimpl.sorterimpl.quickimpl.QuickSortImpl;
import arrayimpl.sorterimpl.quickimpl.QuickSorterConcurrencyQuickSortImpl;

public class QuickSortTest extends SorterTester {

	public QuickSortTest() throws Exception {
		setUp();
	}

	@Override
	protected void setUp() throws Exception {
		valueCounts = new ArrayList<Integer>();
		valueCounts.add(new Integer(1000000)); /* Zehnmillionen */
		MINVALUE = 1;
		MAXVALUE = 100;
		PROBES = 10;
	}

	@Test
	public void testMain() {
		int elementcountvalue = 20000;
		int poolsize = 20;
		System.out.println("Poolsize: " + poolsize
				+ " |  Sucht Sequentiell ab " + elementcountvalue
				+ " elementen\n");

		Map<String, Sorter> sortermap = new HashMap<String, Sorter>();
		sortermap.put("quicksortsequentiell\t", new QuickSortImpl(
				new PivotRightImpl()));
		sortermap.put("quicksort parallel\t",
				new QuickSorterConcurrencyQuickSortImpl(poolsize,
						elementcountvalue, new PivotRightImpl()));
		for (Integer i : valueCounts) {
			executeSorter(sortermap, generateList(i));
		}
	}
}
