package baekjoon.ttzero.mathone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SnailTree {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

//		a,b,v 
//		4 2 10
//		(v-a)��������
//		n*(a-b) n������ b-a�ݺ� 2 4 6 10 

		
		int day = (v-a)/(a-b)+1;
		if((v-a)%(a-b)!=0)
			day++;
		
		
		
		
		System.out.println(day);
	}
}
