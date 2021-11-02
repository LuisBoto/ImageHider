package hider.pictureHiding;

import java.awt.image.BufferedImage;

import hider.BinaryConverter;

public class ImageHiderHandler {

	private BinaryConverter binaryConverter;

	public ImageHiderHandler() {
		this.binaryConverter = new BinaryConverter();
	}

	public BufferedImage hide(BufferedImage canvasImage, BufferedImage secretImage) {
		BufferedImage resultImage = new BufferedImage(canvasImage.getWidth(), canvasImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		int[][] resultPixelMatrix = this.getPixelMatrix(resultImage);
		resultPixelMatrix = this.merge(this.getPixelMatrix(canvasImage), this.getPixelMatrix(secretImage), resultPixelMatrix);
		resultPixelMatrix = this.setTailingPixelMetadata(resultPixelMatrix, secretImage);
		return this.writeResultImagePixels(resultImage, resultPixelMatrix);
	}

	private int[][] merge(int[][] canvas, int[][] secret, int[][] result) {
		MatrixListHandler canvasHandler = new MatrixListHandler(canvas);
		MatrixListHandler secretHandler = new MatrixListHandler(secret);
		MatrixListHandler resultHandler = new MatrixListHandler(result);
		int secretPixel, canvasPixel;
		while (secretHandler.hasNextInt()) {
			secretPixel = secretHandler.getNextInt();
			resultHandler.setNextInt(this.binaryConverter.mergeFirstHalfs(canvasHandler.getNextInt(), secretPixel));
			resultHandler.setNextInt(this.binaryConverter.mergeSecondHalfs(canvasHandler.getNextInt(), secretPixel));
		}
		while (canvasHandler.hasNextInt()) {
			canvasPixel = canvasHandler.getNextInt();
			resultHandler.setNextInt(canvasPixel);
		}
		return resultHandler.getMatrix();
	}

	private int[][] setTailingPixelMetadata(int[][] target, BufferedImage imgToMeasure) {
		target[target.length - 1][target[0].length - 1] = imgToMeasure.getHeight();
		target[target.length - 2][target[0].length - 2] = imgToMeasure.getWidth();
		return target;
	}
	
	private BufferedImage writeResultImagePixels(BufferedImage resultImage, int[][] resultPixelMatrix) {
		for (int i = 0; i < resultImage.getWidth(); i++) {
			for (int j = 0; j < resultImage.getHeight(); j++) {
				resultImage.setRGB(i, j, resultPixelMatrix[i][j]);
			}
		}
		return resultImage;
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
