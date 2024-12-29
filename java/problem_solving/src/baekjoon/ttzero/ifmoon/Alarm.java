package baekjoon.ttzero.ifmoon;

import java.util.Scanner;

public class Alarm {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int h = s.nextInt(), m = s.nextInt();

		s.close();
		if (m < 45) {
			m = m + (60 - 45);
			if (h == 0)h = 23;
			else{h--;}
		} else {
			m -= 45;
		}
		System.out.println(h + " " + m);
	}
}
