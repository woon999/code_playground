package ch2.item6;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapKeySetEx {
	static Map<Integer, Integer> map = new HashMap<>();
	public static void main(String[] args) {
		for(int i=0; i<10; i++){
			map.put(i,i+1);
		}

		Set<Integer> set1 = map.keySet(); // 10
		Set<Integer> set2 = map.keySet(); // 10

		set1.remove(1);
		System.out.println(set1.size()); // 9
		System.out.println(set2.size()); // 9
	}
}
