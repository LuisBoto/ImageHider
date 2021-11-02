package hider.imageHandling;

import java.awt.image.BufferedImage;

public class ImageRevealHandler extends ImageHandler {

	private int totalSecretPixels;

	public ImageRevealHandler() {
		super();
	}

	public BufferedImage reveal(BufferedImage canvasImage) {
		this.canvasImage = canvasImage;
		this.canvasPixels = this.getPixelMatrix(this.canvasImage);
		this.readSecretMetadata();
		MatrixListHandler canvasHandler = new MatrixListHandler(this.canvasPixels);
		MatrixListHandler secretHandler = new MatrixListHandler(this.secretPixels);

		int canvasPixel1, canvasPixel2, canvasCounter = 0, nextPixelPair = 0, secretPixelCount = 0;
		while (canvasHandler.hasNextInt() && secretPixelCount < this.totalSecretPixels) {
			if (canvasCounter == nextPixelPair) {
				nextPixelPair = canvasCounter + 2 + ratio;
				canvasPixel1 = canvasHandler.getNextInt();
				canvasPixel2 = canvasHandler.getNextInt();
				secretHandler.setNextInt(this.binaryConverter.unmergeSecretPixel(canvasPixel1, canvasPixel2));
				canvasCounter++;
				secretPixelCount++;
			} else {
				canvasHandler.getNextInt();
			}
			canvasCounter++;
		}
		this.secretPixels = secretHandler.getMatrix();
		this.secretImage = this.writeImagePixels(this.secretImage, this.secretPixels);
		return this.secretImage;
	}

	private void readSecretMetadata() {
		int width = 0x00ffffff & this.canvasPixels[this.canvasPixels.length - 1][this.canvasPixels[0].length - 1];
		int height = 0x00ffffff & this.canvasPixels[this.canvasPixels.length - 1][this.canvasPixels[0].length - 2];
		this.secretImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.ratio = this.getRatio();
		this.secretPixels = this.getPixelMatrix(this.secretImage);
		this.totalSecretPixels = width * height;
	}

}