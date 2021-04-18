package starcraft;

import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


import chai_utils.CollisionDetection;

//public class Starcraft extends Canvas implements MouseListener, MouseMotionListener {
public class Starcraft extends JPanel implements MouseListener, MouseMotionListener {
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    // Constants
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    
    //-------------------------------------------------------------------------
    // Flag
    //-------------------------------------------------------------------------
//    public static final boolean DRAW_COLLIDER = true;
    public static final boolean DRAW_COLLIDER = false;

    //-------------------------------------------------------------------------
    // General
    //-------------------------------------------------------------------------
    public static final int JFRAME_START_Y_DIFF = 22;

    public static final int W = 1280;
    public static final int H = 720;
//    public static final int W = 1400;
//    public static final int H = 1000;

    public static final int MAP01_W = 2048;
    public static final int MAP01_H = 2048;
    public static final int UI_H = 173;

    public static final int START_MAP01_X = 0;
    public static final int START_MAP01_Y = 0 + JFRAME_START_Y_DIFF;
    
    public static final int MINIMAP01_X = 165;
    public static final int MINIMAP01_Y = 520 + JFRAME_START_Y_DIFF;
    
    public static final int PORTRAIT_X = 794;
    public static final int PORTRAIT_Y = 628 + JFRAME_START_Y_DIFF;
    
    public static final int PORTRAIT_W = 60;
    public static final int PORTRAIT_H = 56;

//    public static final double SCREEN_X = 0.0;
//    public static final double SCREEN_Y = 0.0;
//    public static final int SCREEN_W = 1660;
//    public static final int SCREEN_H = 930;
//    public static final int SCREEN_W = 512;
//    public static final int SCREEN_H = 512;

    public static final int MAIN_GAME_TIMER_DELAY_IN_MILLISEC = 5;
//    public static final int MAIN_GAME_TIMER_DELAY_IN_MILLISEC = 50;
    public static final int FPS = 1000/MAIN_GAME_TIMER_DELAY_IN_MILLISEC;
    
    public static final int MAP_MOVED_BY_MOUSED_SPEED = 5;
    
    public static int ticks = 0;
    
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    // Instance Variables(= Member Variables)
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    // Double Buffering
    //-------------------------------------------------------------------------
    public static BufferStrategy bufferStrategy = null;
    
    //-------------------------------------------------------------------------
    // Global
    //-------------------------------------------------------------------------
    public static JFrame frame;

    public static Random r = new Random();

    public static ImageManager imageManager;

    //-------------------------------------------------------------------------
    // Background/Map
    //-------------------------------------------------------------------------
    public static BufferedImage map01;
    public static BufferedImage map01Mask;
    public static BufferedImage userInterface;
    public static BufferedImage miniMap01;
    
    public static double map01X;
    public static double map01Y;
    public static int map01W;
    public static int map01H;
    public static int map01HPlusUI;
    public static double miniMap01X;
    public static double miniMap01Y;
    public static int miniMap01W;
    public static int miniMap01H;
    public static double miniMapRatio;
    
    public static int screenX;
    public static int screenY;
    
    public static int screenW;
    public static int screenH;
    
//    public static double screenXOnMap;
//    public static double screenYOnMap;
//    public static int screenW;
//    public static int screenH;
    
//    public static MapMask map01MaskOnMap;
    public static Vector<Collider> bgColliders;

    //-------------------------------------------------------------------------
    // Hero
    //-------------------------------------------------------------------------
    public static Hero hero01;
    
    public static boolean heroMouseMoving;

    //-------------------------------------------------------------------------
    // Enemy
    //-------------------------------------------------------------------------
    private static Enemy [] enemies;
      
    //-------------------------------------------------------------------------
    // Bullet
    //-------------------------------------------------------------------------
    public static boolean fired;
    public static int bulletsAmount;

    //-------------------------------------------------------------------------
    // Mouse
    //-------------------------------------------------------------------------
    public double mousePressedX;
    public double mousePressedY;
    public long pressedTime;
    
    public double mouseReleasedX;
    public double mouseReleasedY;
    public long releasedTime;

    public int mouseX;
    public int mouseY;

    public long startTimeMillisec;
    
    //-------------------------------------------------------------------------
    // Key handling
    //-------------------------------------------------------------------------
    public static boolean wKeyPressed;
    public static boolean dKeyPressed;
    public static boolean sKeyPressed;
    public static boolean aKeyPressed;
    public static boolean spaceKeyPressed;
    public static boolean ctrlKeyPressed;
    
    public static boolean spaceKeyReleased;

    public static boolean iKeyPressed;
    public static boolean oKeyPressed;
    
