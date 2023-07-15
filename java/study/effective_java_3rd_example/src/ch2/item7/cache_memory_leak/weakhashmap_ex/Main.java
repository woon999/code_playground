package ch2.item7.cache_memory_leak.weakhashmap_ex;

import java.util.Scanner;
import java.util.WeakHashMap;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Map<UniqueImageName, BigImage> map = new HashMap<>();
		WeakHashMap<UniqueImageName, BigImage> map = new WeakHashMap<>();
		BigImage bigImageFirst = new BigImage("foo");
		UniqueImageName imageNameFirst = new UniqueImageName("name_of_big_image");

		BigImage bigImageSecond = new BigImage("foo_2");
		UniqueImageName imageNameSecond = new UniqueImageName("name_of_big_image_2");

		map.put(imageNameFirst, bigImageFirst);
		map.put(imageNameSecond, bigImageSecond);

		System.out.println(map.containsKey(imageNameFirst));
		System.out.println(map.containsKey(imageNameSecond));

		imageNameFirst = null;
		System.gc();

		while(true) {
			System.out.print("test를 입력하세요 :");
			sc.next();
			if(map.getClass().getTypeName().contains("WeakHashMap")){
				System.out.println("WeakHashMap : "+map.size()); // 1
				System.out.println("WeakHashMap (imageNameFirst) : "+map.containsKey(imageNameFirst)); // false
				System.out.println("WeakHashMap (imageNameSecond) : "+map.containsKey(imageNameSecond)); // true
			}else{
				System.out.println("SoftHashMap : "+map.size()); // 2
				System.out.println("SoftHashMap (imageNameFirst) : "+map.containsKey(imageNameFirst)); // false
				System.out.println("SoftHashMap (imageNameSecond) : "+map.containsKey(imageNameSecond)); // true
			}

		}
	}
}
