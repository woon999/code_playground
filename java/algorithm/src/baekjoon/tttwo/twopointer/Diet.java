package baekjoon.tttwo.twopointer;

// #1484 twopointer 다이어트 
import java.io.*;

public class Diet {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		long s= 1;
		long e =2;
		
		boolean flag = false;
		while(e<=100_000) {
			long dif = e*e - s*s;
			if(dif == n) {
				System.out.println(e);
				flag = true;
			}
			
			if(dif >n){
				s++;
			}else {
				e++;
			}
		}
		
		if(!flag) {
			System.out.println(-1);
		}
		
		
		
	}
}
