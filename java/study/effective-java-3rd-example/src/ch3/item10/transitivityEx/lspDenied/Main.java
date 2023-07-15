package ch3.item10.transitivityEx.lspDenied;

import java.util.List;

import ch3.item10.transitivityEx.Point;

public class Main {
	public static void main(String[] args) {
		System.out.println(UnitCircle.onUnitCircle(new Point(1,0))); // true

		List<CounterPoint> cps = List.of(
			new CounterPoint(1, 0),new CounterPoint(-1, 0),
			new CounterPoint(0, 1), new CounterPoint(0, -1));
		for(CounterPoint cp : cps){
			System.out.println(UnitCircle.onUnitCircle(cp)); // false
		}

	}
}
