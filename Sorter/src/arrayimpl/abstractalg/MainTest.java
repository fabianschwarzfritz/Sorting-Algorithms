package arrayimpl.abstractalg;

import java.util.Arrays;

import arrayimpl.sorterimpl.AusgeglichenesMischenImpl;

public class MainTest {
	public static void main(String[] args) {
		int[] arr = new int[] { 5, 3, 8, 0, 9, 1, 10 };
		Sorter sorter = new AusgeglichenesMischenImpl();
		sorter.setArr(arr);
		int[] sort = sorter.sort();
		System.out.println(Arrays.toString(sort));
	}
}
