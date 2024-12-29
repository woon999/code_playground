package ch2.item7.cache_memory_leak.linkedhashmap_ex;

import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
	private static final int MAX = 5;
	public static void main(String[] args) {
		LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>(){
			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
				return size() > MAX;
			}
		};

		for(int i=0; i<10; i++){
			map.put(i,i);
			System.out.println(i + "map size : " + map.size());
		}

		for(int key : map.keySet()){
			System.out.println(key); // 5 6 7 8 9
		}
	}
}
