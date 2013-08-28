import java.awt.*;
import java.util.*;

public class StageManager{

	Stage stage;
	Player p;

	public StageManager(Graphics g)	{
		stage = new Stage1(g);
		p = new Player(g,stage,stage.getStartPos());
	}

	public void drawStage(){
		stage.drawObject();
		p.drawEntity();
	}

	public void left(){
		p.left();
	}

	public void right(){
		p.right();
	}

	public void fire(){
		p.fire();
	}
}