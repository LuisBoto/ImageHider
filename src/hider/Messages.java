package hider;

public enum Messages {
	
	CANVAS_TOO_SMALL("Canvas image is too small."),
	READING_IMAGE_FILES("Reading image files..."),
	NO_HIDDEN_IMAGE("No hidden image metadata found."),
	HELP("java -jar hider.jar\n "
			+ "\t--hide [canvasImagePath] [secretImagePath] (optional)[outputFilePath]: Hide secretImage into canvasImage.\n"
			+ "\t--reveal [canvasImagePath] (optional)[outputFilePath]: Reveal hidden image within canvasImage.\n"
			+ "\t--help: Display help.");
	
	private String message;
	
	private Messages(String message) {
		this.message = message;
	}
	
	public void println() {
		System.out.println(this.message);
	}

}
