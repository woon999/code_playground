package baekjoon.tttwo.dp;

// #16500 dp 문자열 판별 
import java.io.*;

public class StringCheck_16500 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int n = Integer.parseInt(br.readLine());
		
		String[] a = new String[n];
		for(int i=0; i<n; i++) {
			a[i] = br.readLine();
		}
		
		int sLen = s.length();
		boolean[] check = new boolean[sLen+1];
		check[0] = true;
		for(int i=0; i<sLen; i++) { // 원본 문자열 
			int len = i+1;
			for(int j=0; j<n; j++) { // 비교 문자열 
				String tmp = a[j];
				int tmpLen = tmp.length();
				if(len >= tmpLen) { // 원본 길이 >= 비교 길이 
					// 비교 길이에 맞춰서 원본 문자열 자르기 
					String trimS = s.substring(len-tmpLen, len);
					//비교 문자와 일치 & 기차 연결 가능한지 여부 확인 check[len-tmpLen] = true
					if(trimS.equals(tmp) && check[len - tmpLen]) {
						check[len] = true; 
						break;  
					}
				}
			}
		}
		System.out.println(check[sLen]? 1 : 0);
	}
}
