package ch4.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Thing {
	private int itemId;
	public Thing(int itemId){
		this.itemId = itemId;
	}

	// public void changeId(int id){
	// 	this.itemId = id;
	// }
	@Override
	public String toString() {
		return itemId+"";
	}

	// public static Thing t1 = new Thing(1);
	// public static Thing t2 = new Thing(2);

	// 문제점: 보안 허점이 숨어있다.
	public static final Thing[] VALUES = {new Thing(1), new Thing(2)};

	// 해결책
	private static final Thing[] PRIVATE_VALUES = {new Thing(1), new Thing(2)};

	// 1. public 배열을 private으로 만들고 public 불변 리스트를 추가한다.
	public static final List<Thing> VALUES2 =
		Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

	// 2. 방어적 복사
	public static final Thing[] values(){
		return PRIVATE_VALUES.clone();
	}

}
