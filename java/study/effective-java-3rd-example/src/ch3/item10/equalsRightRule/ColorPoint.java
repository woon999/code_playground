package ch3.item10.equalsRightRule;

import java.awt.*;

public class ColorPoint {
	private final Point point;
	private final Color color;

	public ColorPoint(int x, int y, Color color) {
		point = new Point(x, y);
		this.color = color;
	}

	/**
	 * 이 ColorPoint의 Point 뷰를 반환한다.
	 */
	public Point asPoint(){
		return point;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ColorPoint)){
			return false;
		}
		ColorPoint cp = (ColorPoint) o;
		return cp.point.equals(point) && cp.color.equals(color);
	}

	public static void main(String[] args) {
		Point p = new Point(1,2);
		ColorPoint cp = new ColorPoint(1, 2, Color.RED);

		System.out.println(p.equals(cp.asPoint())); // true
		System.out.println(cp.asPoint().equals(p)); //  true

		ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
		Point p2 = new Point(1,2);
		ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

		System.out.println(p1.asPoint().equals(p2)); // true
		System.out.println(p2.equals(p3.asPoint())); // true
		System.out.println(p1.asPoint().equals(p3.asPoint())); // true
	}
}
