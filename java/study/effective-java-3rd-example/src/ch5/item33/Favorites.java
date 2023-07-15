package ch5.item33;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Favorites {
	private Map<Class<?>, Object> favorites = new HashMap<>();
	public <T> void putFavorite(Class<T> type, T instance){
		favorites.put(Objects.requireNonNull(type), type.cast(instance));
	}


	public <T> T getFavorite(Class<T> type){
		return type.cast(favorites.get(type));
	}

	public static void main(String[] args) {
		Favorites f = new Favorites();
		f.putFavorite(String.class, "Java");
		f.putFavorite(Integer.class, 0xcafebabe);
		f.putFavorite(Class.class, Favorites.class);

		String s = f.getFavorite(String.class);
		int i = f.getFavorite(Integer.class);
		Class<?> c = f.getFavorite(Class.class);

		System.out.printf("%s %x %s%n", s, i, c.getName());

		f.putFavorite(String[].class, new String[]{"a","b"});
		String[] sArr = f.getFavorite(String[].class);
		Stream.of(sArr).forEach(a -> System.out.println(a));

		f.putFavorite(List.class, List.of("1a","2b","3c"));
		List favorite = f.getFavorite(List.class);
		favorite.stream().forEach(a -> System.out.println(a));

	}



}
