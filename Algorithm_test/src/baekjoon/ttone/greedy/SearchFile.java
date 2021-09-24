package baekjoon.ttone.greedy;

// #1543 그리디 문서 검색 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SearchFile {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String pattern = br.readLine();
		
		int cnt =0;
		while(line.length()>0) {
			if(line.startsWith(pattern)) {
				cnt++;
				line =line.substring(pattern.length());
			}else {
				line = line.substring(1);
			}
		}
		System.out.println(cnt);
	}
}
