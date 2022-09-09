package leetcode;

// #657 - Robot Return to Origin
public class RobotReturnToOrigin_657 {
	public static void main(String[] args) {

		String moves = "UD";
		System.out.println("judgeCircle(moves) = " + judgeCircle(moves));
	}

	public static boolean judgeCircle(String moves) {
		int x = 0, y = 0;
		for(char c : moves.toCharArray()){
			switch (c){
				case 'U': y++; break;
				case 'D': y--; break;
				case 'L': x--; break;
				case 'R': x++; break;
			}
		}

		return x==0 && y==0;
	}
}
