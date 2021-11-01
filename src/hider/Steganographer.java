package hider;

import java.awt.image.BufferedImage;

public class Steganographer {

	public void checkImageDimensions(BufferedImage canvas, BufferedImage secret) {
		int canvasArea = canvas.getHeight() * canvas.getWidth();
		int secretArea = secret.getHeight() * secret.getWidth();
		if (canvasArea < 2*secretArea) {
			System.out.println("Canvas image is too small.");
			System.exit(1);
		}
	}

}
