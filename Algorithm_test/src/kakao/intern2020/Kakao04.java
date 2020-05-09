package kakao.intern2020;

import java.util.Arrays;

//[[0,0,0],[0,0,0],[0,0,0]]	900
//[[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0],[0,0,0,0,0,1,0,0],[0,0,0,0,1,0,0,0],[0,0,0,1,0,0,0,1],[0,0,1,0,0,0,1,0],[0,1,0,0,0,1,0,0],[1,0,0,0,0,0,0,0]]	3800
//[[0,0,1,0],[0,0,0,0],[0,1,0,1],[1,0,0,0]]	2100
//[[0,0,0,0,0,0],[0,1,1,1,1,0],[0,0,1,0,0,0],[1,0,0,1,0,1],[0,1,0,0,0,1],[0,0,0,0,0,0]]	3200
public class Kakao04 {

	public static void main(String[] args) {

		int[][] board = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

		solution(board);

	}

	public static int solution(int[][] board) {

		boolean flag = true;

		for (int i = 0; i < board.length; i++) {


			
				if (board[i][board[i].length - 1] == 2) {
					board[++i][board[i].length - 1] = 2;
					if (i == board.length - 1)
						break;
				} else {
					for (int j = 0; j < board[i].length;) {
						board[i][j++] = 2;
						
					}
				}

		}
		System.out.println(Arrays.deepToString(board));
		int answer = 0;
		return answer;
	}
}
