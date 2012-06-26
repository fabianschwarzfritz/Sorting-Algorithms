package arrayimpl.sorterimpl;

import arrayimpl.abstractalg.AbstractSorter;
import arrayimpl.abstractalg.PivotCalculator;

public class QuickSorterThreeWayImpl extends AbstractSorter {

	private PivotCalculator pivot;

	public QuickSorterThreeWayImpl(PivotCalculator pivot) {
		super();
		this.pivot = pivot;
	}

	@Override
	public int[] sort() {
		quicksort(arr, 0, arr.length - 1);
		return arr;
	}

	private void quicksort(int[] arr, int links, int rechts) {
		if (rechts >= 0) {
			int l = links - 1;
			int r = rechts;
			int p = links - 1;
			int q = rechts;
			int v = pivot.getPivot(arr, l, r);
			if (rechts <= links) {
				return;
			}
			while (arr[++l] < v) {

				while (v < arr[--r])
					if (r == links)
						break;
				if (l >= r)
					break;
				tauschen(arr, l, r);
				if (arr[l] == v) {
					p++;
					tauschen(arr, p, l);
				}
				if (v == arr[r]) {
					q--;
					tauschen(arr, r, q);
				}
			}
			tauschen(arr, l, rechts);
			r = l - 1;
			l = l + 1;
			for (int k = links; k < p; k++, r--) {
				tauschen(arr, k, r);
			}
			for (int k = rechts - 1; k > q; k--, l++) {
				tauschen(arr, l, k);
			}
			quicksort(arr, links, r);
			quicksort(arr, l, rechts);
		}
	}

}
