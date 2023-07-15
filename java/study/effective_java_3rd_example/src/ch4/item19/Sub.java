package ch4.item19;

import java.time.Instant;

public final class Sub extends Super{
	// 초기화되지 않은 final 필드. 생성자에서 초기화한다.
	private final Instant instant;
	public Sub(){
		System.out.println("Sub init");
		instant = Instant.now();
	}

	@Override public void overrideMe(){
		System.out.println(instant);
	}

	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.overrideMe();
	}
}
