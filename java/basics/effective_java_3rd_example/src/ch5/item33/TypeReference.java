package ch5.item33;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class TypeReference<T>{
	private final Type type;
	private volatile Constructor<?> constructor;

	protected TypeReference() {
		Type superclass = getClass().getGenericSuperclass();
		if (superclass instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		this.type = ((ParameterizedType) superclass).getActualTypeArguments()[0];
	}

	/**
	 * Instantiates a new instance of {@code T} using the default, no-arg
	 * constructor.
	 */
	@SuppressWarnings("unchecked")
	public T newInstance()
		throws NoSuchMethodException, IllegalAccessException,
		InvocationTargetException, InstantiationException {
		if (constructor == null) {
			Class<?> rawType = type instanceof Class<?>
				? (Class<?>) type
				: (Class<?>) ((ParameterizedType) type).getRawType();
			constructor = rawType.getConstructor();
		}
		return (T) constructor.newInstance();
	}

	/**
	 * Gets the referenced type.
	 */
	public Type getType() {
		return this.type;
	}

	public static void main(String[] args) throws Exception {
		List<String> l1 = new TypeReference<ArrayList<String>>() {}.newInstance();
		List l2 = new TypeReference<ArrayList>() {}.newInstance();
		l1.add("a");
		// l1.add(1);
		l1.stream().forEach(s -> System.out.println(s));

		l2.add("b");
		l2.add(1);
		l2.stream().forEach(s -> System.out.println(s));
	}

}
