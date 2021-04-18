package starcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import chai_utils.CollisionDetection;

public class Hero extends Unit {
//    public static final String IMAGE_FILENAME_1 = "assets/zerg_move_forward_01.png";
//    public static final String IMAGE_FILENAME_2 = "assets/zerg_move_forward_02.png";
//    public static final String IMAGE_FILENAME_3 = "assets/zerg_move_forward_03.png";
//    public static final String IMAGE_FILENAME_4 = "assets/zerg_move_forward_04.png";
//    public static final String IMAGE_FILENAME_5 = "assets/zerg_move_forward_05.png";
//    public static final String IMAGE_FILENAME_6 = "assets/zerg_move_forward_06.png";
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    // Constants
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //------------------------------------------------------------------------- 
    
    //-------------------------------------------------------------------------
    // Hero Action State
    //-------------------------------------------------------------------------
    public static final int ACTION_STATE_NONE = -1;
    public static final int ACTION_STATE_MOVE_FORWARD = 0;
//    public static final int ACTION_STATE_IDLE = 0;
//    public static final int ACTION_STATE_RUNNING = 1;
//    public static final int ACTION_STATE_JUMPING = 2;
//    public static final int ACTION_STATE_FALLING = 3;
//    public static final int ACTION_STATE_RUNNING_SHOOT = 4;
//    public static final int ACTION_STATE_JUMPING_SHOOT = 5;
//    public static final int ACTION_STATE_STANDING_SHOOT = 6;
//    public static final int ACTION_STATE_CLIMBING = 7;
    
    //-------------------------------------------------------------------------
    // Hero Sprite
    //-------------------------------------------------------------------------
    public static final int HERO_NUM_ACTIONS = 2;
    
//  public static final int HERO_ACTION_IDLE = 0;
//  public static final int HERO_ACTION_RUNNING = 1;
//  public static final int PLAYER_ACTION_JUMPING = 2;
//  public static final int PLAYER_ACTION_FALLING = 3;
    
    public static final String[] HERO_SPRITE_ACTION_NAME = {
            "MOVE_FORWARD",
            "PORTRAIT" ,
//            "RUNNING",
//            "JUMPING",
//            "FALLING",
//            "JUMPING_SHOOT",
//            "FALLING_SHOOT",
//            "STANDING_SHOOT",
//            "CLIMBING",
    };
    public static final String[] HERO_SPRITE_FILENAME_PREFIX = {
            "assets/zerg_move_forward_",
            "assets/zdrfid_",
//            "assets/units/megaman_running_",
//            "assets/units/megaman_jump_",
//            "assets/units/megaman_falling_",
//            "assets/units/megaman_run_shoot_",
//            "assets/units/megaman_jump_shooting_",
//            "assets/units/megaman_stationary_shooting_",
//            "assets/units/megaman_climbing_",
    };
    public static final String[] HERO_SPRITE_FILENAME_POSTFIX = {
              ".png",
              ".png",
//            "_085x.png",
//            "_085x.png",
//            "_085x.png",
//            "_085x.png",
//            "_085x.png",
//            "_085x.png",
//            "_085x.png",
    };
    public static final int[] HERO_SPRITE_NUM_SPRITES = {
            6,
            45,
//            10,
//            4,
//            2,
//            12,
//            4,
//            3,
//            11,
    };
    
    public static final int HERO_SPRITE_GAME_FPS = 100; 
    public static final int[] HERO_SPRITE_SPRITE_FPS = {
             5, // 20
            5, // 20
//            10, // 8
//            8, // 10
//            2, // 30
//            5, // ?
//            5, // ?
//            5, // ?
//            5, // 20
    };
    public static final int[] HERO_SPRITE_START_IMAGE_INDEX = {
             0,
             0,
//            0,
//            0,
//            0,
//            0,
//            0,
//            0,
//            0,
    };
    public static final boolean[] HERO_SPRITE_LOOP = {
            true,
            true,
//            true,
//            false,
//            false,
//            false,
//            false,
//            false,
//            true,
    };
    
    //-------------------------------------------------------------------------
    // General
    //-------------------------------------------------------------------------

