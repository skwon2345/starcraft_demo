package starcraft;

import java.awt.Color;
import java.awt.Graphics;

public class BoxCollider extends Collider {
    public int xOnMap;
    public int yOnMap;
    public int w;
    public int h;

    public BoxCollider() {
        super();
        w = -1;
        h = -1;
    }

    public BoxCollider(Unit owner, int w, int h) {
        super(owner);
        this.w = w;
        this.h = h;
    }

    public BoxCollider(int xOnMap, int yOnMap, int w, int h) {
        super(null);
        this.xOnMap = xOnMap;
        this.yOnMap = yOnMap;
        this.w = w;
        this.h = h;
    }

    public double getLeftTopVertexXOnMap() {
        if (owner != null) {
            return owner.xOnMap;
        }
        else {
            return xOnMap;
        }
    }
    
    public double getLeftTopVertexYOnMap() {
        if (owner != null) {
            return owner.yOnMap;
        }
        else {
            return yOnMap;
        }
    }
    
    public double getRightTopVertexXOnMap() {
        return getLeftTopVertexXOnMap() + (w*Math.cos(Math.toRadians(owner.rotationAngle)));
    }
    
    public double getRightTopVertexYOnMap() {
        if (owner != null) {
            return owner.yOnMap+(w*Math.sin(Math.toRadians(owner.rotationAngle)));
        }
        else {
            return yOnMap;
        }
    }
    
    public double getRightBottomVertexXOnMap() {
        return getRightTopVertexXOnMap() - (h*Math.sin(Math.toRadians(owner.rotationAngle)));
    }
    
    public double getRightBottomVertexYOnMap() {
        return getRightTopVertexYOnMap() + (h*Math.cos(Math.toRadians(owner.rotationAngle)));
    }
    
    public double getLeftBottomVertexXOnMap() {
        if (owner != null) {
            return getLeftTopVertexXOnMap() - (h*Math.sin(Math.toRadians(owner.rotationAngle)));
        }
        else {
            return xOnMap;
        }
    }
    
    public double getLeftBottomVertexYOnMap() {
        return getLeftTopVertexYOnMap() + (h*Math.cos(Math.toRadians(owner.rotationAngle)));
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(COLLIDER_COLOR);

//        int x = -1;
//        int y = -1;
//        if (owner != null) {
//            x = (int)owner.x;
//            y = (int)owner.y;
//        }
//        else {
//            x = (int)(getLeftTopVertexXOnMap() + Starcraft.map01X);
//            y = (int)(getLeftTopVertexYOnMap() + Starcraft.map01Y);
//        }
//        
//        g.drawRect((int)x, (int)y, w, h);
        drawRectangle(g, COLLIDER_COLOR);
    }
    
