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
		this.merge(this.getPixelMatrix(canvasImage), this.getPixelMatrix(secretImage),
				this.getPixelMatrix(resultImage));
		this.setLastPixelToSize(resultImage, secretImage);
	}

	private void merge(int[][] canvas, int[][] secret, int[][] result) {
		MatrixListHandler canvasHandler = new MatrixListHandler(canvas);
		MatrixListHandler secretHandler = new MatrixListHandler(secret);
		MatrixListHandler resultHandler = new MatrixListHandler(result);
		int secretPixel;
		while ((secretPixel = secretHandler.getNextInt()) >= 0) {
			resultHandler.setNextInt(this.mergeFirstPairPixels(canvasHandler.getNextInt(), secretPixel));
			resultHandler.setNextInt(this.mergeSecondPairPixels(canvasHandler.getNextInt(), secretPixel));
		}
	}

	private int mergeFirstPairPixels(int canvasFirst, int secret) {
		// First canvas pixel hides first 4 secret bits.
		// Each pixel looks like this: RRRR-RRRR-GGGG-GGGG-BBBB-BBBB
		// This operation substitutes: RRRR-SSSS-GGGG-SSSS-BBBB-SSSS
		String canvasBits = this.binaryConverter.integerToBinaryString(canvasFirst);
		String secretBits = this.binaryConverter.integerToBinaryString(secret);
	}

	private int mergeSecondPairPixels(int canvasSecond, int secret) {
		// Second canvas pixel hides last 4 secret bits.

	}

	private BufferedImage setLastPixelToSize(BufferedImage target, BufferedImage imgToMeasure) {
		target.setRGB(target.getWidth(), target.getHeight(), imgToMeasure.getHeight() * imgToMeasure.getWidth());
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
