package hider;

import java.awt.image.BufferedImage;

import hider.imageHandling.ImageHiderHandler;
import hider.imageHandling.ImageRevealHandler;

public class Steganographer {

	private BufferedImage canvasImage;
	private BufferedImage secretImage;
	private ImageHiderHandler imgHideHandler;
	private ImageRevealHandler imgRevealHandler;
	public final static int METADATA_PIXELS = 2;

	public Steganographer() {
		this.imgHideHandler = new ImageHiderHandler();
		this.imgRevealHandler = new ImageRevealHandler();
	}

	public BufferedImage hide(BufferedImage canvasImage, BufferedImage secretImage) {
		this.canvasImage = canvasImage;
		this.secretImage = secretImage;
		this.checkHideImageDimensions();
		return this.imgHideHandler.hide(this.canvasImage, this.secretImage);

	}

	public BufferedImage reveal(BufferedImage canvasImage) {
		this.canvasImage = canvasImage;
		this.checkImageRevealMetadata();
		return this.imgRevealHandler.reveal(this.canvasImage);
	}

	private void checkHideImageDimensions() {
		int canvasArea = this.canvasImage.getHeight() * this.canvasImage.getWidth();
		int secretArea = this.secretImage.getHeight() * this.secretImage.getWidth();
		if (canvasArea < (2 * secretArea + Steganographer.METADATA_PIXELS)) {
			Messages.CANVAS_TOO_SMALL.println();
			System.exit(1);
		}
	}

	private void checkImageRevealMetadata() {
		int width = 0x00ffffff & this.canvasImage.getRGB(this.canvasImage.getWidth() - 1, this.canvasImage.getHeight() - 1);
		int height = 0x00ffffff	& this.canvasImage.getRGB(this.canvasImage.getWidth() - 1, this.canvasImage.getHeight() - 2);
		double secretArea = Math.abs(width * height);
		double canvasArea = this.canvasImage.getHeight() * this.canvasImage.getWidth();
		if ((2 * secretArea + Steganographer.METADATA_PIXELS) > canvasArea) {
			Messages.NO_HIDDEN_IMAGE.println();
			System.exit(1);
		}
	}

}
