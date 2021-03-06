import java.awt.*;

public class Player extends Entity {

	Point3D center;

	public Player(Graphics g, StageManager sm, Stage s, int startPos) {
		super(g, sm, s, startPos);
	}

	public void newStage(Stage s) {
		this.s = s;
	}

	public void left() {
		pos--;
		while (pos <= 0)
			pos += s.getNumPos();
		pos %= s.getNumPos();
	}

	public void right() {
		pos++;
		while (pos <= 0)
			pos += s.getNumPos();
		pos %= s.getNumPos();
	}

	public void fire() {
		sm.addEntity(new Bullet(g, sm, s, pos, z));
	}

	public void drawEntity() {
		int newpos = pos;

		Point3D one = s.getPos(newpos);
		Point3D two = s.getPos(newpos + 1);

		x = (one.getUserX() + two.getUserX()) / 2;
		y = (one.getUserY() + two.getUserY()) / 2;

		z = (one.getUserZ() + two.getUserZ()) / 2 + 100;
		if (z > 400)
			z = 400;

		Graphics3D temp = new Graphics3D(g);

		for (double rad = 0; rad < Math.PI * 2; rad += 0.25) {
			temp.addPoint((int) (x + 25 * Math.sin(rad)),
					(int) (y + 25 * Math.cos(rad)), z);
		}
		temp.setColor(Color.blue);
		temp.drawLine(0, temp.getNumPoints() - 1);
		for (int index = 0; index < temp.getNumPoints() - 1; index++) {
			temp.drawLine(index, index + 1);
		}

	}

}