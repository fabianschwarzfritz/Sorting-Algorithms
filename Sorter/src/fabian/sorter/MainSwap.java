package fabian.sorter;

import java.util.Arrays;

public class MainSwap {
	public static void main(String[] args) {
		int[] generateList = generateList(10000000);
		long currentTimeMillis = System.currentTimeMillis();
		divide(Arrays.copyOf(generateList, generateList.length), 0,
				generateList.length - 1);
		long currentTimeMillis2 = System.currentTimeMillis();
		long currentTimeMillis3 = System.currentTimeMillis();
		teilen(Arrays.copyOf(generateList, generateList.length), 0,
				generateList.length - 1);
		long currentTimeMillis4 = System.currentTimeMillis();
		System.out.println("Meiner: "
				+ (currentTimeMillis2 - currentTimeMillis));
		System.out.println("Der anderet: "
				+ (currentTimeMillis4 - currentTimeMillis3));
	}

	private static int[] generateList(int valuecount) {
		System.out.println("Generating value list with count " + valuecount);
		int[] arr = new int[valuecount];
		for (int i = 0; i < valuecount; i++) {
			arr[i] = (int) ((Math.random() * 100000) + 1);
		}
		return arr;
	}

	private static int divide(int[] arr, int links, int rechts) {
		int pivotelement = arr[rechts];
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

	private static int teilen(int[] arr, int links, int rechts) {
		int pivotelement = arr[rechts];
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

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
