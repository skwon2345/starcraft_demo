package chai_utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class CollisionDetection {
	//-------------------------------------------------------------------------
    // 201604050050: collisionDetection() ver0001
	//-------------------------------------------------------------------------
//	public static boolean collideWithMask(BufferedImage mask, int maskOffsetX, int maskOffsetY, BufferedImage sprite, int spriteX, int spriteY, int spriteW, int spriteH) {
////		System.out.println("collisionDetection!!");
////		System.out.println("maskOffsetX: " + maskOffsetX + " maskOffsetY:" + maskOffsetY);
////		System.out.println("spriteX: " + spriteX + " spriteY:" + spriteY);
//
//		int maskW = mask.getWidth();
//		int maskH = mask.getHeight();
//		int spriteXOnMask = spriteX + maskOffsetX;
//		int spriteYOnMask = spriteY + maskOffsetY;
//		
//		// check if any pixel(non transparent pixel) of the sprite collide with any pixel(non transparent pixel) of the mask.
//		for (int i = 0; i < spriteH; i++) {
//			for (int j = 0; j < spriteW; j++) {
////				System.out.print("(" + i + ", " + j + "): ");
//				if (i < maskH && j < maskW) {
//					int spriteAlpha = new Color(sprite.getRGB(j, i), true).getAlpha();
//					int maskAlpha = new Color(mask.getRGB(spriteXOnMask + j, spriteYOnMask + i), true).getAlpha();
//	
//					if (spriteAlpha > 0 && maskAlpha > 0) {
//						return true;
//					}
//				}
//			}
//		}
//		
//		return false;
//	}
	
	//-------------------------------------------------------------------------
    // 201604050052: collisionDetection() ver0002: reduce params and bug fixed(when map is moving)
	//-------------------------------------------------------------------------
	public static boolean collideWithBackgroundMask(BufferedImage mask, int maskOffsetX, int maskOffsetY, BufferedImage sprite, int spriteX, int spriteY) {
//		System.out.println("collisionDetection!!");
//		System.out.println("maskOffsetX: " + maskOffsetX + " maskOffsetY:" + maskOffsetY);
//		System.out.println("spriteX: " + spriteX + " spriteY:" + spriteY);

		int maskW = mask.getWidth();
		int maskH = mask.getHeight();
		
		int spriteW = sprite.getWidth();
		int spriteH = sprite.getHeight();
//		System.out.println("spriteW: " + spriteW + " spriteH:" + spriteH);

		int spriteXOnMask = spriteX - maskOffsetX;
		int spriteYOnMask = spriteY - maskOffsetY;
		
		// check if any pixel(non transparent pixel) of the sprite collide with any pixel(non transparent pixel) of the mask.
		for (int i = 0; i < spriteH; i++) {
			for (int j = 0; j < spriteW; j++) {
//				System.out.print("(" + i + ", " + j + "): ");
				if (spriteYOnMask + i < maskH && spriteXOnMask + j < maskW) {
//					System.out.println("spriteXOnMask: " + spriteXOnMask + ", spriteYOnMask: " + spriteYOnMask + "): ");
					int spriteAlpha = new Color(sprite.getRGB(j, i), true).getAlpha();
					int maskAlpha = new Color(mask.getRGB(spriteXOnMask + j, spriteYOnMask + i), true).getAlpha();
                    
					if (spriteAlpha > 0 && maskAlpha > 0) {
					    System.out.println("");
					    System.out.println("-----------------------------------------------------------");
					    System.out.println("Testing i, j, spreteAlpha, maskAlpha starts");
					    System.out.println("-----------------------------------------------------------");
					    
					    System.out.println("CollisionDectection::collideWithBackgroundMask() - i: " + i);
					    System.out.println("CollisionDectection::collideWithBackgroundMask() - j: " + j);
					    System.out.println("CollisionDectection::collideWithBackgroundMask() - spriteAlpha: " + spriteAlpha);
					    System.out.println("CollisionDectection::collideWithBackgroundMask() - maskAlpha: " + maskAlpha);
					    
					    System.out.println("-----------------------------------------------------------");
					    System.out.println("Testing i, j, spreteAlpha, maskAlpha ends");
					    System.out.println("-----------------------------------------------------------");
//					    System.exit(1);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static boolean collideWithBackgroundMaskFromYOffset(BufferedImage mask, int maskOffsetX, int maskOffsetY, BufferedImage sprite, int spriteX, int spriteY, int spriteYOffset) {
//		System.out.println("collisionDetection!!");
//		System.out.println("maskOffsetX: " + maskOffsetX + " maskOffsetY:" + maskOffsetY);
//		System.out.println("spriteX: " + spriteX + " spriteY:" + spriteY);

		int maskW = mask.getWidth();
		int maskH = mask.getHeight();
		
		int spriteW = sprite.getWidth();
		int spriteH = sprite.getHeight();
//		System.out.println("spriteW: " + spriteW + " spriteH:" + spriteH);

		int spriteXOnMask = spriteX - maskOffsetX;
		int spriteYOnMask = spriteY - maskOffsetY;
		
		// check if any pixel(non transparent pixel) of the sprite collide with any pixel(non transparent pixel) of the mask.
		for (int i = spriteYOffset; i < spriteH; i++) {
			for (int j = 0; j < spriteW; j++) {
//				System.out.print("(" + i + ", " + j + "): ");
				if (spriteYOnMask + i < maskH && spriteXOnMask + j < maskW) {
//					System.out.println("spriteXOnMask: " + spriteXOnMask + ", spriteYOnMask: " + spriteYOnMask + "): ");
					int spriteAlpha = new Color(sprite.getRGB(j, i), true).getAlpha();
					int maskAlpha = new Color(mask.getRGB(spriteXOnMask + j, spriteYOnMask + i), true).getAlpha();
	
					if (spriteAlpha > 0 && maskAlpha > 0) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

	public static boolean collideWithUnitMask(BufferedImage sprite1, int sprite1X, int sprite1Y, BufferedImage sprite2, int sprite2X, int sprite2Y) {
		int sprite1W = sprite1.getWidth();
		int sprite1H = sprite1.getHeight();
		
		int sprite2W = sprite2.getWidth();
		int sprite2H = sprite2.getHeight();

		// check if any pixel(non transparent pixel) of the sprite collide with any pixel(non transparent pixel) of the mask.
		for (int i = 0; i < sprite1H; i++) {
			for (int j = 0; j < sprite1W; j++) {
				if (sprite1X + j >= sprite2X && sprite1X + j < sprite2X + sprite2W &&
					sprite1Y + i >= sprite2Y && sprite1Y + i < sprite2Y + sprite2H) {
					int sprite1Alpha = new Color(sprite1.getRGB(j, i), true).getAlpha();
					int sprite2Alpha = new Color(sprite2.getRGB((sprite1X + j) - sprite2X, (sprite1Y + i) - sprite2Y), true).getAlpha();
	
					if (sprite1Alpha > 0 && sprite2Alpha > 0) {
						return true;
					}
				}
			}
		}
		
		return false;
	}

//	public static boolean collideWithMaskAfterRotateByAngle(BufferedImage mask, int maskOffsetX, int maskOffsetY, BufferedImage sprite, int spriteX, int spriteY, double spriteDegree) {
//		int maskW = mask.getWidth();
//		int maskH = mask.getHeight();
//
//		int spriteW = sprite.getWidth();
//		int spriteH = sprite.getHeight();
//
//		double spriteTopLeftXFromCenter = -1.0 * sprite.getWidth() / 2;
//		double spriteTopLeftYFromCenter = -1.0 * sprite.getHeight() / 2;
//
//		//---------------------------------------------------------------------
//		//---------------------------------------------------------------------
//		// 아래처럼 특정 위치의 RGBA값을 빼 볼 수 있다.(아래 코드는 참고삼아 한개 keep할 것. 지우지 말고)
//		//---------------------------------------------------------------------
//		//---------------------------------------------------------------------
//		// top left corner
//    	FreePoint rotatedTopLeft = new FreePoint(spriteTopLeftXFromCenter, spriteTopLeftYFromCenter);
//    	FreePoint.rotate_point(spriteDegree, rotatedTopLeft);
//    	double spriteTopLeftXAfterRotation = rotatedTopLeft.x + sprite.getWidth() / 2;
//    	double spriteTopLeftYAfterRotation = rotatedTopLeft.y + sprite.getHeight() / 2;
//		double spriteTopLeftXAfterRotationOnMap01 = spriteX + spriteTopLeftXAfterRotation - maskOffsetX;
//		double spriteTopLeftYAfterRotationOnMap01 = spriteY + spriteTopLeftYAfterRotation - maskOffsetY;
//
//		// top right corner
//    	FreePoint rotatedTopRight = new FreePoint(spriteTopLeftXFromCenter + spriteW, spriteTopLeftYFromCenter);
//    	FreePoint.rotate_point(spriteDegree, rotatedTopRight);
//    	double spriteTopRightXAfterRotation = rotatedTopRight.x + sprite.getWidth() / 2;
//    	double spriteTopRightYAfterRotation = rotatedTopRight.y + sprite.getHeight() / 2;
//		double spriteTopRightXAfterRotationOnMap01 = spriteX + spriteTopRightXAfterRotation- maskOffsetX;
//		double spriteTopRightYAfterRotationOnMap01 = spriteY + spriteTopRightYAfterRotation- maskOffsetY;
//
//		// bottom left corner
//    	FreePoint rotatedBottomLeft = new FreePoint(spriteTopLeftXFromCenter, spriteTopLeftYFromCenter + spriteH);
//    	FreePoint.rotate_point(spriteDegree, rotatedBottomLeft);
//    	double spriteBottomLeftXAfterRotation = rotatedBottomLeft.x + sprite.getWidth() / 2;
//    	double spriteBottomLeftYAfterRotation = rotatedBottomLeft.y + sprite.getHeight() / 2;
//		double spriteBottomLeftXAfterRotationOnMap01 = spriteX + spriteBottomLeftXAfterRotation - maskOffsetX;
//		double spriteBottomLeftYAfterRotationOnMap01 = spriteY + spriteBottomLeftYAfterRotation - maskOffsetY;
//
//		// bottom right corner
//    	FreePoint rotatedBottomRight = new FreePoint(spriteTopLeftXFromCenter + spriteW, spriteTopLeftYFromCenter + spriteH);
//    	FreePoint.rotate_point(spriteDegree, rotatedBottomRight);
//    	double spriteBottomRightXAfterRotation = rotatedBottomRight.x + sprite.getWidth() / 2;
//    	double spriteBottomRightYAfterRotation = rotatedBottomRight.y + sprite.getHeight() / 2;
//		double spriteBottomRightXAfterRotationOnMap01 = spriteX + spriteBottomRightXAfterRotation - maskOffsetX;
//		double spriteBottomRightYAfterRotationOnMap01 = spriteY + spriteBottomRightYAfterRotation - maskOffsetY;
//
//		//---------------------------------------------------------------------
//		// collision detection
//		//---------------------------------------------------------------------
////		// top left corner
////		System.out.println("(int)spriteTopLeftXAfterRotationOnMap01 = " + (int)spriteTopLeftXAfterRotationOnMap01 + " (int)spriteTopLeftYAfterRotationOnMap01 = " + (int)spriteTopLeftYAfterRotationOnMap01);
//		if ((int)spriteTopLeftXAfterRotationOnMap01 >= 0 && (int)spriteTopLeftXAfterRotationOnMap01 < maskW &&
//			(int)spriteTopLeftYAfterRotationOnMap01 >= 0 && (int)spriteTopLeftYAfterRotationOnMap01 < maskH) {
//			int maskAlpha = new Color(mask.getRGB((int)spriteTopLeftXAfterRotationOnMap01, (int)spriteTopLeftYAfterRotationOnMap01), true).getAlpha();
//	
//			if (maskAlpha > 0) {
//				return true;
//			}
//		}
//
////		// top right corner
////		System.out.println("(int)spriteTopRightXAfterRotationOnMap01 = " + (int)spriteTopRightXAfterRotationOnMap01 + " (int)spriteTopRightYAfterRotationOnMap01 = " + (int)spriteTopRightYAfterRotationOnMap01);
//		if ((int)spriteTopRightXAfterRotationOnMap01 >= 0 && (int)spriteTopRightXAfterRotationOnMap01 < maskW &&
//			(int)spriteTopRightYAfterRotationOnMap01 >= 0 && (int)spriteTopRightYAfterRotationOnMap01 < maskH) {
//			int maskAlpha = new Color(mask.getRGB((int)spriteTopRightXAfterRotationOnMap01, (int)spriteTopRightYAfterRotationOnMap01), true).getAlpha();
//	
//			if (maskAlpha > 0) {
//				return true;
//			}
//		}
//		
////		// bottom left corner
////		System.out.println("(int)spriteBottomLeftXAfterRotationOnMap01 = " + (int)spriteBottomLeftXAfterRotationOnMap01 + " (int)spriteBottomLeftYAfterRotationOnMap01 = " + (int)spriteBottomLeftYAfterRotationOnMap01);
//		if ((int)spriteBottomLeftXAfterRotationOnMap01 >= 0 && (int)spriteBottomLeftXAfterRotationOnMap01 < maskW &&
//			(int)spriteBottomLeftYAfterRotationOnMap01 >= 0 && (int)spriteBottomLeftYAfterRotationOnMap01 < maskH) {
//			int maskAlpha = new Color(mask.getRGB((int)spriteBottomLeftXAfterRotationOnMap01, (int)spriteBottomLeftYAfterRotationOnMap01), true).getAlpha();
//
//			if (maskAlpha > 0) {
//				return true;
//			}
//		}
//
////		// bottom right corner
////		System.out.println("(int)spriteBottomRightXAfterRotationOnMap01 = " + (int)spriteBottomRightXAfterRotationOnMap01 + " (int)spriteBottomRightYAfterRotationOnMap01 = " + (int)spriteBottomRightYAfterRotationOnMap01);
//		if ((int)spriteBottomRightXAfterRotationOnMap01 >= 0 && (int)spriteBottomRightXAfterRotationOnMap01 < maskW &&
//			(int)spriteBottomRightYAfterRotationOnMap01 >= 0 && (int)spriteBottomRightYAfterRotationOnMap01 < maskH) {
//			int maskAlpha = new Color(mask.getRGB((int)spriteBottomRightXAfterRotationOnMap01, (int)spriteBottomRightYAfterRotationOnMap01), true).getAlpha();
//
//			if (maskAlpha > 0) {
//				return true;
//			}
//		}
//		
//		return false;
//
////		int rgba = mask.getRGB((int)spriteXonMap01, (int)spriteYonMap01);
////		Color col = new Color(rgba, true);
////		int red = col.getRed();
////		int green = col.getGreen();
////		int blue = col.getBlue();
////		int alpha = col.getAlpha();
////		System.out.println("(" + (int)spriteXonMap01 + "," + (int)spriteYonMap01 + ") RGB: " + red + ", " + green + ", " + blue + ", " + alpha);
//
//		//??????????????????????????????????????????????????????????????????????????????????????????????????????????????
//		//??????????????????????????????????????????????????????????????????????????????????????????????????????????????
//		//??????????????????????????????????????????????????????????????????????????????????????????????????????????????
//		// 앨거리듬: 회전하는 sprite를 pixel base로 충돌판정하기.
//		// 	정사각형의 똑바른 귀퉁이의 점들 4개를 돌리면, 돌아진 상태에서의 위치의 맨위/옆/아래의 pos들이 나오는데, 이것들을 이용해 사각형을 만든다음,
//		// 	그 영역의 모든 pixel을 다시 원점으로 돌렸을때, sprite의 영역안이면서 불투명한 색인지 점검하고 이것을 mask와 비교하면
//		// 	rotate된 sprite도 pixel base로 충돌판정을 할 수 있다. 
//		//??????????????????????????????????????????????????????????????????????????????????????????????????????????????
//		//??????????????????????????????????????????????????????????????????????????????????????????????????????????????
//		//??????????????????????????????????????????????????????????????????????????????????????????????????????????????
//		// ToDo: 아래에서 일단 점들이 잘 회전되는지 판단하고.. 우선적으로 2D box collide를 만들고.. 그 후에 pixel based collide를 만들어본다.
////		
////		int spriteTopLeftAlpha = new Color(mask.getRGB((int)spriteTopLeftXonMap01, (int)spriteTopLeftYonMap01), true).getAlpha(); // 차의 top-left 귀퉁이.
////		int spriteTopRightAlpha = new Color(mask.getRGB((int)spriteTopRightXonMap01, (int)spriteTopRightYonMap01), true).getAlpha();
////		int spriteBottomLeftAlpha = new Color(mask.getRGB((int)spriteBottomLeftXonMap01, (int)spriteBottomLeftYonMap01), true).getAlpha();
////		int spriteBottomRightAlpha = new Color(mask.getRGB((int)spriteBottomRightXonMap01, (int)spriteBottomRightYonMap01), true).getAlpha();
////		// alpha가 0일 경우가 투명한 곳이고 갈 수 있다. 검정색 부분은 (0,0,0,255) / 투명 부분은 (0,0,0,0)
////		if (spriteTopLeftAlpha == 0 && spriteTopRightAlpha == 0 && spriteBottomLeftAlpha == 0 && spriteBottomRightAlpha == 0) {
////			map01X = spriteX;
////			map01Y = spriteY;
////		}
//	}
}
