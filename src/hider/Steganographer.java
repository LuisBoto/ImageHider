package hider;

import java.awt.image.BufferedImage;

import hider.pictureHiding.ImageHiderHandler;

public class Steganographer {

	private BufferedImage canvasImage;
	private BufferedImage secretImage;
	private ImageHiderHandler imgHandler;
	public final static int METADATA_PIXELS = 3;

	public Steganographer() {
		this.imgHandler = new ImageHiderHandler();
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
		if (canvasArea < (2 * secretArea + Steganographer.METADATA_PIXELS)) {
			System.out.println("Canvas image is too small.");
			System.exit(1);
		}
	}

}
