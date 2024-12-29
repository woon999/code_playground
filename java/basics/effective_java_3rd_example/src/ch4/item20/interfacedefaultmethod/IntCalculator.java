package ch4.item20.interfacedefaultmethod;

public class IntCalculator implements Calculator<Integer> {

	@Override
	public Integer plus(Integer i, Integer j) {
		return i+j;
	}

	@Override
	public Integer minus(Integer i, Integer j) {
		return i-j;
	}

	@Override
	public Integer divide(Integer i, Integer j) {
		return i/j;
	}

	@Override
	public Integer multiple(Integer i, Integer j) {
		return i*j;
	}
}
