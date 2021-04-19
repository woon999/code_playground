package baekjoon.ttzero.sort;

// #1764
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class NoOne {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			list.add(s);
		}
		
		Collections.sort(list);
		String[] arr = new String[list.size()];
		arr = list.toArray(arr);
		
		ArrayList<String> res = new ArrayList<String>();
		
		for(int i=0; i<m; i++) {
			String s = br.readLine();
			int idx = Arrays.binarySearch(arr, s);
			if(idx >=0) {
				res.add(s);
			}
		}
		
		Collections.sort(res);
		
		
		System.out.println(res.size());
		for(String s : res) {
			System.out.println(s);
		}
	}

}
