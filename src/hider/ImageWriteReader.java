package hider;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageWriteReader {

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

	public void writeImage(BufferedImage img) {
		File outputfile = new File("./test.png");
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			System.out.println("Error writing image to disk.");
			System.exit(1);
		}
	}

}
