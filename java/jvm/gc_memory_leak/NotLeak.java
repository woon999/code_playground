package jvm.gc;

import java.util.ArrayList;

public class NotLeak {
	ArrayList list = new ArrayList();
	public void addList(int a){
		list.add("가나다라마바사아자차카타파하" + a);
	}

	public void removeStr(int i){
		list.remove("가나다라마바사아자차카타파하"+ i);
	}
}
