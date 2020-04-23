package baekjoon.mathtwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class RightTriangle {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
		
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			list.add(Integer.parseInt(st.nextToken()));
			list.add(Integer.parseInt(st.nextToken()));
			list.add(Integer.parseInt(st.nextToken()));
			
			Collections.sort(list);
			
			double a = Math.pow(list.get(0),2);
			double b = Math.pow(list.get(1),2);
			double c = Math.pow(list.get(2),2);
			
			if(a ==0 && b==0 && c==0) {
				break;
			}
			
			if(a+b ==c) {
				System.out.println("right");
			}else if(a+b !=c) {
				System.out.println("wrong");
			}
			
			
		}
	}
}
