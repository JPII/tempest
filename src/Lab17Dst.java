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

class AppWindow extends Frame
{
	private final int MAXX = 1000;
	private final int MAXY = 650;

	StageManager sm;
	private Graphics gBuffer;
    private Image virtualMem;
	private boolean gameConstructed = false;

	public AppWindow()
	{
		super("Lab17D");
		setSize(MAXX+18,MAXY+45);
		show();
		virtualMem = createImage(MAXX+18,MAXY+45);
		gBuffer = virtualMem.getGraphics();
		sm = new StageManager(gBuffer);
		gameConstructed = true;
		repaint();
	}

	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(9,36);

		if (gameConstructed)
		{
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
}
