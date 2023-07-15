package jvm.internal2;

public class JvmInternal2 {
	public void operandStack() {
		int a, b, c;
		a = 5;
		b = 6;
		c = a + b;
	}
}

// 	Compiled from "JvmInternal2.java"
// public class jvm.internal2.JvmInternal2 {
// public jvm.internal2.JvmInternal2();
// 	Code:
// 	0: aload_0
// 	1: invokespecial #1                  // Method java/lang/Object."<init>":()V
// 	4: return
//
// public void operandStack();
// 	Code:
// 	0: iconst_5
// 	1: istore_1
// 	2: bipush        6
// 	4: istore_2
// 	5: iload_1
// 	6: iload_2
// 	7: iadd
// 	8: istore_3
// 	9: return
// 	}
