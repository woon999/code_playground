package baekjoon.ttone.dataStructure;

// #1406 dataStructrue 에디터 - stack 
import java.io.*;
import java.util.Stack;

public class Editor {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = br.readLine();
		Stack<Character> s = new Stack<>();
		for(int i=0; i<line.length(); i++) {
			s.push(line.charAt(i));
		}
		int m = Integer.parseInt(br.readLine());
		
		Stack<Character> dS= new Stack<>();
		for(int i=0; i<m; i++) {
			String op = br.readLine();
			char o = op.charAt(0);
			
			if(o == 'L') {
				if(!s.isEmpty()) {
					dS.push(s.pop());
				}
			}else if(o == 'D') {
				if(!dS.isEmpty()) {
					s.push(dS.pop());
				}
			}else if(o == 'B') {
				if(!s.isEmpty()) {
					s.pop();
				}
			}else {
				s.push(op.charAt(2));
			}
		}
		
		while(!dS.isEmpty()) {
			s.push(dS.pop());
		}
		
		for(int i=0; i<s.size(); i++) {
			bw.write(s.get(i));
		}
		
		bw.flush();
		bw.close();
	}
}
