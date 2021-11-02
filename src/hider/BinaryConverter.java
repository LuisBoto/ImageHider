package hider;

public class BinaryConverter {

	public String integerToBinaryString(int value) {
		String result = Integer.toBinaryString(value);
		String paddedResult = String.format("%32s", result).replaceAll(" ", "0");
		System.out.println(paddedResult);
		return paddedResult;
	}

	public int mergeFirstHalfs(int canvas, int secret) {
		int blueCanvas = canvas & 0xff;
		int greenCanvas = (canvas & 0xff00);
		int redCanvas = (canvas & 0xff0000);
		int alphaCanvas = (canvas & 0xff000000);
		int blueSecret = secret & 0xff;
		int greenSecret = (secret & 0xff00);
		int redSecret = (secret & 0xff0000);
		// int alphaSecret = (secret & 0xff000000) >>> 24;

		blueCanvas = (blueCanvas & 0xf0) | ((blueSecret & 0xf0) >> 4);
		greenCanvas = (greenCanvas & 0xf000) | ((greenSecret & 0xf000) >> 4);
		redCanvas = (redCanvas & 0xf00000) | ((redSecret & 0xf00000) >> 4);
		return redCanvas | greenCanvas | blueCanvas | alphaCanvas;
	}

	public int mergeSecondHalfs(int canvas, int secret) {
		int blueCanvas = canvas & 0xff;
		int greenCanvas = (canvas & 0xff00);
		int redCanvas = (canvas & 0xff0000);
		int alphaCanvas = (canvas & 0xff000000);
		int blueSecret = secret & 0xff;
		int greenSecret = (secret & 0xff00);
		int redSecret = (secret & 0xff0000);
		// int alphaSecret = (secret & 0xff000000) >>> 24;

		blueCanvas = (blueCanvas & 0xf0) | (blueSecret & 0x0f);
		greenCanvas = (greenCanvas & 0xf000) | (greenSecret & 0x0f00);
		redCanvas = (redCanvas & 0xf00000) | (redSecret & 0x0f0000);
		return redCanvas | greenCanvas | blueCanvas | alphaCanvas;
	}

}
