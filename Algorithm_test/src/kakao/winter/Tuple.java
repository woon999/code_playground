package kakao.winter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//2019 카카오 개발자 겨울 인턴십 코딩테스트 #2

// {{2},{2,1},{2,1,3},{2,1,3,4}}  -> 2 1 3 4 
// {{1,2,3},{2,1},{1,2,4,3},{2}}  -> 2 1 3 4 
// {{20,111},{111}}               -> 111 20	
// {{123}}						  -> 123	
// {{4,2,3},{3},{2,3,4,1},{2,3}}  -> 3 2 4 1

public class Tuple {

	public static void main(String[] args) {
		String s = "{{20,111},{111}}";

		System.out.println(Arrays.toString(solution(s)));
	}

	public static int[] solution(String s) {
		String temp = s.substring(1, s.length() - 1);

		String[] arr = temp.split("}");

		Arrays.sort(arr, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return Integer.compare(s1.length(), s2.length());
			}
		});
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].length() > 1) {
				if (arr[i].charAt(0) == ',') {
					arr[i] = arr[i].substring(2);
				}
				if (arr[i].charAt(0) == '{') {
					arr[i] = arr[i].substring(1);
				}
			}


			String[] index = arr[i].split(",");
//			System.out.println("index : " + Arrays.toString(index) + ", length :" + index.length);

			for (int j = 0; j < index.length; j++) {
				double num = 0;
				for (int k = 0; k < index[j].length(); k++) {
//					System.out.println("index[j].charAt(k) : " + index[j].charAt(k));
					num += (index[j].charAt(k) - 48) * (Math.pow(10, index[j].length() - k - 1));

				}
				list.add((int) num);

				for (int l = 0; l < list.size() - 1; l++) {
					if (list.size() > 0 && list.get(l) == num)
						list.remove(list.size() - 1);
				}

			}

		}
		int[] answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
}

