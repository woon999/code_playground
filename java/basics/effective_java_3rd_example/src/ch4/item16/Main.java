package ch4.item16;

import java.awt.*;

public class Main {

	public static void main(String[] args) {

		CustomPoint p = new CustomPoint(1,2);
		System.out.println(p.getX()+","+p.getY());

		Point p2 = new Point(1,2);
		System.out.println(p2.x +","+ p2.y); // pulbic 필드 노출

		// System.out.println(p.getWidth()); 접근불가

		Time time = new Time(10, 20);
		System.out.println(time);


	}
}
