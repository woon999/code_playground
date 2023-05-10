package kakao.winter2019;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//2019 카카오 개발자 겨울 인턴십 코딩테스트 #2 튜플

//	{{2},{2,1},{2,1,3},{2,1,3,4}}  -> 2 1 3 4 
//	{{1,2,3},{2,1},{1,2,4,3},{2}}  -> 2 1 3 4 
//	{{20,111},{111}}               -> 111 20	
//	{{123}}						  -> 123	
//	{{4,2,3},{3},{2,3,4,1},{2,3}}  -> 3 2 4 1


//다른 분의 문제풀이
//String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");

public class Kakao02 {

	public static void main(String[] args) {
//		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		String s = "{{123}}";
		
		
		System.out.println(Arrays.toString(solution(s)));
	}


	public static int[] solution(String s) {

		String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
		
//		Arrays.sort(arr, new Comparator<String>() {
//			public int compare(String s1, String s2) {
//				return Integer.compare(s1.length(), s2.length());
//			}
//		});
		
		Arrays.sort(arr, (s1, s2) -> {
			return s1.length() - s2.length();
		});
		
		System.out.println(Arrays.toString(arr));

		Set<Integer> set = new HashSet<>();
		int[] answer = new int[arr.length];
		
		for (int i = 0; i < arr.length; i++) {

			String[] index = arr[i].split(",");
			System.out.println("index : " + Arrays.toString(index) + ", length :" + index.length);

			for (int j = 0; j < index.length; j++) {
				System.out.println(index[j]);
				int num = Integer.parseInt(index[j]);
				if(set.add(num)) {
					answer[i] = num;
				}

			}

		}
		
		return answer;
	}
}

