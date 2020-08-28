package baekjoon.shortestpath;

// #11657
import java.io.*;
import java.util.*;

public class TimeMachine  {

//	INF = 1000000으로 설정하면 출력초과 
//	도시의 개수 N (1 ≤ N ≤ 500)
//	버스 노선의 개수 M (1 ≤ M ≤ 6,000) 
//	A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)
//  INF값 잘 설정하기 
	static int INF = 987654321;
	static long[] dis;
	static Bus[] bus;
	static int n,m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		bus = new Bus[m];
		dis = new long[n + 1];
        Arrays.fill(dis,INF);

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			bus[i] = new Bus(a, b, c);
		}



		if (bellmanFord()) {
			System.out.println(-1);
		} else {
			for (int i = 2; i <= n; i++) {
				if (dis[i] == INF) {
					System.out.println("-1");
				} else {
					System.out.println(dis[i]);
				}
			}
		}

	}
	
	static boolean bellmanFord() {
		dis[1] = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < m; j++) {
                int x = bus[j].start;
				int z = bus[j].end;
				int xzDis = bus[j].distance;
				if (dis[x] == INF)
					continue;

				if (dis[z] > dis[x] + xzDis) {
					dis[z] = dis[x] + xzDis;

				}
			}
		}
		

		boolean check = false;
		for (int i = 0; i < m; i++) {
			int x = bus[i].start;
			int z = bus[i].end;
			int xzDis = bus[i].distance;
			if (dis[x] != INF && dis[z] > dis[x] + xzDis){
				check =true;
				break;
            }
		}
		

		
		return check;
	}

	

}

 class Bus {
	int start;
	int end;
	int distance;

	public Bus(int start, int end, int distance) {
		this.start = start;
		this.end = end;
		this.distance = distance;
	}
}