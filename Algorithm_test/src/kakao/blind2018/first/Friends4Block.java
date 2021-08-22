package kakao.blind2018.first;

//blind #4 프렌즈4블록 - 그래프, 시뮬레이션 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Friends4Block {
	
	static int gn, gm;
	static int[][] map;
	static int[] dx = {0, 1, 1, 0};
	static int[] dy = {0, 0, 1, 1};
	public static void main(String[] args) {
		
		int m = 4;
		int n = 5;
		String[] board = {"CCBDE", "AAADE", "AAABF", "CCBBF"};
//		String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
		
		System.out.println(solution(m, n, board));
	}
	
	public static int solution(int m, int n, String[] board) {
        int answer = 0;
        gn = n;
        gm = m;
        // A~Z : 0~25
        map = new int[gm][gn];
        for(int i=0; i<gm; i++) {
        	for(int j=0; j<gn; j++) {
        		map[i][j] = board[i].charAt(j)-'A';
        	}
        }
        
        while(true) {
        	List<int[]> removeList = new ArrayList<>();
	        for(int i=0; i<gm; i++) {
	        	for(int j=0; j<gn-1; j++) {
	        		int blockSize = checkBlock(j, i, map[i][j]);
	        		if(blockSize==4) {
	        			removeList.add(new int[] {j,i});
	        			
	        		}
	        	}
	        }
	        if(removeList.size()==0) break;
	        for(int[] node : removeList) {
	        	int rx = node[0];
	        	int ry = node[1];
	        	answer += mapIndexing(rx, ry);
	        	
	        }
	        mapUpdate();
        }
        
        
        return answer;
    }
	static int mapIndexing(int x, int y) {
		int cnt = 0;
		for(int i=0; i<4; i++) {
			int nx = x +dx[i];
			int ny = y +dy[i];
			
			if(map[ny][nx] != -1) {
				map[ny][nx] = -1;
				cnt++;
			}
		}
		return cnt;
		
	}
	
	static void mapUpdate() {
		Queue<Integer> q = new LinkedList<>();
		for(int i=0; i<gn; i++) {
        	for(int j=gm-1; j>=0; j--) {
        		if(map[j][i] !=-1) q.add(map[j][i]);
        		map[j][i] = -1;
        	}
        	
        	int h = gm-1;
        	while(!q.isEmpty()) {
        		map[h][i] = q.poll();
        		h--;
        	}
		}
		
	}
	
	static int checkBlock(int x, int y, int type) {
		
		int cnt =0;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx <0 || nx > gn-1 || ny<0 || ny>gm-1) continue;
			if(map[ny][nx] == -1) continue;
			if(map[ny][nx] == type) {
				cnt++;
			}
		}
		return cnt;
		
	}
}
