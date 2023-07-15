package ch9.debugging;

import java.util.Arrays;
import java.util.List;

class Point{
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

public class Debugging {
	public static void main(String[] args) {
		List<Point> points = Arrays.asList(new Point(12, 2), null);
		// points.stream().map(p -> p.getX()).forEach(System.out::println ); // stacktrace: lambda$main$0 ??

		List<Integer> numbers = Arrays.asList(1,2,3);
		numbers.stream().map(Debugging::divideByZero).forEach(System.out::println); // stacktrce: divideByZero
	}

	public static int divideByZero(int n){
		return n/0;
	}
}
