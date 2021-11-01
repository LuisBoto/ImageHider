package hider;

public class ImageHider {
	
	private ImageReader imgReader;
	private Steganographer steganographer;
	
	public void main(String[] args) {
		new ImageHider().hide(args[0], args[1]);
	}
	
	public ImageHider() {
		this.imgReader = new ImageReader();
		this.steganographer = new Steganographer();
	}
	
	public void hide(String canvasImageUrl, String secretImageUrl) {
		this.steganographer.hide(this.imgReader.readImage(canvasImageUrl), this.imgReader.readImage(secretImageUrl));
	}

}
