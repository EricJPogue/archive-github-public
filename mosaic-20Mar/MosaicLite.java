import javax.swing.JFrame;

class MosaicLiteFrame extends JFrame {
    public MosaicLiteFrame() {
        setBounds(200,200,600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


public class MosaicLite {
    public static void main(String[] args) {
        System.out.println("MosaicLite Starting...");

        MosaicLiteFrame myMosaicLiteFrame = new MosaicLiteFrame();
        myMosaicLiteFrame.setVisible(true);
    }
} 