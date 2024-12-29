package baekjoon.ttzero.pratice;

import java.util.*;

public class CheapMenu {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int[] buger = new int[3];
		int[] drink = new int[2];
		for (int i = 0; i < buger.length; i++) {
			buger[i] = s.nextInt();			
		}
		
		for(int j =0; j<drink.length; j++) {
			drink[j] = s.nextInt();
		}
		s.close();
		Arrays.sort(buger);
		Arrays.sort(drink);
		int cheapset = buger[0]+drink[0]-50;
		
		System.out.println(cheapset);

	}

}
