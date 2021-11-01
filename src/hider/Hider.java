package hider;

import java.awt.image.BufferedImage;

public class Hider {
	
	private ImageReader imgReader;
	private Steganographer steganographer;
	private BufferedImage canvasImage;
	private BufferedImage secretImage;
	
	public void main(String[] args) {
		new Hider().hide(args[0], args[1]);
	}
	
	public Hider() {
		this.imgReader = new ImageReader();
		this.steganographer = new Steganographer();
	}
	
	public void hide(String canvasImageUrl, String secretImageUrl) {
		this.canvasImage = this.imgReader.readImage(canvasImageUrl);
		this.secretImage = this.imgReader.readImage(secretImageUrl);
		this.steganographer.checkImageDimensions(this.canvasImage, this.secretImage);
	}

}
