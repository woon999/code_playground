package ch3.item12;

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

	/** 포맷 명시한 경우
	 * 이 전화번호의 문자열 표현을 반환한다.
	 * 이 문자열은 "XXX-YYY-ZZZZ" 형태의 12글자로 구성된다.
	 * XXX는 지역 코드, YYY는 프리픽스, ZZZZ는 가입자 번호다.
	 * 각각의 대문자는 10진수 숫자 하나를 나타낸다.
	 *
	 * 전화번호의 각 부분의 값이 너무 작아서 자릿수를 채울 수 없다면,
	 * 앞에서부터 0으로 채워나간다. 예컨대 가입자 번호가 123이라면
	 * 전화번호의 마지막 네 문자는 "0123"이 된다.
	 */
	@Override public String toString() {
		return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
	}

	/** 포맷 명시하지 않은 경우
	 * 이 약물에 관한 대략적인 설명을 반환한다.
	 * 다음은 이 설명의 일반적인 형태이나,
	 * 상세 형식은 정해지지 않았으며 향후 변경될 수 있다.
	 *
	 * "[약물 #9: 유형=사랑, 냄새=테레빈유, 겉모습=먹물]"
	 */
	// @Override public String toString() {
		// ...
	// }

	public static void main(String[] args) {
		PhoneNumber phoneNumber = new PhoneNumber((short)123, (short)456, (short)789);
		System.out.println(phoneNumber + "에 연결할 수 없습니다.");
	}

}
