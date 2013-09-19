import java.awt.*;

public class Crawler extends Enemy {
	
	int rotateCount=0;
	
	public Crawler(Graphics g, StageManager sm, Stage s, int startPos) {
		super(g, sm, s, startPos);
	}
	
	private void left() {
		pos--;
		while (pos <= 0)
			pos += s.getNumPos();
		pos %= s.getNumPos();
	}

	private void right() {
		pos++;
		while (pos <= 0)
			pos += s.getNumPos();
		pos %= s.getNumPos();
	}
	
	private void rotateTowardPlayer(){
		if(sm.p.pos<pos)
			left();
		else
			right();
	}

	public void drawEntity() {
		if (z < s.getFrontZ())
			z += 4;
		else{
			rotateCount++;
			if(rotateCount>15){
				rotateCount=0;
				rotateTowardPlayer();
			}
		}
		
		Point3D one = s.getPos(pos);
		Point3D two = s.getPos(pos + 1);

		x = (one.getUserX() + two.getUserX()) / 2;
		y = (one.getUserY() + two.getUserY()) / 2;
		
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