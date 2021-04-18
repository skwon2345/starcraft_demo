package starcraft;

import java.awt.image.BufferedImage;

public class SpriteInfo {

	public String spriteFilenamePrefix;
	public String spriteFilenamePostfix; 
	public int numSprites; 
	public int gameFPS;
	public int spriteFPS;
	public int startImageIndex;

	public SpriteInfo(String spriteFilenamePrefix, String spriteFilenamePostfix, int numSprites, int gameFPS, int spriteFPS, int startImageIndex) {
		this.spriteFilenamePrefix = spriteFilenamePrefix;
		this.spriteFilenamePostfix = spriteFilenamePostfix;
		this.numSprites = numSprites;
		this.gameFPS = gameFPS;
		this.spriteFPS = spriteFPS;
		this.startImageIndex = startImageIndex;
	}
}