    public static final double IMAGE_W = 47;
    public static final double IMAGE_H = 40;

    public static final double START_X = Starcraft.W / 2 - (int)(IMAGE_W / 2);
    public static final double START_Y = Starcraft.H / 2 - (int)(IMAGE_H / 2);

    public static final double START_X_ON_MAP = -Starcraft.START_MAP01_X + START_X;
    public static final double START_Y_ON_MAP = -Starcraft.START_MAP01_Y + START_Y;

    public static final int START_X_DIR = 1;
    public static final int START_Y_DIR = 1;

//    public static final double START_X_SPEED = 3.0;
//    public static final double START_Y_SPEED = 3.0;
    public static final double START_X_SPEED = 0.0;
    public static final double START_Y_SPEED = 0.0;

    public static final int MAX_HP = 100;
    public static final int HP = MAX_HP;
    public static final int ATK = 10;

    public static final int START_POINT = 0;
    
    public static final int MOVE_SPEED = 1;
    
    public static final double START_ROTATION_ANGLE = 0.0;
    
    public static final Color MINIMAP_COLOR = Color.GREEN;
    
    public boolean clicked;
    
    public Clip clip;
    public boolean music;

    //-------------------------------------------------------------------------
    // Bullet related
    //-------------------------------------------------------------------------

    public static final double SHOOT_INTERVAL_IN_SEC = 0.3;
    public static final int SHOOT_INTERVAL_NUM_TICKS = (int)(Starcraft.FPS * SHOOT_INTERVAL_IN_SEC);
    
//    public static final int MOVE_SPEED = 30;


    public Bullet [] bullets;
    public int intervalCount;
    public boolean fired;

    //-------------------------------------------------------------------------
    // HPBar related
    //-------------------------------------------------------------------------
    public static final String HPBAR_IMAGE_FRAME_FILENAME = "assets/hpbar/hpbar_01_frame.png";
    public static final String HPBAR_IMAGE_BAR_FILENAME = "assets/hpbar/hpbar_01_bar.png";
    public static final String HPBAR_IMAGE_BG_FILENAME = "assets/hpbar/hpbar_01_bg.png";
    
    //-------------------------------------------------------------------------
    // move() related
    //-------------------------------------------------------------------------
    public double targetXOnMap;
    public double targetYOnMap;

