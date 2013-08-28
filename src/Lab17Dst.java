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
	private final double twoPI = Math.PI * 2;

	private final double step1 = twoPI / 500;
	private final double step2 = twoPI / 1500;
	private final double step3 = twoPI / 4500;
	private final double step4 = twoPI / 1000;

	private double xAngle, yAngle, zAngle, xyzAngle;

	private int pos = 12;

	private Graphics gBuffer;
    private Image virtualMem;
	private boolean gameConstructed = false;
	private Graphics3D g3D;

	public AppWindow()
	{
		super("Lab17D");
		setSize(MAXX+18,MAXY+45);
		show();
		virtualMem = createImage(MAXX+18,MAXY+45);
		gBuffer = virtualMem.getGraphics();
		g3D = new Graphics3D(gBuffer);

		for (double rad = 0; twoPI>=rad; rad+= twoPI/pos ){
			g3D.addPoint((int) (100*Math.sin(rad)),(int) (100*Math.cos(rad)),300);
		}
		for (double rad = 0; twoPI>=rad; rad+= twoPI/pos ){
			g3D.addPoint((int) (100*Math.sin(rad)),(int) (100*Math.cos(rad)),-600);
		}

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

			drawObject();

			rotate();

			g.drawImage (virtualMem,0,0,this);
			Util.delay(20);
			repaint();
		}
	}

	private void rotate(){
		xAngle += step1;
		yAngle += step2;
		zAngle += step3;
		xyzAngle += step4;

		g3D.rotateXYZ(xAngle,yAngle,zAngle);
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

	private void drawObject() {
    	g3D.setColor(Color.red);
    	g3D.drawLine(0,pos-1);
    	g3D.drawLine(pos-1,2*pos-1);
    	g3D.drawLine(pos,2*pos-1);
    	for(int index = 0; index<pos-1; index++){
    		g3D.drawLine(index,index+1);
    		g3D.drawLine(index,index+pos);
    		g3D.drawLine(index+pos,index+1+pos);
    	}
    }
}