    public static boolean key1Pressed;
    public static boolean key2Pressed;
    public static boolean key3Pressed;
    public static boolean key4Pressed;
    public static boolean key5Pressed;
    public static boolean key6Pressed;
    public static boolean key7Pressed;
    public static boolean key8Pressed;
    public static boolean key9Pressed;
    public static boolean key0Pressed;
    
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    // Methods(= Member Functions)
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    // Starcraft() <- constructor
    //-------------------------------------------------------------------------
    public Starcraft() {
        setFocusable(true);
        frame.add(this, new Integer(0), 0);
        
        imageManager = new ImageManager();

        //---------------------------------------------------------------------
        // read bg image
        //---------------------------------------------------------------------
//        screenX = SCREEN_X;
//        screenY = SCREEN_Y;
//        screenW = SCREEN_W;
//        screenH = SCREEN_H;

        String imageFilename = "assets/background/background.png";
        try {
            map01 = ImageIO.read(new File(imageFilename));
            map01W = map01.getWidth();
            map01H = map01.getHeight();
            map01HPlusUI = map01H + UI_H;
        } catch (IOException e) {
            System.out.println("Starcraft::Starcraft(): error - image file not found! : " + imageFilename);
            System.exit(1);
        }

        String imageMaskFilename = "assets/background/background_mask.png";
        try {
            map01Mask = ImageIO.read(new File(imageMaskFilename));
        } catch (IOException e) {
            System.out.println("Starcraft::Starcraft(): error - image file not found! : " + imageMaskFilename);
            System.exit(1);
        }
        
        String imageUIFilename = "assets/user_interface_copy.png";
        try {
            userInterface = ImageIO.read(new File(imageUIFilename));
        } catch (IOException e) {
            System.out.println("Starcraft::Starcraft(): error - image file not found! : " + imageUIFilename);
            System.exit(1);
        }
        
        String imageminiMapFilename = "assets/minimap.png";
        try {
            miniMap01 = ImageIO.read(new File(imageminiMapFilename));
            miniMap01X = MINIMAP01_X;
            miniMap01Y = MINIMAP01_Y;
            miniMap01W = miniMap01.getWidth();
            miniMap01H = miniMap01.getWidth();
            miniMapRatio = (double)miniMap01W/(double)map01W;
            System.out.println("minimap ratio: " + miniMapRatio);
        } catch (IOException e) {
            System.out.println("Starcraft::Starcraft(): error - image file not found! : " + imageminiMapFilename);
            System.exit(1);
        }
        
        map01X = START_MAP01_X;
        map01Y = START_MAP01_Y;
        
        // resize frame and canvas
        setBounds(0, 0, W, H);
        
        screenX = 0;
        screenY = 0;
        screenW = W;
        screenH = H;
        frame.setBounds(screenX, screenX, screenW, screenH + JFRAME_START_Y_DIFF);
        
        System.out.println("frame.getWidth(): " + frame.getWidth() + " frame.getHeight(): " +frame.getHeight());
//        System.out.println("alignment: " + frame.getX() + frame.getY());
//        System.exit(1);
        

//        int maskedSize = W*H;
        
//      public static MapMask map01MaskOnMap;
//        map01MaskOnMap = new MapMask( map01Mask);
        bgColliders = MapMask.createBgColliders(map01Mask);
        
//        System.out.println("--------------------------------------------------------------------------------------");
//        System.out.println("Testing Chunk class start");
//        System.out.println("--------------------------------------------------------------------------------------");
//        System.out.println("Starcraft.java::map01MaskOnMap.chunk.size() " + map01MaskOnMap.chunkColl.size());
//        System.out.println();
//        for (int i = 0; i < map01MaskOnMap.chunkColl.get(0).posColl.size(); i++) {
//            System.out.println("Starcraft.java::Starcraft() : line 208 - exit for testing chunk index: " + i + " x: " + map01MaskOnMap.chunkColl.get(0).posColl.get(i).xOnMap + " y: " + map01MaskOnMap.chunkColl.get(0).posColl.get(i).yOnMap);
//        }
//        System.out.println("boxCollider : " + map01MaskOnMap.chunkColl.get(0).boxCollider + " circleCollider: " + map01MaskOnMap.chunkColl.get(0).circleCollider);
//        System.out.println("--------------------------------------------------------------------------------------");
//        System.out.println("Testing Chunk class ends System.exit(1)");
//        System.out.println("--------------------------------------------------------------------------------------");
//        System.exit(1);
        
        //---------------------------------------------------------------------
        // hero
        //---------------------------------------------------------------------
        hero01 = new Hero();
        
        heroMouseMoving = false;

        //---------------------------------------------------------------------
        // enemy
        //---------------------------------------------------------------------
        enemies = new Enemy[Enemy.MAX_NUM_ENEMIES];
        for (int i = 0; i < Enemy.MAX_NUM_ENEMIES; i++) {
            int randRow = r.nextInt(MAP01_W);
            int randCol = r.nextInt(MAP01_H);
            while(new Color(Starcraft.map01Mask.getRGB(randRow, randCol), true).getAlpha() > 0) {
                randRow = r.nextInt(MAP01_W);
                randCol = r.nextInt(MAP01_H);
            }
            enemies[i] = new Enemy(randRow, randCol);
        }
//        enemy01 = new Enemy();
        
        //---------------------------------------------------------------------
        // bullet
        //---------------------------------------------------------------------
        fired = false;
        bulletsAmount = 0;
        
        //---------------------------------------------------------------------
        // update timer(thread)
        //---------------------------------------------------------------------
        ticks = 0;
        startTimeMillisec = System.currentTimeMillis();

        Timer mainGameLoopTimer = new Timer(true);
        mainGameLoopTimer.schedule(new java.util.TimerTask() {
            public void run() {
                update();

//                repaint();
                mypaint();
                
                ticks++;
//                //---------------------------------------------------------------------------
//                // Time Difference double version Start
//                //---------------------------------------------------------------------------
////              if (ticks % FPS == 0) {
//                long currentTimeMilli = System.currentTimeMillis();
//                double diffTimeMilli = (currentTimeMilli - startTimeMillisec) / 1000;
////                System.out.println("Seconds: " + (int)(ticks / FPS) + " " + diffTimeMilli);
//                System.out.println("hugeX: " + hero01.hugeX + " " + "hugeY: " + hero01.hugeY);
//                //---------------------------------------------------------------------------
//                // Time Difference Start
//                //---------------------------------------------------------------------------
//                if ((double)(ticks/FPS) < diffTimeMilli) {
//                    do {
//                        update();
//
//                        repaint();
//                        ticks++;
//                    } while((double)(ticks/FPS) < diffTimeMilli);
//                }
//                System.out.println("Seconds: " + (int)(ticks / FPS) + " " + diffTimeMilli);
//                //---------------------------------------------------------------------------
//                // Time Difference End
//                //---------------------------------------------------------------------------
////            }
//              //---------------------------------------------------------------------------
//              // Time Difference double version End
//              //---------------------------------------------------------------------------
                
                
            //---------------------------------------------------------------------------
            // Time Difference int version Start
            //---------------------------------------------------------------------------
//                if (ticks % FPS == 0) {
//                    long currentTimeMilli = System.currentTimeMillis();
//                    double diffTimeMilli = (currentTimeMilli - startTimeMillisec) / 1000;
////                    System.out.println("Seconds: " + (int)(ticks / FPS) + " " + diffTimeMilli);
////                    System.out.println("hugeX: " + hero01.hugeX + " " + "hugeY: " + hero01.hugeY);
//                    //---------------------------------------------------------------------------
//                    // Time Difference Start
//                    //---------------------------------------------------------------------------
//                    if ((int)(ticks/FPS) < diffTimeMilli) {
//                        do {
//                            update();
//
//                            repaint();
//                            ticks++;
//                        } while((int)(ticks/FPS) < diffTimeMilli);
//                    }
//                    System.out.println("Seconds: " + (int)(ticks / FPS) + " " + diffTimeMilli);
//                  //---------------------------------------------------------------------------
//                  // Time Difference End
//                  //---------------------------------------------------------------------------
//                }
            //---------------------------------------------------------------------------
            // Time Difference int version End
            //---------------------------------------------------------------------------
            }
        },0,MAIN_GAME_TIMER_DELAY_IN_MILLISEC); // <-- 여기에서 FPS값으로 thread가 1초에 실행되는 횟수를 조절하게 됨.

        //-------------------------------------------------------------------------
        // Key handling
        //-------------------------------------------------------------------------
        wKeyPressed = false;
        dKeyPressed = false;
        sKeyPressed = false;
        aKeyPressed = false;
        spaceKeyPressed = false;
        ctrlKeyPressed = false;
        
        spaceKeyReleased = true;
        
        iKeyPressed = false;
        oKeyPressed = false;
        
        key1Pressed = false;
        key2Pressed = false;
        key3Pressed = false;
        key4Pressed = false;
        key5Pressed = false;
        key6Pressed = false;
        key7Pressed = false;
        key8Pressed = false;
        key9Pressed = false;
        key0Pressed = false;
        
      //---------------------------------------------------------------------
        // key handler
        //---------------------------------------------------------------------
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        wKeyPressed = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dKeyPressed = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        sKeyPressed = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        aKeyPressed = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        //-----------------------------------------------------------------
                        //Hero의 위치를 찾아서 map의 좌표를 바꾸는 코드 starts
                        //-----------------------------------------------------------------
                        if (hero01.xOnMap < W/2) {
                            map01X = 0;
                        }
                        else if (hero01.xOnMap > map01W-(W/2)) {
                            map01X = -(map01W-W);
                        }
                        else {
                            map01X = -(hero01.xOnMap-(W/2));
                        }
                        if (hero01.yOnMap < H/2) {
                            map01Y = 0;
                        }
                        else if (hero01.yOnMap > map01H-(H/2)) {
                            map01Y = -(map01HPlusUI-H-JFRAME_START_Y_DIFF);
                        }
                        else {
                            map01Y = -(hero01.yOnMap-(H/2));
                        }
                        //-----------------------------------------------------------------
                        //Hero의 위치를 찾아서 map의 좌표를 바꾸는 코드 ends
                        //-----------------------------------------------------------------
//                            if (spaceKeyReleased && !hero.jumping && !hero.falling && !hero.onLadder) {
//                                clipJumpSound.stop();
//                                clipJumpSound.setFramePosition(0);
//                                clipJumpSound.start();
//                             if (hero != null) {
//                                    hero.jump();
//                                }
//                            } 

//                          System.out.println("SPACE!!!!!!!!!!!!");
                        spaceKeyPressed = true;
//                        spaceKeyReleased = false;
                        break;
                    case KeyEvent.VK_CONTROL:
                        ctrlKeyPressed = true;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        hero01.clicked = false;
//                            if (pickupedItem != null) {
//                                if (pickupedItemFromWhichPanel == PANEL_NUMBER_SHOP_PANEL) {
//                                    shopPanel.putItemBack(pickupedItem, pickupedItemSlotIndex);
//                                    shopPanel.repaint();
//                                }
//                                else if (pickupedItemFromWhichPanel == PANEL_NUMBER_INV_PANEL) {
//                                    inventoryPanel.putItemBack(pickupedItem, pickupedItemSlotIndex);
//                                    inventoryPanel.repaint();
//                                }
//                                    
//                                Megaman.pickupedItemFromWhichPanel = Megaman.PANEL_NUMBER_NONE;
//                                Megaman.pickupedItemSlotIndex = -1;
//                                Megaman.pickupedItem = null;
//                            }
                        break;
                    case KeyEvent.VK_1:
                        key1Pressed = true;
//                        if (inventoryPanel != null) {
//                                inventoryPanel.useItemAt(0);
//                        }
                        break;
                    case KeyEvent.VK_2:
                        key2Pressed = true;
//                        if (inventoryPanel != null) {
//                                inventoryPanel.useItemAt(1);
//                        }
                        break;
                    case KeyEvent.VK_3:
                        key3Pressed = true;
//                        if (inventoryPanel != null) {
//                                inventoryPanel.useItemAt(2);
//                        }
                        break;
                    case KeyEvent.VK_4:
                        key4Pressed = true;
//                        if (inventoryPanel != null) {
//                                inventoryPanel.useItemAt(3);
//                        }
                        break;
                    case KeyEvent.VK_5:
                        key5Pressed = true;
//                        if (inventoryPanel != null) {
//                                inventoryPanel.useItemAt(4);
//                        }
                        break;
                    case KeyEvent.VK_6:
                        key6Pressed = true;
//                                JOptionPane.showMessageDialog(null, "The price of SpeedItem is $" + SpeedItem.PRICE + ".");
                        break;
                    case KeyEvent.VK_7:
                        key7Pressed = true;

                        break;
                    case KeyEvent.VK_8:
                        key8Pressed = true;

                        break;
                    case KeyEvent.VK_9:
                        key9Pressed = true;

                        break;
                    case KeyEvent.VK_0:
                        key0Pressed = true;

                        break;
                    default:
                        //super.keyPressed(e);
                }
                
