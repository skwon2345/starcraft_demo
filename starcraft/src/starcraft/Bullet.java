package starcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Bullet extends Unit {
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    // Constants
    //-------------------------------------------------------------------------
    //-------------------------------------------------------------------------
    //------------------------------------------------------------------------- 
    public static final Color MINIMAP_COLOR = Color.WHITE;
    public static final int ATK = 5;
    //-------------------------------------------------------------------------
    // Hero Action State
    //-------------------------------------------------------------------------
    public static final int ACTION_STATE_NONE = -1;
    public static final int ACTION_STATE_IDLE = 0;
    
    //-------------------------------------------------------------------------
    // Hero Sprite
    //-------------------------------------------------------------------------
    public static final int BULLET_NUM_ACTIONS = 1;
    
    public static final String[] BULLET_SPRITE_ACTION_NAME = {
            "IDLE",
    };
    public static final String[] BULLET_SPRITE_FILENAME_PREFIX = {
            "assets/bullet_01_",
    };
    public static final String[] BULLET_SPRITE_FILENAME_POSTFIX = {
              ".png",
    };
    public static final int[] BULLET_SPRITE_NUM_SPRITES = {
            1,
    };
    
    public static final int BULLET_SPRITE_GAME_FPS = 100; 
    public static final int[] BULLET_SPRITE_SPRITE_FPS = {
             5, // 20
    };
    public static final int[] BULLET_SPRITE_START_IMAGE_INDEX = {
             0,
    };
    public static final boolean[] BULLET_SPRITE_LOOP = {
            true,
    };
        
    //-------------------------------------------------------------------------
    // General
    //-------------------------------------------------------------------------

    private Unit owner;
//    public BufferedImage image;
    public static final int BULLET_MAX_SIZE = 100;
    
    public static final int START_POINT = -1;
    
    public Bullet() {
        this(null, 0.0, 0.0, 0.0, 0.0, 0, 0, 0.0, 0.0, START_POINT, true, null, 0.0, null);
    }
    
    public Bullet(Unit owner) {
        this(null, 0.0, 0.0, 0.0, 0.0, 0, 0, 0.0, 0.0, START_POINT, true, owner, 0.0, MINIMAP_COLOR);
    }
    
    public Bullet(Animations bulletAnims, double x, double y, double xOnMap, double yOnMap, int xDir, int yDir, double xSpeed, double ySpeed, int point, boolean died, Unit owner, double rotationAngle, Color miniMapColor) {
        if (anims == null) {
            //-----------------------------------------------------------------------
            // anims
            //-----------------------------------------------------------------------        
            bulletAnims = new Animations();
            for (int i = 0; i < BULLET_NUM_ACTIONS; i++) {
                SpriteInfo bulletSpriteInfo =
                        new SpriteInfo(BULLET_SPRITE_FILENAME_PREFIX[i],
                                       BULLET_SPRITE_FILENAME_POSTFIX[i],
                                       BULLET_SPRITE_NUM_SPRITES[i],
                                       BULLET_SPRITE_GAME_FPS,
                                       BULLET_SPRITE_SPRITE_FPS[i],
                                       BULLET_SPRITE_START_IMAGE_INDEX[i]);
                Sprite curSprite = new Sprite(bulletSpriteInfo, this, true);
                bulletAnims.add(BULLET_SPRITE_ACTION_NAME[i], curSprite);
            }
            bulletAnims.setDefaultAction(BULLET_SPRITE_ACTION_NAME[0]); // IDLE
        }
        
        //-----------------------------------------------------------------------
        // Box Collider
        //-----------------------------------------------------------------------        
        double r = bulletAnims.curSprite.curImage.getWidth() / 2.0;
        if (r < bulletAnims.curSprite.curImage.getHeight() / 2.0) {
            r = bulletAnims.curSprite.curImage.getHeight() / 2.0;
        }
        Collider bulletCollider = new BoxCollider(this, bulletAnims.curSprite.curImage.getWidth(), bulletAnims.curSprite.curImage.getHeight());
        super.init(bulletAnims, x, y, xOnMap, yOnMap, xDir, yDir, xSpeed, ySpeed,
                   0, 0, ATK,
                   point, died, bulletCollider, rotationAngle, null, miniMapColor);
        this.owner = owner;
    }
    
    public void incPoint(Unit u) {
        owner.point += u.point;

        u.point = -1;
        System.out.println("Point: " + owner.point);
    }
    
    public void paint(Graphics g) {
        if (died) {
            return;
        }
        super.paint(g);

//        g.drawImage(anims.curSprite.curImage, (int)x, (int)y, null);
    }
    
    public void update() {
        if (died) {
            return;
        }
        
        super.update();
        
        double diffXOnMap = Starcraft.map01X - prevMapX;
        double diffYOnMap = Starcraft.map01Y - prevMapY;
        x += diffXOnMap;
        y += diffYOnMap;
        prevMapX = Starcraft.map01X;
        prevMapY = Starcraft.map01Y;
        
        //미사일이 화면 밖으로 나가면 destroy한다.
        if (y <= 0 || y >= Starcraft.H || x <= 0 || x >= Starcraft.W) {
            died = true;
            return;
        }
        
        Enemy enemyCollided = Starcraft.processCollisionWithEnemies(this);
        if (enemyCollided != null) {
            if ((xOnMap >= enemyCollided.xOnMap && xOnMap <= enemyCollided.xOnMap + enemyCollided.anims.curSprite.curImage.getWidth()) || 
                (xOnMap + anims.curSprite.curImage.getWidth() >= enemyCollided.xOnMap && 
                 xOnMap + anims.curSprite.curImage.getWidth() <= enemyCollided.xOnMap + enemyCollided.anims.curSprite.curImage.getWidth())) {
                died = true;
                incPoint(enemyCollided);
            }
              
            if ((yOnMap >= enemyCollided.yOnMap && yOnMap <= enemyCollided.anims.curSprite.curImage.getHeight() + enemyCollided.yOnMap) || 
                (yOnMap + anims.curSprite.curImage.getHeight() >= enemyCollided.yOnMap && 
                 yOnMap + anims.curSprite.curImage.getHeight() <= enemyCollided.yOnMap + enemyCollided.anims.curSprite.curImage.getHeight())) {
                died = true;
            }
            enemyCollided.decHp(atk);
//            enemyCollided.died = true;
        }
        
        y += ySpeed;       
        yOnMap += ySpeed;
        x += xSpeed;
        xOnMap += xSpeed;
    }
    
    public void shoot(double xOnMap, double yOnMap, double ownerXOnMap, double ownerYOnMap, double targetXOnMap, double targetYOnMap) {
        prevMapX = Starcraft.map01X;
        prevMapY = Starcraft.map01Y;
        this.xOnMap = xOnMap;
        this.yOnMap = yOnMap;
        this.x = xOnMap + Starcraft.map01X ;
        this.y = yOnMap + Starcraft.map01Y;
        
        double radians = 180/Math.PI;
        double angle = 0.0;
        double bulletXSpeed = 0.0;
        double bulletYSpeed = 0.0;
        if (targetXOnMap>=ownerXOnMap && targetYOnMap<=ownerYOnMap) {
            double m = Math.abs((targetXOnMap-ownerXOnMap)/(targetYOnMap-ownerYOnMap));
            angle = Math.atan(m)*radians;
//            System.out.println("angle: " + angle);
            if (angle==0) {
                    bulletXSpeed = 0;
                    bulletYSpeed = -1;
            }
            else if (angle==90) {
                    bulletXSpeed = 1;
                    bulletYSpeed = 0;
            }
            else {
                    bulletXSpeed = Math.sin(Math.toRadians(angle));
                    bulletYSpeed = -Math.cos(Math.toRadians(angle));
            }
        }
        else if (targetXOnMap>=ownerXOnMap && targetYOnMap>=ownerYOnMap) {
            double m = Math.abs((targetYOnMap-ownerYOnMap)/(targetXOnMap-ownerXOnMap));
            angle = (Math.atan(m)*radians)+90;
//            System.out.println("angle: " + angle);
            if (angle==90) {
                    bulletXSpeed = 1;
                    bulletYSpeed = 0;
            }
            else if (angle==180) {
                    bulletXSpeed = 0;
                    bulletYSpeed = 1;
            }
            else {
                    bulletXSpeed = Math.cos(Math.toRadians(angle-90));
                    bulletYSpeed = Math.sin(Math.toRadians(angle-90));
            }
        }
        else if(targetXOnMap<=ownerXOnMap && targetYOnMap>=ownerYOnMap) {
            double m = Math.abs((targetXOnMap-ownerXOnMap)/(targetYOnMap-ownerYOnMap));
            angle = (Math.atan(m)*radians)+180;
//            System.out.println("angle: " + angle);
            if (angle==180) {
                    bulletXSpeed = 0;
                    bulletYSpeed = 1;
            }
            else if (angle==270) {
                    bulletXSpeed = -1;
                    bulletYSpeed = 0;
            }
            else {
                    bulletXSpeed = -Math.sin(Math.toRadians(angle-180));
                    bulletYSpeed = Math.cos(Math.toRadians(angle-180));
            }
        }
        else if (targetXOnMap<=ownerXOnMap && targetYOnMap<=ownerYOnMap) {
            double m = Math.abs((targetYOnMap-ownerYOnMap)/(targetXOnMap-ownerXOnMap));
            angle = (Math.atan(m)*radians)+270;
//            System.out.println("angle: " + angle);
            if (angle==270) {
                    bulletXSpeed = -1;
                    bulletYSpeed = 0;
            }
            else if (angle==360) {
                    bulletXSpeed = 0;
                    bulletYSpeed = -1;
            }
            else {
                    bulletXSpeed = -Math.cos(Math.toRadians(angle-270));
                    bulletYSpeed = -Math.sin(Math.toRadians(angle-270));
            }
        }
        if (bulletXSpeed == 0.0 && bulletYSpeed == 0.0) {
                System.out.println("Starcraft::mousePressed - error : bulletXSpeed == 0.0 && bulletYSpeed == 0.0");
                System.exit(1);
        }
        
        rotationAngle = angle;
        xSpeed = bulletXSpeed;
        ySpeed = bulletYSpeed;
//        System.out.println("sin30: " + Math.sin(Math.toRadians(30)));
      
//        System.out.println("Inside shoot() : xOnMapBullet: " + xOnMap + "yOnMapBullet: " + yOnMap);;
        try {
            File file = new File("assets/zerg_attack.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
       
            // sleep to allow enough time for the clip to play
//            Thread.sleep(500);
       
            stream.close();
       
          } catch (Exception ex) {
            System.out.println(ex.getMessage());
          }
        died = false;
    }
}