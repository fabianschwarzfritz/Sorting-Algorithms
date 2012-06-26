package arrayimpl.sorterimpl;

import arrayimpl.abstractalg.PivotCalculator;

public class PivotRightImpl implements PivotCalculator {

	@Override
	public int getPivot(int[] arr, int l, int r) {
		return arr[r];
	}

}
