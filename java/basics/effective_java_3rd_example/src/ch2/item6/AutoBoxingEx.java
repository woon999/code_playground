package ch2.item6;

public class AutoBoxingEx {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		sum();
		long end = System.currentTimeMillis();

		System.out.println("걸린 시간 : " + (end-start));
		// Long : 5391
		// long : 861
		// 약 6.2배 차이남.

	}

	private static long sum(){
		long sum = 0L; // Long, long
		for(long i=0; i<=Integer.MAX_VALUE; i++){
			sum += i;
		}
		return sum;
	}
}
