//
// ShapesLibrary
import java.util.ArrayList;

public class ShapesLibraryTestApp {
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