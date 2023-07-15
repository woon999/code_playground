package ch13;

interface A{
	default Number getNumber(){
		return 10;
	}
}

interface B{
	default Number getNumber(){
		return 42;
	}
}

public class SameSignature implements A, B{
	private static int type;
	public static void main(String[] args) {
		type = 1;
		System.out.println(new SameSignature().getNumber());

		type = 2;
		System.out.println(new SameSignature().getNumber());
	}

	@Override
	public Number getNumber() {
		if(type == 1){
			return A.super.getNumber();
		}else {
			return B.super.getNumber();
		}

	}
}