                switch (e.getKeyChar()) {
                    case 'w':
                    case 'W':
                        wKeyPressed = true;
//                        hero01.move();
                        break;
                    case 'd':
                    case 'D':
                        dKeyPressed = true;
//                        hero01.move();
                        break;
                    case 's':
                    case 'S':
                        System.out.println("Starcraft::Starcraft()::keyPressed(): case \'S\'");
                        sKeyPressed = true;
//                        hero01.move();
                        break;
                    case 'a':
                    case 'A':
                        aKeyPressed = true;
//                        hero01.move();
                        break;
                    case 'i':
                    case 'I':
                            iKeyPressed = true;
//                            if (inventoryPanel != null) {
//                                inventoryPanel.visible = !inventoryPanel.visible;
//                                inventoryPanel.setVisible(inventoryPanel.visible);
//                            }
                        break;
                    case 'o':
                    case 'O':
                            oKeyPressed = true;
//                            if (shopPanel != null) {
//                                shopPanel.visible = !shopPanel.visible;
//                                shopPanel.setVisible(shopPanel.visible);
//                            }
                        break;
                    case 'c':
                    case 'C':
//                            if (chatPanel != null) {
//                                chatPanelTurnOn = !chatPanelTurnOn;
//                             chatPanel.setVisible(chatPanelTurnOn);
//                            }
                        break;
                    default:
                        super.keyPressed(e);
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        wKeyPressed = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        dKeyPressed = false;
                        break;
                    case KeyEvent.VK_DOWN:
//                        System.out.println("Starcraft::Starcraft():::keyReleased(): case KeyEvent.VK_DOWN");
                        sKeyPressed = false;
                        break;
                    case KeyEvent.VK_LEFT:
                        aKeyPressed = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        spaceKeyPressed = false;
                           spaceKeyReleased = true;
                        break;
                    case KeyEvent.VK_1:
                        key1Pressed = false;
                        break;
                    case KeyEvent.VK_2:
                        key2Pressed = false;
                        break;
                    case KeyEvent.VK_3:
                        key3Pressed = false;
                        break;
                    case KeyEvent.VK_4:
                        key4Pressed = false;
                        break;
                    case KeyEvent.VK_5:
                        key5Pressed = false;
                        break;
                    default:
                        //super.keyPressed(e);
                }
                
