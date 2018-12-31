import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Card {
	private char value;  // A through E
	private Color color;
	private int place;  // first, second, third
	public char getValue() {
		return value;
	}
	public void setValue(char v) {
		if (value < 'A') {
			value = 'A';
		} else if (value > 'E') {
			value = 'E';
		} else {
			value = v;
		}
	}
	public Color getColor() {
		return color;
	}
	public int getRed() {
		return color.getRed();
	}
	public int getGreen() {
		return color.getGreen();
	}
	public int getBlue() {
		return color.getBlue();
	}
	public void setColor(Color col) {
		color = col;
	}
	public void setColor(int r, int g, int b) {
		color = new Color(r, g, b);
	}
	public int getPlace() {
		return place;
	}
	public void setPlace(int p) {
		if (p < 0) {
			place = 0;
		} else if (p > 2) {
			place = 2;
		} else {
			place = p;
		}
	}
	public Card() {
		value = 'A';
		color = Color.BLACK;
		place = 0;
	}
	public Card(char v, Color col, int p) {
		setValue(v);
		setColor(col);
		setPlace(p);
	}
	public Card(char v, int r, int g, int b, int p) {
		this(v, new Color(r,g,b),p);
	}
}
class CardPanel extends JPanel {
	private Card[] cards;
	public CardPanel() {
		cards = null;
	}
	public CardPanel(Card[] c) {
		cards = c;
	}
	public void paintComponent(Graphics g) {
		int xpos;
		int ypos;
		int border = 20;
		int width = 50;
		int height = 100;
		FontMetrics fm = getFontMetrics(getFont());
		int charHeight = fm.getAscent();
		int charWidth;
		super.paintComponent(g);
		if (cards != null) {
			for (Card c : cards) {
				xpos = border + c.getPlace() * width;
				ypos = border;
				g.setColor(Color.BLACK);
				g.drawRect(xpos,ypos,width,height);
				charWidth = fm.stringWidth(""+c.getValue());
				g.setColor(c.getColor());
				g.drawString(""+c.getValue(),xpos+width/2-charWidth/2,ypos+height/2+charHeight/2);
			}
		}
	}
}
class CardRandomizer {
	private Random rnd;
	public CardRandomizer() {
		rnd = new Random();
	}
	public Card buildCard(int pos) {
		char letter = (char)(65+rnd.nextInt(5));
		int red = rnd.nextInt(255);
		int green = rnd.nextInt(255);
		int blue = rnd.nextInt(255);
		return new Card(letter,red,green,blue,pos);
	}
	public void changeCard(Card c) {
		c.setValue((char)(65+rnd.nextInt(5)));
		c.setColor(new Color(rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255)));
	}
	public void changeCards(Card[] cards) {
		for (Card c : cards) {
			changeCard(c);
		}
	}
}
class CardFrame extends JFrame {
	private Card[] cards;
	private CardRandomizer dealer;
	public CardFrame() {
		cards = null;
		dealer = null;
		setupUI(cards);
	}
	public CardFrame(Card[] cards, CardRandomizer cr) {
		this.cards = cards;
		dealer = cr;
		setupUI(cards);
	}
	public void setupUI(Card[] cards) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,200,200);
		CardPanel cp = new CardPanel(cards);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		c.add(cp,BorderLayout.CENTER);
		JPanel panButton = new JPanel();
		panButton.setLayout(new FlowLayout());
		JButton btnRandom = new JButton("Deal new cards");
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dealer.changeCards(cards);
				repaint();
			}
		});
		panButton.add(btnRandom);
		c.add(panButton,BorderLayout.SOUTH);
	}
}
public class CardGame {
	public static void main(String[] args) {
		Card[] cards = new Card[3];
		CardRandomizer randomizer = new CardRandomizer();
		for (int i = 0; i < 3; i++) {
			cards[i] = randomizer.buildCard(i);
		}
		CardFrame frm = new CardFrame(cards,randomizer);
		frm.setVisible(true);
	}
}