package jvm.internal2;

public class JvmInternal2_1 {
	public void operandStack() {
		int a, b, c;
		a = 5;
		b = 6;
		try {
			c = a + b;
		} catch (NullPointerException ex) {
			c = 0;
		}

	}
}

// public void operandStack();
// Code:
// 	0: iconst_5
// 	1: istore_1
// 	2: bipush        6
// 	4: istore_2
// 	5: iload_1
// 	6: iload_2
// 	7: iadd
// 	8: istore_3
// 	9: goto          16
// 	12: astore        4
// 	14: iconst_0
// 	15: istore_3
// 	16: return
// 	Exception table:
// 	from    to  target type
// 	5     9    12   Class java/lang/NullPointerException


