import edu.lewisu.cs.shapes.Circle;

public class CircleTest {
	public static void main(String[] args) {
	    Circle c = new Circle(5);
		System.out.printf("Area=%.2f\n",c.area());
	}
}

// Command line syntax for building solution:
// javac -d . Circle.java
// jar cf shapes.jar edu
// javac -cp shapes.jar CircleTest.java
// java CircleTest