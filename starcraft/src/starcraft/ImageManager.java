package starcraft;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;

public class ImageManager {
	public Vector<BufferedImage> images;
	public Vector<String> imageFilenames;
	
	public ImageManager() {
		images = new Vector<BufferedImage>();
		imageFilenames = new Vector<String>();
	}
	public static boolean lock = false;
	private void addImage(String imageFilename) {
		// timer나 thread등에 의해, 아래의 critical area에서 collision이 일어나지 않도록 한다.
        if (lock) {
        	while (lock) {
        		try        
        		{
        		    Thread.sleep(10); // race condition을 최소화하면서 performance저하를 최소화하는 sleep time을 정할 것.
        		} 
        		catch(InterruptedException ex) 
        		{
        		    Thread.currentThread().interrupt();
        		}
        	}
        }
        lock = true;
        
        //---------------------------------------------------------------------
        //---------------------------------------------------------------------
        //---------------------------------------------------------------------
        // Critical Area: start
        //---------------------------------------------------------------------
        //---------------------------------------------------------------------
        //---------------------------------------------------------------------
		if (!imageFilenames.contains(imageFilename)) {
			imageFilenames.add(imageFilename);
	        try {
	        	BufferedImage image = null;
	        	image = ImageIO.read(new File(imageFilename));
	        	images.add(image);
	        } catch (Exception e) {
	            System.out.println("ImageManager::addImage(): error: image file not found: " + imageFilename);
	            System.exit(1);
	        }
		}
        if (imageFilenames.size() != images.size()) {
            System.out.println("ImageManager::addImage(): ????????????????????????????????????????");
            System.exit(1);
        }
        //---------------------------------------------------------------------
        //---------------------------------------------------------------------
        //---------------------------------------------------------------------
        // Critical Area: end
        //---------------------------------------------------------------------
        //---------------------------------------------------------------------
        //---------------------------------------------------------------------

        lock = false;
	}
	
	public BufferedImage getImage(String imageFilename) {
		addImage(imageFilename);
		
		int index = imageFilenames.indexOf(imageFilename);
		if (index == -1) {
            System.exit(1);
		}
		return images.get(index);
	}
}
