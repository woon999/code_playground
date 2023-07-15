package ch3.item14;

public final class CaseInsensitiveString implements Comparable<CaseInsensitiveString>{
	private String s;

	public CaseInsensitiveString(String s) {
		this.s = s;
	}


	@Override
	public int compareTo(CaseInsensitiveString cis) {
		return String.CASE_INSENSITIVE_ORDER.compare(s, cis.s);
	}

	public static void main(String[] args) {
		CaseInsensitiveString a = new CaseInsensitiveString("apple");
		CaseInsensitiveString b = new CaseInsensitiveString("banana");
		CaseInsensitiveString c = new CaseInsensitiveString("choco");

		System.out.println(a.compareTo(b));
		System.out.println(b.compareTo(c));
		System.out.println(a.compareTo(c));
	}
}
