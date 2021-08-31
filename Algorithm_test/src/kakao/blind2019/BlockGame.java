package kakao.blind2019;

// blind #7 블록 게임 - 그래프 

import java.util.ArrayList;
import java.util.List;

public class BlockGame {

	static int[][] gboard;
	static boolean[][] visited;
	static int[] ddx = {2, -1, 1, -2 ,1};
	static int[] ddy = {0, 1, 1, 0, 0};
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int n,m;
	public static void main(String[] args) {
//		int[][] board= {
//				{0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,0,0,0,4,0,0,0},
//				{0,0,0,0,0,4,4,0,0,0},
//				{0,0,0,0,3,0,4,0,0,0},
//				{0,0,0,2,3,0,0,0,5,5},
//				{1,2,2,2,3,3,0,0,0,5},
//				{1,1,1,0,0,0,0,0,0,5}};
		
//		int[][] board = {
//				{0,0,0,0,0,0,0,0,0,0},
//				{0,0,0,2,2,0,0,0,0,0},
//				{0,0,0,2,1,0,0,0,0,0},
//				{0,0,0,2,1,0,0,0,0,0},
//				{0,0,0,0,1,1,0,0,0,0},
//				{0,0,0,0,0,0,0,0,0,0}
//		};
		
		int[][] board = {
				{0,2,0,0},
				{1,2,0,4},
				{1,2,2,4},
				{1,1,4,4}
		};
		
		System.out.println(solution(board));
		
	}

	public static int solution(int[][] board) {
		int answer = 0;
		gboard = board;
		n = board.length; // y
		m = board[0].length; // x
		
		while(true) {
			List<int[]> removeList = new ArrayList<>();
			visited = new boolean[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(!visited[i][j] && gboard[i][j] !=0) {
						boolean flag;
						visited[i][j] = true;
						flag = identifyBlock(j, i, gboard[i][j]);
						if(flag) {
							System.out.println(i+","+j);
							removeList.add(new int[] {j,i});
						}
					}
				}
			}
			if(removeList.size() ==0) break;
			
			for(int[] block : removeList) {
				int px = block[0], py =block[1];
				boardUpdate(px, py, gboard[py][px]);
				answer++;
			}
		}
		
		return answer;
	}
	
	static void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(gboard[i][j] + " ");
			}
			System.out.println();
		}
	}
	static void boardUpdate(int x, int y, int type) {
		gboard[y][x] =0;
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(isPossible(nx, ny, type)) {
				boardUpdate(nx, ny, type);
			}
		}
		
	}
	
	static boolean identifyBlock(int x, int y, int type) {
		int px = x, py = y+1;
		if(!isPossible(px,py,type)) return false;
		visited[py][px] = true;
		
		for(int d=0; d<5; d++) {
			int nx = px + ddx[d];
			int ny = py + ddy[d];
			if(d==4) {
				if(isPossible(nx, ny, type) && isPossible(nx-2, ny, type)) {
					if(!isRoof(nx, nx-2, ny)) {
						visited[ny][nx-2] = true;
						visited[ny][nx] = true;
						return true;
					}
				}
			}else {
				if(isPossible(nx, ny, type)) {
					if(d==0) {
						if(!isRoof(nx, nx-1, ny)) {
							visited[ny][nx-1] = true;
							visited[ny][nx] = true;
							return true;
						}
					}else if(d==1) {
						if(!isRoof(nx, -1, ny)) {
							visited[ny][nx+1] = true;
							visited[ny][nx] = true;
							return true;
						}
					}else if(d==2) {
						if(!isRoof(nx, -1, ny)) {
							visited[ny][nx-1] = true;
							visited[ny][nx] = true;
							return true;
						}
					}else if(d==3) {
						if(!isRoof(nx, nx+1, ny)) {
							visited[ny][nx+1] = true;
							visited[ny][nx] = true;
							return true;
						}
					}
				}
			}
		}
		return false;
		
//		// 1
//		int nx = px+2, ny = py;
//		if(isPossible(nx, ny, type)) {
//			if(!isRoof(nx, nx-1, ny)) {
//				visited[ny][nx-1] = true;
//				visited[ny][nx] = true;
//				return true;
//			}
//		}
//		
//		// 2
//		nx = px-1; ny = py+1;
//		if(isPossible(nx, ny, type)) {
//			if(!isRoof(nx, -1, ny)) {
//				visited[ny][nx+1] = true;
//				visited[ny][nx] = true;
//				return true;
//			}
//		}
//		
//		// 3
//		nx = px+1; ny = py+1;
//		if(isPossible(nx, ny, type)) {
//			if(!isRoof(nx, -1, ny)) {
//				visited[ny][nx-1] = true;
//				visited[ny][nx] = true;
//				return true;
//			}
//		}
//		
//		// 4
//		nx = px-2; ny = py;
//		if(isPossible(nx, ny, type)) {
//			if(!isRoof(nx, nx+1, ny)) {
//				visited[ny][nx+1] = true;
//				visited[ny][nx] = true;
//				return true;
//			}
//		}
//		
//		
//		// 5
//		nx = px+1; ny = py;
//		if(isPossible(nx, ny, type) && isPossible(nx-2, ny, type)) {
//			if(!isRoof(nx, nx-2, ny)) {
//				visited[ny][nx-2] = true;
//				visited[ny][nx] = true;
//				return true;
//			}
//		}
//		
//		return false;
		
	}
	static boolean isRoof(int x1, int x2, int y) {
		if(x2 == -1) {
			for(int i=0; i<y; i++) {
				if(gboard[i][x1] !=0) { 
					return true;
				}
			}
		}else {
			for(int i=0; i<y; i++) {
				if(gboard[i][x1] !=0 || gboard[i][x2] !=0) { 
					return true;
				}
			}
		}
		return false;
		
	}
	static boolean isPossible(int x, int y, int type) {
		if(x <0 || x >m-1 || y<0 || y>n-1 ||  gboard[y][x] != type) return false;
		
		return true;
	}
}
