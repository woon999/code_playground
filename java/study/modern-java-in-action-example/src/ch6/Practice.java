package ch6;

import static ch6.ingredient.CaloricLevel.*;
import static ch6.ingredient.Currency.*;
import static ch6.ingredient.Type.*;
import static java.util.Arrays.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch6.ingredient.CaloricLevel;
import ch6.ingredient.Currency;
import ch6.ingredient.Dish;
import ch6.ingredient.Trader;
import ch6.ingredient.Transaction;
import ch6.ingredient.Type;

class Practice {

	List<Dish> menu;
	List<Transaction> transactions;
	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario", "Milan");
	Trader alan = new Trader("Alan", "Cambridge");
	Trader brian = new Trader("Brian", "Cambridge");

	@BeforeEach
	public void setup(){
		menu = asList(
			new Dish("seasonal fruit", true, 120, OTHER),
			new Dish("prawns", false, 300, FISH),
			new Dish("rice", true, 350, OTHER),
			new Dish("chicken", false, 400, MEAT),
			new Dish("french fries", true, 530, OTHER)
		);

		transactions = asList(
			new Transaction(brian, 2011, 300, CURRENCY_WON),
			new Transaction(raoul, 2012, 1000, CURRENCY_DOL),
			new Transaction(raoul, 2011, 400, CURRENCY_DOL),
			new Transaction(mario, 2012, 710, CURRENCY_DOL),
			new Transaction(mario, 2012, 700, CURRENCY_EURO),
			new Transaction(alan, 2012, 950, CURRENCY_EURO)
		);
	}

	@Test
	void 명령형과_선언형_비교(){
		// 통화별로 트랜잭션을 그룹화한 코드 (명령형)
		Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
		for(Transaction transaction : transactions){
			Currency currency = transaction.getCurrency();
			List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);

			if(transactionsForCurrency == null){
				transactionsForCurrency = new ArrayList<>();
				transactionsByCurrencies.put(currency, transactionsForCurrency);
			}
			transactionsForCurrency.add(transaction);
		}

		// 선언형
		Map<Currency, List<Transaction>> transactionsByCurrencies2 = transactions.stream().collect(groupingBy(Transaction::getCurrency));

