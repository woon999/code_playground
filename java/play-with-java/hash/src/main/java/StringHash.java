public class StringHash {

	public static void main(String[] args) {
		String password = "password1234";

		int hash = stringHash(password, 234);
		System.out.println("hash = " + hash);
	}

	public static int stringHash(String str, int modulas){
		int kMult = 997;
		int val = 0;
		for(int i=0; i<str.length(); i++){
			char c = str.charAt(i);
			val = (val * kMult + c) % modulas;
		}

		return val;
	}

}
