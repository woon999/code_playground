package ch2.item6.reusable_object;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Roman {
	private static final String REGEX = "^(?=.)M*(C[MD]|D?C{0,3})"
		+ "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";
	private static final Pattern ROMAN = Pattern.compile(REGEX);
	private static Pattern romanInstance;

	static boolean isRomanNumeral(String s){
		// return s.matches()
		Pattern p = Pattern.compile(REGEX);
		System.out.println("# new Pattern Instance : " + p.hashCode());
		Matcher m = p.matcher(s);
		return m.matches();
	}

	// 값비싼 객체 재사용하기
	static boolean isRomanNumeral2(String s){
		System.out.println("# ROMAN : "+ROMAN.hashCode());
		return ROMAN.matcher(s).matches();
	}

	// 객체 지연 초기화
	static boolean isRomanNumeral3(String s){
		if(romanInstance == null){
			romanInstance = Pattern.compile(REGEX);
		}
		return romanInstance.matcher(s).matches();
	}
}
