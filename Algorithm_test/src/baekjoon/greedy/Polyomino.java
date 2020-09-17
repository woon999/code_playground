package baekjoon.greedy;

// #1343
import java.io.*;

public class Polyomino {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		
		String result ="";
		
		int count =0;
		for(int i=0; i<s.length();) {
			
			if(s.charAt(i) == '.') {
				i++;
				result += '.';
				continue;
			}
			
			for(int j=i; j<s.length() && s.charAt(j) == 'X' ;j++) {
				count++;
			}
			
			i += count;
			
			
			if(count %2 !=0) {
				System.out.println(-1);
				return;
			}
			
			while(true) {
				if(count >=4) {
					result+="AAAA";
					count -=4;
				}else if(count ==2) {
					result += "BB";
					count -=2;
				}else {
					break;
				}
			}
		}
		
		System.out.println(result);
	}
}
