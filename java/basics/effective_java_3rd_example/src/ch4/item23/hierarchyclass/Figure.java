package ch4.item23.hierarchyclass;

import java.util.function.Function;

public abstract class Figure {
	abstract double area();
}

class Circle extends Figure {
	private final double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	double area() {
		return Math.PI * (radius * radius);
	}
}

class Rectangle extends Figure {
	private final double length;
	private final double width;

	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	@Override
	double area() {
		return length * width;
	}
}

// 정사각형 추가
class Square extends Rectangle{
	public Square(double side){
		super(side, side);
	}
}