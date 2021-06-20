package baekjoon.ttone.dataStructure;

// #1918 DataStructure 후위 표기식 - Stack 
import java.io.*;
import java.util.Stack;
import java.util.regex.Pattern;

public class PosfixNotation {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] line = br.readLine().split("");
		Stack<String> op = new Stack<>();

		for(int i=0; i<line.length; i++) {
			if(Pattern.matches("^[A-Z]", line[i])) {
				sb.append(line[i]);
			}else if(line[i].equals("(")) {
				op.push("start");
			}else if(line[i].equals(")")) {
				while(!op.isEmpty()) {
					String s = op.pop();
					if(s.equals("start")) break;
					sb.append(s);
				}
			}else {
				while(!op.isEmpty()) {
					int oldOp = operatorPriority(op.peek()); 
					int newOp =operatorPriority(line[i]);
					if(oldOp < newOp) break;
					if(oldOp >= newOp) {
						sb.append(op.pop());
					}
				}			
				op.push(line[i]);
			}
		}
		
		while(!op.isEmpty()) {
			sb.append(op.pop());
		}
		
		System.out.println(sb.toString());
		
		
	}
	static int operatorPriority(String op) {
		if(Pattern.matches("^[*,/]", op)) {
			return 2;
		}else if(Pattern.matches("^[+,\\-]", op)){
			return 1;
		}else {
			return 0;
		}
	}
}

