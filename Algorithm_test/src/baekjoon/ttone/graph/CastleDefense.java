package baekjoon.ttone.graph;

// #17135 graph 캐슬 디펜스 - 시뮬레이션
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class CastleDefense {

	static int n,m,d,ans=0;
	static int[][] map, copyMap;
	static Stack<Integer> s;
	static Queue<int[]> q;
	static boolean[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		copyMap = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		s = new Stack<>();
		check = new boolean[m+1];
		comb(0);
		System.out.println(ans);
	}
	
	static void bfs(int[] archor, int[][] testMap) {
		Queue<int[]> ene = new LinkedList<>();
		int cnt=0;
		int turn =0;
		while(true){
			if(turn > n-1) break;
			for(int t=0; t<3; t++) {
				int px = archor[t]; // start
				int py = n-turn; // n-turn : turn마다 궁수 위치 한 칸씩 위로 이동 
				
				int min = Integer.MAX_VALUE;
				int ex = -1;
				int ey = -1;
				for(int i=py-1; i>=0; i--) {
					for(int j=0; j<m; j++) {
						if(i<0 || i>n-1 || j<0 ||j >m-1) continue;
						if(testMap[i][j] == 1) {
							int dis = Math.abs(py-i) + Math.abs(px-j);
							
							if(dis <= d) {
								if(dis < min) { // 최단거리 적 제거 
									min = dis;
									ex = j;
									ey = i;
								}else if(dis == min) { // 가장 왼쪽에 있는 적부터 제거
									if(ex > j) {
										ex = j;
										ey = i;
									}
								}
							}
						}
					}
				}
				// 제거할 적 저장 
				if(ex != -1 && ey != -1) {
					ene.add(new int[] {ex,ey});
				}
				
			}
			// 맵 업데이트 (적 제거)
			while(!ene.isEmpty()) {
				int[] r = ene.poll();
				int rx = r[0];
				int ry = r[1];
				
				if(testMap[ry][rx] == 1) {
					testMap[ry][rx] = 0;
					cnt++;
				}
			}
			turn++;
		}
		ans = Math.max(ans, cnt);
	}
	
	// (0~n-1) 3개 조합 
	static void comb(int idx) {
		if(s.size() ==3) {		
			copyMap= copy(copyMap);
			int[] archor = new int[3];
			for(int i=0; i<3; i++) {
				archor[i]= s.get(i);
				System.out.print(archor[i]+" ");
			}
			System.out.println();
			bfs(archor, copyMap);
			return;
		}
		for(int i=idx; i<m; i++) {
			if(!check[i]) {
				check[i]=true;
				s.push(i);
				comb(i);
				s.pop();
				check[i]= false;
			}
		}
	}
	
	static int[][] copy(int[][] copyMap) {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}
}
