package baekjoon.mathfour;

// #2914
import java.io.*;
import java.util.StringTokenizer;

public class Copyright {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int a = Integer.parseInt(st.nextToken());
		int i = Integer.parseInt(st.nextToken());
		
		
		System.out.println(a*(i-1)+1);
	}
}
