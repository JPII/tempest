import java.awt.*;

public abstract class Stage{

	protected final double twoPI = Math.PI * 2;
	protected int startPos,pos;
	protected Graphics3D object;

	public Stage(Graphics buffer){
		object = new Graphics3D(buffer);
		pos = startPos = 0;
	}

	public abstract void drawObject();
	public int getNumPos(){
		return pos;
	}
	public Graphics3D getObject(){
		return object;
	}
	public int getStartPos(){
		return startPos;
	}
	public Point3D getPos(int pos){
		while(pos>=this.pos)
			pos-=this.pos;
		return object.getPoint(pos);
	}
}