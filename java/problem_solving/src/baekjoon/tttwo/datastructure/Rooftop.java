package baekjoon.tttwo.datastructure;

// #6198 datastructure 옥상 정원 가꾸기 - stack 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Rooftop {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		long result = 0;
		Stack<Integer> s = new Stack<>();
		for(int i=0; i<n; i++) {
			while(!s.isEmpty() && s.peek() <= arr[i]) {
				s.pop();
			}
			
			s.push(arr[i]);
			result += s.size()-1;
		}
		
		System.out.println(result);
	}
}
