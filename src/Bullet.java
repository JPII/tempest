import java.awt.*;

public class Bullet extends Entity {

	public Bullet(Graphics g, StageManager sm, Stage s, int startPos, int z) {
		super(g, sm, s, startPos);
		this.z = z;

		int newpos = pos;
		while (newpos <= 0)
			newpos += s.getNumPos();
		newpos %= s.getNumPos();

		Point3D one = s.getPos(newpos);
		Point3D two = s.getPos(newpos + 1);

		x = (one.getUserX() + two.getUserX()) / 2;
		y = (one.getUserY() + two.getUserY()) / 2;
	}

	public void drawEntity() {

		z -= 5;

		if (z < -600)
			sm.removeEntity(this);

		Graphics3D temp = new Graphics3D(g);
		for (double rad = 0; rad < Math.PI * 2; rad += 0.25) {
			temp.addPoint((int) (x + 5 * Math.sin(rad)),
					(int) (y + 5 * Math.cos(rad)), z);
		}

		temp.setColor(Color.yellow);
		temp.drawLine(0, temp.getNumPoints() - 1);
		for (int index = 0; index < temp.getNumPoints() - 1; index++) {
			temp.drawLine(index, index + 1);
		}
	}
}