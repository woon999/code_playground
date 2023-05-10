package baekjoon.tttwo.datastructure;

// #3015 datastructure 오아시스 재결합 - stack 
import java.io.*;
import java.util.Stack;

public class Oasis {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		long result = 0;
		Stack<int[]> s = new Stack<>();
		for(int i=0; i<n; i++) {
			// 최고 기둥 갱신 - 이전 까지 stack에 저장된 기둥들 모두 연결 후 삭제  
			while(!s.isEmpty() && s.peek()[0] < arr[i]) {
				result+= s.pop()[1];
			}
			
			if(s.isEmpty()) {
				// stack이 비어있을 경우 새로운 기둥 추가 
				s.push(new int[] {arr[i],1}); 
			}else {
				// 가장 가까운 기둥 중 높은 기둥과 연결  
				if(s.peek()[0] > arr[i]) {
					s.push(new int[] {arr[i],1});
					result++;
				} else { // 가장 가까운 기둥과 높이가 같다면 연결  + 가장 높은 기둥과 연결  
					result+= s.peek()[1]++;
					if(s.size()>1) result++; // 새로운 기둥과 가장 큰 기둥과 쌍을 이룸
				}
			}
		}
		
		System.out.println(result);
	}
}
