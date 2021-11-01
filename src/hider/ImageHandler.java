package hider;

import java.awt.image.BufferedImage;

public class ImageHandler {

	private BinaryConverter binaryConverter;

	public ImageHandler() {
		this.binaryConverter = new BinaryConverter();
	}

	public BufferedImage hide(BufferedImage canvasImage, BufferedImage secretImage) {
		BufferedImage resultImage = new BufferedImage(canvasImage.getWidth(), canvasImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		int[][] resultPixelMatrix = this.getPixelMatrix(resultImage);
		this.merge(this.getPixelMatrix(canvasImage), this.getPixelMatrix(secretImage), resultPixelMatrix);
		this.setLastPixelToSize(resultPixelMatrix, secretImage);
		this.writeResultImagePixels(resultImage, resultPixelMatrix);
		return resultImage;
	}

	private void writeResultImagePixels(BufferedImage resultImage, int[][] resultPixelMatrix) {
		for (int i = 0; i < resultImage.getWidth(); i++) {
			for (int j = 0; j < resultImage.getHeight(); j++) {
				resultImage.setRGB(i, j, resultPixelMatrix[i][j]);
			}
		}
	}

	private void merge(int[][] canvas, int[][] secret, int[][] result) {
		MatrixListHandler canvasHandler = new MatrixListHandler(canvas);
		MatrixListHandler secretHandler = new MatrixListHandler(secret);
		MatrixListHandler resultHandler = new MatrixListHandler(result);
		int secretPixel, canvasPixel;
		while ((secretPixel = secretHandler.getNextInt()) >= 0) {
			resultHandler.setNextInt(this.mergeFirstPairPixels(canvasHandler.getNextInt(), secretPixel));
			resultHandler.setNextInt(this.mergeSecondPairPixels(canvasHandler.getNextInt(), secretPixel));
		}
		while ((canvasPixel = canvasHandler.getNextInt()) >= 0) {
			resultHandler.setNextInt(canvasPixel);
		}
	}

	private int mergeFirstPairPixels(int canvasFirst, int secret) {
		// First canvas pixel hides first half secret bits.
		// Each pixel looks like this: RRRR-RRRR-GGGG-GGGG-BBBB-BBBB
		// This operation substitutes: RRRR-SSSS-GGGG-SSSS-BBBB-SSSS
		char[] canvasBits = this.binaryConverter.integerToBinaryString(canvasFirst).toCharArray();
		char[] secretBits = this.binaryConverter.integerToBinaryString(secret).toCharArray();
		int counter = 0;
		for (int i = 0; i < Globals.SUBSTITUTED_BITS.length; i++) {
			canvasBits[i] = secretBits[counter];
			counter++;
		}
		return Integer.parseInt(new String(canvasBits), 2);
	}

	private int mergeSecondPairPixels(int canvasSecond, int secret) {
		// Second canvas pixel hides last half secret bits.
		char[] canvasBits = this.binaryConverter.integerToBinaryString(canvasSecond).toCharArray();
		char[] secretBits = this.binaryConverter.integerToBinaryString(secret).toCharArray();
		int counter = Globals.SUBSTITUTED_BITS.length - 1;
		for (int i = 0; i < Globals.SUBSTITUTED_BITS.length; i++) {
			canvasBits[i] = secretBits[counter];
			counter++;
		}
		return Integer.parseInt(new String(canvasBits), 2);
	}

	private int[][] setLastPixelToSize(int[][] target, BufferedImage imgToMeasure) {
		target[target.length][target[0].length] = imgToMeasure.getHeight() * imgToMeasure.getWidth();
		return target;
	}

	private int[][] getPixelMatrix(BufferedImage img) {
		int width = img.getWidth();
		int height = img.getHeight();
		int[][] pixel = new int[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixel[i][j] = img.getRGB(i, j);
			}
		}
		return pixel;
	}

}
