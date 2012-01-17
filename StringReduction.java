import java.io.*;
import java.util.*;

public class StringReduction {
	protected Map<String, Character> other = new HashMap<String, Character>();
	
	class Result {
		int value;
		boolean stop;
		
		public Result(int value, boolean stop) {
			this.value = value;
			this.stop = stop;
		}
	}

	public static void main(String[] args) {
		new StringReduction();
	}
	
	public StringReduction() {
		try {
			other.put("ab", 'c');
			other.put("ba", 'c');
			other.put("ac", 'b');
			other.put("ca", 'b');
			other.put("bc", 'a');
			other.put("cb", 'a');
		
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			
			int t = Integer.parseInt(in.readLine());
			String[] strings = new String[t];
			
			for (int i = 0; i < t; i++) {
				strings[i] = in.readLine();	
			}					
			
			for (int i = 0; i < t; i++) {
				System.out.println(solve(strings[i]).value);		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	private Result solve(String str) {
		char[] ary = str.toCharArray();
		
		if (ary.length == 1)
			return new Result(1, true);
		else if (ary.length == 2 && ary[0] == ary[1])
			return new Result(2, true);
			
		int min = ary.length;
		int len = min;
		
		for (int i = 0; i < len - 1; i++) {
			if (ary[i] != ary[i + 1]) {
				String key = String.format("%s%s", ary[i], ary[i + 1]);
				Character otherChar = other.get(key);
				
				String b = new String(ary, 0, i);			
				b = b.concat(otherChar.toString());
				b = b.concat(new String(ary, i + 2, ary.length - (i + 2)));

				Result result = solve(b);
				
				if (result.stop)
					return result;
				
				min = Math.min(result.value, min);
			}
		}

		return new Result(min, false);
	}
}