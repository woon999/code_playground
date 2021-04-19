package baekjoon.ttzero.maththree;

// #11050
import java.util.Scanner;

public class BinomialCoefficient {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		sc.close();
		System.out.println(factoiral(n)/(factoiral(m)*factoiral(n-m)));
		
	}
	
	static int factoiral(int n) {
		if(n<=1)
			return 1;
		
		return n*factoiral(n-1);
	}
}
