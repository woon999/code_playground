package baekjoon.ttone.dataStructure;

// #9935 dataStructure (9935. 문자열 폭발) - stack 
import java.io.*;
import java.util.Stack;

public class StringBoom {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		String regex = br.readLine();
		int regexSize = regex.length();
		
		Stack<Character> st = new Stack<>();
		
		for(int i=0; i<line.length(); i++) {
			st.push(line.charAt(i));
			
			// 폭발 문자열과 길이가 같아지면 탐색 시작 
			if(st.size()>= regexSize) {
				boolean flag = true;
				
				// st.size-regexSize부터 ~ st.size까지 탐색하여 regex와 일치하면 제거 
				for(int j=0; j<regexSize; j++) {
					if(st.get(st.size()-regexSize+j) != regex.charAt(j)) {
						flag = false;
						break;
					}
				}
				if(flag) {
					for(int j=0; j<regexSize; j++) {
						st.pop();
					}
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(Character c : st) {
			sb.append(c);
		}
		
		System.out.println(sb.length()==0? "FRULA" : sb.toString());
		
		
		
	}
}
