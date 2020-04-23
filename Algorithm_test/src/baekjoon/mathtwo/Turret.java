package baekjoon.mathtwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//-1 : 두 원이 중심과 반지름 모두 같을 때 x1=x2 & y1=y2 & r1=r2
// 0 : 두원의 거리가 멀때 r > r1+r2
//	 : 중심이 같은 두원의 반지름이 다를 때 x1=x2 & y1=y2 & r1!=r2
//   : 중점이 다른 두 원에서 큰 원 안에 작은 원이 있고 만나지 않을 때  r < |r1-r2|
// 1 : 두 원이 외접 할 때 r = r1+r2
//   : 두 원이 내접 할 때 r = |r1-r2|
// 2 : 두 원이 두 교점에서 만날 때 else
public class Turret {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			System.out.println(Ryu(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));

		}

	}

	public static int Ryu(int x1, int y1, int r1, int x2, int y2, int r2) {
		double r = Math.sqrt((Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));

		if (x1 == x2 && y1 == y2 && r1 == r2)
			return -1;
		else if ((r > r1 + r2) || (r < Math.abs(r1 - r2) || (x1 == x2 && y1 == y2 && r1 != r2)))
			return 0;
		else if ((r == r1 + r2) || (r == Math.abs(r1 - r2)))
			return 1;
		else
			return 2;

	}

}
