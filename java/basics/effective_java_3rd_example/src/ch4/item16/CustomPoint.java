package ch4.item16;

public class CustomPoint {
	private double x;
	private double y;

	private class inner{
		public double getWidth(){
			return x*y;
		}
	}

	public CustomPoint(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
