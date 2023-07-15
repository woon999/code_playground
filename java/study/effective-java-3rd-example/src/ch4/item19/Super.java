package ch4.item19;

public class Super {
	// 잘못된 예 - 생성자가 재정의 가능 메서드를 호출한다.
	public Super(){
		System.out.println("Super init");
		overrideMe();
	}
	public void overrideMe(){

	}
}
