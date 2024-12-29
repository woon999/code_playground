package ch4.item22;

import static ch4.item22.PhysicalConstants.*;

public class Test {
	static double atoms(double mols){
		return AVOGADROS_NUMBER * mols;
	}

	public static void main(String[] args) {
		System.out.println(atoms(1));
	}
}
