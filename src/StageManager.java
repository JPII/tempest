import java.awt.*;
import java.util.*;

public class StageManager{
	
	Graphics g;
	Stage stage;
	Player p;
	ArrayList<Entity> entities;

	public StageManager(Graphics g)	{
		this.g = g;
		newStage();
	}
	
	public void newStage(){
		stage = getNextStage();
		entities = new ArrayList<Entity>();
		p = new Player(g,this,stage,stage.getStartPos());
		addEntity(p);
	}
	
	public Stage getNextStage(){
		return new Stage1(g);
	}

	public void drawStage(){
		stage.drawObject();
		for(int index = 0; index<entities.size(); index++){
			entities.get(index).drawEntity();
		}
	}
	
	public void nextStage(){
		stage.nextStage();
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