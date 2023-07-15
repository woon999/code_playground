package ch3.item10.transitivityEx.lspDenied;

import java.util.Set;

import ch3.item10.transitivityEx.Point;

public class UnitCircle {
	private static final Set<Point> unitCircle = Set.of(
		new Point(1, 0), new Point(0, 1),
		new Point(-1, 0), new Point(0, -1)
	);

	public static boolean onUnitCircle(Point p){
		return unitCircle.contains(p);
	}
}
