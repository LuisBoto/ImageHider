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
		resultPixelMatrix = this.merge(this.getPixelMatrix(canvasImage), this.getPixelMatrix(secretImage), resultPixelMatrix);
		resultPixelMatrix = this.setLastPixelToSize(resultPixelMatrix, secretImage);
		return this.writeResultImagePixels(resultImage, resultPixelMatrix);
	}

	private BufferedImage writeResultImagePixels(BufferedImage resultImage, int[][] resultPixelMatrix) {
		for (int i = 0; i < resultImage.getWidth(); i++) {
			for (int j = 0; j < resultImage.getHeight(); j++) {
				resultImage.setRGB(i, j, resultPixelMatrix[i][j]);
			}
		}
		return resultImage;
	}

	private int[][] merge(int[][] canvas, int[][] secret, int[][] result) {
		MatrixListHandler canvasHandler = new MatrixListHandler(canvas);
		MatrixListHandler secretHandler = new MatrixListHandler(secret);
		MatrixListHandler resultHandler = new MatrixListHandler(result);
		int secretPixel, canvasPixel;
		while (secretHandler.hasNextInt()) {
			secretPixel = secretHandler.getNextInt();
			resultHandler.setNextInt(this.mergeFirstHalfPixels(canvasHandler.getNextInt(), secretPixel));
			resultHandler.setNextInt(this.mergeSecondHalfPixels(canvasHandler.getNextInt(), secretPixel));
		}
		while (canvasHandler.hasNextInt()) {
			canvasPixel = canvasHandler.getNextInt();
			resultHandler.setNextInt(canvasPixel);
		}
		return resultHandler.getMatrix();
	}

	private int mergeFirstHalfPixels(int canvasFirst, int secret) {
		// First canvas pixel hides first half secret bits.
		return this.binaryConverter.mergeFirstHalfs(canvasFirst, secret);
	}

	private int mergeSecondHalfPixels(int canvasSecond, int secret) {
		// Second canvas pixel hides last half secret bits.
		return this.binaryConverter.mergeSecondHalfs(canvasSecond, secret);
	}

	private int[][] setLastPixelToSize(int[][] target, BufferedImage imgToMeasure) {
		target[target.length - 1][target[0].length - 1] = imgToMeasure.getHeight() * imgToMeasure.getWidth();
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
