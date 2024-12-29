package baekjoon.ttzero.maththree;

// #2891
//a = bq +r
//6/4
//6(a) = 1(a/m)*4(m) +2(a%m)
//a%m = a - (a/m)*m

// A[2] - A[1] = M * (A[2] / M  -  A[1] / M) 
// A[3] - A[2] = M * (A[3] / M  -  A[2] / M) 
// A[4] - A[3] = M * (A[4] / M  -  A[3] / M) 
// ����� m -> 1~n ���� a[i] - a[i-1] ���� �ִ� �����
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Check {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int result = arr[1] - arr[0];

		for (int i = 2; i < n; i++) {
			result = gcd(result, arr[i] - arr[i - 1]);
		}
	
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=2; i<result+1; i++) {
			if(result%i ==0) {
				list.add(i);
			}
		}
		
		for(int i : list) {
			System.out.print(i + " ");
		}

	}

	static int gcd(int n, int m) {

		if (n % m == 0) {
			return m;
		}
		return gcd(m, n % m);

	}
}
