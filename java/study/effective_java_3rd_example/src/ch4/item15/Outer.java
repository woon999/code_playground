package ch4.item15;


class Outer {
	// private static 중첩 클래스(Inner)는 바깥 클래스(Outer)에서만 사용이 가능하다.
	private static class Inner {
		String hello = "abc";
	}
	public static void main(String[] args) {
		Inner inner = new Inner();
		System.out.println(inner.hello);
	}
}
