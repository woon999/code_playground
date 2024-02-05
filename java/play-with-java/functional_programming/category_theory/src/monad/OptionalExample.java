package monad;

import java.util.Optional;
import java.util.function.Function;

public class OptionalExample {
	public Optional<Integer> leftIdentity() {
		// M<T>
		Optional<Integer> original = Optional.of(3);
		// M<T> -(unit)-> M<M<T>>
		Optional<Optional<Integer>> wrapped = original.map(value -> Optional.of(value + 1));

		// M<M<T>> -(flat)-> M<T>
		Optional<Integer> flattened = wrapped.flatMap(Function.identity());
		return flattened;
	}

	public Optional<Integer> rightIdentity() {
		Function<Integer, Optional<Integer>> mapping = value -> Optional.of(value + 1);
		// M<T>
		Optional<Integer> original = Optional.of(3);
		// M<T> -lift(unit)-> M<M<T>>, use mapping
		Optional<Optional<Integer>> wrapped = Optional.of(mapping.apply(original.get()));
		// M<M<T>> -flat-> M<T>
		Optional<Integer> flattened = wrapped.flatMap(Function.identity());
		return flattened;
	}

	public Optional<Integer> associativity1(Optional<Optional<Optional<Integer>>> optOptOptInt) {
		// M<M<M<T>>> -lift(flat)-> M<M<T>> -(flat)-> M<T>
		return optOptOptInt.flatMap(Function.identity()).flatMap(Function.identity());
	}

	public Optional<Integer> associativity2(Optional<Optional<Optional<Integer>>> optOptOptInt) {
		// M<M<M<T>>> -(flat)-> M<M<T>> -(flat)-> M<T>
		return optOptOptInt.flatMap(optOptInt -> optOptInt.flatMap(optInt -> optInt));
	}

	public static void main(String[] args) {
		OptionalExample example = new OptionalExample();
		Optional<Integer> left = example.leftIdentity();
		Optional<Integer> right = example.rightIdentity();
		System.out.println("Left identity: " + left);
		System.out.println("Right identity: " + right);
		System.out.println("Left == Right:" + left.equals(right)+ "\n");

		Optional<Optional<Optional<Integer>>> optOptOptInt = Optional.of(Optional.of(Optional.of(42)));
		Optional<Integer> firstPathResult = example.associativity1(optOptOptInt);
		Optional<Integer> secondPathResult = example.associativity2(optOptOptInt);
		System.out.println("First path result: " + firstPathResult);
		System.out.println("Second path result: " + secondPathResult);
		System.out.println("Associativity holds: " + firstPathResult.equals(secondPathResult));
	}
}
