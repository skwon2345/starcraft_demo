package starcraft;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HPBar {
	public BufferedImage imageFrame;
	public BufferedImage imageBar;
	public BufferedImage imageBG;

	public BufferedImage curImageBar;

	public Unit unit;

	public double x;
	public double y;
	
	public int rightEndIndex;

	public boolean staticType;

	public HPBar() {
		this(null, null, null, null);
	}
	
	public HPBar(String imageFrameFilename, String imageBarFilename, String imageBGFilename, Unit unit, boolean staticType) {
		this(imageFrameFilename, imageBarFilename, imageBGFilename, unit);
		this.staticType = staticType;
	}

	public HPBar(String imageFrameFilename, String imageBarFilename, String imageBGFilename, Unit unit) {
		imageFrame = Starcraft.imageManager.getImage(imageFrameFilename);
		imageBar = Starcraft.imageManager.getImage(imageBarFilename);
		imageBG = Starcraft.imageManager.getImage(imageBGFilename);
		    	
		curImageBar = new BufferedImage(imageBar.getWidth(), imageBar.getHeight(), BufferedImage.TYPE_INT_ARGB);

        	Graphics2D g = curImageBar.createGraphics();
        	g.drawImage(imageBar, 0, 0, null);
        	g.dispose();
        	
        	rightEndIndex = curImageBar.getWidth() - 1;
		
		this.unit = unit;
		
		x = 0.0;
		y = 0.0;
		
		staticType = false;
	}
	
	public void paint(Graphics g) {
        g.drawImage(imageBG, (int)x, (int)y, null);
        g.drawImage(curImageBar, (int)x, (int)y, null); // <-- 요 부분을 바꾸면, hp bar가 줄어들었다가 늘어났다가..
        g.drawImage(imageFrame, (int)x, (int)y, null);
	}

	public void update(int hp) {
		if (!staticType) {
			x = unit.x + (unit.anims.curSprite.curImage.getWidth() - imageBar.getWidth()) / 2;
			y = unit.y - 15;
		}
		
	    // 아래는 비효율적인 버젼:
        // 일단 앨거리듬은 curImageBar에 녹색 bar를 채워놓고, hp가 줄어든 만큼 비율대로, 맨 오른쪽부터
        // 왼쪽으로 가면서 투명색을 칠해서, 마치 bar가 줄어들게 보이게 하는 것이다.
        // 만약 rightEndIndex는 bar image의 맨 오른쪽이므로, 이것을 매번 쓰지 말고,
        // 이전에 줄어들었던 부분을 기억을 해서, 그 지점부터 다시 줄어들게 하면 된다.
        // refactoring
        //    여기에서 두가지 개선점이 필요.
        //    1) 일단 효율적으로 할 것.(위처럼)
        //    2) 늘어나게도 할 것.
        //       원본 image를 다시 늘어난 부분만 옮기거나,
        //       투명도를 0으로 했다가 다시 255로 하는 방법
		double perc = unit.hp / (double)unit.maxHp;
		for (int i = 0; i < curImageBar.getHeight(); i++) {
			for (int j = rightEndIndex; j > (int)(curImageBar.getWidth() * perc); j--) {
				curImageBar.setRGB(j, i, 0);
			}
		}

	}
}
