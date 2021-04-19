package baekjoon.ttzero.mathfour;

// #1212
import java.io.*;

public class OctalToBinary {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = br.readLine();
		
		if(s.equals("0")) {
			System.out.println("0");
		}else {
			int[] arr =new int[3* s.length()];
			int idx = arr.length -1;
			
			for(int i=0; i<s.length(); i++) {
				int tmp = s.charAt(i) - '0';
				String a1 = Integer.toBinaryString(tmp);
				
				if(a1.length() == 3) {
					for(int k=0; k<a1.length(); k++) {
						arr[idx--] = a1.charAt(k) - '0';
					}
					
				}else if(a1.length() == 2) {
					arr[idx--] = 0;
					
					for(int k=0; k<a1.length(); k++) {
						arr[idx--] = a1.charAt(k) - '0';
					}
				} else {
					arr[idx--] = 0;
					arr[idx--] = 0;
					arr[idx--] = Integer.parseInt(a1);
				}
			}
			boolean flag = false;
			
			for(int i = arr.length -1; i>=0; i--) {
				if(!flag) {
					if(arr[i] != 0) {
						flag = true;
						sb.append(arr[i]);
					}
				}else {
					sb.append(arr[i]);
				}
			}
			
 		}
		
		System.out.println(sb.toString());
	}
}
