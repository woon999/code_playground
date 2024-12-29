package baekjoon.ttzero.greedy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Meeting {
	int start;
	int end;

	Meeting(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}
}

public class MeetingRoom {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();

		ArrayList<Meeting> list = new ArrayList<Meeting>();

		for (int i = 0; i < n; i++) {
			list.add(new Meeting(s.nextInt(), s.nextInt()));
		}

		Collections.sort(list, new Comparator<Meeting>() {

			public int compare(Meeting c1, Meeting c2) {
				if (c1.getEnd() > c2.getEnd()) {
					return 1;
				} else if (c1.getEnd() < c2.getEnd()) {
					return -1;
				}
				return c1.getStart() - c1.getEnd();

			}
		});

//		for(int i=0;i<n;i++) {
//			System.out.println(list.get(i).getStart() +"," + list.get(i).getEnd());
//		}
		int end = -1;
		int start;
		int count = 0;

		for (int j = 0; j < n; j++) {
			start = list.get(j).getStart();

			if (start >= end) {
				end = list.get(j).getEnd();
				count++;
			}
		}
		System.out.println(count);

		s.close();

	}
}

//public static void main(String[] args) {
//Scanner s = new Scanner(System.in);
//
//int n = s.nextInt();
//
//int arr[][] = new int[n][2];
//
//for (int i = 0; i < n; i++) {
//	for (int j = 0; j < 2; j++)
//		arr[i][j] = s.nextInt();
//}
//System.out.println(max(arr));
//s.close();
//
//}
//
//public static int max(int arr[][]) {
//
//int n = arr.length;
//int count = 1;
//
//for (int i = 0; i < n; i++) {
//	for (int j = 1; j < n; j++) {
//		if (arr[i][1] < arr[j][0]) {
//			count++;
//			i = j - 1;
//			j = i + 1;
//		}
//	}
//}
//return count;
//}
