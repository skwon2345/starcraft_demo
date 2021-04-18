package starcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Unit {
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
    public static final int ENEMY_NUM_ACTIONS = 1;
    
    public static final String[] ENEMY_SPRITE_ACTION_NAME = {
            "MOVE_FORWARD",
    };
    public static final String[] ENEMY_SPRITE_FILENAME_PREFIX = {
            "assets/zerg_move_forward_",
    };
    public static final String[] ENEMY_SPRITE_FILENAME_POSTFIX = {
              ".png",
    };
    public static final int[] ENEMY_SPRITE_NUM_SPRITES = {
            6,
    };
    
    public static final int ENEMY_SPRITE_GAME_FPS = 100; 
    public static final int[] ENEMY_SPRITE_SPRITE_FPS = {
             5, // 20
    };
    public static final int[] ENEMY_SPRITE_START_IMAGE_INDEX = {
             0,
    };
    public static final boolean[] ENEMY_SPRITE_LOOP = {
            true,
    };
    
    //-------------------------------------------------------------------------
    // General
    //-------------------------------------------------------------------------
    public static final int MAX_NUM_ENEMIES = 20;
    
//    public static final String IMAGE_FILENAME = "assets/zerg_move_and_death1_walk_01_01.png";
    
//    public static final double START_X = Starcraft.W / 3;
//    public static final double START_Y = Starcraft.H / 2;

    public static final double START_Y_MIN = 10;
    public static final double START_Y_MAX = Starcraft.H / 2;

    public static final double START_X_SPEED_MIN = 0.5;
    public static final double START_X_SPEED_MAX = 2.0;
    
    public static final double START_Y_SPEED_MIN = 1.0;
    public static final double START_Y_SPEED_MAX = 3.0;
    
    public static final int MAX_HP = 30;
    public static final int HP = MAX_HP;
    public static final int ATK = 5;
    
    public static final int START_POINT = 1;
    
    public static final double START_ROTATION_ANGLE = 0.0;
    
    public static final Color MINIMAP_COLOR = Color.RED;

    //-------------------------------------------------------------------------
    // HPBar related
    //-------------------------------------------------------------------------
    public static final String HPBAR_IMAGE_FRAME_FILENAME = "assets/hpbar/hpbar_01_frame.png";
    public static final String HPBAR_IMAGE_BAR_FILENAME = "assets/hpbar/hpbar_01_bar.png";
    public static final String HPBAR_IMAGE_BG_FILENAME = "assets/hpbar/hpbar_01_bg.png";
    
    public Enemy() {
        this(0.0, 0.0);
    }
    
    public Enemy(double xOnMap, double yOnMap) {
        //-----------------------------------------------------------------------
        // anims
        //-----------------------------------------------------------------------        
        Animations enemyAnims = new Animations();
        for (int i = 0; i < ENEMY_NUM_ACTIONS; i++) {
            SpriteInfo enemySpriteInfo =
                    new SpriteInfo(ENEMY_SPRITE_FILENAME_PREFIX[i],
                                   ENEMY_SPRITE_FILENAME_POSTFIX[i],
                                   ENEMY_SPRITE_NUM_SPRITES[i],
                                   ENEMY_SPRITE_GAME_FPS,
                                   ENEMY_SPRITE_SPRITE_FPS[i],
                                   ENEMY_SPRITE_START_IMAGE_INDEX[i]);
            Sprite curSprite = new Sprite(enemySpriteInfo, this, true);
            enemyAnims.add(ENEMY_SPRITE_ACTION_NAME[i], curSprite);
        }
        enemyAnims.setDefaultAction(ENEMY_SPRITE_ACTION_NAME[0]); // IDLE
        
        //-----------------------------------------------------------------------
        // Circle Collider
        //-----------------------------------------------------------------------        
        double r = enemyAnims.curSprite.curImage.getWidth() / 2.0;
        if (r < enemyAnims.curSprite.curImage.getHeight() / 2.0) {
            r = enemyAnims.curSprite.curImage.getHeight() / 2.0;
        }
        Collider enemyCollider = new CircleCollider(this, r);        
        
        //-----------------------------------------------------------------------
        // HPBar
        //-----------------------------------------------------------------------        
        HPBar hpBar = new HPBar(HPBAR_IMAGE_FRAME_FILENAME, HPBAR_IMAGE_BAR_FILENAME, HPBAR_IMAGE_BG_FILENAME, this);

        //-----------------------------------------------------------------------
        // Etc
        //-----------------------------------------------------------------------        
        point = START_POINT;
        
//        double randStartY = Starcraft.r.nextDouble() * (START_Y_MAX - START_Y_MIN) + START_Y_MIN;
        
        int randStartXDir = (Starcraft.r.nextInt(2) == 0)?-1:1;
        int randStartYDir = (Starcraft.r.nextInt(2) == 0)?-1:1;
        
        // uniformly distributed random value
        double randStartXSpeed = Starcraft.r.nextDouble() * (START_X_SPEED_MAX - START_X_SPEED_MIN) + START_X_SPEED_MIN;
        double randStartYSpeed = Starcraft.r.nextDouble() * (START_Y_SPEED_MAX - START_Y_SPEED_MIN) + START_Y_SPEED_MIN;
        
        init(enemyAnims, xOnMap + Starcraft.START_MAP01_X, yOnMap + Starcraft.START_MAP01_Y, xOnMap, yOnMap,
             randStartXDir, randStartYDir, randStartXSpeed, randStartYSpeed,
             HP, MAX_HP, ATK,
             START_POINT, false, enemyCollider, START_ROTATION_ANGLE, hpBar, MINIMAP_COLOR);
        
//        // create image
//        BufferedImage tempImage = null;
//        try {
//            tempImage = ImageIO.read(new File(IMAGE_FILENAME));
//        } catch (IOException e) {
//            System.out.println("Unit::Unit(): error - image file not found: " + IMAGE_FILENAME);
//            System.exit(1);
//        }
//        
//        int w = tempImage.getWidth();
//        int h = tempImage.getHeight();
//        
//        double ratio = Starcraft.r.nextDouble() / 2.0 + 0.5;
//        
//        point *= ((int)(ratio*10.0)) * 10;
////        System.out.println(point + "dd");
////        System.exit(1);
//        
//        int randW = (int)(w * ratio);
//        int randH = (int)(h * ratio);
//        
//        randStartXSpeed = randStartXSpeed * ratio;
//        
//        anims.curAction.getCurSpriteCell().image = resize(tempImage, randW, randH);
//        //-----------------------------------------------------------------------
//        // Circle Collider
//        //-----------------------------------------------------------------------
////        double r = image.getWidth() / 2.0;
////        if (r < image.getHeight() / 2.0) {
////            r = image.getHeight() / 2.0;
////        }
////        collider = new CircleCollider(this, r * 10);
//        //-----------------------------------------------------------------------
//        // Box Collider
//        //-----------------------------------------------------------------------
//        collider = new BoxCollider(this, anims.curAction.getCurSpriteCell().image.getWidth(), anims.curAction.getCurSpriteCell().image.getHeight());
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
        if (died) {
            return;
        }
        super.paint(g);
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
    }
}
