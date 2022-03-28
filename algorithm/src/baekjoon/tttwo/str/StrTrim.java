package baekjoon.tttwo.str;

// #2866 str 문자열 잘라내기 

import java.io.*;
import java.util.*;

public class StrTrim {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		char[][] arr = new char[r][c];
		for(int i=0; i<r; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		List<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<c; i++) {
			for(int j=0; j<r; j++) {
				sb.append(arr[j][i]);
			}
			list.add(sb.toString());
			sb.setLength(0);
		}
		
		
		int s =0, e=r;
		int cnt = 0;
		Set<String> set = new HashSet<>();
		while(s <= e) {
			int mid = (s+e)/2;
			for(int i=0; i<list.size(); i++	) {
				String tmp = list.get(i);
				tmp = tmp.substring(mid, tmp.length());
				if(!set.add(tmp)) {
					break;
				}
			}
			
			// 중복이 존재하면 더 긴 문자열에서 존재하는지 탐색 
			if(set.size() < list.size()) {
				e = mid-1;
			}else { // 존재x 하면 문자열 더 줄이기
				s = mid+1;
				cnt = mid;
			}
			set.clear();
		}
		System.out.println(cnt);
	}
}
