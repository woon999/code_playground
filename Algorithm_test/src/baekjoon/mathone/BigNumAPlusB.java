package baekjoon.mathone;


// #10757
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BigNumAPlusB {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] num = br.readLine().split(" ");
		
		BigInteger num1 = new BigInteger(num[0]);
		BigInteger num2 = new BigInteger(num[1]);
		
		System.out.println(num1.add(num2));
	}
}
