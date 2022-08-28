package leetcode;

// #36 - Valid Sudoku
import java.util.HashSet;
import java.util.Set;

public class ValidSudoku_36 {
	public static void main(String[] args) {
		char[][] board = {
			{'5' ,'3', '.', '.', '7', '.', '.', '.', '.'},
			{'6' ,'.', '.', '1', '9', '5', '.', '.', '.'},
			{'.' ,'9', '8', '.', '.', '.', '.', '6', '.'},
			{'8' ,'.', '.', '.', '6', '.', '.', '.', '3'},
			{'4' ,'.', '.', '8', '.', '3', '.', '.', '1'},
			{'7' ,'.', '.', '.', '2', '.', '.', '.', '6'},
			{'.' ,'6', '.', '.', '.', '.', '2', '8', '.'},
			{'.' ,'.', '.', '4', '1', '9', '.', '.', '5'},
			{'.' ,'.', '.', '.', '8', '.', '.', '7', '9'},
		};
		System.out.println("isValidSudoku(board) = " + isValidSudoku(board));
	}

	public static boolean isValidSudoku(char[][] board) {
		Set<Character> set = new HashSet<>();
		return checkRows(board, set) && checkCols(board, set) && checkBox(board, set);
	}

	static boolean checkRows(char[][] board, Set<Character> set){
		set.clear();
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				char c = board[i][j];
				if(c == '.') continue;
				if(!set.add(c)) return false;
			}
			set.clear();
		}
		return true;
	}

	static boolean checkCols(char[][] board, Set<Character> set){
		set.clear();
		for(int i=0; i<board.length; i++){
			for(int j=0; j<board[i].length; j++){
				char c = board[j][i];
				if(c == '.') continue;
				if(!set.add(c)) return false;
			}
			set.clear();
		}
		return true;
	}

	static boolean checkBox(char[][] board, Set<Character> set){
		set.clear();
		for(int i=0; i<board.length; i+=3){
			for(int j=0; j<board[i].length; j+=3){
				for(int y = i; y<i+3; y++){
					for(int x =j; x<j+3; x++){
						char c = board[y][x];
						if(c == '.') continue;
						if(!set.add(c)) return false;
					}
				}
				set.clear();
			}
		}
		return true;
	}
}
