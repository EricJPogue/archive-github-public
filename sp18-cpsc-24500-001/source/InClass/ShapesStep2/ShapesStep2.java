//
// Shapes Step 2: More Setters, Getters, and Constructors... plus toString overriding, 
//     Circles, CalcPerimeter, and ArrayLists. 
//     
// Learning Objectives: 
// 8.Understand implementation of Model portion of Shapes using Model-View-Controller
//     d) Update default and non-default constructors
//     *) Update setter and getter code
//     *) Demonstrate Constructors getting called
//     e) Override the toString function
//     *) Implement Perimeter method   
//     *) Implement Circle class
//     f) Store multiple objects in an ArrayList using generic data types
//     g) Distinguish between using an array and an ArrayList
//     h) Work with a collection of related objects polymorphically
//     i) Explain how Polymorphism is implemented behind the scenes... VMT plus overridding
//         methods in a base (usually abstract) class
//

import java.util.ArrayList;

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
        return String.format("width=%d, height=%d", width, height);
    }

    abstract public double CalcArea();
    abstract public double CalcPerimeter();
}

class Rectangle extends Shape {
    public Rectangle() {
        this(0,0);
    }

    public Rectangle(int widthIn, int heightIn) {
        setWidth(widthIn);
        setHeight(heightIn);
    }

    public String toString() {
        String superClassString = super.toString();
        return String.format("Rectangle: %s", superClassString);
    }

    public double CalcArea() {
        return getWidth() * getHeight();
    }

    public double CalcPerimeter() {
        return (getWidth() * 2) + (getHeight() * 2);
    }
}

class Square extends Rectangle {
    public Square(int widthAndHeightIn) {
        super(widthAndHeightIn, widthAndHeightIn);
    }

    public String toString() {
        String superClassString = super.toString();
        return String.format("Square: %s", superClassString);
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

    public String toString() {
        String superClassString = super.toString();
        return String.format("Circle: %s, radius=%d", superClassString, radius);
    }

    public double CalcArea() {
        return Math.PI * (getRadius() * getRadius());
    }

    public double CalcPerimeter() {
        return 2 * Math.PI * getRadius();
    }
}

public class ShapesStep2 {
    public static void main(String[] args){

        ArrayList<Shape> shapeList = new ArrayList<Shape>();

        for (int loopCount=1; loopCount<7; loopCount++) {
            shapeList.add(new Circle(loopCount));
            shapeList.add(new Rectangle(loopCount, 10-loopCount));
        }

        shapeList.add(new Square(5));

        for (Shape s: shapeList) {
            System.out.println(s);
            System.out.format("Area = %.1f\n", s.CalcArea());
            System.out.format("Perimeter = %.1f\n", s.CalcPerimeter());
        }
/*
        System.out.println("ShapesStep1...");
        Rectangle myRectangle = new Rectangle(3,5);

        System.out.println(myRectangle);

        double area = myRectangle.CalcArea();
        System.out.format("Area = %.1f\n\n", area);

        Square mySquare = new Square(7);
        double areaSquare = mySquare.CalcArea();
        System.out.format("Square Area = %.1f\n\n", areaSquare);

        Circle myCircle = new Circle(9);
        double circleArea = myCircle.CalcArea();
        System.out.println(myCircle);
        System.out.format("Circle Area = %.1f\n\n", circleArea);

        System.out.format("Circle Perimeter = %.1f\n", myCircle.CalcPerimeter());
        */
    }
}