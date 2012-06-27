package arrayimpl.abstractalg;

import java.util.ArrayList;

import org.junit.Test;

public class QuickSortTest extends SorterTester {

	@Override
	protected void setUp() throws Exception {
		valueCounts = new ArrayList<Integer>();
		valueCounts.add(new Integer(10000000)); /* Zehnmillionen */
	}

	@Test
	public void testMain() {

	}
}
