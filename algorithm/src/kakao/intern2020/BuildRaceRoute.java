package kakao.intern2020;

//intern #4 경주로 건설 (BFS) 
import java.util.LinkedList;
import java.util.Queue;

public class BuildRaceRoute {

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int n;
	static int[][] map;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) {
//		int[][] board = {{0,0,0}, {0,0,0},{0,0,0}};
		
//		int[][] board = {{0,0,1,0}, {0,0,0,0},{0,1,0,1},{1,0,0,0}};
		int[][] board = {{0,0,0,0,0,0}, {0,1,1,1,1,0},{0,0,1,0,0,0}
						,{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
		System.out.println(solution(board));
		
	}
	
	public static int solution(int[][] board) {
        n = board.length;
        map = board;
        
        for(int i=0; i<n; i++) {
        	for(int j=0; j<n; j++) {
        		System.out.print(map[i][j]+" ");
        		
        	}
        	System.out.println();
        }
        bfs(0,0);
        
        
        
        return answer;
    }
	
	
	static void bfs(int x, int y) {
		
		Queue<Node> q = new LinkedList<>();
		
		q.add(new Node(x,y,-1, 0));
		map[x][y] = 1;
		
		while(!q.isEmpty()) {
			Node pos = q.poll();
			int value = pos.value;
			
			if(pos.x == n-1 && pos.y == n-1) {
				System.out.println("finish ! : " + value);
				answer = Math.min(answer, value);
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int nx = pos.x + dx[i];
				int ny = pos.y + dy[i];
				
				
				if(nx <0 || ny<0 || nx> n-1 || ny >n-1) continue;
				if(map[nx][ny] == 1) continue;
				
				// 방향에 따른 가격 추가 
				int cost = 0;
				if(pos.dir ==i || pos.dir == -1) {
					cost = value+ 100;
				}else {
					cost = value+ 600;
				}
				
				if(map[nx][ny]==0)  {
					map[nx][ny] = cost;
					q.add(new Node(nx,ny, i, cost));
				}else if( map[nx][ny] >= cost) {
					map[nx][ny] = cost;
					q.add(new Node(nx,ny, i, cost));
				}
			}
			
		}
		
	}
}

class Node{
	int x;
	int y;
	int dir;
	int value;
	
	public Node(int x, int y, int dir, int value) {
		this.x=x;
		this.y=y;
		this.dir = dir;
		this.value = value;
	}
}