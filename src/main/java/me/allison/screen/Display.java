package me.allison.screen;

public class Display {
	public Display() {
	}
	public Display(int width, int height) {
	}

	private int height;
	public int getHeight() { return height; }
	public void setHeight(int h) { height = h; }

	private int width;
	public int getWidth() { return width; }
	public void setWidth(int w) { width = w; }

	private String cache;
	public String getCache() { return cache; }
	public String setCache(String c) { cache = c; }

	private char[][] chars;
	public char[][] get2DCharArray() { return chars; }
	// Returns a String that represents the display. Useful for displaying the display.
	public String getDisplayString() {
		char[] arr = new char[chars.length * (chars[0].length + 1) - 1];
		int arrNdx = 0;
		for(int r = 0; r < chars.length; r++) {
			for(int c = 0; c < chars[r].length; c++) {
				arr[arrNdx] = chars[r][c];
				arrNdx++;
			}
			if(r < chars.length - 1) {
				arr[arrNdx] = '\n';
				arrNdx++;
			}
		}
		displayCache = new String(arr);
		return displayCache;

	}
	public void clearDisplay() {
		chars = new char[height][width];
		populateWithWhiteSpace();
	}
	public void populateWithWhiteSpace() {
		for(int r = 0; r < chars.length; r++) {
			for(int c = 0; c < chars[r].length; c++) {
				if(chars[r][c] == '') {
					chars[r][c] = ' ';
				}
			}
		}
	}
	public boolean printOnDisplay(String in, int xCoord, int yCoord) {
		String[] lines = in.split("\n");

		// Find longest line
		int inputLength = 0;
		for(String l: lines) {
			int len = l.length();
			if(len > inputLength) { inputLength = len; }
		}

		char[][] appendArray = new char[lines.length()][inputLength];
		for(int r = 0;
	}
}
