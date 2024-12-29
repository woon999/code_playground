package baekjoon.ttzero.mathtwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//-1 : �� ���� �߽ɰ� ������ ��� ���� �� x1=x2 & y1=y2 & r1=r2
// 0 : �ο��� �Ÿ��� �ֶ� r > r1+r2
//	 : �߽��� ���� �ο��� �������� �ٸ� �� x1=x2 & y1=y2 & r1!=r2
//   : ������ �ٸ� �� ������ ū �� �ȿ� ���� ���� �ְ� ������ ���� ��  r < |r1-r2|
// 1 : �� ���� ���� �� �� r = r1+r2
//   : �� ���� ���� �� �� r = |r1-r2|
// 2 : �� ���� �� �������� ���� �� else
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
