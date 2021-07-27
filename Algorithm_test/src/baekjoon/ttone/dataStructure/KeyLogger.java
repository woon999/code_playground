package baekjoon.ttone.dataStructure;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// #5397 dataStructrue 키로거 - 중간 저장 (시간초과) 
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class KeyLogger {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			StringBuilder sb = new StringBuilder();
			int cursor = 0;
			for(int j=0; j<line.length(); j++) {
				char pos = line.charAt(j);
				if(pos == '<') {
					cursor--;
					if(cursor <0) cursor =0;
				}else if(pos == '>') {
					cursor++;
					if(cursor>sb.length()) cursor=sb.length();
				}else if(pos == '-') {
					if(cursor>0) {
						sb.deleteCharAt(cursor-1);
						cursor--;
					}
				}else {
					sb.insert(cursor, pos);
					cursor++;
				}
			}
			bw.write(sb.toString()+"\n");
		}
		
		bw.flush();
		bw.close();
		
	}
}
