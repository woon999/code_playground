package practice;

import java.util.Scanner;

//근묵자흑(주어진 수열을 모두 같은 수로 만들때 걸리는 최소 횟수)

public class RubOffOnNumber {

	public static void main(String[] args) throws Exception {
		
		Scanner s = new Scanner(System.in);
		String nl;
		int n = s.nextInt();		
		int k = s.nextInt();
		int[] arr = new int[n];
		
	    nl = s.nextLine();
		
		for(int i = 0; i < n; i++ ){
			int arrInput = s.nextInt();
			arr[i] = arrInput;
		}	

	
		int count = 0;
		
		for(int i = 0; i < arr.length - 1; i = i + (k - 1)){
			count++;
		}
		
		System.out.print(count);
		
		
	}
}
