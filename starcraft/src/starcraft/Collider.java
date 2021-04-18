package starcraft;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Collider {
    public static final Color COLLIDER_COLOR = Color.GREEN;

    public Unit owner;
    
    public Collider() {
        owner = null;
    }

    public Collider(Unit owner) {
        this.owner = owner;
    }

    public void paint(Graphics g) {
    }
    
    public abstract boolean collisionDetection(Collider otherCollider);
}
