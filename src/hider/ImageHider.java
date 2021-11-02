package hider;

public class ImageHider {

	private ImageWriteReader imgWriteReader;
	private Steganographer steganographer;

	public static void main(String[] args) {
		if (args.length <= 0 || args[0].equals("--help")) {
			Messages.HELP.println();
			System.exit(0);
		}
		
		String output;
		if (args[0].equals("--hide")) {
			output = "./hiddenOutput.png";
			if (args.length == 4)
				output = args[3];
			new ImageHider().hide(args[1], args[2], output);				
		}
		if (args[0].equals("--reveal")) {
			output = "./revealedOutput.png";
			if (args.length == 3)
				output = args[2];
			new ImageHider().reveal(args[1], output);	
		}
	}

	public ImageHider() {
		this.imgWriteReader = new ImageWriteReader();
		this.steganographer = new Steganographer();
	}

	public void hide(String canvasImageUrl, String secretImageUrl, String outputUrl) {
		this.imgWriteReader.writeImage(this.steganographer.hide(
				this.imgWriteReader.readImage(canvasImageUrl),
				this.imgWriteReader.readImage(secretImageUrl)),
				outputUrl);
		Messages.IMAGE_HIDDEN_SUCCESSFULLY.println();
	}

	public void reveal(String canvasImageUrl, String outputUrl) {
		this.imgWriteReader.writeImage(this.steganographer.reveal(
				this.imgWriteReader.readImage(canvasImageUrl)),
				outputUrl);
		Messages.IMAGE_EXTRACTED_SUCCESSFULLY.println();
	}

}
