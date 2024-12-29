package ch9.lambdatesting;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Practice {
	static class Point {
		private final int x;
		private final int y;

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

		public Point moveRightBy(int x) {
			return new Point(this.x + x, this.y);
		}

		public final static Comparator<Point> compareByXAndThenY = comparing(Point::getX).thenComparing(Point::getY);
		public static List<Point> moveAllPointsRightBy(List<Point> points, int x){
			return points.stream()
						.map(p -> new Point(p.getX()+x, p.getY()))
						.collect(toList());
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Point point = (Point)o;
			return x == point.x &&
				y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}

	@Test
	public void testMoveRightBy() throws Exception {
		Point p1 = new Point(5, 5);
		Point p2 = p1.moveRightBy(10);
		assertEquals(15, p2.getX());
		assertEquals(5, p2.getY());
	}

	@Test
	public void testComparingTwoPoints() throws Exception{
		Point p1 = new Point(10, 15);
		Point p2 = new Point(10, 20);
		int result = Point.compareByXAndThenY.compare(p1, p2);
		assertEquals(result<0, true);
	}

	@Test
	public void testMoveAllPointsRightBy() throws Exception{
		List<Point> points = Arrays.asList(new Point(5,5), new Point(10, 5));
		List<Point> expectedPoints = Arrays.asList(new Point(15,5), new Point(20, 5));

		List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
		assertEquals(expectedPoints, newPoints);
	}

	@Test
	public void testFilter() throws Exception{
		List<Integer> numbers = Arrays.asList(1,2,3,4);
		List<Integer> even = filter(numbers, i -> i%2 ==0);
		List<Integer> smallerThanThree = filter(numbers, i -> i<3);
		assertEquals(Arrays.asList(2,4), even);
		assertEquals(Arrays.asList(1,2), smallerThanThree);
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p){
		List<T> result = new ArrayList<>();
		for(T e: list){
			if(p.test(e)){
				result.add(e);
			}
		}
		return result;
	}

}