                switch (e.getKeyChar()) {
                    case 'w':
                    case 'W':
                        wKeyPressed = false;
                        break;
                    case 'd':
                    case 'D':
                        dKeyPressed = false;
                        break;
                    case 's':
                    case 'S':
//                        System.out.println("Starcraft::Starcraft()::keyReleased(): case \'S\'");
                        sKeyPressed = false;
                        break;
                    case 'a':
                    case 'A':   
                        aKeyPressed = false;
                        break;
                    case 'i':
                    case 'I':   
                        iKeyPressed = false;
                        break;
                    default:
                        super.keyPressed(e);
                }
            }
        });
        
        addMouseListener( this );
        addMouseMotionListener( this );
    }

//=============================================================================
//=============================================================================
//=============================================================================
// PAINT
//=============================================================================
//=============================================================================
//=============================================================================

    public void paint(Graphics g) {
//        super.paint(g);
//        
////        System.out.println("Starcraft::paint():");
//        Graphics2D g2d = (Graphics2D)g;
////        for (int i = 0; i < Bullet.BULLET_MAX_SIZE; i++) {
////            if (!hero01.bullets[i].died) {
//                
//////                g2d.rotate(3);
////                hero01.bullets[i].paint(g);                
////            }
////        }
//                
////        map01X++;
////        map01Y++;
//        g.drawImage(map01, (int)map01X, (int)map01Y, null);
////        g.drawImage(map01Mask, (int)map01X, (int)map01Y, null);
//
//
//        
//        if (Starcraft.DRAW_COLLIDER && bgColliders != null) {
//            for (int i = 0; i < bgColliders.size(); i++) {
//                bgColliders.get(i).paint(g);
//            }
//        }
//        
////        if (!enemy01.died) {
////            enemy01.paint(g);
////        }
//        g.drawImage(miniMap01, (int)miniMap01X, (int)miniMap01Y, null);
//        for (int i = 0; i < enemies.length; i++) {
//            if (!enemies[i].died) {
//                enemies[i].paint(g);
//            }
//        }
//        hero01.paint(g);
//        g.drawImage(userInterface, (int)0, (int)0, null);
    }

    public void mypaint() {
//        System.out.println("Starcraft::mypaint(): start");

        if (bufferStrategy == null) {
            return;
        }
        
//        if (!gameStart) {
//            return;
//        }
        
//        // 중간 중간에 loadingPanel을 켰을때 나오도록 함.
//        if (loadingPanel != null && loadingPanelOn) {
//            Graphics g2 = (Graphics) bufferStrategy.getDrawGraphics();
//            
//            // gameOverPanel이 사라지도록 NimbleQuest.mypaint()에서 기존의 gameLevel이 살짝만 더 오래 그려지도록 하였다.
//            if (!nextGameLevelLoadingStarted) {
//                gameLevel.paint(g2);
//            }
//            
//            loadingPanel.mypaint(g2);
//            
//            g2.dispose();
//            g2 = null;
//            bufferStrategy.show();
//            return;
//        }

//        if (!gameStart) {
//            if (gameOverPanel != null || nextStagePanel != null) {
////            if (gameOverPanel != null) {
//                Graphics g2 = (Graphics) bufferStrategy.getDrawGraphics();
//
//                gameLevel.paint(g2);
//
//                if (gameOverPanel != null) {
//                    gameOverPanel.mypaint(g2);
//                }
//                if (nextStagePanel != null) {
//                    nextStagePanel.mypaint(g2);
//                }
//                
//                g2.dispose();
//                g2 = null;
//                bufferStrategy.show();
//            }
//            return;
//        }
        
        Graphics g2 = (Graphics) bufferStrategy.getDrawGraphics();
        
//        gameLevel.paint(g2);
        g2.drawImage(map01, (int)map01X, (int)map01Y, null);

        if (Starcraft.DRAW_COLLIDER && bgColliders != null) {
            for (int i = 0; i < bgColliders.size(); i++) {
                bgColliders.get(i).paint(g2);
            }
        }
        
//        if (!enemy01.died) {
//            enemy01.paint(g);
//        }
        g2.drawImage(miniMap01, (int)miniMap01X, (int)miniMap01Y, null);
        for (int i = 0; i < enemies.length; i++) {
            if (!enemies[i].died) {
                enemies[i].paint(g2);
            }
        }
        hero01.paint(g2);
        g2.drawImage(userInterface, (int)0, (int)JFRAME_START_Y_DIFF, null);
        
//        score.paint(g2);
      
//        if (NimbleQuest.itemPickup != null) {
//          g2.drawImage(NimbleQuest.itemPickup.image, mouseX, mouseY, null);
//        }

//        imgBtnShop.paint(g2);
//        imgBtnSetting.paint(g2);
//        imgBtnRestart.paint(g2);
//        imgBtnSound.paint(g2);
//        imgBtnMap.paint(g2);
//        imgBtnLeft.paint(g2);
//        imgBtnRight.paint(g2);
//        imgBtnUp.paint(g2);
//        imgBtnDown.paint(g2);
        
//        if (gameOverPanel != null) {
//            gameOverPanel.mypaint(g2);
//        }
        
//        if (nextStagePanel != null) {
//            nextStagePanel.mypaint(g2);
//        }
        
//        g2.drawImage(bossAnimatedImage[curIndexBoss], 0, 0, null);
//        curIndexBoss = (curIndexBoss + 1) % bossAnimatedImage.length;
        //---------------------------------------------------------------------
        
        g2.dispose();
        g2 = null;
        bufferStrategy.show();
        
//        System.out.println("Starcraft::mypaint(): end");
    }
    
