package baekjoon.sort;

// #5052

import java.io.*;
import java.util.*;

public class PhoneNumberList {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			int t = Integer.parseInt(br.readLine());
			String[] s = new String[t];
			int check = 0;
			for (int j = 0; j < t; j++) {
				s[j] = br.readLine();
			}
			
			Arrays.sort(s, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareTo(o2);
				}
				
				
			});
			
			   for(int j=1;j<t;j++) {
	                if(s[j].startsWith(s[j-1])) {
	                    check=1;
	                    break;
	                }
	            }
	            System.out.println(check==0?"YES":"NO");
		}
	}
}
