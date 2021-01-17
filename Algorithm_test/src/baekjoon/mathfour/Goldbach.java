package baekjoon.mathfour;


// #6588
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Goldbach {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		boolean[] isNotPrime = new boolean[1000001];
		
		for(int i=2; i<1000001; i++) {
			if(!isNotPrime[i]) {
				for(int j=i*2; j<1000001; j+= i) {
					isNotPrime[j] = true;
				}
			}
		}
		
		while(true) {
			int number = Integer.parseInt(br.readLine());
			boolean isExists = false;
			
			if(number == 0) {
				System.out.println(sb.toString());
				return;
			}
			
			for(int i=3; i<number+1; i+=2) {
				if(!isNotPrime[i] && !isNotPrime[number-i]) {
				sb.append(number + " = " + i + " + " + (number-i)+"\n");
				isExists = true;
				break;
				}
			}
			
			if(!isExists) {
				sb.append("Goldbach's conjecture is wrong."+"\n");
			} 
		}
		
	}
}
