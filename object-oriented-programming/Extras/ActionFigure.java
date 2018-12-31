

class Dimension {
    private double height;
    private double width;
    private double length;
}

abstract class Toy {
    private Dimension dimensions;
    private double weight;

    public String getType() {
        return "Toy";
    }

    public void play();
}

class ActionFigure extends Toy {
    private String gender;
    public String getType() {
        return "ActionFigure
    }
}