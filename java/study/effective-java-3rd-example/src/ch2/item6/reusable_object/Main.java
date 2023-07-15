package ch2.item6.reusable_object;

public class Main {
	static String[] romans = { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
	public static void main(String[] args) {

		System.out.println("## isRomanNumeral 1");
		for(int i=0; i<10; i++){
			Roman.isRomanNumeral(romans[i]);
		}

		System.out.println("## isRomanNumeral 2");
		for(int i=0; i<10; i++){
			Roman.isRomanNumeral2(romans[i]);
		}
	}
}
