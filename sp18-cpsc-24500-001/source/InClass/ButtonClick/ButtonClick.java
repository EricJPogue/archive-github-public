import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class ButtonFrame extends JFrame implements ActionListener {
    public ButtonFrame() {
        setTitle("Button Frame - EJP");f
        setBounds(100,100,400,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel myButtonPanel = new JPanel();
        myButtonPanel.setLayout(new FlowLayout());

        JButton myButton = new JButton("Our First Button");
        myButton.addActionListener(thish);
        myButtonPanel.add(myButton);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(myButtonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("public void actionPerformed(ActionEvent e) {...}");
    }
}

public class ButtonClick {
    public static void main(String[] args) {
        System.out.println("Hello ButtonClick!");

        ButtonFrame myButtonFrame = new ButtonFrame();
        myButtonFrame.setVisible(true);
    }
}