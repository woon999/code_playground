package kakao.intern2020;

import java.util.ArrayList;
import java.util.Arrays;

//["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]	[3, 7]
//["AA", "AB", "AC", "AA", "AC"]	[1, 3]
//["XYZ", "XYZ", "XYZ"]	[1, 1]
//["ZZZ", "YYY", "NNNN", "YYY", "BBB"]	[1, 5]
public class Kakao03 {

	public static void main(String[] args) {

		String[] s = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };

		System.out.println(Arrays.toString(solution(s)));
	}

	public static int[] solution(String[] gems) {

		ArrayList<String> list = new ArrayList<String>();


		int[] answer = new int[2];

		int min = Integer.MAX_VALUE;
		int jump = gems.length;
		
		for(int i = 0; i<gems.length-list.size()+1; i++) {

			for (String e : gems) {
				if(!list.contains(e)) {
					list.add(e);	
				}
			}

			int size = list.size();
			

			int count =0;
			for(int j = i; j<jump;j++) {
				if(list.contains(gems[j])) {
					list.remove(gems[j]);
					count++;
				}
				
				if(count == size) {
					System.out.println("Áö±Ý:"+ (i+1) +"," +(j+1));	
					if(min> j-i) {
						
						min = j-i;
						answer[0] = i+1;
						answer[1] = j+1;
						
//						System.out.println(min);
						jump = j;
						break;
					}
					
				}
			}
				
		}
		
		return answer;
	}
}
