import java.io.*;

public class KDifference {
	public static void main(String[] args) {
		new KDifference();
	}
	
	public KDifference() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			String line = in.readLine();			
			String args[] = line.split(" ");
			
			int n = Integer.parseInt(args[0]);
			int k = Integer.parseInt(args[1]);
			
			line = in.readLine();
			args = line.split(" ");
			
			int[] set = new int[n];
			
			for (int i = 0; i < args.length; i++) {
				set[i] = Integer.parseInt(args[i]);
			}
			
			MergeSort sorter = new MergeSort();
			sorter.sort(set);
			
			long total = 0;
			
			for (int i = 0; i < set.length - 1; i++) {
				boolean foundFirstMatch = false;
			
				for (int j = i + 1; j < set.length; j++) {
					int diff = set[j] - set[i];
					
					if (diff == k) {
						total++;
						foundFirstMatch = true;
					} else {
						if ((!foundFirstMatch && diff > k) || foundFirstMatch) {
							break;
						}
					}
				}
			}
			
			System.out.println(total);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class MergeSort {
		private int[] numbers;
		private int number;
		
		public void sort(int[] numbers) {
			this.numbers = numbers;
			this.number = numbers.length;
			
			mergesort(0, number - 1);
		}
		
		private void mergesort(int low, int high) {
			if (low < high) {
				int middle = (low + high) / 2;
				mergesort(low, middle);
				mergesort(middle + 1, high);
				mergesort(low, middle, high);
			}
		}
		
		private void mergesort(int low, int middle, int high) {
			int[] helper = new int[number];
			
			for (int i = low; i <= high; i++) {
				helper[i] = numbers[i];
			}
			
			int i = low;
			int j = middle + 1;
			int k = low;
			
			while (i <= middle && j <= high) {
				if (helper[i] <= helper[j]) {
					numbers[k] = helper[i];
					i++;
				} else {
					numbers[k] = helper[j];
					j++;
				}
				
				k++;
			}
			
			while (i <= middle) {
				numbers[k] = helper[i];
				k++;
				i++;
			}
			
			helper = null;
		}
	}
}