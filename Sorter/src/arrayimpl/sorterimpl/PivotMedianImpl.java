package arrayimpl.sorterimpl;

import arrayimpl.abstractalg.PivotCalculator;

public class PivotMedianImpl implements PivotCalculator {

	@Override
	public int getPivot(int[] arr, int l, int r) {
		int m = (l + r) / 2;
		int median = l;
		if ((l <= m && m <= r) || (r <= m && m <= l)) {
			median = m;
		} else if ((l <= r && r <= m) || (m <= r && r <= l)) {
			median = r;
		}
		System.out.println("-----------");
		System.out.println(l + " | " + m + " + " + r);
		System.out.println("-----------");
//		System.out.println("Median: " + m + " Wert: " + arr[m]);
		// Positionstausch
		int pivot = arr[median];
		arr[median] = arr[r];
		arr[r] = pivot;
		return median;
	}
}
