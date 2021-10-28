package baekjoon.ttone.sort;

// #18870 sort 좌표 압축 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class PosCompression {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<Integer, Integer> map = new HashMap<>();
		
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			map.putIfAbsent(arr[i], 0);
		}
		
		List<Integer> keySet = new ArrayList<>(map.keySet());
		Collections.sort(keySet);
		
		int rank=0;
		for(int key : keySet) {
			map.put(key, rank++);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			sb.append(map.get(arr[i])+" ");
		}
		System.out.println(sb.toString());
	}
}
