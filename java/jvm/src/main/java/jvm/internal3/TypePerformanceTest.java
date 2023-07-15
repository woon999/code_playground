package jvm.internal3;

// int와 Integer 성능 비교
// primitive time: 1075
// reference time: 2568

public class TypePerformanceTest {
	public static void main(String[] args) {
		primitiveTypeFunc();
		System.out.println();
		referenceTypeFunc();
	}

	static void primitiveTypeFunc() {
		long start = System.currentTimeMillis();

		long result = 0;
		for(int i=0; i<1e9; i++){
			result += i;
		}

		System.out.println(result);
		System.out.println("primitive time: " + (System.currentTimeMillis()-start));
	}

	static void referenceTypeFunc() {
		long start = System.currentTimeMillis();

		long result = 0;
		for(Integer i=0; i<1e9; i++){
			result += i;
		}

		System.out.println(result);
		System.out.println("reference time: " + (System.currentTimeMillis()-start));
	}
}
