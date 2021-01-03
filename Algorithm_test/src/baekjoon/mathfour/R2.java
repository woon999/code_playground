package baekjoon.mathfour;

// #3046
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class R2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r1 = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		
		System.out.println(2*s-r1);
		
	}
}
