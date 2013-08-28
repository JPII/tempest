import java.awt.*;

public abstract class Entity{

	Graphics g;
	Graphics3D object;
	StageManager sm;
	int pos,x,y,z;
	Stage s;

	public Entity(Graphics pass,StageManager s,Stage st,int startPos){
		pos = 0;
		g = pass;
		object = new Graphics3D(g);
		sm = s;
		pos = startPos;
		this.s = st;
	}

	public abstract void drawEntity();

}