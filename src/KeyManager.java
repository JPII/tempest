import java.awt.event.*;
import java.util.*;

public class KeyManager{

	ArrayList<Key> keys;
	StageManager sm;

	public KeyManager(StageManager s){
		keys = new ArrayList<Key>();
		sm = s;
	}

	public void addKey(int k){
		for(int index = 0; index<keys.size(); index++){
			if(keys.get(index).getCode()==k)
				return;
		}
		keys.add(new Key(k));
		action(k);
	}

	public void removeKey(int k){
		for(int index = 0; index<keys.size(); index++){
			if(keys.get(index).getCode()==k)
				keys.remove(index);
		}
	}

	public void action(int code){
		if(code == KeyEvent.VK_LEFT){
			sm.left();
		}
	 	else if(code == KeyEvent.VK_RIGHT){
			sm.right();
		}
		else if(code == KeyEvent.VK_Z){
			sm.fire();
		}
		else if(code == KeyEvent.VK_SPACE){
			sm.nextStage();
		}
	}

	public void tick(){
		for(int index = 0; index<keys.size(); index++){
			Key temp = keys.get(index);
			if(temp.canAct()){
				action(temp.getCode());
			}
		}
	}
}