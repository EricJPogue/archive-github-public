import java.awt.BorderLayout;
import java.awt.Container; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;

class OvalDraw extends Oval {
    private int R,G,B;
    public void setColor(int rIn, int gIn, int bIn) {
        R = rIn;
        G = gIn;
        B = bIn;
    }

    OvalDraw() {
        super();
        setColor(0,0,0);
    }

    OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
        setColor(0,0,0);
    }

    OvalDraw(int panelWidthIn, int panelHeightIn) {
        // Create random and appropriate dimensions given the inputs of panel width and height.
        final int widthMin = 10;
        int width = GetNumberBetween(widthMin, panelWidthIn);

        final int heightMin = 10;
        int height = GetNumberBetween(heightMin, panelHeightIn);

        final int xMin = 0;
        int xMax = panelWidthIn - width;
        int x = GetNumberBetween(xMin, xMax);

        final int yMin = 0;
        int yMax = panelHeightIn - height;
        int y = GetNumberBetween(yMin, yMax);

        setPositionX(x);
        setPositionY(y);
        setWidth(width);
        setHeight(height);
    }

    private static int GetNumberBetween(int min, int max) {
        Random myRandom = new Random();
        return min + myRandom.nextInt(max-min);
    }

    public void paintComponent(Graphics g) {
        Color myColor = new Color(R,G,B);
        g.setColor(myColor); 

        g.drawOval(getPositionX(),getPositionY(),getWidth(),getHeight());
        System.out.format("OvalDraw::pC(x=%d,y=%d,w=%d,h=%d\n", 
            getPositionX(), getPositionY(), getWidth(), getHeight());
    }        
}

class ShapeDrawPanel extends JPanel {
    public void setOvalDrawList(ArrayList<OvalDraw> OvalDrawListIn) { OvalDrawList = OvalDrawListIn; }
    private ArrayList<OvalDraw> OvalDrawList;
    
    ShapeDrawPanel() {
        super();
        assert false:"Unexpected call to ShapeDrawPanel default constructor.";
        OvalDrawList = null;
    }

    ShapeDrawPanel(ArrayList<OvalDraw> OvalDrawListIn) {
        setOvalDrawList(OvalDrawListIn);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        for (OvalDraw oD : OvalDrawList) {
            oD.paintComponent(g); 
        }
    }

    public void addRandomOval() {
        addRandomOval(0,0,0);
    }

    public void addRandomOval(int rIn, int gIn, int bIn) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();   
        OvalDraw myOvalDraw = new OvalDraw(panelWidth, panelHeight);
        myOvalDraw.setColor(rIn, gIn, bIn);
        OvalDrawList.add(myOvalDraw);
        repaint(); 
    }

    public void clearOvals() {
        OvalDrawList.clear();
    }
}

class ShapeDrawFrame extends JFrame implements ActionListener, KeyListener {
    private ShapeDrawPanel myShapeDrawPanel;
    private JTextField colorRTextField;
    private JTextField colorGTextField;
    private JTextField colorBTextField;
   
    public ShapeDrawFrame() {
         assert false:"Unexpected call to ShapeDrawFrame default constructor.";       
    }

    public ShapeDrawFrame(ArrayList<OvalDraw> OvalDrawListIn) {
        setBounds(100,100,600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel myButtonPanel = new JPanel();
        myButtonPanel.setLayout(new FlowLayout());

        JButton newOvalButton = new JButton("Add Random Oval");
        newOvalButton.addActionListener(this);
        myButtonPanel.add(newOvalButton);

        contentPane.add(myButtonPanel, BorderLayout.SOUTH);

        myShapeDrawPanel = new ShapeDrawPanel(OvalDrawListIn);
        contentPane.add(myShapeDrawPanel, BorderLayout.CENTER);

        // Create menu bar and menus. 
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
        fileMenu.add(openMenuItem);

        JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(editMenu);
        
        JMenuItem clearMenuItem = new JMenuItem("Clear", KeyEvent.VK_C);
        clearMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                myShapeDrawPanel.clearOvals();
                repaint();
            }
        });        
        editMenu.add(clearMenuItem);
        add(menuBar, BorderLayout.NORTH);

        // Add lebel and text fields.
        JLabel colorRLabel = new JLabel("Color R:");
        myButtonPanel.add(colorRLabel);

        colorRTextField = new JTextField(3);
        colorRTextField.addKeyListener(this);
        myButtonPanel.add(colorRTextField);

        JLabel colorGLabel = new JLabel("G:");
        myButtonPanel.add(colorGLabel);

        colorGTextField = new JTextField(3);
        colorGTextField.addKeyListener(this);
        myButtonPanel.add(colorGTextField);

        JLabel colorBLabel = new JLabel("B:");
        myButtonPanel.add(colorBLabel);

        colorBTextField = new JTextField(3);
        colorBTextField.addKeyListener(this);
        myButtonPanel.add(colorBTextField);
    }

    public void actionPerformed(ActionEvent e) {
        addRandomOvalToDrawPanel();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            addRandomOvalToDrawPanel();
        }
    }

    private void addRandomOvalToDrawPanel() {
        String R = colorRTextField.getText();
        String G = colorGTextField.getText();
        String B = colorBTextField.getText();
        myShapeDrawPanel.addRandomOval(TextToRGB(R),TextToRGB(G),TextToRGB(B));
    }

    private static int TextToRGB(String valueRGB) {
        int returnValue = 0;
        try {
            returnValue = Integer.parseInt(valueRGB);
            if (returnValue < 0) {
                returnValue = 0;
            } else if (returnValue > 255) {
                returnValue = 255;
            }
        }
        catch(Exception e) {
            returnValue = 0;
        }
        return returnValue;
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}

public class ShapeDraw {
    public static void main(String[] args) {
        System.out.println("ShapeDraw Starting!");

        ArrayList<OvalDraw> myOvalDrawList = new ArrayList<OvalDraw>();
        ShapeDrawFrame myShapeDrawFrame = new ShapeDrawFrame(myOvalDrawList);
        myShapeDrawFrame.setVisible(true);
    }
}