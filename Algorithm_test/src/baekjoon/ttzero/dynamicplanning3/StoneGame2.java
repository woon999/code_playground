package baekjoon.ttzero.dynamicplanning3;

// #9656 
import java.io.*;

public class StoneGame2 {
 
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		System.out.println((n%2 == 1 )?  "CY" : "SK");
	}
}
