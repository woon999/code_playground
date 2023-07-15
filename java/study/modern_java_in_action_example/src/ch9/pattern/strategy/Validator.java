package ch9.pattern.strategy;

// 전략 패턴은 한 유형의 알고리즘을 보유한 상태에서 런타임에 적절한 알고리즘을 선택하는 기법
interface ValidationStrategy{
	boolean execute(String s);
}

class IsAllLowerCase implements ValidationStrategy{
	@Override public boolean execute(String s) {
		return s.matches("[a-z]+");
	}
}

class IsNumeric implements ValidationStrategy{
	@Override public boolean execute(String s) {
		return s.matches("\\d+");
	}
}

public class Validator {
	private final ValidationStrategy strategy;

	public Validator(ValidationStrategy strategy) {
		this.strategy = strategy;
	}

	public boolean validate(String s){
		return strategy.execute(s);
	}

	public static void main(String[] args) {
		Validator numericValidator = new Validator(new IsNumeric());
		System.out.println("numericValidator = " + numericValidator.validate("aaaa")); // false
		Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
		System.out.println("lowerCaseValidator = " + lowerCaseValidator.validate("bbbbb")); // true
	}
}
