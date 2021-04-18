package starcraft;

import java.util.HashMap;

public class Animations {
	public HashMap<String, Sprite> anims;
	
	public String defaultAction;
	public Sprite defaultSprite;
	
	public String curAction;
	public Sprite curSprite;
	
	public Animations() {
		anims = new HashMap<String, Sprite>();
		defaultAction = null;
		defaultSprite = null; 
		curAction = null;
		curSprite = null;
	}
	
	public void add(String action, Sprite sprite) {
		anims.put(action, sprite);
	}
	
	// pre-condition: the given action must exist.
	public void setDefaultAction(String action) {
		defaultAction = action;
		defaultSprite = anims.get(action);

		curAction = defaultAction;
		curSprite = defaultSprite;
	}
	
	// pre-condition: the given action must exist.
	public void setCurrentAction(String action) {
		curAction = action;
		curSprite = anims.get(action);
		curSprite.refresh(); // set invis mode if needed
	}
}
