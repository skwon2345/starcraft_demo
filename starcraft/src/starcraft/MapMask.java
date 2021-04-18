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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;
import java.util.Timer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import chai_utils.CollisionDetection;

public class MapMask {
    public static int size;
    public static BufferedImage mapMask;
    public static Vector<Pos> mapMaskPos;
//    int maskedSize;

    public static Vector<Chunk> chunkColl;
    public static int chunkIndex;
    public static int posIndex;
    
    public MapMask() {
        size = 0;
        mapMask = null;
        
//        maskedSize = 0;
        chunkColl = null;
        chunkIndex = 0;
        mapMaskPos = null;
        posIndex = 0;

    }
    
    public static Vector<Collider> createBgColliders(BufferedImage mapMask) {
        Vector<Collider> bgColliders = new Vector<Collider>();
        
        size = mapMask.getWidth() * mapMask.getHeight();
//        maskedSize = 0;
        chunkColl = new Vector<Chunk>();
        chunkIndex = 0;
        MapMask.mapMask = mapMask;
        mapMaskPos = new Vector<Pos>(size);
        posIndex = 0;

        int accumSizeVertex = 0;
        int accumSizeHull = 0;
        for (int i = 0; i< mapMask.getHeight(); i++) {
            for (int j = 0; j < mapMask.getWidth(); j++) {
                mapMaskPos.insertElementAt(new Pos(j,i), (i*mapMask.getWidth())+j);
            }    
        }
        
        for (int i = 0; i < mapMask.getHeight(); i++) {
            for (int j = 0; j < mapMask.getWidth(); j++) {
                if (new Color(mapMask.getRGB(j, i), true).getAlpha() > 0) {
                    if (!mapMaskPos.get(i*mapMask.getWidth()+j).inChunk) {
                        chunkColl.add(new Chunk());
                        fill(mapMask.getWidth(), mapMask.getHeight(), j, i, chunkIndex, posIndex);
                        //----------------------------------
                        // test용 print start
                        //----------------------------------
                        System.out.println("MapMask::createBgColliders() - chunkColl.get(" + chunkIndex + ") mapMaskVertexTest.size(): " + chunkColl.get(chunkIndex).mapMaskVertexTest.size());
                        accumSizeVertex += chunkColl.get(chunkIndex).mapMaskVertexTest.size();
                        System.out.println("MapMask::createBgColliders() - accumSizeVertex : " + accumSizeVertex);
//                        for (int r = 0; r < chunkColl.get(chunkIndex).mapMaskVertexTest.size(); r++) {
//                            System.out.println("MapMask::createBgColliders() - mapMaskVertexTest index of (" + r + ") xOnMap: " + chunkColl.get(chunkIndex).mapMaskVertexTest.get(r).xOnMap + " yOnMap: " + chunkColl.get(chunkIndex).mapMaskVertexTest.get(r).yOnMap);
//                        }
                        
                        //-----------------------------------------------------
                        //-----------------------------------------------------
                        //-----------------------------------------------------
                        // Get ConvexCollider by the current chunk
                        //-----------------------------------------------------
                        //-----------------------------------------------------
                        //-----------------------------------------------------
                        
                        convexHull(chunkColl.get(chunkIndex).mapMaskVertexTest, chunkColl.get(chunkIndex).mapMaskVertexTest.size());
                        System.out.println("MapMask::createBgColliders() - chunkColl.get(" + chunkIndex + ") mapMaskHullTest.size(): " + chunkColl.get(chunkIndex).mapMaskHullTest.size());
                        accumSizeHull += chunkColl.get(chunkIndex).mapMaskHullTest.size();
                        System.out.println("MapMask::createBgColliders() - accumSizeHull : " + accumSizeHull);
                        //----------------------------------
                        // test용 print end
                        //----------------------------------
                        
                        if (chunkColl.get(chunkIndex).boxCollisBest()) {
                            bgColliders.add(new ConvexCollider(chunkColl.get(chunkIndex).mapMaskHullTest));
//                            bgColliders.add(new BoxCollider((int)chunkColl.get(chunkIndex).xOnMap, (int)chunkColl.get(chunkIndex).yOnMap,
//                                                            chunkColl.get(chunkIndex).getWidth(), chunkColl.get(chunkIndex).getHeight()));
                        }
                        else {
                            bgColliders.add(new CircleCollider((int)(chunkColl.get(chunkIndex).xOnMap + chunkColl.get(chunkIndex).getWidth() / 2),
                                                               (int)(chunkColl.get(chunkIndex).yOnMap + chunkColl.get(chunkIndex).getHeight() / 2),
                                                               chunkColl.get(chunkIndex).r));
                        }
                        chunkIndex++;
                    }
                }
            }
        }
        //---------------------------------------------------------------------------------------
        // Comment out when you use MapMaskTest.java
        //---------------------------------------------------------------------------------------
//        mapMaskPos = null;
//        chunkColl = null;
        
        return bgColliders;
    }
    
