package ch3.item11;

import java.util.HashMap;
import java.util.Map;

public final class PhoneNumber {
	private final short areaCode, prefix, lineNum;

	public PhoneNumber(short areaCode, short prefix, short lineNum) {
		this.areaCode = rangeCheck(areaCode, 999, "지역코드");
		this.prefix = rangeCheck(prefix, 999, "프리픽스");
		this.lineNum = rangeCheck(lineNum, 999, "가입자 번호");
	}

	private static short rangeCheck(int val, int max, String arg){
		if(val < 0 || val > max){
			throw new IllegalArgumentException(arg + " : " + val);
		}
		return (short)val;
	}

	// 전형적인 equals 메서드 예
	@Override public boolean equals(Object o) {
		if(o == this){
			return true;
		}
		if(!(o instanceof PhoneNumber)){
			return false;
		}
		PhoneNumber pn = (PhoneNumber)o;
		return pn.lineNum == lineNum && pn.prefix == prefix
					&& pn.areaCode == areaCode;
	}

	@Override
	public String toString() {
		return "PhoneNumber{" +
			"areaCode=" + areaCode +
			", prefix=" + prefix +
			", lineNum=" + lineNum +
			'}';
	}

	// 최악의 (하지만 적법한) hashCode 구현 - 사용 금지!
	// @Override public int hashCode() {
	// 	return 42;
	// }

	// 전형적인 hashCode 메서드
	// @Override public int hashCode() {
	// 	int result = Short.hashCode(areaCode);
	// 	result = 31 * result + Short.hashCode(prefix);
	// 	result = 31 * result + Short.hashCode(lineNum);
	// 	return result;
	// }

	// 한 줄짜리 hashCode 메서드 - 성능이 살짝 아쉽다
	// @Override public int hashCode() {
	// 	return Objects.hash(areaCode, prefix, lineNum);
	// }

	// 해시코드를 지연 초기화하는 hashCode 메서드 - 스레드 안전성까지 고려해야 한다.
	private int hashCode;
	@Override public int hashCode() {
		int result = hashCode;
		if(result == 0){
			result = Short.hashCode(areaCode);
			result = 31 * result + Short.hashCode(prefix);
			result = 31 * result + Short.hashCode(lineNum);
			hashCode = result;
		}
		return result;
	}

	public static void main(String[] args) {
		Map<PhoneNumber, String> m = new HashMap<>();
		m.put(new PhoneNumber((short)123,(short)456,(short)789), "제니");

		System.out.println(m.get(new PhoneNumber((short)123,(short)456,(short)789)));
	}

}
