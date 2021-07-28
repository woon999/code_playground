package baekjoon.ttone.tree;

// #13209 tree 검역소 - 중간 저장 2 
// 현재 코드는 양방향으로 이뤄져 있기 때문에 후위 순회로 dp방문을 하지 않음  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class QuarantineShip {

	static int MAX = 100_001;
	static long INF = Long.MAX_VALUE-100_001; 
	static int[] data;
	static long[][] dp;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			data = new int[n+1];
			long sum = 0;
			for(int i=1; i<n+1; i++) {
				data[i] = Integer.parseInt(st.nextToken());
				sum += data[i];
			}
			
			list = new ArrayList[n+1];
			for(int i=1; i<n+1; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<n-1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list[a].add(b);
				list[b].add(a);
			}
			
			long left = 0;
			long right = sum;
			int max =0;
			for (int i = 1; i <n+1; i++) {
				max = Math.max(max, data[i]);
			}
			while(left+1 < right) {
				long mid = (left+right)/2;
				dp = new long[n+1][2];
				System.out.println("----------start----------------");
				System.out.println(left+"," +right +", mid :" + mid);
				solve(1,0,mid);
				System.out.println(dp[1][0] +" <= " + k);
				System.out.println();
				if(max <= mid && dp[1][0] <= k) {
					right = mid;
				}else {
					left= mid;
				}
				
			}
			sb.append(right+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static void solve(int pos, int prev, long w){
		
		
		boolean flag = true;
		for(int nxt : list[pos]) {
			if(nxt != prev) {
				flag = false;
				solve(nxt, pos, w);
			}
		}
		
		
		if(flag) {
			System.out.println("##" + pos);
			if(data[pos] <= w) {
				dp[pos][0] = 0; 
				dp[pos][1] = data[pos];
			}else {
				dp[pos][0] = MAX;
				dp[pos][1] = INF;
			}
		}else {
			// 자식 노드가 1개 
			if(list[pos].size() == 1) {
				System.out.println("---1---");
				int child = list[pos].get(0);
				// 두개 합치기 
				if(data[pos] + dp[child][1] <= w) {
					dp[pos][0] =  dp[child][0];
					dp[pos][1] = data[pos] + dp[child][1];
					System.out.println("#1 union  "+ pos +"----" + child +" :" + dp[pos][1]);
				}
				
				// 자식노드와 바리게이트 
				else if(data[pos] <= w) {
					dp[pos][0] = dp[child][0] + 1;
					dp[prev][1] = data[prev];
					System.out.println("#2 바리게이트  "+ pos +"----" + child +" :" + dp[pos][1]);
				}
				// w가 커서 x 
				else {
					System.out.println("#3");
					dp[pos][0] = MAX;
					dp[pos][1] = INF;
				}
			}
			// 자식 노드가 2개 이상 
			else {
				for(int c=0; c<list[pos].size(); c++) {
					int child = list[pos].get(c);
					// pos 첫방문
					if(c==0) {
						System.out.println("---2---");
						// 두개 합치기 
						if(data[pos] + dp[child][1] <= w) {
							dp[pos][0] =  dp[child][0];
							dp[pos][1] = data[pos] + dp[child][1];
							System.out.println("#1 union  "+ pos +"----" + child +" :" + dp[pos][1]);
						}
						
						// 자식노드와 바리게이트 
						else if(data[pos] <= w) {
							dp[pos][0] = dp[child][0] + 1;
							dp[pos][1] = data[pos];
							System.out.println("#2 바리게이트 "+ pos +"----" + child +" :" + dp[pos][1]);
						}
						// w가 커서 x 
						else {
							System.out.println("#3");
							dp[pos][0] = MAX;
							dp[pos][1] = INF;
						}
					}
					// pos 재방문 
					else {
						System.out.println("---3---");
						int cchild = list[pos].get(c-1);
						// 합치기 
						if(dp[pos][1] + dp[child][1] <= w)	{
							dp[pos][0] +=  dp[child][0];
							dp[pos][1] +=  dp[child][1];
							System.out.println("#1 union  "+ pos +"----" + child +" :" + dp[pos][1]);
						}
						// 이전 자식노드보다 해당 자식 노드가 더 작을 경우  
						else if((dp[child][1] < dp[cchild][1]) && (data[pos] + dp[child][1] <= w)) {
							dp[pos][0] += dp[child][0] + 1; 
							dp[pos][1] = dp[child][1] - dp[cchild][1];
							System.out.println("#2-1 바리게이트 "+ pos +"----" + child +" :" + dp[pos][1]);
						}
						// 자식 노드와 바리게이트 
						else if(dp[pos][1] <= w) {
							dp[pos][0] = dp[child][0] + 1;
							dp[pos][1] = dp[pos][1];
							System.out.println("#2-2 바리게이트 "+ pos +"----" + child +" :" + dp[pos][1]);
						}
						else {
							System.out.println("#3");
							dp[pos][0] = MAX;
							dp[pos][1] = INF;
						}
					}
				}
			}
		}
	}
}
//1
//12 2
//12 30 1 8 8 6 20 7 5 10 4 1
//10 8
//10 5
//8 7
//8 2
//7 4
//7 1
//5 9
//5 6
//9 12
//6 3
//6 11

//1
//4 0
//6 9 7 5
//4 3
//4 2
//3 1

//1
//4 1
//6 9 7 5
//4 3
//4 2
//3 1


//1
//4 3
//6 9 7 5
//4 3
//4 2
//3 1
