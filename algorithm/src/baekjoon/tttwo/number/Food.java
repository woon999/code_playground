package baekjoon.tttwo.number;

// #1188 number 음식평론가 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Food {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		System.out.println(m-gcd(n,m));
	}
	
	static int gcd(int a, int b) {
		return a%b == 0 ? b: gcd(b, a%b);
	}
}
