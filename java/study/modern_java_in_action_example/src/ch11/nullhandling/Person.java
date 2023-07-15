package ch11.nullhandling;

class Car{
	private Insurance insurance;
	public Insurance getInsurance() {
		return insurance;
	}
}

class Insurance{
	private String name;
	public String getName(){
		return name;
	}
}

public class Person {
	private Car car;
	public Car getCar(){
		return car;
	}
}
