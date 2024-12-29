package ch3.item10.transitivityEx.lspDenied;

import java.util.concurrent.atomic.AtomicInteger;

import ch3.item10.transitivityEx.Point;

public class CounterPoint extends Point {
	private static final AtomicInteger counter = new AtomicInteger();

	public CounterPoint(int x, int y){
		super(x, y);
		counter.incrementAndGet();
	}

	public static int numberCreated(){
		return counter.get();
	}
}
