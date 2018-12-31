import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

class OvalDraw extends Oval {
    OvalDraw() {
    }

    OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
    }

    public void paint(Graphics g) {
        g.drawOval(getPositionX(), getPositionY(), getWidth(), getHeight());
        System.out.format("OvalDraw.paint(x=%d,y=%d,w=%d,h=%d)\n", 
            getPositionX(), getPositionY(), getWidth(), getHeight());
    }
}

class SadCyclopsFace extends OvalDraw {
    private OvalDraw eye;

    SadCyclopsFace() {
        eye = new OvalDraw(getPositionX(), getPositionY(), getWidth()/2, getHeight()/2);
    }

    SadCyclopsFace(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
        eye = new OvalDraw(getPositionX(), getPositionY(), getWidth()/2, getHeight()/2);
    }

    public void paint(Graphics g) {
        super.paint(g);
        eye.paint(g);
    }
}

class SadCyclopsPanel extends JPanel {
    private OvalDraw myOvalDraw;
    private SadCyclopsFace mySadFace;

    SadCyclopsPanel() {
        myOvalDraw = new OvalDraw(200,200,120,160);
        mySadFace = new SadCyclopsFace(300,300,140,180);
    }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(10,10,20,25);
        g.setColor(Color.blue);
        g.fillRect(30,40,50,60);
        g.fillOval(100,100,80,120);

        myOvalDraw.paint(g);
        mySadFace.paint(g);
        
    }
}

public class FaceDrawLite {
    public static void main(String[] args) {
        System.out.println("FaceDrawLite...");

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame myFrame = new JFrame("Sad Cyclops FaceDraw");
        myFrame.setBounds(100,100,600, 400);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SadCyclopsPanel myPanel = new SadCyclopsPanel();
        myFrame.add(myPanel);

        myFrame.setVisible(true);
    }
}