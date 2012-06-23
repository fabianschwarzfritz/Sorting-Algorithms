package arrayimpl.abstractalg;

public abstract class AbstractSorter implements Sorter {
	protected int[] arr;

	protected void tauschen(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public int[] getArr() {
		return arr;
	}

	public void setArr(int[] arr) {
		this.arr = arr;
	}

}
