import java.awt.*;

public class Stage1 extends Stage{
	
	boolean translateOff;
	
	public Stage1(Graphics g){
		super(g);
		pos = 12;
		translateOff = false;
		for (double rad = 0; twoPI>=rad; rad+= twoPI/pos ){
			object.addPoint((int) (100*Math.sin(rad)),(int) (100*Math.cos(rad)),300);
		}
		for (double rad = 0; twoPI>=rad; rad+= twoPI/pos ){
			object.addPoint((int) (100*Math.sin(rad)),(int) (100*Math.cos(rad)),-600);
		}
	}

	public void drawObject() {
		
		if(translateOff){
			Graphics3D next = new Graphics3D(g);
				for(int index=0; index<object.getNumPoints(); index++){
					Point3D temp = object.getPoint(index);
					int z = temp.getUserZ()+5;
					if(z>625)
						z=625;
					next.addPoint(temp.getUserX(),temp.getUserY(),z);
				}
			object = next;
		}
		
    	object.setColor(Color.red);
    	object.drawLine(0,pos-1);
    	object.drawLine(pos-1,2*pos-1);
    	object.drawLine(pos,2*pos-1);
    	for(int index = 0; index<pos-1; index++){
    		object.drawLine(index,index+1);
    		object.drawLine(index,index+pos);
    		object.drawLine(index+pos,index+1+pos);
    	}
    }

    public int getNumPlayerPos(){
    	return pos;
    }

	public void nextStage() {
		translateOff = true;
	}
}