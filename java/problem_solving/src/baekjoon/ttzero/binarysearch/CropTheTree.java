package baekjoon.ttzero.binarysearch;

// #2805
import java.io.*;
import java.util.*;

public class CropTheTree {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken());

		long[] tree = new long[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(tree);

		long max = tree[n - 1];
		long min = 1;
		long mid = 0;

		while (min <= max) {
			mid = (max + min) / 2;

			long cnt = 0;
			for (int i = 0; i < n; i++) {
				if (tree[i] > mid) {
					cnt += tree[i] - mid;
				}
			}
			System.out.println("min :" + min +", max :" +max);
			System.out.println("mid :" +mid);
			System.out.println("cnt :"+ cnt);
			System.out.println("mmmmmmmmmmmmmmmmmmm");
			
			if(cnt >= m) {
				min = mid+1;
			}else if(cnt<m) {
				max = mid-1;
			}
		}
		
		System.out.println(max);
	}
}