//=============================================================================
//=============================================================================
//=============================================================================
// UPDATE
//=============================================================================
//=============================================================================
//=============================================================================

    public void update() {
        //--------------------------------------------------------------------------------
        // screen의 변화에대한 control starts
        //--------------------------------------------------------------------------------
        if (frame.getX() != screenX || frame.getY() != screenY) {
            screenX = frame.getX();
            screenY = frame.getY();
            frame.setBounds(screenX, screenY, screenW, screenH + JFRAME_START_Y_DIFF);
        }
        
//        if (frame.getWidth() != screenW || frame.getHeight() != screenH) {
//            screenW = frame.getWidth();
//            screenH = frame.getHeight();
////            setBounds(0, 0, frame.getWidth(), frame.getHeight());
//            frame.setBounds(0, 0, screenW, screenH);
//        }
        //--------------------------------------------------------------------------------
        // screen의 변화에대한 control ends
        //--------------------------------------------------------------------------------
//        System.out.println("frame.getWidth(): " + frame.getWidth() + " frame.getHeight(): " +frame.getHeight());
        //--------------------------------
        // map 움직이게 하는 controller starts
        //--------------------------------
        mouseX = MouseInfo.getPointerInfo().getLocation().x;
        mouseY = MouseInfo.getPointerInfo().getLocation().y + JFRAME_START_Y_DIFF;
        
        if (mouseX > screenX+screenW-20) {
            double ratio = 1.0-Math.abs((mouseX-(screenX+screenW))/20.0);
            if (ratio <= 0.0 || mouseX > screenX+screenW) {
                ratio = 1.0;
            }
            if (screenW-map01X+MAP_MOVED_BY_MOUSED_SPEED < map01W) {
                System.out.println("ratio: " + ratio + " screenX: " + screenX + " mouseX: " + mouseX);
                map01X -= MAP_MOVED_BY_MOUSED_SPEED*ratio;
            }
        }
        
        if (mouseX < screenX+20) {
            double ratio = 1.0-Math.abs((mouseX-screenX)/(20.0));
            if (ratio <= 0.0 || mouseX < screenX) {
                ratio = 1.0;
            }
            if (map01X+MAP_MOVED_BY_MOUSED_SPEED <= 0.0) {
                System.out.println("ratio: " + ratio + " screenX: " + screenX + " mouseX: " + mouseX);
                map01X += MAP_MOVED_BY_MOUSED_SPEED*ratio;
            }          
        }
        
        if (mouseY > screenY+screenH+JFRAME_START_Y_DIFF+23-20) {
            double ratio = 1.0-Math.abs((mouseY-(screenY+screenH+JFRAME_START_Y_DIFF+23))/20.0);
            if (ratio <= 0.0 || mouseY > screenY+screenH+JFRAME_START_Y_DIFF+23) {
                ratio = 1.0;
            }
            if (screenH+JFRAME_START_Y_DIFF-map01Y+MAP_MOVED_BY_MOUSED_SPEED < map01HPlusUI) {
                System.out.println("ratio: " + ratio + " screenY: " + (screenY+screenH+JFRAME_START_Y_DIFF) + " mouseY: " + mouseY);
                map01Y -= MAP_MOVED_BY_MOUSED_SPEED*ratio;
            }
          
        }
        
        if (mouseY < screenY+JFRAME_START_Y_DIFF+23+20) {
            double ratio = 1.0-Math.abs((mouseY-(screenY+JFRAME_START_Y_DIFF+23))/20.0);
            if (ratio <= 0.0 || mouseY < screenY+JFRAME_START_Y_DIFF+23) {
                ratio = 1.0;
            }
            if (map01Y+JFRAME_START_Y_DIFF+MAP_MOVED_BY_MOUSED_SPEED <= 0.0) {
                System.out.println("ratio: " + ratio + " screenY: " + (screenY+JFRAME_START_Y_DIFF+23) + " mouseY: " + mouseY);

                map01Y += MAP_MOVED_BY_MOUSED_SPEED*ratio;
            }
          
        }
        //--------------------------------
        // map 움직이게 하는 controller ends
        //--------------------------------
//        System.out.println("mouse: " + MouseInfo.getPointerInfo().getLocation().x);
        hero01.update();

        for (int i = 0; i < enemies.length; i++) {
                enemies[i].update();
        }
        
//        for (int i = 0; i < bgColliders.size(); i++) {
//            map01MaskOnMap.chunkColl.get(i).update();
//        }
//        System.out.println("time: " + System.currentTimeMillis());
    }

