//
// Shapes Step 1: Setters, Getters, and Constructors by creating the Model portions of Shapes
// Learning Objectives: 
//     a) Declare an abstract class and explain why it is useful
//     b) Use Inheritance to build classes and objects that extend base class functionality
//     c) Implement Encapsulation and Data Hiding by using setters and getters
//     d) Write default and non-default constructors

abstract class Shape {
    protected final int getWidth() { return width; }
    protected final void setWidth(int widthIn) { width = widthIn; }
    private int width;

    protected final int getHeight() { return height; }
    protected final void setHeight(int heightIn) { height = heightIn; }
    private int height;

    public Shape() {
        this(0,0);
    }

    public Shape(int widthIn, int heightIn) {
        width = widthIn;
        height = heightIn;
    }

    public String toString() {
        return String.format("width=%d, height=%d\n", width, height);
    }

    abstract public double CalcArea();
}

class Rectangle extends Shape {
    public Rectangle() {
        this(0,0);
    }

    public Rectangle(int widthIn, int heightIn) {
        setWidth(widthIn);
        setHeight(heightIn);
    }

    public double CalcArea() {
        return getWidth() * getHeight();
    }
}

class Square extends Rectangle {
    public Square(int widthAndHeightIn) {
        super(widthAndHeightIn, widthAndHeightIn);
    }
}

class Circle extends Shape {
    public final int getRadius() { return radius; }
    public final void setRadius(int radiusIn) {
        radius = radiusIn; 
        setWidth(radius * 2);
        setHeight(radius * 2);
    }
    private int radius;

    public Circle(int radiusIn) {
        setRadius(radiusIn);
    }

    public double CalcArea() {
        return Math.PI * getWidth();
    }
}

public class ShapesStep1 {
    public static void main(String[] args){
        System.out.println("ShapesStep1...");
        Rectangle myRectangle = new Rectangle(3,5);

        System.out.format(myRectangle.toString());
        double area = myRectangle.CalcArea();
        System.out.format("Area = %.1f\n\n", area);

        Square mySquare = new Square(7);
        double areaSquare = mySquare.CalcArea();
        System.out.format("Square Area = %.1f\n\n", areaSquare);

        Circle myCircle = new Circle(9);
        double circleArea = myCircle.CalcArea();
        System.out.format("Circle Area = %.1f\n\n", circleArea);
    }
}