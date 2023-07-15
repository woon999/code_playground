package ch3.item10;

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

	public static void main(String[] args) {
		short s1 = 123;
		short s2 = 445;
		short s3 = 234;
		PhoneNumber pn = new PhoneNumber(s1, s2, s3);
		System.out.println(pn);
		System.out.println(pn.equals(new PhoneNumber((short)123, (short)445, (short)234)));
	}

}
