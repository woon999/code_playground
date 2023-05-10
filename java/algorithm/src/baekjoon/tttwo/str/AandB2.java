package baekjoon.tttwo.str;

// #12919 str a와b 2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AandB2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		
		System.out.println(dfs(s,t)? 1: 0);
		
	}
	
	static boolean dfs(String s, String t) {
		boolean flag = false;
		if(s.length() >= t.length()) {
			if(s.equals(t)) {
				return flag = true;
			}
			return flag = false;
		}
		
		if(t.charAt(t.length()-1) == 'A') {
			flag |= dfs(s, t.substring(0, t.length()-1));	
		}
		if(t.charAt(0) == 'B'){
			flag |= dfs(s, new StringBuilder().append(t).reverse().substring(0, t.length()-1).toString());
		}
		
		return flag;
		
	}
}
