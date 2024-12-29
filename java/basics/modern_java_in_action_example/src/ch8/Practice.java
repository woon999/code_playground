package ch8;

import static ch8.Currency.*;
import static java.util.Arrays.*;
import static java.util.Map.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Practice {

	List<Transaction> transactions = new ArrayList<>();
	Trader raoul = new Trader("Raoul", "Cambridge");
	Trader mario = new Trader("Mario", "Milan");
	Trader alan = new Trader("Alan", "Cambridge");
	Trader brian = new Trader("Brian", "Cambridge");

	@BeforeEach
	public void setup() {
		transactions.add(new Transaction(brian, 2011, 300, "0AB231", CURRENCY_WON));
		transactions.add(new Transaction(raoul, 2012, 1000, "BA8C12", CURRENCY_DOL));
		transactions.add(new Transaction(raoul, 2011, 400, "K1T361", CURRENCY_DOL));
		transactions.add(new Transaction(mario, 2012, 710, "Z0S361", CURRENCY_DOL));
		transactions.add(new Transaction(mario, 2012, 700, "1B5A23", CURRENCY_EURO));
		transactions.add(new Transaction(alan, 2012, 950, "P19QT1", CURRENCY_EURO));

	}

	/**
	 * 컬렉션 팩토리
	 */
	@Test
	void 리스트팩토리() {
		List<String> alphabet = List.of("A", "B", "C");
		System.out.println(alphabet);

		Assertions.assertThrows(UnsupportedOperationException.class, () -> alphabet.add("D")); //변경 불가
	}

	@Test
	void 집합팩토리() {
		Set<String> alphabet = Set.of("A", "B", "C");
		System.out.println(alphabet);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Set<String> set = Set.of("A", "A");
		}); // 중복 불가
	}

	@Test
	void 맵팩토리() {
		Map<String, Integer> numOfAlpha = of("A", 1, "B", 2, "C", 3);
		System.out.println(numOfAlpha);

		// 10개 이상의 맵을 만들 때는 Map.Entry<K,V> 객체를 인수로 받으며 가변 인수로 구현된 Map.ofEntries 팩토리 메서드를 이용하는 것이 좋다.
		// 오버로딩과 가변인수의 차이 (E e1, E e2, ..) vs (E... elements)
		Map<String, Integer> alphas = ofEntries(
			entry("A", 1), entry("B", 2), entry("C", 3),
			entry("D", 4), entry("E", 5), entry("F", 6),
			entry("G", 7), entry("H", 8), entry("I", 9),
			entry("J", 10), entry("K", 11), entry("L", 12)
		);
		System.out.println(alphas);
	}

	/**
	 * 리스트와 집합 처리
	 */
	@Test
	void removeIf_기존버전() {
		int size = transactions.size();
		int count = 0;
		for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext(); ) {
			Transaction transaction = iterator.next();
			if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
				iterator.remove();
				count++;
			}
		}
		transactions.forEach(t -> System.out.println(t.getReferenceCode()));
		assertEquals(size - count, transactions.size());
	}

	@Test
	void removeIf메서드() {
		transactions.removeIf(transaction -> Character.isDigit(transaction.getReferenceCode().charAt(0)));
		transactions.forEach(t -> System.out.println(t.getReferenceCode()));
		assertEquals(4, transactions.size());
	}

	@Test
	void replaceAll메서드() {
		// 스트림 API
		List<String> referencesCode = List.of("a12", "C14", "b13");
		referencesCode.stream().map(code -> Character.toUpperCase(code.charAt(0)) +
			code.substring(1))
			.collect(Collectors.toList())
			.forEach(System.out::println);

		// replaceAll 메서드
		List<String> referencesCode2 = new ArrayList<>();
		referencesCode2.add("a12");
		referencesCode2.add("C14");
		referencesCode2.add("b13");
		referencesCode2.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));
		referencesCode2.forEach(s -> System.out.println(s));
	}

	/**
	 * 맵 처리
	 */
	@Test
	void forEach메서드() {
		Map<String, Integer> alphas = ofEntries(
			entry("A", 1), entry("B", 2), entry("C", 3),
			entry("D", 4), entry("E", 5), entry("F", 6),
			entry("G", 7), entry("H", 8), entry("I", 9),
			entry("J", 10), entry("K", 11), entry("L", 12)
		);

		alphas.forEach((k, v) -> System.out.println(k + " number is " + v));
	}

	@Test
	void 정렬메서드() {
		Map<String, String> favoriteMovies = ofEntries(
			entry("Z", "Star Wars"), entry("A", "Matrix"), entry("C", "James Bond"));

		favoriteMovies.entrySet().stream().sorted(Entry.comparingByKey())
			.forEachOrdered(System.out::println);
	}

	@Test
	void getOrDefault메서드() {
		Map<String, String> favoriteMovies = ofEntries(
			entry("Z", "Star Wars"), entry("A", "Matrix"), entry("C", "James Bond"));

		assertEquals(favoriteMovies.getOrDefault("A", "NULL"), "Matrix");
		assertEquals(favoriteMovies.getOrDefault("Q", "NULL"), "NULL");
	}

	private MessageDigest messageDigest;

	@Test
	void 계산패턴1() throws NoSuchAlgorithmException {
		Map<String, byte[]> dataToHash = new HashMap<>();
		messageDigest = MessageDigest.getInstance("SHA-256");

		List<String> lines = List.of("chicken", "pizza");
		lines.forEach(line -> dataToHash.computeIfAbsent(line, this::calculateDigest));
		dataToHash.forEach((k, v) -> System.out.println(k + ": " + Arrays.toString(v)));
	}

	private byte[] calculateDigest(String key) {
		return messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));
	}

	@Test
	void 계산패턴_기존버전() {
		Map<String, List<String>> friendsToMovies = new HashMap<>();
		String friend = "A";
		List<String> movies = friendsToMovies.get(friend);
		if (movies == null) {
			movies = new ArrayList<>();
			friendsToMovies.put(friend, movies);
		}
		movies.add("Stars wars");
		friendsToMovies.forEach((k, v) -> System.out.println(k + ", " + v));
	}

	@Test
	void 계산패턴2() {
		Map<String, List<String>> friendsToMovies = new HashMap<>();
		String friend = "A";
		friendsToMovies.computeIfAbsent(friend, name -> new ArrayList<>()).add("Stars Wars");
		friendsToMovies.forEach((k, v) -> System.out.println(k + ", " + v));
	}

	@Test
	void 삭제패턴_기존버전() {
		Map<String, String> favoriteMovies = new HashMap<>();
		favoriteMovies.putIfAbsent("A", "Jack Reacher 2");
		String key = "A";
		String value = "Jack Reacher 2";
		if (favoriteMovies.containsKey(key) && Objects.equals(favoriteMovies.get(key), value)) {
			favoriteMovies.remove(key);
		}

		assertEquals(null, favoriteMovies.get(key));
	}

	@Test
	void 삭제패턴() {
		Map<String, String> favoriteMovies = new HashMap<>();
		favoriteMovies.putIfAbsent("A", "Jack Reacher 2");
		String key = "A";
		String value = "Jack Reacher 2";
		assertEquals(true, favoriteMovies.remove(key, value));
		assertEquals(null, favoriteMovies.get(key));
		assertEquals(false, favoriteMovies.remove(key, value));
	}

	@Test
	void 교체패턴() {
		Map<String, String> favoriteMovies = new HashMap<>();
		favoriteMovies.put("A", "aaa");
		favoriteMovies.put("B", "bbb");
		favoriteMovies.put("C", "ccc");
		favoriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());
		System.out.println(favoriteMovies);

		favoriteMovies.replace("A", "KKK");
		System.out.println(favoriteMovies);
	}

	@Test
	void 합침_putAll() {
		Map<String, String> family = Map.ofEntries(
			entry("A", "aaa"), entry("B", "bbb")
		);
		Map<String, String> friends = Map.ofEntries(
			entry("C", "ccc")
		);

		Map<String, String> everyone = new HashMap<>(family);
		everyone.putAll(friends);
		System.out.println(everyone);
	}

	@Test
	void 합침_merge() {
		Map<String, String> family = Map.ofEntries(
			entry("A", "aaa"), entry("B", "bbb")
		);
		Map<String, String> friends = Map.ofEntries(
			entry("A", "zzz"), entry("C", "ccc")
		);
		Map<String, String> everyone = new HashMap<>(family);
		friends.forEach((k, v) -> everyone.merge(k, v, (movie1, movie2) -> movie1 + " & " + movie2));
		System.out.println(everyone);

		// movieCount
		Map<String, Long> moviesToCount = new HashMap<>();
		String movieName = "bbb";
		moviesToCount.merge(movieName, 1L, (key ,count) -> count+1L);
		assertEquals(1L, moviesToCount.get(movieName));

		// 기존 방법
		long count = moviesToCount.getOrDefault(movieName, 0L);
		if(count == 0L){
			moviesToCount.put(movieName, 1L);
		}else {
			moviesToCount.put(movieName, count+1);
		}
		assertEquals(2L, moviesToCount.get(movieName));
	}

	/**
	 * 개선된 ConcurrentHashMap
	 */
	@Test
	void 리듀스와검색() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		map.put("a" , 312872131231233L); map.put("b" , 367421312312313L); map.put("c" , 312421312312313L);
		map.put("d" , 312421269312313L); map.put("e" , 378221312312313L);
		long parallelismThreshold = 1;
		Optional<Long> maxValue = Optional.ofNullable(map.reduceValues(parallelismThreshold, Long::max));
		System.out.println("maxValue = " + maxValue);
	}

	@Test
	void 계수() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		map.put("a" , 312872131231233L); map.put("b" , 367421312312313L); map.put("c" , 312421312312313L);
		map.put("d" , 312421269312313L); map.put("e" , 378221312312313L);

		assertEquals(5, map.mappingCount());
	}

	@Test
	void 집합뷰() {
		ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
		map.put("a" , 312872131231233L); map.put("b" , 367421312312313L); map.put("c" , 312421312312313L);
		map.put("d" , 312421269312313L); map.put("e" , 378221312312313L);

		ConcurrentHashMap.KeySetView<String, Long> mapKey = map.keySet();
		System.out.println(mapKey);
		mapKey.removeIf((key) -> key.equals("a"));
		System.out.println(map);
	}
}
