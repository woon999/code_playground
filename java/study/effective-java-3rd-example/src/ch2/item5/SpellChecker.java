package ch2.item5;

import java.util.Objects;
import java.util.regex.Pattern;

public class SpellChecker {
	private final Lexicon dictionary;

	public SpellChecker(Lexicon dictionary) {
		this.dictionary = Objects.requireNonNull(dictionary);
	}

	public boolean isValid(String word){
		return Pattern.matches("^[a-zA-Z]*$",word);
	}
}