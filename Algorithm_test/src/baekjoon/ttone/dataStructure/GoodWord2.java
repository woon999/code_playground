package baekjoon.ttone.dataStructure;

// #3986 dataStructure 좋은 단어 - stack  
import java.io.*;
import java.util.Stack;

public class GoodWord2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int cnt=0;
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			Stack<Character> st = new Stack<>();
			
			for(int j=0; j<line.length(); j++) {
				if(st.size()>0 && st.peek() == line.charAt(j)) {
					st.pop();
				}else {
					st.push(line.charAt(j));
				}
			}
			
			if(st.size()==0) cnt++;
			
		}
		
		System.out.println(cnt);
	}
}
