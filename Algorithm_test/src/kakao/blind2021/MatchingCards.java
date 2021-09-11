package kakao.blind2021;

//blind #6 카드 짝 맞추기 (그래프 탐색, BFS, 순열)   
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MatchingCards {
	
	static List<String> orders;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1}; 

	public static void main(String[] args) {
//		int[][] board = {{1,0,0,3}, 
//						 {2,0,0,0}, 
//						 {0,0,0,2}, 
//						 {3,0,1,0}};
//		int r =1;
//		int c= 0;
		
		int[][] board = {{3,0,0,2}, 
						 {0,0,1,0}, 
						 {0,1,0,0}, 
						 {2,0,0,3}};
		int r =0;
		int c= 1;
		System.out.println(solution(board,r,c));
	}
	
	public static int solution(int[][] board, int r, int c) {
		 
		 int cardNum =0;
		 
		 // 카드 개수 
		 for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				if(board[i][j] != 0) {
					cardNum ++;
				}
			}	
		 }
		 cardNum /=2;
		
		 int[] card = new int[cardNum];
		 for(int i=0; i<cardNum; i++) {
			 card[i] = i+1;
		 }
		 
		 // 카드조합 순서 경우의 수 구하기 (123,132,231 ...) 
		 orders = new ArrayList<>();
		 permutation("", 0, card);
		 
		 int min = Integer.MAX_VALUE;
		 for(String comb : orders) {
			 String[] order = comb.split("");
			 
			 int total_move =0;
			 int[] pos = new int[2];
			 pos[0] =r;
			 pos[1] =c;			
			 
			 int[][] copy_board  = new int[4][4];
			 for(int i=0; i<4; i++) {
				 for(int j=0; j<4; j++) {
					 copy_board[i][j] = board[i][j];
				 }
			 }
			 
			 System.out.println("start : " + pos[0]+","+pos[1]);
			 for(String target_card : order) {
				 int card_num = Integer.parseInt(target_card);
				 
				 // -------------------------------------------
				 // 첫 번째 카드 찾기 
				 total_move += cardSearch(pos, card_num, copy_board);				 
				 copy_board[pos[0]][pos[1]] = 0;
				 // enter 
				 total_move += 1;
				 System.out.println("1 :" + pos[0]+","+pos[1]);
				 // -------------------------------------------
				 
				 // -------------------------------------------
				 // 두 번째 카드 찾기 
				 total_move += cardSearch(pos, card_num, copy_board);
				 copy_board[pos[0]][pos[1]] = 0;
				 
				 // enter 
				 total_move += 1;
				 System.out.println("2 :" + pos[0]+","+pos[1]);
				 // -------------------------------------------
				 
				 System.out.println("move : " +total_move);
				 System.out.println();
			 }
			 
			 min = Math.min(min, total_move);
			 System.out.println("-----------");
			 System.out.println(min);
			 System.out.println("-----------");
			 System.out.println();
		 }
		 
		 return min;
	}

	// 1. 카드 찾기 순서 경우의 수 모두 구하기 
	static void permutation(String comb, int depth,int[] card) {
		if(card.length == depth) {
			System.out.println(comb);
			orders.add(comb);
			return ;
		}
		
		for(int i=0; i<card.length; i++) {
			int num = card[i];
			if(!comb.contains(""+num)) {
				permutation(comb+num, depth+1, card);
			}
		}
		
	}
	
	// 2. 카드 찾으러 이동하기  
	static int cardSearch(int[] pos, int target_card, int[][] copy_board) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] check = new boolean[4][4];
		int x= pos[0];
		int y= pos[1];
		
		check[x][y] = true;
		q.add(new Pair(x,y,0));
		while(!q.isEmpty()) {
			Pair next = q.poll();
			int px = next.x;
			int py = next.y;
			int move =next.move;
			
			if(copy_board[next.x][next.y] == target_card) {
				System.out.println("["+target_card+ "] find! "+ next.x+","+next.y+ ":"+ move);
				pos[0] = next.x;
				pos[1] = next.y;
				return move;
			}
			
			//상하좌우 1칸 move 
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || ny<0 || nx>3 || ny>3) continue;
				if(check[nx][ny]) continue;
				
				check[nx][ny] = true;
				q.add(new Pair(nx,ny, move+1));
			}
			
			// ctrl+상하좌우 move
			for(int i=0; i<4; i++) {
				
				Pair res = checkRoute(px,py, i, copy_board);
				int nx = res.x, ny =res.y;
				
				if(nx==x && ny ==y) continue;
				if(check[nx][ny]) continue;
				
				check[nx][ny] = true;
				q.add(new Pair(nx,ny, move+1));
			}
		}
		return 0;
		
		
		
	}
	// ctrl 이동 시 해당 경로에 카드가 있는지 확인 
	static Pair checkRoute(int x, int y, int direction, int[][] copy_board) {
		x += dx[direction];
		y += dy[direction];
		
		while(x >= 0 && x < 4 && y >= 0 && y < 4) {
			if(copy_board[x][y] != 0) return new Pair(x,y,0);
			
			x += dx[direction];
			y += dy[direction];
		}
		
		// 카드 없으면 끝 
		return new Pair(x-dx[direction],y-dy[direction],0);
		
		
	}
}

class Pair{
	int x;
	int y;
	int move;
	
	public Pair(int x, int y, int move){
		this.x =x;
		this.y =y;
		this.move = move;
	}
}
