package ch4.item17;

import java.util.Objects;

// 생성자 대신 정적 팩터리를 사용한 불변 클래스
public class Complex2 {
	private final double re;
	private final double im;

	private Complex2(double re, double im) {
		this.re = re;
		this.im = im;
	}

	public static final Complex2 valueOf(double re, double im){
		return new Complex2(re, im);
	}

	public double realPart() {
		return re;
	}

	public double imaginaryPart() {
		return im;
	}

	public Complex2 plus(Complex2 c) {
		return new Complex2(re + c.re, im + c.im);
	}

	public Complex2 minus(Complex2 c) {
		return new Complex2(re - c.re, im - c.im);
	}

	public Complex2 times(Complex2 c) {
		return new Complex2(re * c.re - im * c.im,
			re * c.im - im * c.re);
	}

	public Complex2 dividedBy(Complex2 c) {
		double tmp = c.re * c.re + c.im * c.im;
		return new Complex2((re * c.re - im * c.im) / tmp,
			(im * c.re - re * c.im) / tmp);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Complex2 complex = (Complex2)o;
		return Double.compare(complex.re, re) == 0 &&
			Double.compare(complex.im, im) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(re, im);
	}

	@Override
	public String toString() {
		return "(" +  re + ", " + im + "i)";
	}
}
