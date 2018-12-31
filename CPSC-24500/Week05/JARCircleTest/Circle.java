package edu.lewisu.cs.shapes;

public class Circle {
	private double radius;
	public double getRadius() { return radius; }
	public void setRadius(double rad) { radius = Math.abs(rad);	}
	public Circle() {
		radius = 0;
	}
	public Circle(double rad) {
		setRadius(rad);
	}
	public double area() {
		return Math.PI * radius * radius;
	}
}