		assertEquals(transactionsByCurrencies, transactionsByCurrencies2);
	}


	@Test
	void dish_count와max(){
		// count
		long howManyDishes = menu.stream().count();
		System.out.println("howManyDishes = " + howManyDishes);
		assertEquals(howManyDishes, 5);

		// 최대 칼로리
		Comparator<Dish> dishCaloriesComp = comparingInt(Dish::getCalories);
		Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComp));
		System.out.println("mostCalorieDish = " + mostCalorieDish.get().getMenuName());
		assertEquals(mostCalorieDish.get().getMenuName(), "french fries");
	}

	@Test
	void dish_요약연산(){
		// 전체 칼로리
		int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
		System.out.println("totalCalories = " + totalCalories);
		assertEquals(totalCalories, 1700);

		// 칼로리 평균 
		double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
		System.out.println("avgCalories = " + avgCalories);
		assertEquals(avgCalories, 340);

		// 통계
		IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println("menuStatistics = " + menuStatistics);
		assertEquals(menuStatistics.getAverage(), avgCalories);
		assertEquals(menuStatistics.getSum(), totalCalories);
	}
	
	@Test
	void 문자열연결(){
		String shortMenu = menu.stream().map(Dish::getMenuName).collect(joining(", "));
		System.out.println("shortMenu = " + shortMenu);
		assertEquals(menu.stream().allMatch(s -> shortMenu.contains(s.getMenuName())), true);
	}

	@Test
	void 범용_리듀싱_요약_연산(){
		/**
		 *  첫 번째 인수 : 시작값이이거나 스트림에 인수가 없을 때는 반환값
		 *  두 번째 인수 : 변환 함수
		 *  세 번째 인수 : BinaryOperator
		 */
		int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i+j));
		System.out.println("totalCalories = " + totalCalories);
		assertEquals(totalCalories, 1700);

		// 최대 칼로리
		Optional<Dish> mostCalorieDish = menu.stream().collect(reducing(
			(d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)
		);
		System.out.println("mostCalorieDish = " + mostCalorieDish.get().getMenuName());
		assertEquals(mostCalorieDish.get().getMenuName(), "french fries");
	}

	@Test
	void 컬렉션_프레임워크_유연성(){
		// 컬렉터 리듀싱
		int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
		System.out.println("totalCalories = " + totalCalories);
		assertEquals(totalCalories, 1700);

		// map으로 변환 후 리듀싱
		totalCalories = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
		System.out.println("totalCalories = " + totalCalories);
		assertEquals(totalCalories, 1700);

		// IntStream으로 매핑 후 sum 메서드 호출
		totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
		System.out.println("totalCalories = " + totalCalories);
		assertEquals(totalCalories, 1700);
	}

	@Test
	void 그룹화(){
		System.out.println("-------dishesByType-------");
		Map<Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println("dishesByType = " + dishesByType);

		System.out.println("-------dishesByCaloricLevel-------");
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
			groupingBy(dish -> {
				if(dish.getCalories() <= 400) {
					return DIET;
				} else if(dish.getCalories() <= 700) {
					return NORMAL;
				} else return FAT;
			})
		);
		System.out.println("dishesByCaloricLevel = " + dishesByCaloricLevel);
	}

	@Test
	void 그룹화된_요소_조작(){
		// 문제: 해당되지 않은 키가 사라짐
		Map<Type, List<Dish>> caloricDishesByType = menu.stream().filter(dish -> dish.getCalories() > 500)
			.collect(groupingBy(Dish::getType));
		System.out.println("caloricDishesByType = " + caloricDishesByType);

		System.out.println("--------------------------");
		// groupingBy 팩토리 메서드를 오버로딩해 해결할 수 있음
		caloricDishesByType = menu.stream()
										.collect(
											groupingBy(Dish::getType,
											filtering(dish -> dish.getCalories() > 500, toList())
										)
									);
		System.out.println("caloricDishesByType = " + caloricDishesByType);

		System.out.println("--------------------------");
		// 맵핑 함수를 이용해 요소를 변환할 수 있음 (Type 별로 요리 이름(menuName)을 저장함)
		Map<Type, List<String>> dishNamesByType = menu.stream()
			.collect(groupingBy(Dish::getType, mapping(Dish::getMenuName, toList())));
		System.out.println("dishNamesByType = " + dishNamesByType);

		System.out.println("--------------------------");
		// flatMapping 컬렉터를 이용하면 각 형식의 요리의 태그를 간편하게 추출할 수 있음
		// Stream<Stream<String>> -(flatMapping)-> Stream<String> -(toSet())-> Set<String>
		Map<String, List<String>> dishTags = initDishTags();
		Map<Type, Set<String>> dishNamesByType2 = menu.stream()
			.collect(groupingBy(Dish::getType,
				flatMapping(dish -> dishTags.get(dish.getMenuName()).stream(), toSet())
			));
		System.out.println("dishNamesByType2 = " + dishNamesByType2);
	}

	private static Map<String, List<String>> initDishTags(){
		Map<String, List<String>> dishTags = new HashMap<>();
		dishTags.put("seasonal fruit", asList("fresh", "natural"));
		dishTags.put("prawns", asList("tasty", "roasted"));
		dishTags.put("chicken", asList("fried", "crisp"));
		dishTags.put("french fries", asList("greasy", "fried"));
		dishTags.put("rice", asList("light", "natural"));
		return dishTags;
	}

	@Test
	void 다수준_그룹화(){
		// Type -> (CaloricLevel -> Dish) 두 수준으로 그룹화
		Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
			groupingBy(Dish::getType,
				groupingBy(dish -> {
					if(dish.getCalories() <= 400){
						return DIET;
					}else if(dish.getCalories() <= 700){
						return NORMAL;
					}else return FAT;
				})
			)
		);
		System.out.println("dishesByTypeCaloricLevel = " + dishesByTypeCaloricLevel);
	}

	@Test
	void 서브그룹으로_데이터_수집(){
		// 진짜 쿼리네
		Map<Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
		System.out.println("typesCount = " + typesCount);

		System.out.println("--------------------------");
		// 요리의 종류를 분류하는 컬렉터로 가장 높은 칼로리 가진 요리 찾기
		Map<Type, Optional<Dish>> mostCaloricByType = menu.stream()
			.collect(groupingBy(Dish::getType,
				maxBy(comparingInt(Dish::getCalories))));
		System.out.println("mostCaloricByType = " + mostCaloricByType);

		System.out.println("--------------------------");
		// 컬렉터 결과를 다른 형식에 적용하기 (Optional 삭제)
		Map<Type, Dish> mostCaloricByType2 = menu.stream()
			.collect(
				groupingBy(Dish::getType,
						collectingAndThen(
							maxBy(comparingInt(Dish::getCalories)),
							Optional::get
						)
				));
		System.out.println("mostCaloricByType2 = " + mostCaloricByType2);

	}

	@Test
	void 분할(){
		Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
		System.out.println("partitionedMenu = " + partitionedMenu);

		System.out.println("--------------------------");
		// 참, 거짓의 스트림 요소를 모두 유지하는 것이 분할의 장점
		Map<Boolean, Map<Type, List<Dish>>> vegetarianDishesByType = menu.stream()
			.collect(
				partitioningBy(Dish::isVegetarian, // <- 분할 함수
					groupingBy(Dish::getType))); // <- 두번째 컬렉터
		System.out.println("vegetarianDishesByType = " + vegetarianDishesByType);
	}

	@Test
	void 소수와비소수_분할(){
		Map<Boolean, List<Integer>> primes = partitionPrimes(100);
		System.out.println("primes = " + primes.get(true));
	}

	public Map<Boolean, List<Integer>> partitionPrimes(int n){
		return IntStream.rangeClosed(2, n).boxed()
			.collect(
				partitioningBy(candidate -> isPrime(candidate)));
	}

	public boolean isPrime(int candidate){
		int candidateRoot = (int)Math.sqrt((double)candidate);
		return IntStream.range(2, candidateRoot)
			.noneMatch(i -> candidate%i ==0);
	}

	@Test
	void 커스텀컬렉터로만든_소수비소수_분할(){
		Map<Boolean, List<Integer>> primes = partitionPrimesWithCustomCollector(100);
		System.out.println("primes = " + primes.get(true));
	}
	public Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n){
		return IntStream.rangeClosed(2, n).boxed()
			.collect(new PrimeNumbersCollector());
	}


}
