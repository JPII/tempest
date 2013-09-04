import java.awt.*;
import java.util.*;

public class StageManager{
	
	Graphics g;
	Stage stage;
	Player p;
	ArrayList<Entity> entities;
	int score;

	public StageManager(Graphics g)	{
		this.g = g;
		score = 0;
		stage = getNextStage();
		entities = new ArrayList<Entity>();
		p = new Player(g,this,stage,stage.getStartPos());
		newStage();
	}
	
	private void newStage(){
		stage = getNextStage();
		entities = new ArrayList<Entity>();
		p.newStage(stage);
		addEntity(p);
		for(int index = 0; index<10; index++){
			entities.add(new Crawler(g, this, stage, index));
		}
	}
	
	public void removeEntites(){
		entities.removeAll(entities);
		entities.add(p);
	}
	
	public Stage getNextStage(){
		return new Stage1(g);
	}

	public void drawStage(){
		drawScore();
		stage.drawObject();
		for(int index = 0; index<entities.size(); index++){
			entities.get(index).drawEntity();
		}		
		if(stage.stageOver()){
			newStage();
		}
		else if(entities.size()==1){
			nextStage();
		}
	}
	
	private void nextStage(){
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
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public void removeScore(int score) {
		this.score -= score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	private void drawScore() {
		g.setColor(Color.GREEN);
		g.drawString(Integer.toString(score), 5, 15);
	}
}