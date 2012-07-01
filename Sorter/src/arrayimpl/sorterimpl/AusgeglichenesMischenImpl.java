package arrayimpl.sorterimpl;

import java.util.ArrayList;
import java.util.List;

import arrayimpl.abstractalg.Sorter;

public class AusgeglichenesMischenImpl implements Sorter {

	private int[] arr;

	@Override
	public int[] sort() {
		// TODO implement ausgeglichenes Mischen
		mergesort(arr);
		return arr;
	}

	private void mergesort(int[] arr) {
		// Array in 4 teile teilen
		ArrayList<Integer> listA = new ArrayList<Integer>(arr.length);
		ArrayList<Integer> listB = new ArrayList<Integer>(arr.length / 2);
		ArrayList<Integer> listC = new ArrayList<Integer>(arr.length);
		ArrayList<Integer> listD = new ArrayList<Integer>(arr.length / 2);

		// partition on listc and listd
		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				listC.add(arr[i]);
			} else {
				listD.add(arr[i]);
			}
		}

	}

	private List<ArrayList<Integer>> mergeList(ArrayList<Integer> firstfromlist,
			ArrayList<Integer> secondformlist) {
		ArrayList<Integer> intofirst = new ArrayList<Integer>();
		ArrayList<Integer> intosecond = new ArrayList<Integer>();
		for (int i = 0; i < firstfromlist.size(); i++) {
			if (i % 2 == 0) {
				Integer firstfrom = firstfromlist.get(i);
				Integer secondfrom = firstfromlist.get(i);
				List<Integer> mergeBlock = mergeBlock(firstfrom, secondfrom);
				
			}
		}

	}

	private List<Integer> mergeBlock(Integer firstfrom, Integer secondfrom) {
	}

	@Override
	public void setArr(int[] arr) {
		this.arr = arr;
	}

}
