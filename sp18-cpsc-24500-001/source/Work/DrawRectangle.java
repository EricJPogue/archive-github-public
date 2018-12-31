import java.awt.*;
import javax.swing.*;
 
public class DrawRectangle {
 
  public static void main(String[] arguments) {

 
    // create a basic JFrame
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("JFrame Color Example");
    frame.setSize(300,200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 
    // add panel to main frame
     
    MyPanel panel = new MyPanel();
    frame.add(panel);
 

         frame.setVisible(true);
  }
}
 
// create a panel that you can draw on.
class MyPanel extends JPanel {
  public void paint(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(10,10,100,100);
    g.setColor(Color.blue);
                    g.drawOval(10, 10, 20, 25);

                    g.fillOval(120,120, 100,100);
  }
}