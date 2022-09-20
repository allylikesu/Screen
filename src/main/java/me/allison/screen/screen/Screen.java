package me.allison.screen.screen;

public class Screen {
	private int height;
	private int width;
	private char[] borderChars;
	private char[][] chars;
	private String displayCache;

	public Screen() {
		height = Integer.parseInt(System.getenv("ROWS")) - 1;
		width = Integer.parseInt(System.getenv("COLUMNS"));

		borderChars = new char[] {'┏', '━', '┓', '┃', '┃', '┗', '━', '┛'};
		clear();
	}
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		borderChars = new char[] {'┏', '━', '┓', '┃', '┃', '┗', '━', '┛'};
		clear();
	}

	public void clear() {
		chars = new char[height][width];
		populateWithWhiteSpace();
	}

	public void populateWithBorder() {
		// Top
		for(int i = 0; i < chars[0].length; i++) {
			chars[0][i] = borderChars[1];
		}
		chars[0][0] = borderChars[0];
		chars[0][chars[0].length - 1] = borderChars[2];

		// Left side
		for(int i = 1; i < chars.length - 1; i++) {
			chars[i][0] = borderChars[3];
		}
		// Right side
		for(int i = 1; i < chars.length; i++) {
			chars[i][chars[0].length - 1] = borderChars[4];
		}

		// Bottom
		int bottom = chars.length - 1;
		for(int i = 0; i < chars[bottom].length; i++) {
			chars[bottom][i] = borderChars[6];
		}
		chars[bottom][0] = borderChars[5];
		chars[bottom][chars[bottom].length - 1] = borderChars[7];
	}
	public void populateWithWhiteSpace() {
		for(int r = 0; r < chars.length; r++) {
			for(int c = 0; c < chars[r].length; c++) {
				chars[r][c] = ' ';
			}
		}
	}

	public int getHeight() { return height; }
	public int getWidth() { return height; }

	public String toString() {
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
}
