package baekjoon.greedy;

// #10162
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Microwave {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int a=0,b=0,c=0;
		
		
		while(n>=10) {
			if(n>=300) {
				n -=300;
				a++;
			}
			
			else if(n>=60) {
				n -=60;
				b++;
			}
			else if(n>=10) {
				n -=10;
				c++;
			}
		}
		
		
		System.out.println(n==0? a+" "+b+" "+c: -1);
	}

}