    public boolean collisionDetection(Collider otherCollider) {
        if (otherCollider instanceof BoxCollider) {
            BoxCollider otherBoxCollider = (BoxCollider)otherCollider;
            if (getRightTopVertexXOnMap() >= otherBoxCollider.getLeftBottomVertexXOnMap() && getRightTopVertexXOnMap() <= otherBoxCollider.getRightBottomVertexXOnMap() &&
                getRightTopVertexYOnMap() >= otherBoxCollider.getLeftTopVertexYOnMap() && getRightTopVertexYOnMap() <= otherBoxCollider.getLeftBottomVertexYOnMap()) {
                return true;
            }
            else if (getLeftTopVertexXOnMap() >= otherBoxCollider.getLeftBottomVertexXOnMap() && getLeftTopVertexXOnMap() <= otherBoxCollider.getRightBottomVertexXOnMap() &&
                     getLeftTopVertexYOnMap() >= otherBoxCollider.getRightTopVertexYOnMap() && getLeftTopVertexYOnMap() <= otherBoxCollider.getRightBottomVertexYOnMap()) {
                return true;
            }
            else if (getRightBottomVertexXOnMap() >= otherBoxCollider.getLeftTopVertexXOnMap() && getRightBottomVertexXOnMap() <= otherBoxCollider.getRightTopVertexXOnMap() &&
                     getRightBottomVertexYOnMap() >= otherBoxCollider.getLeftTopVertexYOnMap() && getRightBottomVertexYOnMap() <= otherBoxCollider.getLeftBottomVertexYOnMap()) {
                return true;
            }
            else if (getLeftBottomVertexXOnMap() >= otherBoxCollider.getLeftTopVertexXOnMap() && getLeftBottomVertexXOnMap() <= otherBoxCollider.getRightTopVertexXOnMap() &&
                     getLeftBottomVertexYOnMap() >= otherBoxCollider.getRightTopVertexYOnMap() && getLeftBottomVertexYOnMap() <= otherBoxCollider.getRightBottomVertexYOnMap()) {
                return true;
            }
            
        }
        else if (otherCollider instanceof CircleCollider) {
            CircleCollider otherCircleCollider = (CircleCollider)otherCollider;
            if (getLeftTopVertexXOnMap() >= otherCircleCollider.getCenterXOnMap() && getLeftTopVertexYOnMap() >= otherCircleCollider.getCenterYOnMap()) {
                double xDiff = Math.abs(getLeftTopVertexXOnMap() - otherCircleCollider.getCenterXOnMap());
                double yDiff = Math.abs(getLeftTopVertexYOnMap() - otherCircleCollider.getCenterYOnMap());
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                return dist <= (otherCircleCollider.r + 0.5);
            } 
            else if (getLeftBottomVertexXOnMap() >= otherCircleCollider.getCenterXOnMap() && getLeftBottomVertexYOnMap() <= otherCircleCollider.getCenterYOnMap()) {
                double xDiff = Math.abs(getLeftBottomVertexXOnMap() - otherCircleCollider.getCenterXOnMap());
                double yDiff = Math.abs(getLeftBottomVertexYOnMap() - otherCircleCollider.getCenterYOnMap());
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                return dist <= (otherCircleCollider.r + 0.5);
            }
            else if (getRightTopVertexXOnMap() <= otherCircleCollider.getCenterXOnMap() && getRightTopVertexYOnMap() >= otherCircleCollider.getCenterYOnMap()) {
                double xDiff = Math.abs(getRightTopVertexXOnMap() - otherCircleCollider.getCenterXOnMap());
                double yDiff = Math.abs(getRightTopVertexYOnMap() - otherCircleCollider.getCenterYOnMap());
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                return dist <= (otherCircleCollider.r + 0.5);
            }
            else if (getRightBottomVertexXOnMap() <= otherCircleCollider.getCenterXOnMap() && getRightBottomVertexYOnMap() <= otherCircleCollider.getCenterYOnMap()) {
                double xDiff = Math.abs(getRightBottomVertexXOnMap() - otherCircleCollider.getCenterXOnMap());
                double yDiff = Math.abs(getRightBottomVertexYOnMap() - otherCircleCollider.getCenterYOnMap());
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                return dist <= (otherCircleCollider.r + 0.5);
            }
            if (getRightTopVertexXOnMap() >= otherCircleCollider.getCenterXOnMap() - otherCircleCollider.r && getRightTopVertexXOnMap() <= otherCircleCollider.getCenterXOnMap() + otherCircleCollider.r &&
                getRightTopVertexYOnMap() >= otherCircleCollider.getCenterYOnMap() - otherCircleCollider.r && getRightTopVertexYOnMap() <= otherCircleCollider.getCenterYOnMap() + otherCircleCollider.r) {
                return true;
            }
            else if (getLeftTopVertexXOnMap() >= otherCircleCollider.getCenterXOnMap() - otherCircleCollider.r && getLeftTopVertexXOnMap() <= otherCircleCollider.getCenterXOnMap() + otherCircleCollider.r &&
                     getLeftTopVertexYOnMap() >= otherCircleCollider.getCenterYOnMap() - otherCircleCollider.r && getLeftTopVertexYOnMap() <= otherCircleCollider.getCenterYOnMap() + otherCircleCollider.r) {
                return true;
            }
            else if (getRightBottomVertexXOnMap() >= otherCircleCollider.getCenterXOnMap() - otherCircleCollider.r && getRightBottomVertexXOnMap() <= otherCircleCollider.getCenterXOnMap() + otherCircleCollider.r &&
                     getRightBottomVertexYOnMap() >= otherCircleCollider.getCenterYOnMap() - otherCircleCollider.r && getRightBottomVertexYOnMap() <= otherCircleCollider.getCenterYOnMap() + otherCircleCollider.r) {
                return true;
            }
            else if (getLeftBottomVertexXOnMap() >= otherCircleCollider.getCenterXOnMap() - otherCircleCollider.r && getLeftBottomVertexXOnMap() <= otherCircleCollider.getCenterXOnMap() + otherCircleCollider.r &&
                     getLeftBottomVertexYOnMap() >= otherCircleCollider.getCenterYOnMap() - otherCircleCollider.r && getLeftBottomVertexYOnMap() <= otherCircleCollider.getCenterYOnMap() + otherCircleCollider.r) {
                return true;
            }

        }
        return false;
    }
    
    public void drawRectangle(Graphics g, Color color) {
        int x = -1;
        int y = -1;
        if (owner != null) {
            x = (int)owner.x;
            y = (int)owner.y;
        }
        else {
            x = (int)(getLeftTopVertexXOnMap() + Starcraft.map01X);
            y = (int)(getLeftTopVertexYOnMap() + Starcraft.map01Y);
        }
        
        g.setColor(color);
        g.drawLine((int)(getLeftTopVertexXOnMap() + Starcraft.map01X), (int)(getLeftTopVertexYOnMap() + Starcraft.map01Y), (int)(getRightTopVertexXOnMap() + Starcraft.map01X), (int)(getRightTopVertexYOnMap() + Starcraft.map01Y));
        g.drawLine((int)(getLeftTopVertexXOnMap() + Starcraft.map01X), (int)(getLeftTopVertexYOnMap() + Starcraft.map01Y), (int)(getLeftBottomVertexXOnMap() + Starcraft.map01X), (int)(getLeftBottomVertexYOnMap() + Starcraft.map01Y));
        g.drawLine((int)(getLeftBottomVertexXOnMap() + Starcraft.map01X), (int)(getLeftBottomVertexYOnMap() + Starcraft.map01Y), (int)(getRightBottomVertexXOnMap() + Starcraft.map01X), (int)(getRightBottomVertexYOnMap() + Starcraft.map01Y));
        g.drawLine((int)(getRightBottomVertexXOnMap() + Starcraft.map01X), (int)(getRightBottomVertexYOnMap() + Starcraft.map01Y), (int)(getRightTopVertexXOnMap() + Starcraft.map01X), (int)(getRightTopVertexYOnMap() + Starcraft.map01Y));//        g.drawLine((int)centerX, (int)centerY, (int)centerX, (int)centerY);
//        for (int i = y; i <= y + h; i++) {
//            for (int j = x; j <= x + w; j++) {
//                if (i == y || i == y + h) {
//                    g.drawLine(j, i, j, i);
//                }
//                else if (j == x || j == x + w) {
//                    g.drawLine(j, i, j, i);
//                }
//            }
//        }
    }
}

