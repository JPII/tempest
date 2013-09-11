import java.awt.*;

public class Crawler extends Enemy {

	public Crawler(Graphics g, StageManager sm, Stage s, int startPos) {
		super(g, sm, s, startPos);
		
		z -= (2000*Math.random()); //maximum start location
	}

	public void drawEntity() {
		if (z < s.getFrontZ())
			z += 3;

		Graphics3D temp = new Graphics3D(g);

		for (double rad = 0; rad < Math.PI * 2; rad += 0.25) {
			temp.addPoint((int) (x + 5 * Math.sin(rad)),
					(int) (y + 5 * Math.cos(rad)), z);
		}
		temp.setColor(Color.white);
		temp.drawLine(0, temp.getNumPoints() - 1);
		for (int index = 0; index < temp.getNumPoints() - 1; index++) {
			temp.drawLine(index, index + 1);
		}

	}

}