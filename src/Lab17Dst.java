import java.awt.*;
import java.awt.event.*;

public class Lab17Dst
{
	public static void main(String args[])
	{
		AppWindow tester = new AppWindow();
		tester.addWindowListener(new WindowAdapter()
		{public void windowClosing(WindowEvent e)
		{System.exit(0);}});
		tester.setSize(1018,695);
		tester.setVisible(true);
	}
}

class AppWindow extends Frame implements KeyListener
{
	private static final long serialVersionUID = 1L;
	private final int MAXX = 1000;
	private final int MAXY = 650;

	StageManager sm;
	KeyManager km;
	private Graphics gBuffer;
    private Image virtualMem;
	private boolean gameConstructed = false;

	public AppWindow()
	{
		super("Lab17D");
		addKeyListener(this);
		setSize(MAXX+18,MAXY+45);
		setVisible(true);
		virtualMem = createImage(MAXX+18,MAXY+45);
		gBuffer = virtualMem.getGraphics();
		sm = new StageManager(gBuffer);
		km = new KeyManager(sm);
		gameConstructed = true;
		repaint();
	}

	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(9,36);

		if (gameConstructed)
		{
			km.tick();

			drawBackground(gBuffer);

			sm.drawStage();

			g.drawImage (virtualMem,0,0,this);
			Util.delay(20);
			repaint();
		}
	}

	public void drawBackground(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0,0,MAXX,MAXY);
	}

	public void update(Graphics g)
	{
		paint(g);
	}

	public void keyReleased(KeyEvent k){ km.removeKey(k.getKeyCode()); }
	public void keyPressed(KeyEvent k){ km.addKey(k.getKeyCode()); }
	public void keyTyped(KeyEvent k){ }

}
