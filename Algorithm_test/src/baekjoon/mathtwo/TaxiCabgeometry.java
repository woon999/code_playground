package baekjoon.mathtwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaxiCabgeometry {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int r = Integer.parseInt(br.readLine());
		
		double uclid = Math.pow(r, 2)*Math.PI;
		double taxi = 2*Math.pow(r, 2);
		
		System.out.println(String.format("%.6f", uclid));
		System.out.println(String.format("%.6f", taxi));
		
	}
}
