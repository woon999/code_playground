package ch7;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

import ch7.forkjoin.ForkJoinSumCalculator;
import ch7.spliterator.WordCounter;
import ch7.spliterator.WordCounterSpliterator;

public class Practice {

	private Stream<Character> stream;
	// private String line = "Inflation and the Fed continue to be the theme next week, but I do think we’re looking forward to have some earnings results to sink our teeth into";
	private String line = "a b c d e f g h i j k l "; // 24

	@Test
	void forkJoinSum(){
		long start = System.currentTimeMillis();
		ForkJoinSumCalculator.forkJoinSum(100_000_000L);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	@Test
	void 단어수_세기(){
		String[] word = line.split(" ");

		stream = line.chars().mapToObj(c -> (char)c);
		System.out.println("word = " + word.length);

		assertEquals(countWords(stream), word.length);
	}

	@Test
	void 단어수_세기_spliterator(){
		Spliterator<Character> spliterator = new WordCounterSpliterator(line);
		stream = StreamSupport.stream(spliterator, true);

		String[] word = line.split(" ");
		System.out.println("word = " + word.length);

		assertEquals(countWords(stream), word.length);
	}

	private int countWords(Stream<Character> stream){
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true),
			WordCounter::accumulate, WordCounter::combine);

		return wordCounter.getCounter();
	}
}

