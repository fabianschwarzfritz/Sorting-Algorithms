package arrayimpl.sorterimpl;

import arrayimpl.abstractalg.AbstractSorter;

public class HeapSorterImpl extends AbstractSorter {

	public void heapSort(int[] a) {
		int count = a.length;
		heapify(a, count);
		int end = count - 1;
		while (end > 0) {
			int tmp = a[end];
			a[end] = a[0];
			a[0] = tmp;
			siftDown(a, 0, end - 1);
			end--;
		}
	}

	public void heapify(int[] a, int count) {
		int start = (count - 2) / 2; // binary heap
		while (start >= 0) {
			siftDown(a, start, count - 1);
			start--;
		}
	}

	public void siftDown(int[] a, int start, int end) {
		int root = start;
		while ((root * 2 + 1) <= end) {
			int child = root * 2 + 1;
			if (child + 1 <= end && a[child] < a[child + 1])
				child = child + 1;
			if (a[root] < a[child]) {
				int tmp = a[root];
				a[root] = a[child];
				a[child] = tmp;
				root = child;
			} else
				return;
		}
	}

	@Override
	public int[] sort() {
		heapSort(arr);
		return arr;
	}
}
