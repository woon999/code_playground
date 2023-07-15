package ch4.item21;

import java.util.ArrayList;
import java.util.List;

public class Practice {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<10; i++){
			list.add(i);
		}

		list.removeIf(i -> i%2==0);
		for(int num : list){
			System.out.println(num); // 1 3 5 7 9
		}
	}
}
