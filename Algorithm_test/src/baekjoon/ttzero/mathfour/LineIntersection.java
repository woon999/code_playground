package baekjoon.ttzero.mathfour;

// #17386
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LineIntersection {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		Point2 a = new Point2(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		Point2 b = new Point2(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

		st = new StringTokenizer(br.readLine());
		Point2 c = new Point2(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
		Point2 d = new Point2(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));

		
        if (chk(a, b, c, d))
            System.out.println(1);
        else
            System.out.println(0);
	}

	public static int ccw(Point2 a, Point2 b, Point2 c) {
		long result = ((a.x * b.y) + (b.x * c.y) + (c.x * a.y)) - ((a.y * b.x) + (b.y * c.x) + (c.y * a.x));
		if (result < 0)
			return -1;
		else if (result > 0)
			return 1;
		else
			return 0;
	}

	public static boolean chk(Point2 a, Point2 b, Point2 c, Point2 d) {
		int abc = ccw(a, b, c);
		int abd = ccw(a, b, d);
		int cda = ccw(c, d, a);
		int cdb = ccw(c, d, b);
		if (abc * abd == 0 && cda * cdb == 0) {
			if (a.cmp(b)) {
				Point2 tmp = a;
				a = b;
				b = tmp;
			}

			if (c.cmp(d)) {
				Point2 tmp = c;
				c = d;
				d = tmp;
			}
			if (b.equals(c) || a.equals(d))
				return true;

			return b.cmp(c) != a.cmp(d);
		}

		return abc * abd <= 0 && cda * cdb <= 0;
	}
}

class Point2 {
	long x;long y;

	public Point2(long x, long y) {
		this.x = x;
		this.y = y;
	}
	
	  public boolean cmp(Point2 o) {
          if (y > o.y)
              return true;
          if (y == o.y) {
              if (x >= o.x)
                  return true;
          }
          return false;
      }
      public boolean equals(Point2 obj) {
          if(x==obj.x && y==obj.y)
              return true;
          return false;
      }
}