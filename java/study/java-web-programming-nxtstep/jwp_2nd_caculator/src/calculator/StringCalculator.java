package calculator;

import static calculator.Constant.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class StringCalculator {
	private List<String> regexList;
	private List<String> numbers;

	StringCalculator(){
		numbers = new ArrayList<>();
		regexList = new ArrayList<>();
		regexList.add(COMMA);
		regexList.add(COLON);
	}

	int add(String text){
		text = getCustomPattern(text);
		String regex = String.join(DIVISION, regexList);

		numbers = Stream.of(text.split(regex))
			.map(i -> isZeroOrNum(i)).collect(toList());

		return numbers.stream().mapToInt(i -> toInt(i)).sum();
	}

	private String getCustomPattern(String text) {
		Matcher m = CUSTOM_REGEX_PATTERN.matcher(text);
		if(m.find()){
			regexList.add(m.group(1));
			text = m.group(2);
		}
		return text;
	}

	private String isZeroOrNum(String value){
		if(value.isEmpty() || value == null){
			return ZERO;
		}
		return value;
	}

	private int toInt(String value){
		int num = Integer.parseInt(value);
		if(num < 0) {
			throw new RuntimeException();
		}
		return num;
	}

}
