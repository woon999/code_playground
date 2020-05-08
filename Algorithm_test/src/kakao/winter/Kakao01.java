package kakao.winter;

import java.util.ArrayList;

//	2019 카카오 개발자 겨울 인턴십 코딩테스트 #1 크레인 인형 뽑기


//	[[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]],[1,5,3,5,1,2,1,4]
//	stack,list도 가능
public class Kakao01 {
	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 },
				{ 3, 5, 1, 3, 1 } };
		int[] moves = { 1, 5, 3, 5, 1, 2, 1, 4 };

		System.out.println(solution(board, moves));

	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < moves.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[j][moves[i] - 1] != 0) {
					System.out.println("j : " + j + " ,인형 : " + board[j][moves[i] - 1]);

					if (list.size()!=0 && list.get(list.size() - 1) == board[j][moves[i] - 1]) {
						answer += 2;
						list.remove(list.size()-1);
					}

					else {
						list.add(board[j][moves[i] - 1]);
					}

					board[j][moves[i] - 1] = 0;
					break;

				}
			}
		}

		System.out.println(list);
		return answer;
	}

}
