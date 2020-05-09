package kakao.intern2020;

import java.util.ArrayList;
import java.util.Arrays;

//	[1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]	"right"	"LRLLLRLLRRL"
//	[7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2]	"left"	"LRLLRRLLLRR"
//	[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]	"right"	"LLRLLRLLRL"
public class Kakao01 {

	static int[] left_side = { 1, 4, 7 };
	static int[] right_side = { 3, 6, 9 };
	static int[] middle_side = { 2, 5, 8, 0 };

	public static void main(String[] args) {

		int[] numbers = { 7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2 };

		System.out.println(Arrays.toString(numbers));
		System.out.println("LRLLRRLLLRR");
		System.out.println(solution(numbers, "right"));
	}

	public static String solution(int[] numbers, String hand) {

		int left_position = '*';
		int right_position = '#';
		ArrayList<String> list = new ArrayList<String>();
		
		boolean[] position = new boolean[2];

		boolean first = false; 
		if(hand == "right") {
			first = true;
		}
		

		for (int i : numbers) {

		}

		System.out.println(list);
		String answer = "";
		for(int i =0; i<list.size();i++) {
			answer += list.get(i);
		}
		return answer;
	}
}
