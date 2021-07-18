package kakao.intern2021;

// #2 kakao2021intern 거리두기 확인하기 - BFS
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheckTheDistance {

	static class Node{
		int x;
		int y;
		int move;
		
		public Node(int x, int y, int move){
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
	static int[][] map;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) {
		String[][] places = {{ "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
				{"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"},
				{"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
				{"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

		System.out.println(Arrays.toString(solution(places)));
	}
	public static int[] solution(String[][] places) {
		int[] answer = new int[places.length];
        for(int i=0; i<places.length; i++) {
        	answer[i] =1;
        }
        
        // P 사람 : 32, O 빈 테이블: 31, X 파티션 : 40   
        for(int t = 0; t<5; t++) {
        	map = new int[5][5];
        	List<Node> seat_list = new ArrayList<>();
        	
	        for(int i=0; i<places[t].length; i++) {
	        	String line = places[t][i];
	        	for(int j=0; j<line.length(); j++) {
	        		map[i][j] = line.charAt(j)-'0';
	        		if(map[i][j] == 32) {
	        			seat_list.add(new Node(i,j,0));
	        		}
	        	}
	        }
	        
	        for(Node p : seat_list) {
	        	if(bfs(p.x, p.y) == 0) {
	        		answer[t] = 0;
	        		break;
	        	}
	        }
	        
        }
        return answer;
    }
	static int bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] check = new boolean[5][5];
		check[x][y] = true;
		map[x][y] =1;
		q.add(new Node(x,y,0));
		
		while(!q.isEmpty()) {
			Node pos = q.poll();
			int move = pos.move;
			
			if(map[pos.x][pos.y] == 32) {
				if(move<=2) return 0;
			}
			
			
			for(int i=0; i<4; i++) {
				int nx = pos.x+dx[i];
				int ny = pos.y+dy[i];
				
				if(nx <0 || ny<0 || nx>4 || ny>4) continue;
				if(check[nx][ny]) continue;
				
				if(map[nx][ny] != 40) {
					check[nx][ny]= true;
					q.add(new Node(nx,ny, move+1));
				}
			}
		}
		return 1;
	}
}
