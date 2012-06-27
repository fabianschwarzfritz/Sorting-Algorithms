package arrayimpl.abstractalg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import arrayimpl.sorterimpl.BubbleSorterImpl;
import arrayimpl.sorterimpl.HeapSorterImpl;
import arrayimpl.sorterimpl.PivotRightImpl;
import arrayimpl.sorterimpl.QuickSortImpl;
import arrayimpl.sorterimpl.SortierenDurchAuswaehlenImpl;
import arrayimpl.sorterimpl.SortierenDurchEinfuegenImpl;

public class SorterTableTester extends SorterTester {
	@Override
	protected void setUp() throws Exception {
		valueCounts = new ArrayList<Integer>();
		valueCounts.add(new Integer(1000));
		valueCounts.add(new Integer(10000));
		valueCounts.add(new Integer(100000));
	}

	@Test
	public void testSorter() {
		Map<String, Sorter> sortermap = new HashMap<String, Sorter>();
		sortermap.put("heapsorter     ", new HeapSorterImpl());
		sortermap.put("bubblesorter   ", new BubbleSorterImpl());
		sortermap.put("sortieren d einf", new SortierenDurchEinfuegenImpl());
		sortermap.put("sortieren d aus", new SortierenDurchAuswaehlenImpl());
		sortermap.put("Quicksort      ",
				new QuickSortImpl(new PivotRightImpl()));
		for (Integer i : valueCounts) {
			executeSorter(sortermap, generateList(i));
		}
	}
}