//=============================================================================
//=============================================================================
//=============================================================================
// COLLISION DETECTION
//=============================================================================
//=============================================================================
//=============================================================================
    
    public static Enemy processCollisionWithEnemies(Unit unit) {
        for (int i = 0; i < enemies.length; i++) {
            if (!enemies[i].died &&
//                CollisionDetection.collideWithUnitMask(unit.image, (int)unit.x, (int)unit.y, enemies[i].image, (int)enemies[i].x, (int)enemies[i].y)) {
                unit.collider != null &&  (unit.collider.collisionDetection(enemies[i].collider) || enemies[i].collider.collisionDetection(unit.collider))) {
                return enemies[i];
            }
        }
        return null;
    }
    
    public static boolean processCollisionWithChunks(Unit unit, int nextXOnMap, int nextYOnMap) {
        double prevXOnMap = unit.xOnMap;
        double prevYOnMap = unit.yOnMap;
        unit.xOnMap = nextXOnMap;
        unit.yOnMap = nextYOnMap;
        for (int i = 0; i < bgColliders.size(); i++) {
            if (unit.collider != null && (unit.collider.collisionDetection(bgColliders.get(i)) || 
                bgColliders.get(i).collisionDetection(unit.collider))) {
                unit.xOnMap = prevXOnMap;
                unit.yOnMap = prevYOnMap;
                return true;
            }
        }
        unit.xOnMap = prevXOnMap;
        unit.yOnMap = prevYOnMap;
        return false;
    }
    
    public static boolean isOnMiniMap(double x, double y) {
        if (x >= miniMap01X && x <= miniMap01X + miniMap01W && y >= miniMap01Y && y <= miniMap01Y + miniMap01H) {
            return true;
        }
        return false;
    }
    
    public static boolean isOnPortrait(double x, double y) {
        if (x >= PORTRAIT_X && x <= PORTRAIT_X + PORTRAIT_W && y >= PORTRAIT_Y && y<= PORTRAIT_Y + PORTRAIT_H) {
            return true;
        }
        return false;
    }
    
    //==================================================================================================== Mouse listener methods
    //==================================================================================================== Mouse listener methods
    //==================================================================================================== Mouse listener methods
    // Mouse listener methods
    //==================================================================================================== Mouse listener methods
    //==================================================================================================== Mouse listener methods
    //==================================================================================================== Mouse listener methods

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY() + JFRAME_START_Y_DIFF;

