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

public class Unit {
//    public BufferedImage image;
    public Animations anims;

    public double x;
    public double y;
    
    public double xOnMap;
    public double yOnMap;

    public int xDir;
    public int yDir;

    public double xSpeed;
    public double ySpeed;

    // Hero를 제외하고, 모든 unit은 화면에(Hero가 화면의 위치를 계속 바꾸므로) 남아 있기 위해 이 코드를 쓴다.
    // 각 Unit의 child class의 update()(또는 move()이 있다면..)에 가면, 맨 처음 시작부에 이렇게 prevMapX/prevMapY를 써서 화면에 그대로 stay하는 코드를 넣게 된다.
    public double prevMapX;
    public double prevMapY;

    public int hp;
    public int maxHp;
    public int atk;
    
    public int point; // score
    
    public boolean died;
    
    public Collider collider;
    public double rotationAngle;
    
    public Color miniMapColor;
    
    //-------------------------------------------------------------------------
    // HPBar
    //-------------------------------------------------------------------------
    public HPBar hpBar;
    
    public Unit() {
        this(null, 0.0, 0.0, 0.0, 0.0, 0, 0, 0.0, 0.0, 0, 0, 0, 0, false, null, 0.0, null, null);
    }
    
//    public Unit(String imageFilename, double x, double y, double xOnMap, double yOnMap, int xDir, int yDir, double xSpeed, double ySpeed, int point, boolean died, Collider collider) {
    public Unit(Animations anims, double x, double y, double xOnMap, double yOnMap,
            int xDir, int yDir, double xSpeed, double ySpeed,
            int hp, int maxHp, int atk,
            int point, boolean died, Collider collider, double rotationAngle, HPBar hpBar, Color miniMapColor) {
        init(anims, x, y, xOnMap, yOnMap, xDir, yDir, xSpeed, ySpeed, hp, maxHp, atk, point, died, collider, rotationAngle, hpBar, miniMapColor);
    }
    
    public void init(Animations anims, double x, double y, double xOnMap, double yOnMap,
                    int xDir, int yDir, double xSpeed, double ySpeed,
                    int hp, int maxHp, int atk,
                    int point, boolean died, Collider collider, double rotationAngle, HPBar hpBar, Color miniMapColor) {
//        if (imageFilename != null) {
//            try {
//                image = ImageIO.read(new File(imageFilename));
//            } catch (IOException e) {
//                System.out.println("Unit::Unit(): error - image file not found: " + imageFilename);
//                System.exit(1);
//            }
//        }
//        else {
//            image = null;
//        }
        this.anims = anims;
        
        this.x = x;
        this.y = y + Starcraft.JFRAME_START_Y_DIFF;
        
        this.xOnMap = xOnMap;
        this.yOnMap = yOnMap + Starcraft.JFRAME_START_Y_DIFF;

        this.xDir = xDir;
        this.yDir = yDir;
        
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        
        this.prevMapX = Starcraft.map01X;
        this.prevMapY = Starcraft.map01Y;
        
        this.hp = hp;
        this.maxHp = maxHp;
        this.atk = atk;

        this.point = point;
        this.died = died;
        
        this.collider = collider;
        
        this.rotationAngle = rotationAngle;
        
//        anims = new Animation(imageFilename, "move");
        
        this.hpBar = hpBar;
        
        this.miniMapColor = miniMapColor;
    }
    
    public void paint(Graphics g) {
        if (died) {
            return;
        }
        if (!(x+anims.curSprite.curImage.getWidth() >= Starcraft.miniMap01X && x <= Starcraft.miniMap01X + Starcraft.miniMap01W && 
             y+anims.curSprite.curImage.getHeight() >= Starcraft.miniMap01Y)) { //만약 unit이 minimap위에 있다면 그려주지 않는다. 만약 그리게되면 Unit의 paint()가 miniMap paint()보다 
                                                                                   //나중에 call되기 때문에 unit이 minimap위를 지나다니며 그려지게되는 불상사가 일어날 수 있다.
            
            
            AffineTransform at = AffineTransform.getTranslateInstance(x, y);

            if (this instanceof Hero) {
                if (((Hero)this).clicked || (Starcraft.DRAW_COLLIDER && collider != null)) {
                    collider.paint(g);
                }
                
                if (((Hero)this).clicked && hpBar != null) {
                    hpBar.paint(g);
                }
                
                if (rotationAngle >= 0 && rotationAngle <= 90) {
                    double ratio = rotationAngle / 90.0;
                    at = AffineTransform.getTranslateInstance(x+(ratio*anims.curSprite.curImage.getWidth()), y);
//                    anims.curSprite.curImage.getWidth();
                }
                
                else if (rotationAngle >= 90 && rotationAngle <= 180) {
                    double ratio = (rotationAngle-90.0) / 90.0;
                    at = AffineTransform.getTranslateInstance(x+anims.curSprite.curImage.getWidth(), y+(ratio*anims.curSprite.curImage.getHeight()));
                }
                
                else if (rotationAngle >= 180 && rotationAngle <= 270) {
                    double ratio = (rotationAngle-180.0) / 90.0;
                    at = AffineTransform.getTranslateInstance(x+((1-ratio)*anims.curSprite.curImage.getWidth()), y+anims.curSprite.curImage.getHeight());
                }
                else if (rotationAngle >= 270 && rotationAngle <= 360) {
                    double ratio = (rotationAngle-270.0) / 90.0;
                    at = AffineTransform.getTranslateInstance(x, y+((1-ratio)*anims.curSprite.curImage.getHeight()));
                }
            }
            else { // hero가 아닌 다른 Unit의 paint()를 처리 
                if (Starcraft.DRAW_COLLIDER && collider != null) {
                    collider.paint(g);
                }
                
                if (hpBar != null) {
                    hpBar.paint(g);
                }
            }
            
            at.rotate(Math.toRadians(rotationAngle));
            
            Graphics2D g2d = (Graphics2D)g;
            g2d.drawImage(anims.curSprite.curImage, at, null);
//            g2d.drawImage(anims.curSprite.curImage, (int)x, (int)y, null);
            anims.curSprite.next();
        }
        
        g.setColor(miniMapColor);
        g.drawRect((int)(Starcraft.miniMap01X + (xOnMap*Starcraft.miniMapRatio)), (int)(Starcraft.miniMap01Y + (yOnMap*Starcraft.miniMapRatio)), 1, 1);
        
//        anims.anims.get("IDLE").next();
    }
    
    public void update() {
        if (died) {
            if (this instanceof Hero) {
                System.out.println("Game Over");
                System.exit(1);
            }
            return;
        }
        
        if (hpBar != null) {
            hpBar.update(hp);
        }
    }
    
    public void decHp(int hpToDec) {
        if (died) {
            return;
        }
        
        if (hp - hpToDec <= 0) {
            hp = 0;
            died = true;
            try {
                File file = new File("assets/zerg_died.wav");
                AudioInputStream stream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(stream);
                clip.start();
           
                // sleep to allow enough time for the clip to play
//                Thread.sleep(500);
           
                stream.close();
           
              } catch (Exception ex) {
                System.out.println(ex.getMessage());
              }
        }
        else {
            hp -= hpToDec;
        }
    }
}
