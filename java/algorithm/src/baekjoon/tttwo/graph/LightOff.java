package baekjoon.tttwo.graph;

// #14939 grahp 불 끄기 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LightOff {

	static final int SIZE = 10;
	static final int INF = Integer.MAX_VALUE;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] map = new char[SIZE][SIZE];
		for(int i=0; i<SIZE; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int answer = INF;
		char[][] tmp = new char[SIZE][SIZE];
		
		// 0~9 버튼 누를 모든 경우의 수 
		for(int c=0; c<(1<<10); c++) {
			deepCopy(map, tmp);
			
			int cnt = 0;
			cnt = simulation(tmp, c, cnt);
			
			if(isAllOff(tmp)) {
				answer = Math.min(answer, cnt);
			}
		}
		
		
		System.out.println(answer == INF ? -1 : answer);
		
	}

	private static boolean isAllOff(char[][] tmp) {
		boolean flag = true;
		for(int x=0; x<10; x++) {
			if(tmp[9][x] == 'O') {
				flag = false;
				break;
			}
		}
		return flag;
	}

	private static int simulation(char[][] tmp, int c, int cnt) {
		for(int x=0; x<10; x++) {
			if((c & (1<<x)) != 0) {
				lightSwitch(tmp, x, 0);
				cnt++;
			}
		}
		
		for(int y=1; y<10; y++) {
			for(int x=0; x<10; x++) {
				if(tmp[y-1][x] == 'O') {
					lightSwitch(tmp, x, y);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	static void lightSwitch(char[][] map, int x, int y) {
		map[y][x] = switchOnOff(map[y][x]);
		for(int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx <0 || ny <0 || nx >= SIZE || ny >= SIZE) continue;
			
			map[ny][nx] = switchOnOff(map[ny][nx]);
		}
	}
	
	static char switchOnOff(char status) {
		if(status == '#') return 'O';
		else return '#';
	}
	
	static void deepCopy(char[][] src, char[][] copy) {
		for(int i=0; i<SIZE; i++) {
			for(int j=0; j<SIZE; j++) {
				copy[i][j] = src[i][j];
			}
		}
	}
}
