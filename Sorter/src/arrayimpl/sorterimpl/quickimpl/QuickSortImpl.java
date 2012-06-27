package arrayimpl.sorterimpl.quickimpl;

import arrayimpl.abstractalg.AbstractSorter;
import arrayimpl.abstractalg.PivotCalculator;

public class QuickSortImpl extends AbstractSorter {

	private PivotCalculator pivot;

	public QuickSortImpl(PivotCalculator pivot) {
		super();
		this.pivot = pivot;
	}

	@Override
	public int[] sort() {
		int l = 0;
		int r = arr.length - 1;
		quickSortSequential(arr, l, r);
		return arr;
	}

	private void quickSortSequential(int[] arr, int left, int right) {
		if (right > left) {
			int pivot = divide(arr, left, right);
			quickSortSequential(arr, left, pivot - 1);
			quickSortSequential(arr, pivot + 1, right);
		}
	}

	private int divide(int[] arr, int links, int rechts) {
		int pivotelement = pivot.getPivot(arr, links, rechts);
		int l = links;
		int r = rechts;
		do {
			// linken rand bis kleiner pivot und l kleiner gleich als der von
			// diesem rekursionablauf zu bearabeitende, maximale rechte rand
			while (arr[l] <= pivotelement & l < rechts) {
				l++;
			}
			// rechter rand bis kleiner pivot und größer gleich als der von
			// diesem rekursionablauf zu bearabeitende, maximale linke rand
			while (pivotelement <= arr[r] & r > links) {
				r--;
			}
			// Hier gibt es ein element links und eine element recht vom pivot
			// element, welche beide auf der falschen seite liegen. Die indizes
			// dieser element liegen bei l und r
			if (l < r) {
				// Dreieckstausch
				swap(arr, l, r);
			}
		} while (l < r);
		// Übriges element tauschen
		if (pivotelement < arr[l]) {
			swap(arr, l, rechts);
		}
		return l;
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
