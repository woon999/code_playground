package baekjoon.ttone.dataStructure;

// #1935 DataStructure 후위 표기식2 - Stack 
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class PosfixNotation2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split("");
		
		Map<Character, Double> numMap = new HashMap<>();
		for(int i=0; i<n; i++) {
			int num =Integer.parseInt(br.readLine());
			numMap.put((char)(65+i), (double)num);
		}
		
		Stack<Double> st = new Stack<>();
		for(int i=0; i<line.length; i++) {
			char c = line[i].charAt(0);
			if(numMap.containsKey(c)) {
				st.push((double)numMap.get(c));
			}else {
				if(st.size()<2)  continue;
				double res = operator(c, st.pop(), st.pop());
				st.push(res);
			}
			
		}
		
		System.out.println(String.format("%.2f", st.pop()));
		
		
	}
	static double operator(char op, double num1, double num2) {
		double res =0;
		if(op=='*') {
			res= num2*num1;
		}else if(op=='/'){
			res= num2/num1;
		}else if(op=='+'){
			res= num2+num1;
		}else {
			res= num2-num1;
		}
		
		return Math.round(res *100)/100.0;
	}
}

