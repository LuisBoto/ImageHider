package hider;

public class BinaryConverter {
	
	public String integerToBinaryString(int value) {
		String result = Integer.toBinaryString(value);
        String paddedResult = String.format("%32s", result).replaceAll(" ", "0");
        return paddedResult;
	}

}
