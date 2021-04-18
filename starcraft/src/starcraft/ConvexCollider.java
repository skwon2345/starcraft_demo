package starcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class ConvexCollider extends Collider {
    public Vector<Pos> hull;
    
    public ConvexCollider() {
        super();
        hull = null;
    }
    
    public ConvexCollider(Vector<Pos> hull) {
        super(null);
        this.hull = new Vector<Pos>();
//      for (int i = 0; i < hull.size(); i++) {
//          this.hull.set(i, hull.get(i));
//      }
        this.hull = hull;
    }
    
    public void paint(Graphics g) {
        super.paint(g);

        drawConvex(g, COLLIDER_COLOR);
    }
    
    public boolean collisionDetection(Collider otherCollider) {
        if (otherCollider instanceof CircleCollider) {
            CircleCollider otherCircleCollider = (CircleCollider)otherCollider;
            for (int i = 0; i < hull.size(); i++) {
                double xDiff = Math.abs(hull.get(i).xOnMap - otherCircleCollider.getCenterXOnMap());
                double yDiff = Math.abs(hull.get(i).yOnMap - otherCircleCollider.getCenterYOnMap());
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                if (dist <= (otherCircleCollider.r + 0.5)) {
                    return true;
                }
            }
        }
        
//        if (getRightTopVertexX() >= otherCircleCollider.getCenterXOnMap() - otherCircleCollider.r && getRightTopVertexX() <= otherCircleCollider.getCenterXOnMap() + otherCircleCollider.r &&
//            getRightTopVertexY() >= otherCircleCollider.getCenterYOnMap() - otherCircleCollider.r && getRightTopVertexY() <= otherCircleCollider.getCenterYOnMap() + otherCircleCollider.r) {
//            return true;
//        }
//        else if (getLeftTopVertexX() >= otherCircleCollider.getCenterXOnMap() - otherCircleCollider.r && getLeftTopVertexX() <= otherCircleCollider.getCenterXOnMap() + otherCircleCollider.r &&
//                 getLeftTopVertexY() >= otherCircleCollider.getCenterYOnMap() - otherCircleCollider.r && getLeftTopVertexY() <= otherCircleCollider.getCenterYOnMap() + otherCircleCollider.r) {
//            return true;
//        }
//        else if (getRightBottomVertexX() >= otherCircleCollider.getCenterXOnMap() - otherCircleCollider.r && getRightBottomVertexX() <= otherCircleCollider.getCenterXOnMap() + otherCircleCollider.r &&
//                 getRightBottomVertexY() >= otherCircleCollider.getCenterYOnMap() - otherCircleCollider.r && getRightBottomVertexY() <= otherCircleCollider.getCenterYOnMap() + otherCircleCollider.r) {
//            return true;
//        }
//        else if (getLeftBottomVertexX() >= otherCircleCollider.getCenterXOnMap() - otherCircleCollider.r && getLeftBottomVertexX() <= otherCircleCollider.getCenterXOnMap() + otherCircleCollider.r &&
//                 getLeftBottomVertexY() >= otherCircleCollider.getCenterYOnMap() - otherCircleCollider.r && getLeftBottomVertexY() <= otherCircleCollider.getCenterYOnMap() + otherCircleCollider.r) {
//            return true;
//        }

        return false;
    }
    
    public void drawConvex(Graphics g, Color color) {
//        int x = -1;
//        int y = -1;
//        if (owner != null) {
//            x = (int)owner.x;
//            y = (int)owner.y;
//        }
//        else {
//            x = (int)(getLeftTopVertexX() + Starcraft.map01X);
//            y = (int)(getLeftTopVertexY() + Starcraft.map01Y);
//        }
        g.setColor(color);
        for (int i = 0; i < hull.size(); i++) {
            if (i+1 < hull.size()) {
                g.drawLine((int)(hull.get(i).xOnMap + Starcraft.map01X), (int)(hull.get(i).yOnMap + Starcraft.map01Y), 
                           (int)(hull.get(i+1).xOnMap + Starcraft.map01X), (int)(hull.get(i+1).yOnMap + Starcraft.map01Y));
            }
            else {
                g.drawLine((int)(hull.get(i).xOnMap + Starcraft.map01X), (int)(hull.get(i).yOnMap + Starcraft.map01Y), 
                           (int)(hull.get(0).xOnMap + Starcraft.map01X), (int)(hull.get(0).yOnMap + Starcraft.map01Y));
            }
        }
    }

}

