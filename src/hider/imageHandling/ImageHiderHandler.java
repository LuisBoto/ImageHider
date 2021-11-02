package hider.imageHandling;

import java.awt.image.BufferedImage;

public class ImageHiderHandler extends ImageHandler {

	private BufferedImage resultImage;
	private int[][] resultPixels;

	public ImageHiderHandler() {
		super();
	}

	public BufferedImage hide(BufferedImage canvasImage, BufferedImage secretImage) {
		this.canvasImage = canvasImage;
		this.secretImage = secretImage;
		this.resultImage = new BufferedImage(canvasImage.getWidth(), canvasImage.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.ratio = this.getRatio();
		this.canvasPixels = this.getPixelMatrix(this.canvasImage);
		this.secretPixels = this.getPixelMatrix(this.secretImage);
		this.resultPixels = this.getPixelMatrix(this.resultImage);
		
		this.merge();
		this.setTailingPixelMetadata();
		this.writeImagePixels(this.resultImage, this.resultPixels);
		return this.resultImage;
	}

	private void merge() {
		MatrixListHandler canvasHandler = new MatrixListHandler(this.canvasPixels);
		MatrixListHandler secretHandler = new MatrixListHandler(this.secretPixels);
		MatrixListHandler resultHandler = new MatrixListHandler(this.resultPixels);
		int secretPixel, canvasPixel, counter = 0, nextPixelPair = 0;
		while (canvasHandler.hasNextInt()) {
			if (counter == nextPixelPair && secretHandler.hasNextInt()) {
				nextPixelPair = counter + 2 + ratio;
				secretPixel = secretHandler.getNextInt();
				resultHandler.setNextInt(this.binaryConverter.mergeFirstHalfs(canvasHandler.getNextInt(), secretPixel));
				resultHandler.setNextInt(this.binaryConverter.mergeSecondHalfs(canvasHandler.getNextInt(), secretPixel));
				counter++;
			} else {
				canvasPixel = canvasHandler.getNextInt();
				resultHandler.setNextInt(canvasPixel);
			}
			counter++;
		}
		this.resultPixels = resultHandler.getMatrix();
	}

	private void setTailingPixelMetadata() {
		this.resultPixels[this.resultPixels.length - 1][this.resultPixels[0].length - 1] = this.secretImage.getWidth();
		this.resultPixels[this.resultPixels.length - 1][this.resultPixels[0].length - 2] = this.secretImage.getHeight();
	}

}