    public static void fill(int width, int height, int x, int y, int chunkIndex, int posIndex) {
        //-----------------------------------------------------------------------------------------------------------
        // Fill() While Loop Version
        //-----------------------------------------------------------------------------------------------------------
        boolean returnToParent = true;
//        Queue<Pos> notCheckedPos = new LinkedList<Pos>();
        Stack<Pos> notCheckedPos = new Stack<Pos>();
        
//        int notCheckedIndex = 0;
        chunkColl.get(chunkIndex).xOnMap = x;
        chunkColl.get(chunkIndex).yOnMap = y;

        chunkColl.get(chunkIndex).rightMostXOnMap = x + 1;
        chunkColl.get(chunkIndex).bottomMostYOnMap = y + 1;
        while(returnToParent) {
            if (y >= 0 && x+1 <= width && y+1 <= height && x >= 0) {
                if (x-1 >= 0 && y-1 >= 0) {
                    if (new Color(mapMask.getRGB(x-1, y-1), true).getAlpha() > 0  && !mapMaskPos.get((y-1)*mapMask.getWidth()+(x-1)).inChunk) {
                            if (x-1 == 0 && y-1 == 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x,y-1));                                
                            }
                            else if (x-1 == 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y));
                            }
                            else if (y-1 == 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x,y-1));
                            }
                            else if (new Color(mapMask.getRGB(x-2, y-1), true).getAlpha() <= 0 || new Color(mapMask.getRGB(x-1, y-2), true).getAlpha() <= 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x,y-1));
                            }
                    }
                }
                if (x-1 >= 0 && y+1 < height) {
                    if (new Color(mapMask.getRGB(x-1, y+1), true).getAlpha() > 0  && !mapMaskPos.get((y+1)*mapMask.getWidth()+(x-1)).inChunk) {
                            if (x-1 == 0 && y+1 == height-1) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y+1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y+2));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x,y+2));
                            }
                            else if (x-1 == 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y+1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y+2));
                            }
                            else if (y+1 == height-1) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x,y+2));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y+2));
                            }
                            else if (new Color(mapMask.getRGB(x-2, y+1), true).getAlpha() <= 0 || new Color(mapMask.getRGB(x-1, y+2), true).getAlpha() <= 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y+1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x-1,y+2));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x,y+2));
                            }
                    }
                }
                if (x+1 < width && y-1 >= 0) {
                    if (new Color(mapMask.getRGB(x+1, y-1), true).getAlpha() > 0  && !mapMaskPos.get((y-1)*mapMask.getWidth()+(x+1)).inChunk) {
                            if (x+1 == width-1 && y-1 == 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+1,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y));
                            }
                            else if (x+1 == width-1) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y));
                            }
                            else if (y-1 == 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+1,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y-1));
                            }   
                            else if (new Color(mapMask.getRGB(x+1, y-2), true).getAlpha() <= 0 || new Color(mapMask.getRGB(x+2, y-1), true).getAlpha() <= 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+1,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y-1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y));
                            }
                    }
                }
                if (y+1 < height && x+1 < width) {
                    if (new Color(mapMask.getRGB(x+1, y+1), true).getAlpha() > 0  && !mapMaskPos.get((y+1)*mapMask.getWidth()+(x+1)).inChunk) {
                            if (x+1 == width-1 && y+1 == height-1) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+1,y+2));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y+1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y+2));
                            }
                            else if (x+1 == width-1) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y+1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y+2));
                            }
                            else if (y+1 == height-1) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+1,y+2));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y+2));
                            }
                            else if (new Color(mapMask.getRGB(x+1, y+2), true).getAlpha() <= 0 || new Color(mapMask.getRGB(x+2, y+1), true).getAlpha() <= 0) {
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+1,y+2));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y+1));
                                chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+2,y+2));
                            }
                    }
                }
                if (new Color(mapMask.getRGB(x, y), true).getAlpha() > 0  && !mapMaskPos.get(y*mapMask.getWidth()+x).inChunk) {
                        if (x == 0 || y == 0) {
                        chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x,y));
                        chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+1,y));
                        chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x,y+1));
                        chunkColl.get(chunkIndex).mapMaskVertexTest.add(new Pos(x+1,y+1));
                        }
                }
            }
            
            if (chunkColl.get(chunkIndex).xOnMap > x) {
                chunkColl.get(chunkIndex).xOnMap = x;
            }
            if (chunkColl.get(chunkIndex).yOnMap > y) {
                chunkColl.get(chunkIndex).yOnMap = y;
            }
            if (chunkColl.get(chunkIndex).bottomMostYOnMap < y+1) {
                chunkColl.get(chunkIndex).bottomMostYOnMap = y+1;
            }
            if (chunkColl.get(chunkIndex).rightMostXOnMap < x+1) {
                chunkColl.get(chunkIndex).rightMostXOnMap = x+1;
            }

            mapMaskPos.get(y*mapMask.getWidth()+x).inChunk = true;
            chunkColl.get(chunkIndex).posColl.add(mapMaskPos.get(y*mapMask.getWidth()+x));
            
            if (y-1 >= 0 && !mapMaskPos.get((y-1)*mapMask.getWidth()+x).inChunk && new Color(mapMask.getRGB(x,y-1), true).getAlpha() > 0) { // north?
//                mapMaskPos.get((y-1)*mapMask.getWidth()+x).parentPos = chunkColl.get(chunkIndex).posColl.get(posIndex);
                chunkColl.get(chunkIndex).posColl.get(posIndex).n = true;
                returnToParent = false;
            }
            if (x+1 < width && !mapMaskPos.get(y*mapMask.getWidth()+(x+1)).inChunk && new Color(mapMask.getRGB(x+1,y), true).getAlpha() > 0) { // east?
//                mapMaskPos.get(y*mapMask.getWidth()+(x+1)).parentPos = chunkColl.get(chunkIndex).posColl.get(posIndex);
                chunkColl.get(chunkIndex).posColl.get(posIndex).e = true;
                returnToParent = false;
            }
            if (y+1 < height && !mapMaskPos.get((y+1)*mapMask.getWidth()+x).inChunk && new Color(mapMask.getRGB(x, y+1), true).getAlpha() > 0) { // south?
//                mapMaskPos.get((y+1)*mapMask.getWidth()+x).parentPos = chunkColl.get(chunkIndex).posColl.get(posIndex);
                chunkColl.get(chunkIndex).posColl.get(posIndex).s = true;
                returnToParent = false;
            }
            if (x-1 >= 0 && !mapMaskPos.get(y*mapMask.getWidth()+(x-1)).inChunk && new Color(mapMask.getRGB(x-1, y), true).getAlpha() > 0) { // west?
//                mapMaskPos.get(y*mapMask.getWidth()+(x-1)).parentPos = chunkColl.get(chunkIndex).posColl.get(posIndex);
                chunkColl.get(chunkIndex).posColl.get(posIndex).w = true;
                returnToParent = false;
            }
            
            if (!returnToParent) {
                if (chunkColl.get(chunkIndex).posColl.get(posIndex).n) {
                    chunkColl.get(chunkIndex).posColl.get(posIndex).n = false;
                    if (chunkColl.get(chunkIndex).posColl.get(posIndex).e) {
                        notCheckedPos.add(mapMaskPos.get(y*mapMask.getWidth()+(x+1)));
                        chunkColl.get(chunkIndex).posColl.get(posIndex).e = false;
                    }
                    if (chunkColl.get(chunkIndex).posColl.get(posIndex).s) {
                        notCheckedPos.add(mapMaskPos.get((y+1)*mapMask.getWidth()+x));
                        chunkColl.get(chunkIndex).posColl.get(posIndex).s = false;
                    }
                    if (chunkColl.get(chunkIndex).posColl.get(posIndex).w) {
                        notCheckedPos.add(mapMaskPos.get(y*mapMask.getWidth()+(x-1)));
                        chunkColl.get(chunkIndex).posColl.get(posIndex).w = false;
                    }
                    y--;
                }
                else if (chunkColl.get(chunkIndex).posColl.get(posIndex).e) {
                    chunkColl.get(chunkIndex).posColl.get(posIndex).e = false;
                    if (chunkColl.get(chunkIndex).posColl.get(posIndex).s) {
                        notCheckedPos.add(mapMaskPos.get((y+1)*mapMask.getWidth()+x));
                        chunkColl.get(chunkIndex).posColl.get(posIndex).s = false;
                    }
                    if (chunkColl.get(chunkIndex).posColl.get(posIndex).w) {
                        notCheckedPos.add(mapMaskPos.get(y*mapMask.getWidth()+(x-1)));
                        chunkColl.get(chunkIndex).posColl.get(posIndex).w = false;
                    }
                    x++;
                }
                else if (chunkColl.get(chunkIndex).posColl.get(posIndex).s) {
                    chunkColl.get(chunkIndex).posColl.get(posIndex).s = false;
                    if (chunkColl.get(chunkIndex).posColl.get(posIndex).w) {
                        notCheckedPos.add(mapMaskPos.get(y*mapMask.getWidth()+(x-1)));
                        chunkColl.get(chunkIndex).posColl.get(posIndex).w = false;
                    }
                    y++;
                }
                else if (chunkColl.get(chunkIndex).posColl.get(posIndex).w) {
                    chunkColl.get(chunkIndex).posColl.get(posIndex).w = false;
                    x--;
                }
                posIndex++;
                returnToParent = true;
            }
            else if (!notCheckedPos.isEmpty()) {
                x = (int)notCheckedPos.peek().xOnMap;
                y = (int)notCheckedPos.peek().yOnMap;
                
                notCheckedPos.pop();
                if (mapMaskPos.get(y*mapMask.getWidth()+x).inChunk) {
                    while (mapMaskPos.get(y*mapMask.getWidth()+x).inChunk) {
                        if (notCheckedPos.isEmpty()) {
                            break;
                        }
                        x = (int)notCheckedPos.peek().xOnMap;
                        y = (int)notCheckedPos.peek().yOnMap;
                        
                        notCheckedPos.pop();
                    }
                    if (mapMaskPos.get(y*mapMask.getWidth()+x).inChunk) {
                        break;
                    }
                }
                posIndex++;
            }
            else {
                break;
            }
        }
        
        //-----------------------------------------------------------------------------------------------------------
        // Fill() Recursive Version
        //-----------------------------------------------------------------------------------------------------------
