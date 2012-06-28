package arrayimpl.sorterimpl;

import arrayimpl.abstractalg.Sorter;

public class ShellSorterImpl implements Sorter {

	private int[] arr;

	@Override
	public int[] sort() {
		int n = arr.length;
		int i, j, k, h, x;
		int max = (int) (Math.log(n) / Math.log(3.0) - 1);
		int[] schrittweiten = new int[max + 1];
		for (int s = 1; s <= max; s++) {
			// h[k+1]=3*h[k]+1
			schrittweiten[s] = schrittweiten[s - 1] * 3 + 1;
		}
		for (k = 0; k < 6; k++) {
			h = schrittweiten[k]; // Sortiere die "Spalten" mit Insertionsort
			for (i = h; i < n; i++) {
				x = arr[i];
				j = i;
				while (j >= h && arr[j - h] > x) {
					arr[j] = arr[j - h];
					j = j - h;
				}
				arr[j] = x;
			}
		}
		return arr;
	}

	@Override
	public void setArr(int[] arr) {
		this.arr = arr;
	}

}
