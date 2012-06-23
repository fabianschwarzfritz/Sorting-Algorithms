package arrayimpl.sorterimpl;

import arrayimpl.abstractalg.AbstractSorter;

public class SortierenDurchEinfuegenImpl extends AbstractSorter {

	@Override
	public int[] sort() {
		int j;
		int x;
		for (int i = 1; i < arr.length; i++) {
			// Element nehmen
			x = arr[i];
			j = i;
			// Nach links "ziehen" (also "graben")
			while (j > 0 && x < arr[j - 1]) {
				arr[j] = arr[j - 1];
				j--;
			}
			// und setzen
			arr[j] = x;
		}
		return arr;
	}
}
