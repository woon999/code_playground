package ch4.item22;

public class PhysicalConstants {
	private PhysicalConstants() {} // 인스턴스화 방지

	// 아보가드로 수 (1/몰)
	public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
	// 볼츠만 상수 (J/K)
	public static final double BOLTZMANN_CONST = 1.380_648_52e-23;
	// 전자 질량 (kg)
	public static final double ELECTRON_MASS = 9.109_383_56e-31;
}


// 상수 인터페이스 안티패턴 - 사용금지 !
// public interface PhysicalConstants {
// 	// 아보가드로 수 (1/몰)
// 	public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
// 	// 볼츠만 상수 (J/K)
// 	public static final double BOLTZMANN_CONST = 1.380_648_52e-23;
// 	// 전자 질량 (kg)
// 	public static final double ELECTRON_MASS = 9.109_383_56e-31;
// }
