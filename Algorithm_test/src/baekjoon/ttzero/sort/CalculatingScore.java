package baekjoon.ttzero.sort;


// #2822
import java.io.*;
import java.util.*;

public class CalculatingScore {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int res=0;
		List<Score> list = new ArrayList<Score>();
		for (int i = 1; i < 9; i++) {
			Score s = new Score();
			s.score = Integer.parseInt(br.readLine());
			s.number = i;
			list.add(s);
			res += s.score;
		}

		list.sort(Comparator.naturalOrder());
		
		System.out.println(res - list.get(0).score - list.get(1).score - list.get(2).score);
		
		List<Integer> max = new ArrayList<>();
		
		for(int i=3; i<8; i++) {
			max.add(list.get(i).number);
		}
		
		max.sort(Comparator.naturalOrder());
		
		for(int i=0; i<5; i++) {
			System.out.print(max.get(i) + " ");
		}

	}
	
}


class Score implements Comparable<Score> {
	int score;
	int number;

	@Override
	public int compareTo(Score o) {
		if (this.score > o.score) {
			return 1;
		}

		return -1;
	}

}