package arrayimpl.abstractalg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import arrayimpl.sorterimpl.AusgeglichenesMischenImpl;
import arrayimpl.sorterimpl.BubbleSorterImpl;
import arrayimpl.sorterimpl.HeapSorterImpl;
import arrayimpl.sorterimpl.PivotRightImpl;
import arrayimpl.sorterimpl.ShellSorterImpl;
import arrayimpl.sorterimpl.SortierenDurchAuswaehlenImpl;
import arrayimpl.sorterimpl.SortierenDurchEinfuegenImpl;
import arrayimpl.sorterimpl.quickimpl.QuickSortImpl;

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
		sortermap.put("Shellsorter    ", new ShellSorterImpl());
		System.out.println("Shuffled");
		for (Integer i : valueCounts) {
			executeSorter(sortermap, generateList(i));
		}
		System.out.println("sorted");
		for (Integer i : valueCounts) {
			executeSorter(sortermap, generateSortedList(i));
		}
		System.out.println("inverse");
		for (Integer i : valueCounts) {
			executeSorter(sortermap, generateInverseSortedList(i));
		}
	}
}
