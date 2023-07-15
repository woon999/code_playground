package jvm.internal3;

// Runtime Data Areas Simulation
public class JvmInternal3 {
	static int cv = 0;
	final static int fcv = 100;

	public static void main(String[] args) {
		int a, b, c;
		a = Integer.parseInt(args[0]);
		b = Integer.parseInt(args[0]);
		c = addTwoArgs(a, b);
	}

	static int addTwoArgs(int x, int y) {
		cv = fcv;
		return x + y;
	}
}

// 	Compiled from "JvmInternal3.java"
// public class jvm.internal3.JvmInternal3 {
// static int cv;
//
// static final int fcv;
//
// public jvm.internal3.JvmInternal3();
// 	Code:
// 	0: aload_0
// 	1: invokespecial #1                  // Method java/lang/Object."<init>":()V
// 	4: return
//
// public static void main(java.lang.String[]);
// 	Code:
// 	0: aload_0
// 	1: iconst_0
// 	2: aaload
// 	3: invokestatic  #2                  // Method java/lang/Integer.parseInt:(Ljava/lang/String;)I
// 	6: istore_1
// 	7: aload_0
// 	8: iconst_0
// 	9: aaload
// 	10: invokestatic  #2                  // Method java/lang/Integer.parseInt:(Ljava/lang/String;)I
// 	13: istore_2
// 	14: iload_1
// 	15: iload_2
// 	16: invokestatic  #3                  // Method addTwoArgs:(II)I
// 	19: istore_3
// 	20: return
//
// static int addTwoArgs(int, int);
// 	Code:
// 	0: bipush        100
// 	2: putstatic     #5                  // Field cv:I
// 	5: iload_0
// 	6: iload_1
// 	7: iadd
// 	8: ireturn
//
// static {};
// 	Code:
// 	0: iconst_0
// 	1: putstatic     #5                  // Field cv:I
// 	4: return
// 	}
