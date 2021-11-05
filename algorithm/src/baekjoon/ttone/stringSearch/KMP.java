package baekjoon.ttone.stringSearch;

// #16916 StringSearch 부분 문자열 - KMP  
import java.io.*;

public class KMP {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		System.out.println(KMP(s1, s2));
		
	}
	
	static int KMP(String parent, String pattern) {
		int[] table = makeTable(pattern);
		
		int n1 = parent.length();
		int n2 = pattern.length();
		
		for(int num : table) {
			System.out.print(num+" ");
		}
		System.out.println();
		
		int idx = 0;
		for(int i=0; i< n1; i++) {
			while(idx>0 && parent.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
//			System.out.println(i+" : idx " + idx);
			
			if(parent.charAt(i) == pattern.charAt(idx)) {
				if(idx == n2-1) {
					System.out.println(i-idx+1 + "번째에서 찾았습니다.");
					idx =table[idx];
					return 1; // 매칭이 되었으면 1  
				}else {
					idx += 1;
				}
			}
		}
		 
		return 0; // 매칭이 안되었으면 0 
		
	}
	
	static int[] makeTable(String pattern) {
		int n = pattern.length();
		int[] table = new int[n];
		
		int idx=0;
		for(int i=1; i<n; i++) {
	        // 일치하는 문자가 발생했을 때(idx>0), 
			// 연속적으로 더 일치하지 않으면 idx = table[idx-1]로 돌려준다.
			while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				idx += 1;
				table[i] = idx;  
			}
		}
		
		return table;
 	}
}
