package fabian.sorter;

import arrayimpl.abstractalg.QuickSortTest;
import arrayimpl.abstractalg.SorterTableTester;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.println("** --  Fabian Schwarz-Fritz  --  **");
		System.out.println("** quicksort comparison    **");
		QuickSortTest test = new QuickSortTest();
		test.testMain();
		System.out.println("** comparison for xsl    **");
		SorterTableTester tester = new SorterTableTester();
		tester.testSorter();
	}
}
