package baekjoon.ttone.dp;

// #3344 N-Queen  
import java.io.*;


public class NQueen2 {

 	public static void main(String[] args) throws IOException{
 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 		StringBuilder sb = new StringBuilder();
 		int n = Integer.parseInt(br.readLine());
 		
 		if(n%6 !=2 && n%6 !=3) {
 			for(int i=1; i<=n/2; i++) {
 				sb.append(2*i-1).append("\n");
 			}
 			if(n%2 ==1) {
 				sb.append(n).append("\n");
 			}
 			for(int i=1; i<=n/2; i++) {
 				sb.append(2*i).append("\n");
 			}
 			
 		}else if(n % 6 == 2) {
 			
 			for(int i=1; i<=n/2; i++) {
 				sb.append(2*i).append("\n");
 			}
 			sb.append(3).append("\n");
 			sb.append(1).append("\n");
 			for(int i=n/2+2; i<n-1; i++) {
 				sb.append(2*(i-n/2 +1)+1).append("\n"); // 2x+1 
 			}
 			sb.append(5).append("\n");
 			
 		}else if(n%6 ==3) {
 			for(int i=2; i<=n/2; i++) {
 				sb.append(2*i).append("\n");
 			}
 			sb.append(2).append("\n");
 			
 			for(int i=n/2; i<n-2; i++) {
 				sb.append((i-n/2 +2)*2+1).append("\n");
 			}
 			sb.append(1).append("\n");
 			sb.append(3).append("\n");
 		}
 		
 		
 		System.out.println(sb.toString());
 		
		
	}
}
