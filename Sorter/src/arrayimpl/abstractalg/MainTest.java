package arrayimpl.abstractalg;

import java.util.Arrays;

public class MainTest {
	public static void main(String[] args) {
		SorterTester t = new SorterTester();
		System.out.println(Arrays.toString(t.generateInverseSortedList(10)));
	}
}