//        hero01.x = mouseX;
//        hero01.y = mouseY;
        if (new Color(Starcraft.userInterface.getRGB((int)mouseX, (int)mouseY), true).getAlpha() <= 0) {
            if(SwingUtilities.isLeftMouseButton(e)) {
                if (hero01.clicked && !isOnMiniMap(mouseX, mouseY) && !isOnPortrait(mouseX, mouseY)) {
                    // 이 밑에 hero01.fired = false를 쓰게되면 bullet이 연속으로 아주 빠르게 발사된다. 
//                    hero01.fired = false;
                    hero01.shoot(mouseX-map01X,mouseY-map01Y);
                }
            }
            else {
                if (hero01.x <= mouseX && hero01.x+hero01.anims.curSprite.curImage.getWidth() >= mouseX && hero01.y <= mouseY && 
                    hero01.y+hero01.anims.curSprite.curImage.getHeight() >= mouseY) {
                        hero01.clicked = true;
                }
                else {
                    if (hero01.clicked) {
                        if (isOnMiniMap(mouseX, mouseY)) {
                        }
                        else if (isOnPortrait(mouseX, mouseY)) {
                        }
                        else {
                            //--------------------------------------------------------
                            // Lol느낌이 나는 마우스 조작 코드 starts 이걸 없애면 스타느낌 
                            //--------------------------------------------------------
                            hero01.targetXOnMap = mouseX-map01X;
                            hero01.targetYOnMap = mouseY-map01Y;
                            //--------------------------------------------------------
                            // Lol느낌이 나는 마우스 조작 코드 ends
                            //--------------------------------------------------------
                        }
                    }
//                    hero01.clicked = false;
                }
            }
        }
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY() + JFRAME_START_Y_DIFF;
        
//        System.out.println("x: " + mouseX + " y: " + mouseY + " heroX: " + hero01.x + " heroY: " + hero01.y + " heroMapX: " + hero01.xOnMap + " heroMapY: " + hero01.yOnMap);
        

        
        
//        if (heroMouseMoving) {
//            mouseX = mouseX;
//            mouseY = mouseY;
//    
//    //        System.out.println("x: " + mouseX + " y: " + mouseY);
//            hero01.x = mouseX;
//            hero01.y = mouseY;
//        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY() + JFRAME_START_Y_DIFF;
        
        fired = true;
//        System.out.println("mouseX= " + (mouseX-map01X) + " mouseY: " + (mouseY-map01Y));
//        System.out.println("heroX()= " + hero01.xOnMap + " heroY(): " + hero01.yOnMap);
        //--------------------------------------------
        //For ver0015
        //--------------------------------------------
//        mouseX = mouseX;
//        mouseY = mouseY;
        
//        hero01.x = mouseX;
//        hero01.y = mouseY;
        
//        hero01.update();
        
        if (new Color(Starcraft.userInterface.getRGB((int)mouseX, (int)mouseY), true).getAlpha() <= 0) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (hero01.clicked && !isOnMiniMap(mouseX, mouseY) && !isOnPortrait(mouseX, mouseY)) {
                    hero01.fired = false;
                    hero01.shoot(mouseX-map01X,mouseY-map01Y);
                }
            }
            
            if (SwingUtilities.isRightMouseButton(e)) {
                if (hero01.x <= mouseX && hero01.x+hero01.anims.curSprite.curImage.getWidth() >= mouseX && hero01.y <= mouseY && 
                    hero01.y+hero01.anims.curSprite.curImage.getHeight() >= mouseY) {
                        hero01.clicked = true;
                        hero01.music = true;// 좋은 design decision??
                }
                else {
//                    hero01.clicked = false;
                    // 만약 hero의 paint()가 버벅거리면 여기서 문제가 일어날수도 있다 
                    if (hero01.clicked) {
                        if (isOnMiniMap(mouseX, mouseY)) {

                        }
                        else if (isOnPortrait(mouseX, mouseY)) {
                        }
                        else {
                            //--------------------------------------------------------
                            // Lol느낌이 나는 마우스 조작 코드 starts 이걸 없애면 스타느낌 
                            //--------------------------------------------------------
                            hero01.targetXOnMap = mouseX-map01X;
                            hero01.targetYOnMap = mouseY-map01Y;
                            //--------------------------------------------------------
                            // Lol느낌이 나는 마우스 조작 코드 ends
                            //--------------------------------------------------------
                        }
                    }
                }
            }
        }
