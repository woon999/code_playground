package baekjoon.dp;

// #2666 dp 벽장문의 이동 
import java.io.*;
import java.util.*;

public class MoveTheCloset {

	static List<Integer> opens;
	static int[] target;
	static int[][][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		String[] line = br.readLine().split(" ");
		opens= new ArrayList<>();
		for(int i=0; i<line.length; i++) {
			opens.add(Integer.parseInt(line[i]));
		}
		
		int test = Integer.parseInt(br.readLine());

		target = new int[test];
		dp = new int[test+1][n+1][n+1];
		for(int i=0; i<test+1; i++) {
			for(int j=0; j<n+1; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		for(int i=0; i<test; i++) {
			target[i] = Integer.parseInt(br.readLine());
		}
		
		System.out.println(solve(0,opens.get(0),opens.get(1)));
		
		
	}
	static int solve(int cnt, int a, int b) {
		
		if(cnt == target.length) return 0;
		
		if(dp[cnt][a][b] != -1) return dp[cnt][a][b];
		
		int tmp1 = Math.abs(a-target[cnt]);
		int tmp2 = Math.abs(b-target[cnt]);
		
		return dp[cnt][a][b] =Math.min(tmp1 + solve(cnt+1,b,target[cnt]),
					tmp2 + solve(cnt+1, a, target[cnt]));
		
	}
	
	// 그리디 x 
//	static int solve(int target) {
//		int min = Integer.MAX_VALUE;
//		int idx = -1;
//		for(int i=0; i<opens.size(); i++) {
//			int num = Math.abs(opens.get(i)-target);
//			if(min > num) {
//				min = num;
//				idx = i;
//			}
//		}
//		opens.remove(idx);
//		opens.add(target);
//		return min;
//	}
}
