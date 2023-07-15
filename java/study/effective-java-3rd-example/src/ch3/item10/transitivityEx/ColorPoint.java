package ch3.item10.transitivityEx;

import java.awt.*;

public class ColorPoint extends Point {
	private final Color color;

	public ColorPoint(int x, int y, Color color){
		super(x, y);
		this.color = color;
	}

	// 대칭성 위배
	// @Override public boolean equals(Object o) {
	// 	if(!(o instanceof ColorPoint)){
	// 		return false;
	// 	}
	// 	return super.equals(o) && ((ColorPoint) o).color == color;
	// }

	// 추이성 위배
	@Override public boolean equals(Object o) {
		if(!(o instanceof Point)){
			return false;
		}

		// o가 일반 Point이면 색상을 무시하고 비교한다.
		if(!(o instanceof  ColorPoint)){
			return o.equals(this);
		}

		// o가 ColorPoint면 색상까지 비교한다.
		return super.equals(o) && ((ColorPoint) o).color == color;
	}

	public static void main(String[] args) {
		// 대칭성 위배 테스트
		Point p = new Point(1,2);
		ColorPoint cp = new ColorPoint(1, 2, Color.RED);

		System.out.println(p.equals(cp)); // true
		System.out.println(cp.equals(p)); // false

		// 추이성 위배 테스트
		ColorPoint p1 = new ColorPoint(1, 2, Color.RED);
		Point p2 = new Point(1,2);
		ColorPoint p3 = new ColorPoint(1, 2, Color.BLUE);

		// 추이성: p1 == p2, p2 == p3 -> p1 == p3
		System.out.println(p1.equals(p2)); // true
		System.out.println(p2.equals(p3)); // true
		System.out.println(p1.equals(p3)); // false 추이성 위배
	}
}
