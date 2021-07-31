package baekjoon.ttone.tree;

// #13209 tree 검역소 - X 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class QuarantineShip {

	static int MAX = 100_001;
	static long mid, INF = MAX*MAX*MAX; 
	static int[] data;
	static long[][] dp;
	static boolean[] checked;
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
				mid = (left+right)/2;
				dp = new long[n+1][2];
				checked = new boolean[n+1];
				System.out.println("----------start----------------"+ mid);
				solve(1,0);
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
	
	static void solve(int pos, int prev){
		long now = data[pos];
		if(data[pos] <= mid) {
			dp[pos][0] = 0; 
			dp[pos][1] = now;
		}else {
			dp[pos][0] = MAX;
			dp[pos][1] = INF;
		}
		
		for(int child : list[pos]) {
			if(child != prev) {
				solve(child, pos);
				 
				System.out.println("pos :" + pos + ", child :"+ child + " : ("+dp[pos][1]+")");
				if(!checked[pos]) {
					checked[pos]= true;
					// 1. 두 트리 합치기 
					if(dp[pos][1]+dp[child][1] <= mid) {
						dp[pos][0] = dp[child][0];
						dp[pos][1] = dp[child][1] + dp[pos][1];
//						System.out.println("#합 " + pos+"+"+child+" : ("+ dp[pos][1]+")  <=" + mid);
					}
					// 2. 자식 노드와 바리게이트 pos --- child
					else if(dp[pos][1] <= mid) {
						dp[pos][0] = dp[child][0] +1;
						dp[pos][1] = dp[pos][1];
						System.out.println("#바리게이트 " + pos+"~"+child+" : ("+ dp[pos][1]+"+"+dp[child][1]+")  > " + mid);
						
					}
					// 3. 해당 w 탐색 불가
					else {
						dp[pos][0] = MAX;
						dp[pos][1] = INF;
					}
				}else {
//					System.out.println(checked[pos]);
					// 해당 pos 노드 재방문 (자식노드 2개 이상)
					if(dp[pos][1] + dp[child][1] <= mid) {
						dp[pos][0] += dp[child][0];
						dp[pos][1] = dp[child][1] + dp[pos][1];
//						System.out.println("#합 " + pos+"+"+child+" : ("+ dp[pos][1]+"+"+dp[child][1]+")  <=" + mid);
					}
					// 자식노드 갈라야하면 가장 높은 자식노드 제거 
					else if(now + dp[child][1] <= mid) {
						// 이전 자식노드가 크다면 
						if((dp[pos][1] - now) > dp[child][1]) {
							dp[pos][0] += dp[child][0] + 1;
							dp[pos][1] = now + dp[child][1];
							System.out.println("#바리게이트 " + pos+"~"+child+" : ("+ now+"+"+dp[child][1]+")  <= " + mid);
						}
						// 현재 자식노드가 크다면 
						else {
							dp[pos][0] += dp[child][0] + 1;
							System.out.println("#바리게이트 " + pos+"~"+child+" : ("+ dp[pos][1]+"----"+dp[child][1]+")  > " + mid);
						}
					}
					// 자식노드와 바리게이트 
					else if(dp[pos][1] <= mid) {
						dp[pos][0] += dp[child][0] +1;
						dp[pos][1] = dp[pos][1];
						System.out.println("#바리게이트 " + pos+"~"+child+" : ("+ dp[pos][1]+"+"+dp[child][1]+")  > " + mid);
						
					}
					else {
						dp[pos][0] = MAX;
						dp[pos][1] = INF;
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


