import java.awt.*;

public class Stage1 extends Stage {

	boolean flyOut, flyIn;

	public Stage1(Graphics g) {
		super(g);
		pos = 12;
		flyOut = false;
		flyIn = true; // 300,-600
		for (double rad = 0; twoPI >= rad; rad += twoPI / pos) {
			object.addPoint((int) (100 * Math.sin(rad)),
					(int) (100 * Math.cos(rad)), -600);
		}
		for (double rad = 0; twoPI >= rad; rad += twoPI / pos) {
			object.addPoint((int) (100 * Math.sin(rad)),
					(int) (100 * Math.cos(rad)), -1500);
		}
	}

	public void drawObject() {
		if (flyIn) {
			Graphics3D next = new Graphics3D(g);
			for (int index = 0; index < object.getNumPoints(); index++) {
				Point3D temp = object.getPoint(index);
				int z = temp.getUserZ() + 5;
				next.addPoint(temp.getUserX(), temp.getUserY(), z);
			}
			object = next;
			if (object.getPoint(0).getUserZ() >= 300) {
				flyIn = false;
			}
		} else if (flyOut) {
			Graphics3D next = new Graphics3D(g);
			for (int index = 0; index < object.getNumPoints(); index++) {
				Point3D temp = object.getPoint(index);
				int z = temp.getUserZ() + 5;
				if (z > 625)
					z = 625;
				next.addPoint(temp.getUserX(), temp.getUserY(), z);
			}
			object = next;
			if (object.getPoint(object.getNumPoints() - 1).getUserZ() >= 625)
				stageOver = true;
		}

		object.setColor(Color.red);
		object.drawLine(0, pos - 1);
		object.drawLine(pos - 1, 2 * pos - 1);
		object.drawLine(pos, 2 * pos - 1);
		for (int index = 0; index < pos - 1; index++) {
			object.drawLine(index, index + 1);
			object.drawLine(index, index + pos);
			object.drawLine(index + pos, index + 1 + pos);
		}
	}

	public int getNumPlayerPos() {
		return pos;
	}

	public void nextStage() {
		flyOut = true;
	}

	public int getFrontZ() {
		return object.getPoint(0).getUserZ();
	}

	public int getBackZ() {
		return object.getPoint(object.getNumPoints()-1).getUserZ();
	}
}