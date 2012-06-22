package arrayimpl;

public class QuickSortImpl implements Sorter {

	private int[] arr;

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

//	private int divide(int[] arr, int links, int rechts) {
//		int pivotelement = arr[rechts];
//		int l = links;
//		int r = rechts;
//		do {
//			// linken rand bis kleiner pivot und l kleiner gleich als der von
//			// diesem rekursionablauf zu bearabeitende, maximale rechte rand
//			while (arr[l] <= pivotelement & l < rechts) {
//				l++;
//			}
//			// rechter rand bis kleiner pivot und größer gleich als der von
//			// diesem rekursionablauf zu bearabeitende, maximale linke rand
//			while (pivotelement <= arr[r] & r > links) {
//				r--;
//			}
//			// Hier gibt es ein element links und eine element recht vom pivot
//			// element, welche beide auf der falschen seite liegen. Die indizes
//			// dieser element liegen bei l und r
//			if (l < r) {
//				// Dreieckstausch
//				tauschen(arr, l, r);
//			}
//		} while (l < r);
//		// Übriges element tauschen
//		if (pivotelement < arr[l]) {
//			tauschen(arr, l, rechts);
//		}
//		return l;
//	}
	
	private int divide(int[] arr, int low, int high) {
		int pivotIndex = high;
		int pivotValue = arr[pivotIndex];
		tauschen(arr, low, pivotIndex); // Move pivot to end
		int left = low;
		for (int i = low + 1; i <= high; i++) {
			if (arr[i] < pivotValue) {
				left++;
				tauschen(arr, i, left);
			}
		}
		tauschen(arr, low, left); // Move pivot to its final place
		return left;
	}

	private void tauschen(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	@Override
	public void setArr(int[] arr) {
		this.arr = arr;
	}
}
