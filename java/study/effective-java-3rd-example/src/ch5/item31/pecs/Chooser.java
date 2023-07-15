package ch5.item31.pecs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser<T> {
	private final List<T> choiceList;

	public Chooser(Collection<? extends T> choices){
		choiceList = new ArrayList<>(choices);
	}

	public T choose(){
		Random rnd = ThreadLocalRandom.current();
		return choiceList.get(rnd.nextInt(choiceList.size()));
	}

	public static void main(String[] args) {
		Chooser<Integer> chooser = new Chooser<>(List.of(1,2,3));
		Integer choose = chooser.choose();
		System.out.println("choose = " + choose);

		List<Integer> list = List.of(1,2,3);
		Chooser<Number> chooser2 = new Chooser<>(list);
		Number choose2 = chooser2.choose();
		System.out.println("choose2 = " + choose2);
	}
}
