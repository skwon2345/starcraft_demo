package starcraft;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {
	public BufferedImage[] sprites;
	public BufferedImage[] spritesInvis;
	public int numSprites;
	
	public int startImageIndex;
	public int curImageIndex;
	public BufferedImage curImage;
	
	public int gameFPS;
	public int spriteFPS;
	
	public int countCurImageFramesToSkip;
	public int maxCountCurImageFramesToSkip;
	
//	public Unit owner; // invis등을 check하기 위해 필요.
	public boolean initInvis; // invis set를 마련할 것.
	
	public Sprite() {
		sprites = null;
		numSprites = 0;
		
		startImageIndex = 0;
		curImageIndex = 0;
		curImage = null;
		
		gameFPS = 0;
		spriteFPS = 0;
		
		countCurImageFramesToSkip = 0;
		maxCountCurImageFramesToSkip = 0;
		
//		owner = null;
	}

	public Sprite(SpriteInfo spriteInfo) {
		this(spriteInfo.spriteFilenamePrefix,
			 spriteInfo.spriteFilenamePostfix,
			 spriteInfo.numSprites,
			 spriteInfo.gameFPS,
			 spriteInfo.spriteFPS,
			 spriteInfo.startImageIndex,
			 null,
			 false
			);
	}
	
	public Sprite(SpriteInfo spriteInfo, Unit owner, boolean initInvis) {
		this(spriteInfo.spriteFilenamePrefix,
			 spriteInfo.spriteFilenamePostfix,
			 spriteInfo.numSprites,
			 spriteInfo.gameFPS,
			 spriteInfo.spriteFPS,
			 spriteInfo.startImageIndex,
			 owner,
			 initInvis
			);
	}
	
	public Sprite(String spriteFilenamePrefix, String spriteFilenamePostfix, int numSprites, int gameFPS, int spriteFPS, int startImageIndex, Unit owner, boolean initInvis) {
//		this.owner = owner;
		this.initInvis = initInvis;
		
		this.numSprites = numSprites;
		sprites = new BufferedImage[numSprites];
		spritesInvis = null;
		if (initInvis) {
			spritesInvis = new BufferedImage[numSprites];
		}
		if (spriteFilenamePrefix != null) {
			String fileToOpen = "";
	    		try {
	    			for (int i = 0; i < numSprites; i++) {
	    				fileToOpen = spriteFilenamePrefix + String.format("%02d", i+1) + spriteFilenamePostfix;
	    				sprites[i] = ImageIO.read(new File(fileToOpen));
	    				
	    				if (initInvis) {
	    					spritesInvis[i] = ImageIO.read(new File(fileToOpen));
	    				}
	    				
	    				if (initInvis) {
		    				for (int y = 0; y < spritesInvis[i].getHeight(); y++) {
			    				for (int x = 0; x < spritesInvis[i].getWidth(); x++) {
			    					int curColorInInt = spritesInvis[i].getRGB(x, y);
			    					int alpha = 50;
			    		            int mc = (alpha << 24) | 0x00ffffff;
			    		            int newcolor = curColorInInt & mc;
			    		            spritesInvis[i].setRGB(x, y, newcolor);
			    				}
		    				}
	    				}
	    			}
		   	} catch (IOException e) {
		   		System.out.println("error: failed to open image file: " + fileToOpen);
		   	    return;
		   	}
		}
		
		this.startImageIndex = startImageIndex;
		curImageIndex = startImageIndex;
		curImage = sprites[curImageIndex];
		
		this.gameFPS = gameFPS;
		this.spriteFPS = spriteFPS;
		
		countCurImageFramesToSkip = 0;
		maxCountCurImageFramesToSkip = gameFPS / spriteFPS;
	}
	
	public void reset() {
		curImageIndex = startImageIndex;
        curImage = sprites[startImageIndex];
//		if (owner != null && owner instanceof Hero && ((Hero)owner).invisUpStatus) {
//			curImage = spritesInvis[startImageIndex];
//		}
//		else {
//			curImage = sprites[startImageIndex];
//		}
		countCurImageFramesToSkip = 0;
	}
	
	public BufferedImage next() {
		countCurImageFramesToSkip++;
		
		if (countCurImageFramesToSkip >= maxCountCurImageFramesToSkip) {
			curImageIndex++;
			if (curImageIndex >= numSprites) {
				curImageIndex = 0;
			}
			
            curImage = sprites[curImageIndex];
//			if (owner != null && owner instanceof Hero && ((Hero)owner).invisUpStatus) {
//				curImage = spritesInvis[curImageIndex];
//			}
//			else {
//				curImage = sprites[curImageIndex];
//			}
			countCurImageFramesToSkip = 0;
		}
		return curImage;
	}
	
	public BufferedImage peek() {
		return curImage;
	}
	
	public void refresh() {
        curImage = sprites[curImageIndex];
//		if (owner != null && owner instanceof Hero && ((Hero)owner).invisUpStatus) {
//			curImage = spritesInvis[curImageIndex];
//		}
//		else {
//			curImage = sprites[curImageIndex];
//		}
	}
}
