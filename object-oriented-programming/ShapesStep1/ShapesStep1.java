//
// Shapes Step 1: Setters, Getters, and Constructors by creating the Model portions of Shapes
// Learning Objectives: 
//     a) Declare an abstract class and explain why it is useful
//     b) Use Inheritance to build classes and objects that extend base class functionality
//     c) Implement Encapsulation and Data Hiding by using setters and getters
//     d) Write default and non-default constructors
//

abstract class Shape {
    private int positionX;
    private int positionY;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionXIn) {
        positionX = positionXIn;
    }

    public int getPostionY() {
        return positionY;
    }

    public void setPostionY(int positionYIn)  {
        positionY = positionYIn;
    }

    public Shape() {
        positionX = 0;
        positionY = 0;
    }

    public Shape(int positionXIn, int positionYIn) {
        positionX = positionXIn;
        positionY = positionYIn;
    }

    abstract public double CalcArea();
}

class Rectangle extends Shape {
    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public void setWidth(int widthIn) {
        width = widthIn;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int heightIn) {
        height = heightIn;
    }

    public Rectangle() {
        width = 0;
        height = 0;
    }

    public Rectangle(int widthIn, int heightIn) {
        width = widthIn;
        height = heightIn;
    }

    public double CalcArea() {
        return width * height;
    }
}

public class ShapesStep1 {
    public static void main(String[] args) {
        System.out.println("ShapesStep1!!!");

        Rectangle myRectangle = new Rectangle(3,7);
        double area = myRectangle.CalcArea();
        System.out.format("Area = %f\n", area);
    }
}