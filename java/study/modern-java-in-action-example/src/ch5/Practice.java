package ch5;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Practice {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
		);

		// 1. 2011년에 일어난 모든 트랜잭션 오름차순
		System.out.println("1. 2011년에 일어난 모든 트랜잭션 오름차순");
		List<Transaction> tr2011 = transactions.stream()
			.filter(i -> i.getYear() == 2011)
			.sorted(comparing(Transaction::getValue))
			.collect(toList());

		tr2011.stream().forEach(i -> System.out.print(i + " "));
		System.out.println();

		// 2. 거래자가 근무하는 모든 도시를 중복없이 나열
		System.out.println(System.lineSeparator() + "2. 거래자가 근무하는 모든 도시를 중복없이 나열");
		List<String> distinctCity = transactions.stream()
			.map(i -> i.getTrader().getCity())
			.distinct()
			.collect(toList());

		distinctCity.stream().forEach(i -> System.out.print(i + " "));
		System.out.println();

		// 3. 케임브리지에서 근무하는 모든 거래자 찾아서 이름순 정렬
		System.out.println(System.lineSeparator() + "3. 케임브리지에서 근무하는 모든 거래자 찾아서 이름순 정렬");
		List<Trader> cambridgeTraders = transactions.stream()
			.map(Transaction::getTrader)
			.filter(i -> i.getCity().equalsIgnoreCase("Cambridge"))
			.distinct()
			.sorted(comparing(Trader::getName))
			.collect(toList());

		cambridgeTraders.stream().forEach(i -> System.out.print(i.getName() + " "));
		System.out.println();

		// 4. 모든 거래자 이름 알파벳 순으로 정렬
		System.out.println(System.lineSeparator() + "4. 모든 거래자 이름 알파벳 순으로 정렬");
		String getSortedTraders = transactions.stream()
			.map(i -> i.getTrader().getName())
			.distinct()
			.sorted()
			.reduce("", (n1, n2) -> n1 + n2+" ");

		System.out.println(getSortedTraders);

		// 5. 밀라노 거래자가 있는가?
		System.out.println(System.lineSeparator() + "5. 밀라노 거래자가 있는가?");
		boolean milanBased = transactions.stream()
			.anyMatch(i -> i.getTrader().getCity().equalsIgnoreCase("Milan"));

		System.out.println("milanBased = " + milanBased);

		// 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션 값 출력
		System.out.println(System.lineSeparator() + "6. 케임브리지에 거주하는 거래자의 모든 트랜잭션 값 출력");
		transactions.stream()
			.filter(i -> i.getTrader().getCity().equalsIgnoreCase("Cambridge"))
			.map(Transaction::getValue)
			.forEach(System.out::println);

		// 7. 전체 트랙잭션 중 최댓값
		System.out.println(System.lineSeparator() + "7. 전체 트랙잭션 중 최댓값");
		Optional<Transaction> max = transactions.stream()
			.max(comparing(Transaction::getValue));

		System.out.println(max);

		// 8. 전체 트랙잭션 중 최솟값
		System.out.println(System.lineSeparator() + "8. 전체 트랙잭션 중 최솟값");
		Optional<Transaction> min = transactions.stream()
			.min(comparing(Transaction::getValue));

		System.out.println(min);
	}
}

class Trader {
	private final String name;
	private final String city;

	public Trader(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "Trader:" + name + " in " + city;
	}
}

class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;

	public Transaction(Trader trader, int year, int amount) {
		this.trader = trader;
		this.year = year;
		this.value = amount;
	}

	public Trader getTrader() {
		return trader;
	}

	public int getYear() {
		return year;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "{trader=" + trader +
			", year=" + year +
			", amount=" + value + "}";
	}
}