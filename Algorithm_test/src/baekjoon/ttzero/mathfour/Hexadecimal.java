package baekjoon.ttzero.mathfour;

// #1550 
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Hexadecimal {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		
		System.out.println(Integer.parseInt(a, 16));
	}
}
