package hider.imageHandling;

import java.awt.image.BufferedImage;

import hider.Steganographer;

public abstract class ImageHandler {
	
	BinaryConverter binaryConverter;
	BufferedImage canvasImage;
	BufferedImage secretImage;
	int ratio;
	int[][] canvasPixels;
	int[][] secretPixels;
	
	ImageHandler() {
		this.binaryConverter = new BinaryConverter();
	}
	
	int getRatio() {
		int usableCanvasPixels = (this.canvasImage.getHeight() * this.canvasImage.getWidth())
				- Steganographer.METADATA_PIXELS;
		return (usableCanvasPixels / (2 * this.secretImage.getWidth() * this.secretImage.getHeight()));
	}

	int[][] getPixelMatrix(BufferedImage img) {
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
	
	BufferedImage writeImagePixels(BufferedImage resultImage, int[][] pixels) {
		for (int i = 0; i < resultImage.getWidth(); i++) {
			for (int j = 0; j < resultImage.getHeight(); j++) {
				resultImage.setRGB(i, j, pixels[i][j]);
			}
		}
		return resultImage;
	}
}
