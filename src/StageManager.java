import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

public class StageManager {

	Graphics g;
	Stage stage;
	Player p;
	ArrayList<Entity> entities;
	int score;

	public StageManager(Graphics g) {
		this.g = g;
		score = 0;
		stage = getNextStage();
		entities = new ArrayList<Entity>();
		p = new Player(g, this, stage, stage.getStartPos());
		newStage();
	}

	private void newStage() {
		stage = getNextStage();
		entities = new ArrayList<Entity>();
		p.newStage(stage);
		addEntity(p);
		for (int index = 0; index < 10; index++) {
			entities.add(new Crawler(g, this, stage, index));//replace index with random value
		}
	}

	public void removeEntites() {
		entities.removeAll(entities);
		entities.add(p);
	}

	public Stage getNextStage() {
		return new Stage1(g);
	}

	public void drawStage() {
		drawScore();
		checkCollision();
		stage.drawObject();
		for (int index = 0; index < entities.size(); index++) {
			entities.get(index).drawEntity();
		}
		if (stage.stageOver()) {
			newStage();
		} else if (entities.size() == 1) {
			nextStage();
		}
	}

	private void nextStage() {
		stage.nextStage();
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	public void left() {
		p.left();
	}

	public void right() {
		p.right();
	}

	public void fire() {
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
		g.setFont(new Font("Dialog", Font.PLAIN, 20));

		DecimalFormat df = new DecimalFormat("00000000");
		String newScore = df.format(score);

		g.drawString(newScore, 5, 20);
	}
	
	private void checkCollision() {
		for(int index = 0; index<entities.size(); index++) {
			Entity e = entities.get(index);
			if(e instanceof Crawler)
				for(int count = 0; count<entities.size(); count++) {
					Entity e2 = entities.get(count);
					if(e2 instanceof Bullet)
						if(e.pos == e2.pos && didCollide(e, e2)) {
							removeEntity(e);
							removeEntity(e2);
						}
				}
		}
	}
	
	private boolean didCollide(Entity e, Entity e2) {
		if(e.z - e2.z >= 6)
			return true;
		return false;
	}
}