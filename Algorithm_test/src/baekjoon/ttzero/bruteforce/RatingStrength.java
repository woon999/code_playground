package baekjoon.ttzero.bruteforce;

import java.util.Scanner;


public class RatingStrength {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] people = new int[n][3];
		System.out.println(people.length);
		for(int i = 0; i<n;i++) {
			people[i][0] = sc.nextInt();
			people[i][1] = sc.nextInt();
		}
		
		rating(people);
		
		
	}
	
	public static void rating(int[][] people) {
	
		int n = people.length;
		for(int i = 0; i<n; i++) {
			for(int j =0; j<n; j++) {
				if(i!=j && people[i][0]>people[j][0] && people[i][1]>people[j][1])
					people[j][2]++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<n;i++) {
			sb.append(people[i][2]+1 + " ");
		}
		System.out.print(sb);

	}
}
