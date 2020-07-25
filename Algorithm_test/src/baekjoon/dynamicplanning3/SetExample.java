package baekjoon.dynamicplanning3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SetExample {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[21];
		StringBuilder sb = new StringBuilder();

		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if (s.contains("add")) {
				arr[Integer.parseInt(st.nextToken())] =1;
			} else if (s.contains("remove")) {
				arr[Integer.parseInt(st.nextToken())] =0;
			} else if (s.contains("check")) {
				if(arr[Integer.parseInt(st.nextToken())]==1) {
					sb.append(1 +"\n");
				}else {
					sb.append(0 +"\n");
				}
			} else if (s.contains("toggle")) {
				int a = Integer.parseInt(st.nextToken());
				if(arr[a]==1) {
					arr[a] =0;
				}else {
					arr[a] =1;
				}
			} else if (s.contains("all")) {
				Arrays.fill(arr,1);
			} else if (s.contains("empty")) {
				Arrays.fill(arr,0);
			}

		}
		
		System.out.println(sb.toString());
	}
}
