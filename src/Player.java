import java.awt.*;

public class Player{

	Point3D center;
	Graphics g;
	Stage s;
	int pos;

	public Player(Graphics g,Stage st,int startPos){
		s = st;
		this.g = g;
		pos = startPos;
	}

	public void left(){
		pos--;
	}

	public void right(){
		pos++;
	}

	public void fire(){
	}

	public void drawEntity(){
		int newpos = pos;
		while(newpos<=0)
			newpos+=s.getNumPos();
		newpos%=s.getNumPos();

		Point3D one = s.getPos(newpos);
		Point3D two = s.getPos(newpos+1);

		int x = (one.getUserX() + two.getUserX()) /2;
		int y = (one.getUserY() + two.getUserY()) /2;

		int z = (one.getUserZ() + two.getUserZ()) /2 +100;

		Graphics3D temp = new Graphics3D(g);

		for(double rad = 0; rad<Math.PI*2; rad+=0.25){
			temp.addPoint((int) (x+25*Math.sin(rad)),(int) (y+25*Math.cos(rad)),z);
		}
		temp.setColor(Color.blue);
		temp.drawLine(0,temp.getNumPoints()-1);
		for(int index = 0; index<temp.getNumPoints()-1; index++){
			temp.drawLine(index,index+1);
		}

	}

}