import java.io.*;

public class StringSimilarity {
	public static void main(String[] args) {
		new StringSimilarity();
	}
	
	public StringSimilarity() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			int t = Integer.parseInt(in.readLine());
			String[] strings = new String[t];
			
			for (int i = 0; i < t; i++) {
				strings[i] = in.readLine();	
			}
			
			for (int i = 0; i < t; i++) {
				System.out.println(solve(strings[i]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected int solve(String str) {
		int length = str.length();
		char[] original = str.toCharArray();
				
		if (length == 1)
			return 1;
				
		int total = length;
		
		for (int i = 1; i < length; i++) {
			int similar = 0;
			
			for (int j = i; j < original.length; j++) {
				if (original[j] == original[j - i])
					similar++;
				else
					break;
			}
			
			total += similar;
		}
		
		return total;
	}
}