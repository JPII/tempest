import java.awt.*;

public class Stage1 extends Stage{

	public Stage1(Graphics g){
		super(g);
		pos = 12;
		for (double rad = 0; twoPI>=rad; rad+= twoPI/pos ){
			object.addPoint((int) (100*Math.sin(rad)),(int) (100*Math.cos(rad)),300);
		}
		for (double rad = 0; twoPI>=rad; rad+= twoPI/pos ){
			object.addPoint((int) (100*Math.sin(rad)),(int) (100*Math.cos(rad)),-600);
		}
	}

	public void drawObject() {
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
}