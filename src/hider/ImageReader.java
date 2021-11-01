package hider;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageReader {
	
	public BufferedImage readImage(String url) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {
			System.out.println("Invalid file parameter.");
			System.exit(1);
		}
		return img;
	}

}
