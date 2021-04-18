package starcraft;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import chai_utils.CollisionDetection;

public class Chunk {
    public Vector<Pos> posColl;
    public int bottomMostYOnMap; // bottomMostYOnMap
    public int rightMostXOnMap; // rightMostXOnMap
    public double r;
//    public boolean boxCollider;
//    public boolean circleCollider;

    public double xOnMap;
    public double yOnMap;

    public double prevMapX;
    public double prevMapY;
    
    public Vector<Pos> mapMaskVertexTest; // 약간 거친 외
    public Vector<Pos> mapMaskHullTest; // 위의 mapMaskVertexTest을 다시 가공하여, 둥글둥글한 외곽을 만듦.
    
    public Chunk() {
        super();
        posColl = new Vector<Pos>();
        bottomMostYOnMap = 0;
        rightMostXOnMap = 0;
//        boxCollider = false;
//        circleCollider = false;
        r = 0.0;
        
        xOnMap = -1;
        yOnMap = -1;

        prevMapX = -1;
        prevMapY = -1;
        mapMaskHullTest = new Vector<Pos>();
        mapMaskVertexTest = new Vector<Pos>(); 
    }
    
    public int getWidth() {
        return (int)(rightMostXOnMap - xOnMap);
    }
    
    public int getHeight() {
        return (int)(bottomMostYOnMap - yOnMap);
    }
    
    public double getAreaOfBox() {
        return (rightMostXOnMap - xOnMap) * (bottomMostYOnMap - yOnMap);
    }
    
    public double getAreaOfCircle() {
//        double r = Math.sqrt(Math.pow(w,2.0) + Math.pow(h, 2.0))/2;
//        double r = (w>h) ? w/2 : h/2;
        double w = rightMostXOnMap - xOnMap;
        double h = bottomMostYOnMap - yOnMap;
        r = (w>h) ? w/2 : h/2;
        for (int i = 0; i < posColl.size(); i++) {
            if (posColl.get(i).isEqual(xOnMap, yOnMap) || posColl.get(i).isEqual(xOnMap, bottomMostYOnMap-1) || posColl.get(i).isEqual(rightMostXOnMap-1, yOnMap) || posColl.get(i).isEqual(rightMostXOnMap-1, bottomMostYOnMap-1)) {
                r = Math.sqrt(Math.pow(w,2.0) + Math.pow(h, 2.0))/2;
                break;
            }
        }
        return Math.PI*r*r;
    }
    
    public double boxCollEfficiency (double areaOfBox) {
        return areaOfBox - posColl.size();
    }
    
    public double circleCollEfficiency (double areaOfCir) {
        return areaOfCir - posColl.size();
    }
    
    public boolean boxCollisBest() {
        return boxCollEfficiency(getAreaOfBox()) <= circleCollEfficiency(getAreaOfCircle());
    }
    
    public void paint(Graphics g) {
        // 만약 이 chunk를 그릴 일이 있다면, posColl들에 색깔을 주어 그리게 하면, 복잡한 앨거리듬 때는 visualization이 가능해지므로 디버깅이 쉬워진다.
        // 하지만 여기서는, mask그림을 바탕에 깔고, 그 위에 detect된 collider들을 그리므로, 굳이 그럴 필요는 없다.
        
        // 단, 그릴 때 x, y는 xOnMap과 yOnMap에 Starcraft의 map01X와 map01Y를 각각 더하면 된다.
    }
    
    public void update() {
        double diffXOnMap = Starcraft.map01X - prevMapX;
        double diffYOnMap = Starcraft.map01Y - prevMapY;
        
        prevMapX = Starcraft.map01X;
        prevMapY = Starcraft.map01Y;
    }    
}


