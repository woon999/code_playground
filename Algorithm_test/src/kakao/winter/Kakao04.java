package kakao.winter;

import java.util.Arrays;

//	2019 카카오 개발자 겨울 인턴십 코딩테스트 #4 호텔 방 배정
//	[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

// 	      입출력 예시
//		k	room_number		result
//		10	[1,3,4,1,3,1]	[1,3,4,2,5,6]


//	효율성 테스트 통과 못함
//	방이 200000개 있을 때는 빈방의 수가 너무 많아 시간초과
//  HashMap을 이용해서 방문한 방 노드에 부모 값 지정

public class Kakao04 {

	public static void main(String[] args) {

		long[] room_number = { 1, 3, 4, 1, 3, 1 };
		System.out.println(Arrays.toString(solution(10, room_number)));
	}

	public static long[] solution(long k, long[] room_number) {

		boolean[] check = new boolean[(int) k + 1];
		long[] answer = new long[room_number.length];
		int idx = 0;
		int p = 0;
		for (long i : room_number) {
			int room = (int) i;
			if (!check[room]) {
				check[room] = true;
				answer[idx++] = room;
			} else {
				p = room+1;
				while (true) {
					if (!check[p]) {
						check[p] = true;

						answer[idx++] = p;
						break;
					}
					p++;
				}
			}
			
			
			
		}
		
		return answer;
	}

}
