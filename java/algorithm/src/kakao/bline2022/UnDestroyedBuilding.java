package kakao.bline2022;

//#6 kakao2022blind 파괴되지 않은 건물  
public class UnDestroyedBuilding {
	
	public static void main(String[] args) {
		int[][] board = {
				{5,5,5,5,5},
				{5,5,5,5,5},
				{5,5,5,5,5},
				{5,5,5,5,5},
		};
		
		int[][] skill = {
				{1,0,0,3,4,4},
				{1,2,0,2,3,2},
				{2,1,0,3,1,2},
				{1,0,1,3,3,1}
		};
		
		int[][] board2 = {
				{1,2,3},
				{4,5,6},
				{7,8,9}
		};
		
		int[][] skill2 = {
				{1,1,1,2,2,4},
				{1,0,0,1,1,2},
				{2,2,0,2,0,100}
		};
		
		System.out.println(solution(board2, skill2));
	}
	static int[] arr;
	static int[][] map;
	static int n,m;
	public static int solution(int[][] board, int[][] skill) {
        int answer = 0;
        n = board.length; //y
        m = board[0].length; //x
        map = new int[n+1][m+1];
        
        for(int i=0; i<skill.length; i++) {
        	int type =skill[i][0];
        	int y1 =skill[i][1];
        	int x1 =skill[i][2];
        	int y2 =skill[i][3];
        	int x2 =skill[i][4];
        	int degree = skill[i][5];
        	
//        	System.out.println(type+" : ("+y1+","+x1+") - ("+y2+","+x2+") ..." + degree);
        	skillEffect(type, x1, y1, x2, y2, degree);
//        	print();
        	
        }
        

        // 누적합 계산 
        calculatingSum(board);
        	
        answer = buildingCnt();
        return answer;
    }
	
	static void skillEffect(int type, int sx, int sy, int ex, int ey,int degree) {
		if(type ==1) {
			degree *= -1;
		}
		map[sy][sx] += degree;
		map[ey+1][sx] -= degree;
		map[sy][ex+1] -= degree;
		map[ey+1][ex+1] += degree;
	}

	static void calculatingSum(int[][] board) {
		for(int x=0; x<m+1; x++) {
			for(int y=1; y<n+1; y++) {
				map[y][x] += map[y-1][x];
			}
		}	
		for(int y=0; y<n+1; y++) {
			for(int x=1; x<m+1; x++) {
				map[y][x] += map[y][x-1];
			}
		}
		
		for(int y=0; y<n; y++) {
			for(int x=0; x<m; x++) {
				map[y][x] += board[y][x];
			}
		}
	}
	
	static int buildingCnt() {
		int cnt=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] >0) cnt++;
			}
		}
		return cnt;
	}
	
	static void print() {
		for(int y=0; y<n+1; y++) {
			for(int x=0; x<m+1; x++) {
				System.out.print(map[y][x] +" ");
			}
			System.out.println();
		}
		
		System.out.println("------");
	}
	

}