//        
//        mousePressedX = mouseX;
//        mousePressedY = mouseY;
//        
//        hero01.x = mouseX;
//        hero01.y = mouseY;
//        
//        heroMouseMoving = true;
//        
//        pressedTime = System.currentTimeMillis();
        
//        System.out.println("pressedTime: " + pressedTime);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        fired = false;
        hero01.fired = true;
        //--------------------------------------------
        //For ver0015
        //--------------------------------------------
//        mouseX = mouseX;
//        mouseY = mouseY;
//        
//        hero01.x = mouseX;
//        hero01.y = mouseY;
//        
//        mouseReleasedX = mouseX;
//        mouseReleasedY = mouseY;
//        
//        hero01.xSpeed = mouseReleasedX - mousePressedX;
//        if (hero01.xSpeed < 0.0) {
//            hero01.xSpeed *= -1.0;
//            hero01.xDir = -1;
//        }
//        else {
//            hero01.xDir = 1;
//        }
//        
//        hero01.ySpeed = mouseReleasedY - mousePressedY;
//        if (hero01.ySpeed < 0.0) {
//            hero01.ySpeed *= -1.0;
//            hero01.yDir = -1;
//        }
//        else {
//            hero01.yDir = 1;
//        }
//
//        hero01.x = mouseX;
//        hero01.y = mouseY;
//        
//        heroMouseMoving = false;
//        
//        releasedTime = System.currentTimeMillis();
//        System.out.println("releasedTime: " + releasedTime);
//        
//        long timeDiffInMillisec = releasedTime - pressedTime;
//        
//        double deltaTime = timeDiffInMillisec / MAIN_GAME_TIMER_DELAY_IN_MILLISEC;
//        hero01.xSpeed /= deltaTime;
//        hero01.ySpeed /= deltaTime;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
 
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    // main()
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    private static Rectangle screenBounds;
    private static GraphicsDevice myScreen;
//    JTextField txtMsg = null;
    
    public static void main(String[] args) {
        System.out.println("Starcraft::main(): start");
        
        //---------------------------------------------------------------------
        // init bufferStrategy form double buffering
        //---------------------------------------------------------------------
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
        myScreen = env.getDefaultScreenDevice(); //maybe select a certain device
        GraphicsConfiguration gConf = myScreen.getDefaultConfiguration(); //maybe setup a different configuration
        screenBounds = gConf.getBounds();
        //---------------------------------------------------------------------

        Starcraft.frame = new JFrame("Starcraft");
        
        Starcraft.frame.setLayout(null);      // set the layout manager
        
        Toolkit tool = Starcraft.frame.getToolkit();
//        Dimension actualScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        System.out.println("screen size " + actualScreenSize);
//        System.exit(1);
        int width = Starcraft.W;
        int height = Starcraft.H;
        System.out.println(width + ", " + height);
        
//        Starcraft.frame.setBounds((width/3),(height/4), width, height);
        
        System.out.println("Starcraft::main(): hello1");

        ImageIcon img = new ImageIcon("frameIcon.GIF");
        Starcraft.frame.setIconImage(img.getImage());
        Starcraft.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Starcraft gamePanel = new Starcraft();
        System.out.println("Starcraft::main(): hello2");
//        gamePanel.setBounds(0, 0, Starcraft.W, Starcraft.H);
//        gamePanel.addMouseListener( gamePanel );
//        gamePanel.addMouseMotionListener( gamePanel );
        
        Starcraft.frame.setVisible(true);
        
        System.out.println("Starcraft::main(): end");
        
        //---------------------------------------------------------------------
        // init bufferStrategy form double buffering
        //---------------------------------------------------------------------
        try {
            bufferStrategy = null;
            BufferCapabilities bcaps = gConf.getBufferCapabilities();
            int amountBuffer = 0;
            if (bcaps.isMultiBufferAvailable() ){               
                amountBuffer = 3;
            }else {
                amountBuffer = 2;
            }
            Starcraft.frame.createBufferStrategy(amountBuffer, bcaps);
            //          frame.createBufferStrategy(2, bcaps);
            bufferStrategy = frame.getBufferStrategy();
            
            System.out.println("amountBuffer: " + amountBuffer);
            //          System.exit(1);
        } catch (HeadlessException e1) {
        } catch (AWTException e1) {
        }
        //------------------------------------------------------------------------------------
        //Sound Effect starts
        //------------------------------------------------------------------------------------
        try {
            File file = new File("assets/background_music.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
       
            // sleep to allow enough time for the clip to play
//            Thread.sleep(500);
       
            stream.close();
       
          } catch (Exception ex) {
            System.out.println(ex.getMessage());
          }

        //------------------------------------------------------------------------------------
        //Sound Effect ends
        //------------------------------------------------------------------------------------
        
    }
    
    public static int add(int num1, int num2) {
        return num1 + num2;
    }
}
