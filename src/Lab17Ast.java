import java.awt.*;
import java.awt.event.*;


public class Lab17Ast
{
	public static void main(String args[])
	{
		AppWindow tester = new AppWindow();
		tester.addWindowListener(new WindowAdapter()
		{public void windowClosing(WindowEvent e)
		{System.exit(0);}});
		tester.setSize(1018,695); // Window is made bigger to accommodate borders and title bar.
		tester.setVisible(true);
	}
}


class AppWindow extends Frame
{
	private final int MAXX = 1000;
	private final int MAXY = 650;
	private final int MIDX = MAXX / 2;
	private final int MIDY = MAXY / 2;
	private final double twoPI = Math.PI * 2;
	private final double step1 = twoPI / 500;
	private final double step2 = twoPI / 1500;
	private final double step3 = twoPI / 4500;
	private final double step4 = twoPI / 1000;


	private double xAngle, yAngle, zAngle, xyzAngle;

	private Graphics gBuffer;
	private Graphics3D g3D;
    private Image virtualMem;
	private boolean gameConstructed = false;

	public AppWindow()
	{
		super("Lab17A");

		setSize(MAXX+18,MAXY+45);
		show();
		virtualMem = createImage(MAXX+18,MAXY+45);
		gBuffer = virtualMem.getGraphics();

		g3D  = new Graphics3D(gBuffer);
		///////////////////////////////////////////////////////////////////
		g3D.addPoint(-175,-50,0);
		g3D.addPoint(-75,50,0);
		g3D.addPoint(-75,0,0);
		g3D.addPoint(-25,0,0);
		g3D.addPoint(-25,50,0);
		g3D.addPoint(25,50,0);
		g3D.addPoint(25,0,0);
		g3D.addPoint(75,0,0);
		g3D.addPoint(75,50,0);
		g3D.addPoint(175,-50,0);

		g3D.addPoint(-175,-50,25);
		g3D.addPoint(-75,50,25);
		g3D.addPoint(-75,0,25);
		g3D.addPoint(-25,0,25);
		g3D.addPoint(-25,50,25);
		g3D.addPoint(25,50,25);
		g3D.addPoint(25,0,25);
		g3D.addPoint(75,0,25);
		g3D.addPoint(75,50,25);
		g3D.addPoint(175,-50,25);
		///////////////////////////////////////////////////////////////////
		gameConstructed = true;
		repaint();
	}

	public void paint(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;
		g2D.translate(9,36);

		if (gameConstructed)
		{
			drawAxes(gBuffer);

			drawObject();

			xAngle += step1;
			yAngle += step2;
			zAngle += step3;
			xyzAngle += step4;

			g3D.rotateXYZ(xAngle,yAngle,zAngle);

			g.drawImage (virtualMem,0,0,this);
			Util.delay(20);

			repaint();
		}
	}

	public void drawObject() {
                g3D.setColor(Color.red);

                int n = 0;
                int temp = 0;
                boolean test = false;
                for(int x = 0; x <= 19; x++) {
                        if(x == 19) {
                                n = 10;
                        } else {
                                if(x == 9) {
                                        if(temp == 0) {
                                                temp++;
                                                n = 0;
                                        } else if(temp == 1) {
                                                n = 19;
                                                temp++;
                                        }
                                } else {
                                        if(!test) {
                                                n = x + 1;
                                        } else {
                                                n = x + 10;
                                                if(n >= 20)
                                                        break;
                                        }
                                }
                        }

                        g3D.drawLine(x, n);

                        if(x == 19) {
                                x = -1;
                                test = true;
                        }
                }
        }

	public void drawAxes(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0,0,MAXX,MAXY);

		g.setColor(Color.black);
		g.drawLine(0,MIDY,MAXX,MIDY);  // X-AXIS
		g.drawLine(MIDX,0,MIDX,MAXY);  // Y-AXIS
	}


	public void update(Graphics g)
	{
		paint(g);
	}
}
