import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Entity {

	public Enemy(Graphics g, StageManager sm, Stage s, int startPos) {
		super(g, sm, s, startPos);

		Point3D one = s.getPos(pos);
		Point3D two = s.getPos(pos + 1);

		x = (one.getUserX() + two.getUserX()) / 2;
		y = (one.getUserY() + two.getUserY()) / 2;
		z = (int) (s.getBackZ() - (2000.0*Math.random()));
	}

	public void drawEntity() {
		if (z < s.getFrontZ())
			z += 5;

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