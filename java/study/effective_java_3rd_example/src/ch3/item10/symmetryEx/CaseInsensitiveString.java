package ch3.item10.symmetryEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class CaseInsensitiveString {
	private String s;

	public CaseInsensitiveString(String s){
		this.s = Objects.requireNonNull(s);
	}

	// 대칭성(symmetry) 위배
	// @Override public boolean equals(Object o) {
	// 	if(o instanceof CaseInsensitiveString){
	// 		return s.equalsIgnoreCase(
	// 			((CaseInsensitiveString)o).s);
	// 	}
	// 	if(o instanceof String) {// 한 방향으로만 작동한다.
	// 		return s.equalsIgnoreCase((String) o);
	// 	}
	// 	return false;
	// }

	// String과 연동하겠다는 허황한 꿈을 버려라.
	@Override public boolean equals(Object o) {
		return o instanceof CaseInsensitiveString &&
			((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
	}

	public static void main(String[] args) {
		String s1 = "abc";
		CaseInsensitiveString cis = new CaseInsensitiveString("abc");

		System.out.println(cis.equals(s1)); // true
		System.out.println(s1.equals(cis)); // false

		List<CaseInsensitiveString> list = new ArrayList<>();
		list.add(cis);

		System.out.println(list.contains(s1)); // false
	}
}
