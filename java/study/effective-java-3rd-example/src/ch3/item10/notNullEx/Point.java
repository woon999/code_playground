package ch3.item10.notNullEx;

public class Point {
	private final int x;
	private final int y;

	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}

	// 명시적 null 검사 - 필요없다
	// @Override public boolean equals(Object o) {
	// 	if(o == null){
	// 		return false;
	// 	}
	// 	Point p = (Point)o;
	// 	return p.x == x && p.y == y;
	// }

	// 묵시적 null 검사 - 그나마 이쪽이 낫다
	@Override public boolean equals(Object o) {
		if(!(o instanceof Point)){
			return false;
		}
		Point p = (Point)o;
		return p.x == x && p.y == y;
	}

	public static void main(String[] args) {
		Point p1 = new Point(1,1);
		Point p2 = new Point(1,1);

		System.out.println(p1.equals(p2)); // true
		System.out.println(p1.equals(null)); // false
	}

}
