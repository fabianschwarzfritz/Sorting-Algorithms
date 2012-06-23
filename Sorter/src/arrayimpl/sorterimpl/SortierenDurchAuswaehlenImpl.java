package arrayimpl.sorterimpl;

import arrayimpl.abstractalg.AbstractSorter;

public class SortierenDurchAuswaehlenImpl extends AbstractSorter {

	@Override
	public int[] sort() {
		int k;
		int x;
		for (int i = 0; i < arr.length - 1; i++) {
			k = i;
			// Element j, welches immer das naechste linke repraesentiert...
			for (int j = i + 1; j < arr.length; j++) {
				// ... ist kleiner, als das kleineste im restlichen listenteil
				// von k
				if (arr[j] < arr[k]) {
					k = j;
				}
			}
			// tauschen
			tauschen(arr, k, i);
		}
		return arr;
	}
}
