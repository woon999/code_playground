package ch3.item13;

public final class PhoneNumber implements Cloneable {
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

	@Override public String toString() {
		return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
	}

	// 가변 상태를 참조하지 않는 클래스용 clone 메서드
	@Override public PhoneNumber clone() {
		try{
			return (PhoneNumber) super.clone();
		} catch (CloneNotSupportedException e){
			throw new AssertionError(); // 일어날 수 없는 일이다.
		}
	}

	public static void main(String[] args) {
		PhoneNumber phoneNumber = new PhoneNumber((short)123, (short)456, (short)789);
		PhoneNumber clone = phoneNumber.clone();
		System.out.println(phoneNumber.getClass().hashCode());
		System.out.println(clone.getClass().hashCode());
	}

}
