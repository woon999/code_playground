package ch4.item24.anonymous;

enum Type{
	PLUS, MINUS;
}
interface Operator{
	int plus();
	int minus();
}

public class Calculator {
	private int num1;
	private int num2;

	public Calculator(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}

	public int operate(Type type){
		Operator operator = new Operator() {
			@Override public int plus() {
				return num1 + num2;
			}
			@Override public int minus() {
				return num1 - num2;
			}
		};

		switch (type){
			case PLUS : return operator.plus();
			case MINUS : return operator.minus();
			default: throw new AssertionError(type);
		}
	}

	public static void main(String[] args) {
		Calculator calculator = new Calculator(5, 2);
		int plus = calculator.operate(Type.PLUS);
		System.out.println("plus = " + plus);
		int minus = calculator.operate(Type.MINUS);
		System.out.println("minus = " + minus);
	}
}
