package starcraft;

import java.awt.Color;
import java.awt.Graphics;

public class CircleCollider extends Collider {
    public int centerXOnMap;
    public int centerYOnMap;
    public double r;
    
    public CircleCollider() {
        super();
        
        r = 0.0;
    }

    public CircleCollider(Unit owner, double r) {
        super(owner);
        
        this.r = r;
    }
    
    public CircleCollider(int centerXOnMap, int centerYOnMap, double r) {
        super(null);
        this.centerXOnMap = centerXOnMap;
        this.centerYOnMap = centerYOnMap;
        this.r = r;
    }
    
    //-------------------------------------------------------------------------
    // to print collider on screen
    //-------------------------------------------------------------------------
    public double getCenterX() {
        if (owner != null) {
            return owner.x + owner.anims.curSprite.curImage.getWidth() / 2.0;
        }
        else {
            return centerXOnMap + Starcraft.map01X;
        }
    }

    public double getCenterY() {
        if (owner != null) {
            return owner.y + owner.anims.curSprite.curImage.getHeight() / 2.0;
        }
        else {
            return centerYOnMap + Starcraft.map01Y;
        }
    }

    //-------------------------------------------------------------------------
    // to check collision
    //-------------------------------------------------------------------------
    public double getCenterXOnMap() {
        if (owner != null) {
            return owner.xOnMap + owner.anims.curSprite.curImage.getWidth() / 2.0;
        }
        else {
            return centerXOnMap;
        }
    }

