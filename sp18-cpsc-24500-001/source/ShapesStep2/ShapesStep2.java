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
    public final void setPositionX(int positionXIn) { positionX = positionXIn; }
    public final int getPositionX() { return positionX; }
    private int positionX;

    public final int getPositionY() { return positionY;}
    public final void setPositionY(int positionYIn) { positionY = positionYIn; }
    private int positionY;

    public Shape() {
        this(0,0);
    }

    public Shape(int positionXIn, int positionYIn) {
        setPositionX(positionXIn);
        setPositionY(positionYIn);
    }

    public String toString() {
        return String.format("positionX=%d, positionY=%d", positionX, positionY);
    }

    abstract public double CalcArea();
    abstract public double CalcPerimeter();
}

class Rectangle extends Shape {
    public final void setWidth(int widthIn) { width = widthIn; }
    public final int getWidth() { return width; }
    private int width;

    public void setHeight(int heightIn) { height = heightIn; }
    public int getHeight() { return height; }
    private int height;

    public Rectangle() {
        this(0,0);
    }

    public Rectangle(int widthIn, int heightIn) {
        setWidth(widthIn);
        setHeight(heightIn);
    }

    public Rectangle(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn);
        setWidth(widthIn);
        setHeight(heightIn); 
    }

    public String toString() {
        String superClassString = super.toString();
        return String.format("%s width=%d, height=%d", superClassString, width, height);
    }

    public double CalcArea() {
        return width * height;
    }

    public double CalcPerimeter() {
        return width + width + height + height;
    }
}

class Circle extends Shape {
    public void setRadius(int radiusIn) { radius = radiusIn; }
    public int getRadius() { return radius; }
    private int radius;

    Circle() {
      this(0);
    }

    Circle(int radiusIn ) {
        setRadius(radiusIn);
    }

    Circle(int positionXIn, int positionYIn, int radiusIn) {
       super(positionXIn, positionYIn);
       setRadius(radiusIn);
    }

    public String toString() {
        String superClassString = super.toString();
        return String.format("%s radius=%d", superClassString, radius);
    }

    public double CalcArea() {
        return Math.PI * radius * radius;
    }

    public double CalcPerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class ShapesStep2 {
    public static void main(String[] args) {
        ArrayList<Shape> shapesList = new ArrayList<Shape>();

        for (int loopCount = 1; loopCount < 7; loopCount++) {
            shapesList.add(new Circle(5,5,loopCount));
            shapesList.add(new Rectangle(loopCount, loopCount, 10-loopCount, 4));
        }

        for (Shape s : shapesList) {
            System.out.println(s);
            System.out.format("Area = %.1f\n", s.CalcArea());
            System.out.format("Perimeter = %.1f\n\n", s.CalcPerimeter());
        }
    }
}