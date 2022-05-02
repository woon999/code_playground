package kakao.bline2022;

//#7 kakao2022blind 사라지는 발판 - 미니맥스 알고리즘, 게임이론 

public class DisappearBoard {

	public static void main(String[] args) {
//		int[][] board = {
//				{1,1,1}, {1,1,1}, {1,1,1}
//		};
//		int[] aloc = {1, 0};
//		int[] bloc = {1, 2};
		
		int[][] board = {
				{1,1,1}, {1,0,1}, {1,1,1}
		};
		int[] aloc = {1, 0};
		int[] bloc = {1, 2};
		
//		int[][] board = {
//				{1,1,1,1}
//		};
//		int[] aloc = {0, 0};
//		int[] bloc = {0, 3};
		
		solution(board, aloc, bloc);
	}
	
	static class GameResult{
		boolean isWin;
		int count;
		public GameResult(boolean isWin, int count) {
			this.isWin = isWin;
			this.count = count;
		}
	}
	
	static int n,m;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int[][] map;
	public static int solution(int[][] board, int[] aloc, int[] bloc) {
        
        n = board.length;
        m = board[0].length;
        map = new int[n][m];
        for(int i=0; i<n; i++) {
        	for(int j=0; j<m; j++) {
        		map[i][j] = board[i][j];
        	}
        }
        
        GameResult res = simulation(aloc[1], aloc[0], bloc[1], bloc[0], true);
        System.out.println("! " + res.isWin +" -- " + res.count);
        return res.count;
    }
	
	// flag true : A turn / false : B turn
	static GameResult simulation(int tx, int ty, int ox, int oy, boolean flag) {
		if(!canMove(tx, ty)) { // 더 이상 움직일 수 없으면 패배
			return new GameResult(false, 0);
		}
		if(tx == ox && ty == oy) { // 움직일 수 있는 데 같은 위치라면 승리 
			return new GameResult(true, 1);
		}
		
		boolean isWin = false;
		int maxMove = 0;
		int minMove = Integer.MAX_VALUE;
		for(int i=0; i<4; i++) {
			int nx = tx +dx[i];
			int ny = ty +dy[i];
			
			if(nx < 0 || ny < 0 || nx > m-1 || ny > n-1 || map[ny][nx] == 0) continue;
			
//			System.out.println((flag? "A": "B") + "! "+ty +"," + tx);
			map[ty][tx] = 0;
			// 상대방 턴 결과 
			GameResult result = simulation(ox, oy, nx, ny, !flag);
			map[ty][tx] = 1;
//			System.out.println(result.isWin +"," + result.count);

			// 이기는 경우가 있으면 true 
			isWin |= !result.isWin; 
			
			// 상대방 졌을 경우 최소 움직임 카운트  
			if(!result.isWin) {
				minMove = Math.min(minMove, result.count);
			} else { // 상대방이 이겼을 경우 최대 움직임 카운트  
				maxMove = Math.max(maxMove, result.count);
			}
		}
		
		// 나의 움직임(1) + 상대방 결과 움직임  
		return new GameResult(isWin, (isWin ? minMove : maxMove)+1);
	}
	
	static boolean canMove(int x, int y) {
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny < 0 || nx > m-1 || ny > n-1 || map[ny][nx] == 0) continue;
			
			return true;
		}
		
		return false;
	}
	
}
