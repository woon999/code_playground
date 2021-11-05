package baekjoon.ttzero.mathfour;

// #5565
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Receipt {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		
		for(int i=0; i<9; i++) {
			int price = Integer.parseInt(br.readLine());
			
			num -= price;
		}
		
		System.out.println(num);
	}
}
