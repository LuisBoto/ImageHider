package hider;

public class ImageHider {

	private ImageWriteReader imgWriteReader;
	private Steganographer steganographer;

	public static void main(String[] args) {
		//new ImageHider().hide(args[0], args[1]);
		new ImageHider().hide("./madre.png", "sableye.png");
	}

	public ImageHider() {
		this.imgWriteReader = new ImageWriteReader();
		this.steganographer = new Steganographer();
	}

	public void hide(String canvasImageUrl, String secretImageUrl) {
		this.imgWriteReader.writeImage(this.steganographer.hide(
				this.imgWriteReader.readImage(canvasImageUrl),
				this.imgWriteReader.readImage(secretImageUrl)));
	}

}
