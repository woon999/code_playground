package kakao.blind2020;

//blind #7 블록 이동하기 - bfs, 시뮬레이션 
import java.util.LinkedList;
import java.util.Queue;

public class BlockMove {

	static int size;
	static int[][] map;
	static int[] dx= {-1,1,0,0};
	static int[] dy= {0,0,-1,1};
	public static void main(String[] args) {
		int[][] board = {
				{0,0,0,1,1},
				{0,0,0,1,0},
				{0,1,0,1,1},
				{1,1,0,0,1},
				{0,0,0,0,0},
		};
		
		System.out.println(solution(board));
		
	}
	
	public static int solution(int[][] board) {
        int answer = 0;
        size = board.length;	
        map = board;
        for(int i=0; i<size; i++) {
        	for(int j=0; j<size; j++) {
        		System.out.print(map[i][j] +" ");
        	}
        	System.out.println();
        }
        
        answer =  bfs();
        return answer;
    }
	
	static int bfs() {
		Queue<Robot> q = new LinkedList<>();
		boolean[][][] check = new boolean[size][size][2];
		q.add(new Robot(0,0,1,0,0,0));
		
		while(!q.isEmpty()) {
			Robot robot = q.poll();
			int pbx = robot.bx, pby =robot.by;
			int pfx = robot.fx, pfy =robot.fy;
			int pStatus = robot.status;
			int mv = robot.move;
			
			if(check[pby][pbx][pStatus] && check[pfy][pfx][pStatus]) continue;
			check[pby][pbx][pStatus] = true;
			check[pfy][pfx][pStatus] = true;
			
			System.out.println("(" + pby +"," + pbx+") - ("+pfy+","+pfx+") ... status(1가로/ 2세로) :" + pStatus +" , move :" + mv );
			if((pbx == size-1 && pby==size-1)
					|| (pfx == size-1 && pfy==size-1)) {
				System.out.println("도착 ");
				return mv;
			}
			// 1. 상하좌우 이동 
			for(int d=0; d<4; d++) {
				int nbx = pbx+dx[d];
				int nby = pby+dy[d];
				int nfx = pfx+dx[d];
				int nfy = pfy+dy[d];
				
				if(graphCondition(nbx, nby, nfx, nfy)) {
						q.add(new Robot(nbx, nby, nfx, nfy, pStatus, mv+1));
				}
			}
			
			
			// 2. 90도 회전
			// 로봇 가로 상태
			if(pStatus==0) {
				// 위로 회전 
				if(graphCondition(pbx, pby-1, pfx, pfy-1)) {
						// 앞축고정 회전(위로 90)
						q.add(new Robot(pfx, pfy, pfx, pfy-1, 1, mv+1));
						// 앞축고정 회전(위로 90)
						q.add(new Robot(pbx, pby, pbx, pby-1, 1, mv+1));
				}
				
				// 아래로 회전 
				if(graphCondition(pbx, pfy+1, pfx, pfy+1)) {
						// 앞축고정 회전(아래로 90)
						q.add(new Robot(pfx, pfy, pfx, pfy+1, 1, mv+1));
						// 뒤축고정 회전(아래로 90)
						q.add(new Robot(pbx, pby, pbx, pby+1, 1, mv+1));
				}
				
			}
			// 로봇 세로 상태
			else if(pStatus==1) {
				// 오른쪽 회전 
				if(graphCondition(pbx+1, pby, pfx+1, pfy)) {
					// 앞축고정 회전 (오른쪽 90)
						q.add(new Robot(pfx, pfy, pfx+1, pfy, 0, mv+1));
						// 뒤축고정 회전 (오른쪽 90)
						q.add(new Robot(pbx, pby, pbx+1, pby, 0, mv+1));
				}
				
				// 왼쪽 회전 
				if(graphCondition(pbx-1, pby, pfx-1, pfy)) {
						// 앞축고정 회전 (왼쪽 90)
						q.add(new Robot(pfx, pfy, pfx-1, pfy, 0, mv+1));
						// 뒤축고정 회전 (왼쪽  90)
						q.add(new Robot(pbx, pby, pbx-1, pby, 0, mv+1));
				}
				
			}
		}
		return -1;
	}
	
	
	static boolean graphCondition(int x1, int y1, int x2, int y2) {
		if(x1 <0 || x2 <0 || x1>size-1 || x2>size-1
				|| y1<0 || y2<0 || y1>size-1 || y2>size-1
				|| map[y1][x1] ==1 || map[y2][x2] ==1) return false;
		
		return true;
	}
	
	static class Robot{
		int bx;
		int by;
		int fx;
		int fy;
		int status; // 0 가로, 1 세로 
		int move;
		
		public Robot(int bx, int by, int fx, int fy, int status, int move) {
			this.bx = bx;
			this.by = by;
			this.fx = fx;
			this.fy = fy;
			this.status = status;
			this.move = move;
		}
	}
}
