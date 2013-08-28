import java.awt.*;
import java.util.*;

public class StageManager{

	Stage stage;
	Player p;
	ArrayList<Entity> entities;

	public StageManager(Graphics g)	{
		stage = new Stage1(g);
		entities = new ArrayList<Entity>();
		p = new Player(g,this,stage,stage.getStartPos());
		addEntity(p);
	}

	public void drawStage(){
		stage.drawObject();
		for(int index = 0; index<entities.size(); index++){
			entities.get(index).drawEntity();
		}
	}

	public void addEntity(Entity e){
		entities.add(e);
	}

	public void removeEntity(Entity e){
		entities.remove(e);
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