    public double getCenterYOnMap() {
        if (owner != null) {
            return owner.yOnMap + owner.anims.curSprite.curImage.getHeight() / 2.0;
        }
        else {
            return centerYOnMap;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        drawCircle(g, getCenterX(), getCenterY(), r, COLLIDER_COLOR);
    }
    
    public boolean collisionDetection(Collider otherCollider) {
        if (otherCollider instanceof CircleCollider) {
            CircleCollider otherCircleCollider = (CircleCollider)otherCollider;
            double xDiff = Math.abs(getCenterXOnMap() - otherCircleCollider.getCenterXOnMap());
            double yDiff = Math.abs(getCenterYOnMap() - otherCircleCollider.getCenterYOnMap());
            double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
            
//            System.out.println("CircleCollider::collisionDetection(): xDiff = " + xDiff);
//            System.out.println("CircleCollider::collisionDetection(): yDiff = " + yDiff);
//
//            System.out.println("CircleCollider::collisionDetection(): dist = " + dist);
//            System.out.println("CircleCollider::collisionDetection(): (r + 0.5) + (otherCircleCollider.r + 0.5) = " + ((r + 0.5) + (otherCircleCollider.r + 0.5)));
            
            return dist <= (r + 0.5) + (otherCircleCollider.r + 0.5);
        }
        if (otherCollider instanceof BoxCollider) {
            BoxCollider otherBoxCollider = (BoxCollider)otherCollider;
            
            if (getCenterXOnMap() <= otherBoxCollider.getLeftTopVertexXOnMap() && getCenterYOnMap() <= otherBoxCollider.getLeftTopVertexYOnMap()) {
                double xDiff = Math.abs(getCenterXOnMap() - otherBoxCollider.getLeftTopVertexXOnMap());
                double yDiff = Math.abs(getCenterYOnMap() - otherBoxCollider.getLeftTopVertexYOnMap());
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                return dist <= (r + 0.5);
            }
            else if (getCenterXOnMap() <= otherBoxCollider.getLeftBottomVertexXOnMap() && getCenterYOnMap() >= otherBoxCollider.getLeftBottomVertexYOnMap()) {
                double xDiff = Math.abs(getCenterXOnMap() - otherBoxCollider.getLeftBottomVertexXOnMap());
                double yDiff = Math.abs(getCenterYOnMap() - otherBoxCollider.getLeftBottomVertexYOnMap());
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                return dist <= (r + 0.5);
            }
            else if (getCenterXOnMap() >= otherBoxCollider.getRightBottomVertexXOnMap() && getCenterYOnMap() >= otherBoxCollider.getRightBottomVertexYOnMap()) {
                double xDiff = Math.abs(getCenterXOnMap() - otherBoxCollider.getRightBottomVertexXOnMap());
                double yDiff = Math.abs(getCenterYOnMap() - otherBoxCollider.getRightBottomVertexYOnMap());
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                return dist <=  (r + 0.5);
            }
            else if (getCenterXOnMap() >= otherBoxCollider.getRightTopVertexXOnMap() && getCenterYOnMap() <= otherBoxCollider.getRightTopVertexYOnMap()) {
                double xDiff = Math.abs(getCenterXOnMap() - otherBoxCollider.getRightTopVertexXOnMap());
                double yDiff = Math.abs(getCenterYOnMap() - otherBoxCollider.getRightTopVertexYOnMap());
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                return dist <=  (r + 0.5);                
            }
            else if (getCenterXOnMap() + r >= otherBoxCollider.getLeftBottomVertexXOnMap() && getCenterXOnMap() + r <= otherBoxCollider.getRightBottomVertexXOnMap() &&
                     getCenterYOnMap() - r >= otherBoxCollider.getLeftTopVertexYOnMap() && getCenterYOnMap() - r <= otherBoxCollider.getLeftBottomVertexYOnMap()) {
                return true;
            }
            else if (getCenterXOnMap() + r >= otherBoxCollider.getLeftTopVertexXOnMap() && getCenterXOnMap() + r <= otherBoxCollider.getRightTopVertexXOnMap() && 
                     getCenterYOnMap() + r >= otherBoxCollider.getLeftTopVertexYOnMap() && getCenterYOnMap() + r <= otherBoxCollider.getLeftBottomVertexYOnMap()) {
                return true;
            }
            else if (getCenterXOnMap() - r >= otherBoxCollider.getLeftBottomVertexXOnMap() && getCenterXOnMap() - r <= otherBoxCollider.getRightBottomVertexXOnMap() && 
                     getCenterYOnMap() - r >= otherBoxCollider.getRightTopVertexYOnMap() && getCenterYOnMap() - r <= otherBoxCollider.getRightBottomVertexYOnMap()) {
                return true;
            }
            else if (getCenterXOnMap() - r >= otherBoxCollider.getLeftTopVertexXOnMap() && getCenterXOnMap() - r <= otherBoxCollider.getRightTopVertexXOnMap() && 
                     getCenterYOnMap() + r >= otherBoxCollider.getRightTopVertexYOnMap() && getCenterYOnMap() + r <= otherBoxCollider.getRightBottomVertexYOnMap()) {
                return true;
            }
            
        }
        return false;
    }

    //-------------------------------------------------------------------------
    // Porting "draw circle" from C++ 
    //-------------------------------------------------------------------------
//    #ifndef M_PI
//    #define M_PI (4.0 * atan2(1.0, 1.0))
//    #endif
    
    double deg2rad(double degree) {
        return Math.PI * degree / 180.0;
    }

    double rad2deg(double radian) {
        return 180.0 * radian / Math.PI;
    }
    
    public void drawCircle(Graphics g, double centerX, double centerY, double r, Color color) {
        g.setColor(color);
//        g.drawLine((int)centerX, (int)centerY, (int)centerX, (int)centerY);
        
        double degree = 0.0;
        double rotateInterval = 2.0;
        
        while (true) {
            double xPos = centerX - (int)(Math.cos(deg2rad(degree)) * r);
            double yPos = centerY - (int)(Math.sin(deg2rad(degree)) * r);
            
            g.drawLine((int)xPos, (int)yPos, (int)xPos, (int)yPos);

            degree += rotateInterval;

            if (degree > 360.0) {
                break;
            }
        }
    }
}
