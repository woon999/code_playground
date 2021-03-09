package baekjoon.dynamicplanning2;

// # 10942
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//�Ӹ����(palindrome)�̶� �տ������� ������ �ڿ������� ������ ���� �ܾ�

public class Palindrome {

	static boolean[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
	
		int[] palin = new int[n+1];
		dp = new boolean[n+1][n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			palin[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i <= n; i++)
            dp[i][i] = true;
		
		for(int i=1; i<=n-1; i++) {
			if(palin[i] == palin[i+1]) dp[i][i+1] =true;
		}
		IsPalin(palin,n);
		
		int m  = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<m; i++) {
		 st = new StringTokenizer(br.readLine());
		 int first = Integer.parseInt(st.nextToken());
		 int last = Integer.parseInt(st.nextToken());
		 
//		 �׳� sysout���� 1 0 ����ϸ� �ð��ʰ� 
//		 dpŽ��
		 if(dp[first][last]) {
			sb.append("1\n");
		 }
		 else {
			 sb.append("0\n");
		 }
//		 System.out.println(Arrays.deepToString(dp));
		}

		 
		 System.out.println(sb);
	}
	
	static void IsPalin(int[] palin, int n) {
	
		for(int i=2; i<n; i++) {
			for(int j=1; j<=n-i; j++) {
				if(palin[j] == palin[j+i] && dp[j+1][j+i-1]) {
					dp[j][j+i] = true;
				}
			}
		}
		
	}
}
