package hider;

public enum Messages {
	
	CANVAS_TOO_SMALL("Canvas image is too small."),
	READING_IMAGE_FILES("Reading image files..."),
	SETTING_METADATA("Setting secret image metadata..."),
	MERGING_IMAGES("Merging images..."),
	READING_METADATA("Reading metadata..."),
	UNMERGING_IMAGES("Extracting hidden image..."),
	NO_HIDDEN_IMAGE("No hidden image metadata found."),
	IMAGE_HIDDEN_SUCCESSFULLY("Image hidden successfully."),
	IMAGE_EXTRACTED_SUCCESSFULLY("Hidden image extracted successfully."),
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