//        if (mapMaskPos.get(y*mapMask.getWidth()+x).inChunk) {
//            return;
//        }
//        System.out.println("---------------------------------------------------------------------");
//        System.out.println("Testing MapMask.java::fill() begins");
//        System.out.println("---------------------------------------------------------------------");
//        System.out.println("x: " + x + " y: " + y);
//        
//        mapMaskPos.get(y*mapMask.getWidth()+x).inChunk = true;
//        chunkColl.get(chunkIndex).posColl.add(mapMaskPos.get(y*mapMask.getWidth()+x));
//        System.out.println("chunkIndex: " + chunkIndex + " posIndex: " + posIndex);
//        System.out.println("index of " + posIndex + " x: " + chunkColl.get(chunkIndex).posColl.get(posIndex).xOnMap + " y: " + chunkColl.get(chunkIndex).posColl.get(posIndex).yOnMap);
//        posIndex++;
//        System.out.println("---------------------------------------------------------------------");
//        System.out.println("Testing MapMask.java::fill() ends");
//        System.out.println("---------------------------------------------------------------------");
//        
//        if (y-1 >= 0 && !mapMaskPos.get((y-1)*mapMask.getWidth()+x).inChunk && new Color(mapMask.getRGB(x,y-1), true).getAlpha() > 0) { // north?
//            fill(width, height, x, y-1, chunkIndex, posIndex);
//        }
//        if (x+1 < width && !mapMaskPos.get(y*mapMask.getWidth()+(x+1)).inChunk && new Color(mapMask.getRGB(x+1,y), true).getAlpha() > 0) { // east?
//            fill(width, height, x+1,y, chunkIndex, posIndex);
//        }
//        if (y+1 < height && !mapMaskPos.get((y+1)*mapMask.getWidth()+x).inChunk && new Color(mapMask.getRGB(x, y+1), true).getAlpha() > 0) { // south?
//            fill(width, height, x, y+1, chunkIndex, posIndex);
//        }
//        if (x-1 >= 0 && !mapMaskPos.get(y*mapMask.getWidth()+(x-1)).inChunk && new Color(mapMask.getRGB(x-1, y), true).getAlpha() > 0) { // west?
//            fill(width, height, x-1, y, chunkIndex, posIndex);
//        }
////        System.exit(1);
//        return;
    }
    
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    // Cenvex Hull Algorithm:
    // 출처: http://www.geeksforgeeks.org/convex-hull-set-1-jarviss-algorithm-or-wrapping/
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    public static int orientation(Pos p, Pos q, Pos r)
    {
        double val = (q.yOnMap - p.yOnMap) * (r.xOnMap - q.xOnMap) -
                  (q.xOnMap - p.xOnMap) * (r.yOnMap - q.yOnMap);
     
        if (val == 0.0) return 0;  // colinear
        return (val > 0.0)? 1: 2; // clock or counterclock wise
    }
    
    public static void convexHull(Vector<Pos> mapMaskVertexTest, int size)
    {
        // There must be at least 3 points
        if (size < 3) return;
     
        // Initialize Result
//        vector<Point> hull;
     
        // Find the leftmost point
        int l = 0;
        for (int i = 1; i < size; i++)
            if (mapMaskVertexTest.get(i).x < mapMaskVertexTest.get(l).x)
                l = i;
        
        // Start from leftmost point, keep moving counterclockwise
        // until reach the start point again.  This loop runs O(h)
        // times where h is number of points in result or output.
        int p = l, q;
        do
        {
            // Add current point to result\
            chunkColl.get(chunkIndex).mapMaskHullTest.add(mapMaskVertexTest.get(p));
     
            // Search for a point 'q' such that orientation(p, x,
            // q) is counterclockwise for all points 'x'. The idea
            // is to keep track of last visited most counterclock-
            // wise point in q. If any point 'i' is more counterclock-
            // wise than q, then update q.
            q = (p+1)%size;
            for (int i = 0; i < size; i++)
            {
               // If i is more counterclockwise than current q, then
               // update q
               if (orientation(mapMaskVertexTest.get(p), mapMaskVertexTest.get(i), mapMaskVertexTest.get(q)) == 2)
                   q = i;
            }
     
            // Now q is the most counterclockwise with respect to p
            // Set p as q for next iteration, so that q is added to
            // result 'hull'
            p = q;
        } while (p != l);  // While we don't come to first point
     
        // Print Result
//        for (int i = 0; i < mapMaskHullTest.size(); i++)
//            System.out.println("(" + mapMaskHullTest.get(i).x + ", " + mapMaskHullTest.get(i).y + ")");
    }
    
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    // end of "Cenvex Hull Algorithm"
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
}
