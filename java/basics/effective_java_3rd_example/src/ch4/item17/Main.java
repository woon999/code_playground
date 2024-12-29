package ch4.item17;

import java.math.BigInteger;
import java.util.BitSet;

public class Main {
	public static final Complex ZERO = new Complex(0,0);
	public static final Complex ONE = new Complex(1,0);
	public static final Complex I = new Complex(0,1);

	public static final Complex2 ONE_v2 = Complex2.valueOf(1,0);

	public static void main(String[] args) {
		// Complex
		System.out.println("ZERO : " + ZERO);
		System.out.println("ONE : " + ONE);
		System.out.println("I : " + I);

		Complex complex = new Complex(1,2);
		System.out.println(complex);

		Complex complex2 = new Complex(1,-2);

		complex = complex.times(complex2);
		System.out.println(complex);

		System.out.println("--------------------------");
		// Complex 정적 팩토리 메서드
		Complex2 c = Complex2.valueOf(1, 0);
		System.out.println("#" +c.equals(ONE_v2));

		System.out.println("--------------------------");
		// BigInger, BitSet
		BigInteger num1 = new BigInteger("10000000");
		BigInteger num2 = new BigInteger("-10000000");
		System.out.println(num1 +",  " + num2);
		System.out.println(num1.add(num2));
		System.out.println(num1.subtract(num2));

		BigInteger negateNum1 = num1.negate();
		System.out.println(num1 + " negate() : "+ negateNum1);

		BigInteger moby = new BigInteger("1000000");
		System.out.println(moby);
		System.out.println(moby.flipBit(0));

		BitSet bit = new BitSet();
		bit.set(2);
		System.out.println(bit);
		bit.flip(2);
		System.out.println(bit.isEmpty());

		BigInteger bi1 = new BigInteger("10000000000");
		BigInteger safeBi1 = safeInstance(bi1);
		System.out.println(bi1 +" , " + safeBi1);
		bi1 = bi1.add(new BigInteger("1"));
		System.out.println(bi1 +" , " + safeBi1);
	}

	public static BigInteger safeInstance(BigInteger val){
		return val.getClass() == BigInteger.class ?
			val : new BigInteger(val.toByteArray());
	}
}
