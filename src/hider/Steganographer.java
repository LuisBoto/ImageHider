package hider;

import java.awt.image.BufferedImage;

public class Steganographer {

	private BufferedImage canvasImage;
	private BufferedImage secretImage;
	private ImageHandler imgHandler;

	public Steganographer() {
		this.imgHandler = new ImageHandler();
	}

	public BufferedImage hide(BufferedImage canvasImage, BufferedImage secretImage) {
		this.canvasImage = canvasImage;
		this.secretImage = secretImage;
		this.checkImageDimensions();		
		return this.imgHandler.hide(this.canvasImage, this.secretImage);
		
	}

	private void checkImageDimensions() {
		int canvasArea = this.canvasImage.getHeight() * this.canvasImage.getWidth();
		int secretArea = this.secretImage.getHeight() * this.secretImage.getWidth();
		if (canvasArea < (2 * secretArea + 1)) {
			System.out.println("Canvas image is too small.");
			System.exit(1);
		}
	}

}
