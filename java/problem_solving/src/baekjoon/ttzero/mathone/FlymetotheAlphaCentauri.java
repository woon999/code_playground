package baekjoon.ttzero.mathone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlymetotheAlphaCentauri {

//	1 k1 =1 							
//  2 k1 =1 k2 =1      					
//	3 k1 =1 k2 =1 k3 =1					
//	4 k1 =1 k2 =2 k3 =1   				
//	5 k1 =1 k2 =2 k3 =1 k4 =1			
//  6 k1 =1 k2 =2 k3= 2 k4 =1			
//	7 k1 =1 k2 =2 k3= 2 k4 =1 k5 =1		
//	8 k1 =1 k2 =2 k3 =2 k4 =2 k5 =1		
//	9 k1 =1 k2 =2 k3 =3 k4 =2 k5 =1     
//	10 k1 =1 k2 =2 k3 =3 k4 =2 k5 =1 k6 =1 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			
			int move = 0;
			int xcount = 0;
			int ycount = 0;
	
			while(true) {
				move++;
				
				x += move;
				xcount++;
				if(x>=y) {
					break;
				}
				
				y -= move;
				ycount++;
				if(y<=x) {
					break;
				}
				
			}
			System.out.println(xcount+ycount);
	
		}
	}
}
