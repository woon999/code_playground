package ch4.item24.staticmember;

@FunctionalInterface
interface Operator{
	int operate(int x, int y);
}

public class Calculator {
	static enum Operation{
		PLUS((x,y) -> (x+y)),
		MINUS((x,y) -> (x-y));

		private final Operator operator;

		private Operation(Operator operator) {
			this.operator = operator;
		}

		int operate(int x, int y){
			return this.operator.operate(x,y);
		}
	}

	public static void main(String[] args) {
		int operate = Operation.PLUS.operate(1, 2);
		System.out.println("operate = " + operate);

		int operate2 = Operation.MINUS.operate(1, 2);
		System.out.println("operate2 = " + operate2);
	}
}
