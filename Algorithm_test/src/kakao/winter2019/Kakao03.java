package kakao.winter2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


//	2019 카카오 개발자 겨울 인턴십 코딩테스트 #3 불량 사용자

// 	user_id : 	["frodo", "fradi", "crodo", "abc123", "frodoc"]
// 	banned_id :  ["fr*d*", "abc1**"] 
// 	result = 2
public class Kakao03 {

	public static void main(String[] args) {

		String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		String[] banned_id = { "fr*d*", "*rodo", "******", "******" };

		System.out.println(solution(user_id, banned_id));
	}

	public static int solution(String[] user_id, String[] banned_id) {

		int answer = 1;
						
		 
		ArrayList<String> c = new ArrayList<String>(); 
		String[][] arr =new String[banned_id.length][user_id.length];

		int idx = 0;
		for (String s1 : banned_id) {

			
			int star = 0, result = 0;
			for (int i = 0; i < s1.length(); i++) {
				if (s1.charAt(i) == '*') star++;
			}

			for (String s2 : user_id) {
				int count = 0;
				if (s1.length() == s2.length()) {

					for (int j = 0; j < s2.length(); j++) {
						if (s1.charAt(j) == s2.charAt(j)) {
							count++;
						}
					}

//				System.out.println(count + star + " vs " + s1.length() );
					if (star + count == s1.length()) {
					System.out.println(s1.length()+ " ㅡㅡ" + s1 + ", s2 : " +s2);
						result++;

						c.add(s2);
					}
				}

			}
			for(int i =0; i<result; i++) {
				arr[idx][i]  =c.get(i);
			}
			
			c.clear();
			answer *= result;
			idx++;
			
		}
		
		System.out.println(Arrays.deepToString(arr));

		
		
		return answer;
	}
}
