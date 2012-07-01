package arrayimpl.sorterimpl;

import java.util.ArrayList;
import java.util.List;

import arrayimpl.abstractalg.Sorter;

public class BucketSorterImpl implements Sorter {

	private int[] arr;
	private int max;

	@Override
	public void setArr(int[] arr) {
		this.arr = arr;
	}

	@Override
	public int[] sort() {
		// max element, bzw arr length finden
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		if (arr.length > max) {
			max = arr.length;
		}
		return sort(arr, 0);
	}

	private int[] sort(int[] arr, int position) {
		// Eimer erstellen - 10 Eimer, da 0-9
		@SuppressWarnings("unchecked")
		List<Integer>[] eimer = new ArrayList[10];
		for (int i = 0; i < eimer.length; i++) {
			eimer[i] = new ArrayList<Integer>();
		}

		// elementen den eimern zuordnen
		for (int element : arr) {
			int index = indexfunction(element, position);
			eimer[index].add(element);
		}
		int i = 0;
		for (List<Integer> bucket : eimer) {
			for (Integer element : bucket) {
				arr[i++] = element;
			}
		}
		// Hier ist die "position" te zahl sortiert
		double pow = Math.pow(10, position);
		// max ist entweder die arraylänge, oder die größte Zahl im Array. Hier
		// aufpassen, wenn die maximale Zahl größer als die arraylänge ist!
		if (pow < max) {
			sort(arr, ++position);
		}

		return arr;
	}

	private int indexfunction(int element, int position) {
		// get pow for correct dividing
		double pow = Math.pow(10, position);
		// die letzten "position" ziffern abschneiden
		int divide = (int) (element / pow);
		// die neue letzte ziffer holen
		int mod10 = divide % 10;
		return mod10;
	}
}