    public Hero() {
//        super(IMAGE_FILENAME_1, START_X, START_Y, START_X_ON_MAP, START_Y_ON_MAP, START_X_DIR, START_Y_DIR, START_X_SPEED, START_Y_SPEED, START_POINT, false, null);
//        anims.curAction.addSpriteCell(IMAGE_FILENAME_2);
//        anims.curAction.addSpriteCell(IMAGE_FILENAME_3);
//        anims.curAction.addSpriteCell(IMAGE_FILENAME_4);
//        anims.curAction.addSpriteCell(IMAGE_FILENAME_5);
//        anims.curAction.addSpriteCell(IMAGE_FILENAME_6);

        //-----------------------------------------------------------------------
        // anims
        //-----------------------------------------------------------------------        
        Animations heroAnims = new Animations();
        for (int i = 0; i < HERO_NUM_ACTIONS; i++) {
            SpriteInfo heroSpriteInfo =
                    new SpriteInfo(HERO_SPRITE_FILENAME_PREFIX[i],
                                   HERO_SPRITE_FILENAME_POSTFIX[i],
                                   HERO_SPRITE_NUM_SPRITES[i],
                                   HERO_SPRITE_GAME_FPS,
                                   HERO_SPRITE_SPRITE_FPS[i],
                                   HERO_SPRITE_START_IMAGE_INDEX[i]);
            Sprite curSprite = new Sprite(heroSpriteInfo, this, true);
            heroAnims.add(HERO_SPRITE_ACTION_NAME[i], curSprite);
        }
        heroAnims.setDefaultAction(HERO_SPRITE_ACTION_NAME[0]); // IDLE
        
        //-----------------------------------------------------------------------
        // Circle Collider
        //-----------------------------------------------------------------------        
        double r = heroAnims.curSprite.curImage.getWidth() / 2.0;
        if (r < heroAnims.curSprite.curImage.getHeight() / 2.0) {
            r = heroAnims.curSprite.curImage.getHeight() / 2.0;
        }
        Collider heroCollider = new CircleCollider(this, r);

        //-----------------------------------------------------------------------
        // HPBar
        //-----------------------------------------------------------------------        
        HPBar hpBar = new HPBar(HPBAR_IMAGE_FRAME_FILENAME, HPBAR_IMAGE_BAR_FILENAME, HPBAR_IMAGE_BG_FILENAME, this);

        //-----------------------------------------------------------------------
        // init parent
        //-----------------------------------------------------------------------        
        super.init(heroAnims, START_X, START_Y, START_X_ON_MAP, START_Y_ON_MAP,
                   START_X_DIR, START_Y_DIR, START_X_SPEED, START_Y_SPEED,
                   HP, MAX_HP, ATK,
                   START_POINT, false, heroCollider, START_ROTATION_ANGLE, hpBar, MINIMAP_COLOR);

        //-----------------------------------------------------------------------
        // Box Collider
        //-----------------------------------------------------------------------
//        collider = new BoxCollider(this, image.getWidth() * 3, image.getHeight() * 3);
        
        bullets = new Bullet[Bullet.BULLET_MAX_SIZE];
        for (int i = 0; i < Bullet.BULLET_MAX_SIZE; i++) {
            bullets[i] = new Bullet(this);
        }
        
        intervalCount = 0;
        fired = true;
        
        clicked = false;
        
        targetXOnMap = -1.0;
        targetYOnMap = -1.0;
        //-------------------------------------------------------------------------------------------
        // MUSIC SETTING starts
        //-------------------------------------------------------------------------------------------
        music = false;
        try {
            File file = new File("assets/zerg_drone_music.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(stream);
//            clip.start();
       
            // sleep to allow enough time for the clip to play
//            Thread.sleep(500);
            stream.close();
       
          } catch (Exception ex) {
            System.out.println(ex.getMessage());
          }
        //-------------------------------------------------------------------------------------------
        // MUSIC SETTING ends
        //-------------------------------------------------------------------------------------------
    }
    
    public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }  

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < Bullet.BULLET_MAX_SIZE; i++) {
            if (!bullets[i].died) {
                bullets[i].paint(g);
            }
        }
        
        if (clicked) {
            g.drawImage(anims.anims.get("PORTRAIT").curImage, Starcraft.PORTRAIT_X, Starcraft.PORTRAIT_Y, null);
            anims.anims.get("PORTRAIT").next();
        }
       
    }
    
    public void update() {
        for (int i = 0; i < Bullet.BULLET_MAX_SIZE; i++) {
            if (!bullets[i].died) {
                bullets[i].update();
            }
        }
        

        super.update();
        
        double diffXOnMap = Starcraft.map01X - prevMapX;
        double diffYOnMap = Starcraft.map01Y - prevMapY;

        x += diffXOnMap;
        y += diffYOnMap;
        prevMapX = Starcraft.map01X;
        prevMapY = Starcraft.map01Y;
        
        if (!clicked) {
            clip.stop();
            targetXOnMap = -1;
            targetYOnMap = -1;
            return;
        }
//        if (Starcraft.ticks%(Starcraft.FPS/10) == 0) { //대략 1초에 10번 움직임 
//            anims.curAction.curIndex = (anims.curAction.curIndex+1)%anims.curAction.spriteCells.size();
//        }
//        System.out.println("Point: " + point);

        
        boolean willMove = false;
        double nextXOnMap = xOnMap;
        double nextYOnMap = yOnMap;
        double nextX = x;
        double nextY = y;
        double nextMap01X = Starcraft.map01X;
        double nextMap01Y = Starcraft.map01Y;
        
        if (targetXOnMap != -1 && targetYOnMap != -1) {
            move(targetXOnMap, targetYOnMap);
            willMove = true;
        }
        
        nextY += ySpeed;       
        nextYOnMap += ySpeed;
        nextX += xSpeed;
        nextXOnMap += xSpeed;
        //-------------------------------------------------------------------------------------------------------------
        //hero가 맵 중앙에 위치해서 맵을 돌아다니게 하는 코드 start 
        //-------------------------------------------------------------------------------------------------------------
//        if (ySpeed < 0) {
//            if (yOnMap > 0) {
//                if (yOnMap <= Starcraft.map01HPlusUI - (Starcraft.H / 2 + (int)(anims.curSprite.curImage.getHeight()/2)) && Starcraft.map01Y < 0) {
//                    nextYOnMap += ySpeed;
//                    nextMap01Y -= ySpeed;
//                }
//                else {
//                    nextYOnMap += ySpeed;
//                    nextY += ySpeed;
//                }
////                rotationAngle = 0;
//                willMove = true;
//            }                
//        }
//        else if (ySpeed > 0) {
//            if (yOnMap+anims.curSprite.curImage.getHeight() < Starcraft.map01HPlusUI) {
//                if (yOnMap >= Starcraft.H / 2 - (int)(anims.curSprite.curImage.getHeight()/2) && -Starcraft.map01Y+Starcraft.H < Starcraft.map01HPlusUI) {
//                    nextYOnMap += ySpeed;
//                    nextMap01Y -= ySpeed;
//                }
//                else {
//                    nextYOnMap += ySpeed;
//                    nextY += ySpeed;
//                }
////                rotationAngle = 180;
//                willMove = true;
//            }                
//        }
//        
//        if (xSpeed < 0) {
//            if (xOnMap > 0) {
//                if (xOnMap <= Starcraft.map01W - (Starcraft.W / 2 + (int)(anims.curSprite.curImage.getWidth()/2)) && Starcraft.map01X < 0) {
//                    nextXOnMap += xSpeed;
//                    nextMap01X -= xSpeed;
//                }
//                else {
//                    nextXOnMap += xSpeed;
//                    nextX += xSpeed;
//                }
////                rotationAngle = 270;
//                willMove = true;
//            }                
//        }
//        else if (xSpeed > 0) {
//            if (xOnMap+anims.curSprite.curImage.getWidth() < Starcraft.map01W) {
//                if (xOnMap >= Starcraft.W / 2 - (int)(anims.curSprite.curImage.getWidth()/2) && -Starcraft.map01X+Starcraft.W < Starcraft.map01W) {
//                    nextXOnMap += xSpeed;
//                    nextMap01X -= xSpeed;
//                }
//                else {
//                    nextXOnMap += xSpeed;
//                    nextX += xSpeed;
//                }
////                rotationAngle = 90;
//                willMove = true;
//            }                
//        }
        //-------------------------------------------------------------------------------------------------------------
        //hero가 맵 중앙에 위치해서 맵을 돌아다니게 하는 코드 ends
        //-------------------------------------------------------------------------------------------------------------
        
        if (willMove && //!CollisionDetection.collideWithBackgroundMask(Starcraft.map01Mask, 0, 0, image, (int)nextXOnMap, (int)nextYOnMap)) {
            !Starcraft.processCollisionWithChunks(this, (int)nextXOnMap, (int)nextYOnMap)) {
            if (nextX >= 0 && nextX < Starcraft.W && nextY >=0 && nextY+anims.curSprite.curImage.getHeight() < Starcraft.H) {
//                if (new Color(Starcraft.userInterface.getRGB((int)nextX, (int)nextY+anims.curSprite.curImage.getHeight()), true).getAlpha() <= 0) {
//                    x = nextX;
//                    y = nextY;
//                    xOnMap = nextXOnMap;
//                    yOnMap = nextYOnMap;
//                }
              x = nextX;
              y = nextY;
              xOnMap = nextXOnMap;
              yOnMap = nextYOnMap;
            }
            else {
                x = nextX;
                y = nextY;
                xOnMap = nextXOnMap;
                yOnMap = nextYOnMap;
            }

            Starcraft.map01X = nextMap01X;
            Starcraft.map01Y = nextMap01Y;
        }
        
        //---------------------------------------------------------------------
        // handle collision when the hero collided with enemy
        //---------------------------------------------------------------------
        Enemy enemyCollided = Starcraft.processCollisionWithEnemies(this);
        if (enemyCollided != null) {
            if ((x >= enemyCollided.x && x <= enemyCollided.x + enemyCollided.anims.curSprite.curImage.getWidth()) || 
                (x + anims.curSprite.curImage.getWidth() >= enemyCollided.x && 
                 x + anims.curSprite.curImage.getWidth() <= enemyCollided.x + enemyCollided.anims.curSprite.curImage.getWidth())) {
                xDir *= -1;
//                x += anims.curSprite.curImage.getWidth();
            }
              
            if ((y >= enemyCollided.y && y <= enemyCollided.anims.curSprite.curImage.getHeight() + enemyCollided.y) || 
                (y + anims.curSprite.curImage.getHeight() >= enemyCollided.y && 
                 y + anims.curSprite.curImage.getHeight() <= enemyCollided.y + enemyCollided.anims.curSprite.curImage.getHeight())) {
                yDir *= -1;
//                y += anims.curSprite.curImage.getHeight();
            }
//            x += xSpeed * xDir;
//            y += ySpeed * yDir;      

            decHp(enemyCollided.atk);
//            enemyCollided.died = true;
            enemyCollided.decHp(atk);
        }
        
        if (fired) {
            intervalCount++;
            if (intervalCount >= SHOOT_INTERVAL_NUM_TICKS) {
                fired = false;
            }
        }
        
//        System.out.println("Hero::update(): x=" + x + "y=" + y);
//        System.out.println("Hero::update(): xOnMap=" + xOnMap + "yOnMap=" + yOnMap);

        if (music) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            music = false;
        }
        
    }
    
    public void move (double targetXOnMap, double targetYOnMap) {
        if (xOnMap <= targetXOnMap+0.5 && xOnMap >= targetXOnMap-0.5 && yOnMap <= targetYOnMap+0.5 && yOnMap >= targetYOnMap-0.5) {
//            System.exit(1);
            xSpeed = 0;
            ySpeed = 0;
            this.targetXOnMap = -1.0;
            this.targetYOnMap = -1.0;
            
            return;
        }
        
        this.targetXOnMap = targetXOnMap;
        this.targetYOnMap = targetYOnMap;
        
        if (this.targetXOnMap == -1.0 || this.targetYOnMap == -1.0) {
            System.out.println("Hero::move() - error :  this.targetXOnMap == -1.0 || this.targetYOnMap == -1.0");
            System.exit(1);
        }
//        prevMapX = Starcraft.map01X;
//        prevMapY = Starcraft.map01Y;
//        this.xOnMap = xOnMap;
//        this.yOnMap = yOnMap;
//        this.x = xOnMap + Starcraft.map01X ;
//        this.y = yOnMap + Starcraft.map01Y;
        

        
        double radians = 180/Math.PI;
        double angle = 0.0;
        double heroXSpeed = 0.0;
        double heroYSpeed = 0.0;
        
        if (targetXOnMap>=xOnMap && targetYOnMap<=yOnMap) {
            double m = Math.abs((targetXOnMap-xOnMap)/(targetYOnMap-yOnMap));
            angle = Math.atan(m)*radians;
//            System.out.println("angle: " + angle);
            if (angle==0) {
                    heroXSpeed = 0;
                    heroYSpeed = -1;
            }
            else if (angle==90) {
                    heroXSpeed = 1;
                    heroYSpeed = 0;
            }
            else {
                    heroXSpeed = Math.sin(Math.toRadians(angle));
                    heroYSpeed = -Math.cos(Math.toRadians(angle));
            }
        }
        else if (targetXOnMap>=xOnMap && targetYOnMap>=yOnMap) {
            double m = Math.abs((targetYOnMap-yOnMap)/(targetXOnMap-xOnMap));
            angle = (Math.atan(m)*radians)+90;
//            System.out.println("angle: " + angle);
            if (angle==90) {
                    heroXSpeed = 1;
                    heroYSpeed = 0;
            }
            else if (angle==180) {
                    heroXSpeed = 0;
                    heroYSpeed = 1;
            }
            else {
                    heroXSpeed = Math.cos(Math.toRadians(angle-90));
                    heroYSpeed = Math.sin(Math.toRadians(angle-90));
            }
        }
        else if(targetXOnMap<=xOnMap && targetYOnMap>=yOnMap) {
            double m = Math.abs((targetXOnMap-xOnMap)/(targetYOnMap-yOnMap));
            angle = (Math.atan(m)*radians)+180;
//            System.out.println("angle: " + angle);
            if (angle==180) {
                    heroXSpeed = 0;
                    heroYSpeed = 1;
            }
            else if (angle==270) {
                    heroXSpeed = -1;
                    heroYSpeed = 0;
            }
            else {
                    heroXSpeed = -Math.sin(Math.toRadians(angle-180));
                    heroYSpeed = Math.cos(Math.toRadians(angle-180));
            }
        }
        else if (targetXOnMap<=xOnMap && targetYOnMap<=yOnMap) {
            double m = Math.abs((targetYOnMap-yOnMap)/(targetXOnMap-xOnMap));
            angle = (Math.atan(m)*radians)+270;
//            System.out.println("angle: " + angle);
            if (angle==270) {
                heroXSpeed = -1;
                    heroYSpeed = 0;
            }
            else if (angle==360) {
                    heroXSpeed = 0;
                    heroYSpeed = -1;
            }
            else {
                    heroXSpeed = -Math.cos(Math.toRadians(angle-270));
                    heroYSpeed = -Math.sin(Math.toRadians(angle-270));
            }
        }
        if (heroXSpeed == 0.0 && heroYSpeed == 0.0) {
                System.out.println("Starcraft::mousePressed - error : bulletXSpeed == 0.0 && bulletYSpeed == 0.0");
                System.exit(1);
        }
        
        rotationAngle = angle;
        //------------------------------------------
        // Hero의 move 속도를 조정하는 controller starts
        //------------------------------------------
        xSpeed = heroXSpeed;
        ySpeed = heroYSpeed;
        //------------------------------------------
        // Hero의 move 속도를 조정하는 controller ends
        //------------------------------------------
    }
    
    public void shoot(double targetXOnMap, double targetYOnMap) {
        double r = anims.curSprite.curImage.getWidth() / 2.0;
        if (r < anims.curSprite.curImage.getHeight() / 2.0) {
            r = anims.curSprite.curImage.getHeight() / 2.0;
        }
        double heroCenterXOnMap = xOnMap+(anims.curSprite.curImage.getWidth()/2);
        double heroCenterYOnMap = yOnMap+(anims.curSprite.curImage.getHeight()/2);
        double xDiff = Math.abs(targetXOnMap - heroCenterXOnMap);
        double yDiff = Math.abs(targetYOnMap - heroCenterYOnMap);
        double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        double ratio = -1.0;
        if (dist>=r) {
                ratio = r/dist;
        }
        else {
                ratio = dist/r;
        }
        if (ratio == -1.0) {
                System.out.println("Starcraft::mousePressed - error : ratio == -1.0");
                System.exit(1);
        }
        double bulletStartXOnMap = (targetXOnMap - heroCenterXOnMap)*ratio;
        double bulletStartYOnMap = (targetYOnMap - heroCenterYOnMap)*ratio;
        
        if (!fired) {
            for (int i = 0; i < Bullet.BULLET_MAX_SIZE; i++) {
                if (bullets[i].died) {
//                    bullets[i].xOnMap = xOnMap + (image.getWidth()/2);
//                    bullets[i].yOnMap = yOnMap;
//                    
//                    bullets[i].died = false;
                    bullets[i].shoot(heroCenterXOnMap + bulletStartXOnMap, heroCenterYOnMap + bulletStartYOnMap, heroCenterXOnMap, heroCenterYOnMap, targetXOnMap, targetYOnMap);
//                    System.out.println("Inside shoot() : xOnMapHero: " + xOnMap + "yOnMapHero: " + yOnMap);
                    
                    intervalCount = 0;
                    fired = true;
                    break;
                }
            }
        }
    }
}
