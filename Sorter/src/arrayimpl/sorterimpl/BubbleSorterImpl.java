package arrayimpl.sorterimpl;

import arrayimpl.abstractalg.AbstractSorter;

public class BubbleSorterImpl extends AbstractSorter {

	@Override
	public int[] sort() {
		// Linker rand in dem schon sortier ist
		for (int i = 1; i < arr.length; i++) {
			// j geht jeweils von links nach rechts durch das array und tauscht
			for (int j = arr.length - 1; j >= i; j--) {
				if (arr[j - 1] > arr[j]) {
					// tauschen
					tauschen(arr, j - 1, j);
				}
			}
		}
		return arr;
	}
}
