package baekjoon.dp;

// #2602 dp 돌다리 건너기 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoneBridge {

	static String[] str;
	static String[] dev;
	static String[] ang;
	static int[][][] dp;
	static int len;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine().split("");
		
		dev = br.readLine().split("");
		ang = br.readLine().split("");
		len = dev.length;
		
		dp = new int[str.length+1][len+1][2];
		for(int i=0; i<2; i++) {
			for(int j=0; j<len; j++) {
				for(int k=0; k<str.length; k++) {
					dp[k][j][i] = -1;
				}
			}
		}
		
		
	
		int dev_start = dfs(0,0,0);
		int ang_start = dfs(0,0,1);
		
		System.out.println(dev_start+ ang_start);
		System.out.println(sb.toString());
		
		
	}
	
	
	// dp 메모이제이션을 하지 않으면 최악의 경우 시간초과 발생 
	// dp[현재 건너야 할 돌다리][지금 탐색중인 돌다리][돌다리 종류] 
	
	// pos : 현재 건너는 문자열 위치 
	// idx : 천사/악마 문자열 위치 , turn: 악마/ 천시 차례 번갈아 
	static int dfs(int pos, int idx, int turn){
		
		if(pos == str.length) {
			System.out.println("coin!");
			sb.append("\n");
			return 1;
		}
		
		
		if(dp[pos][idx][turn] != -1) return dp[pos][idx][turn];
		
		int count =0;
		
		// 악마 
		if(turn==0) {
			for(int i=idx; i<len; i++) {
				if(str[pos].equals(dev[i])) {
					sb.append("idx :"+i+" =="+dev[i]+" ");
					count += dfs(pos+1, i+1, 1);
				}
			}
		}
		// 천사 
		else {
			for(int i=idx; i<len; i++) {
				if(str[pos].equals(ang[i])) {
					sb.append("idx :"+i+" =="+ang[i]+" ");
					count += dfs(pos+1, i+1, 0);
				}
			}
		}
		
		return dp[pos][idx][turn] = count;
		
	}
}
