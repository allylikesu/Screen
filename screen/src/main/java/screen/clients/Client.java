package screen.clients;

import screen.core.Screen;
import java.util.ArrayList;

/**
 * A Client for a Screen object. Meant to be extended and customized.
 * @author allison
 * @version 0.1.0
 */
public class Client {

	/* ################## */
	/* INSTANCE VARIABLES */
	/* ################## */

	/**
	 * X-Coordinate of this Client's top-left corner.
	 * Gotten with {@link #getX()}
	 * Set with  {@link #setCoordinates(int, int)}
	 * @since 0.1.0
	 */
  	private int x;

	/**
	 * Y-Coordinate of this Client's top-left corner.
	 * Gotten with {@link #getY()}
	 * Set with {@link #setCoordinates(int, int)}
	 * @since 0.1.0
	 */
	private int y;

	/**
	 * Total length of this Client, in characters. Minimum value of 1.
	 * Gotten with {@link #getLength()}
	 * Set by the constructor or {@link #setGeom(int, int)}
	 * @since 0.1.0
	 */
  	private int length;

	/**
	 * Total height of this Client, in characters. Minimum value of 1.
	 * Gotten with {@link #getHeight()}
	 * Set by the constructor or {@link #setGeom(int, int)}
	 * @since 0.1.0
	 */
  	private int height;

	/**
	 * This Client's background character, used in {@link #clear()}.
	 * Gotten with {@link #getBackgroundChar()} or {@link #getBackgroundStr()}
	 * Set by {@link #setBackground(String)} or {@link #setBackground(char)}
	 * @since 0.1.0
	 */
  	private char background;

	/**
	 * 2-D ArrayList of single-character Strings representing this Client's contents. 
  	 * Initialized by the constructor
	 * Gotten with {@link #getContents()}
	 * Set by {@link #clear()}, {@link #setContents(String)}, {@link #setLine(int, String)}, and {@link #setLine(ArrayList<ArrayList<String>>)}
	 * @since 0.1.0
	 */
  	private ArrayList<ArrayList<String>> contents;

	/**
	 * Screen that this Client is currently attatched to.
	 * Gotten with {@link #getContext()}
	 * Set by {@link #attatch(Screen)}
	 * @since 0.1.0
	 */
  	private Screen context;

	/* ############ */
	/* CONSTRUCTORS */
	/* ############ */

	/**
	 * Creates a new Client of geometry (l, h). Sets the background character to 'X', calls {@link #clear()}
	 * @param l Client length
	 * @param h Client height
	 * @since 0.1.0
	 */
  	public Client(int l, int h) {
    		setBackground('X');
    		setGeom(l, h);
  	}

	/* ########################## */
	/* CONTENT MANAGEMENT METHODS */
	/* ########################## */

	/**
	 * Fills the Client's contents with the background character.
	 * @since 0.1.0
	 */
  	public void clear() {
    		char[] arr = {background};
    		String bg = new String(arr);
    		contents = new ArrayList<ArrayList<String>>();
    		for(int r = 0; r < height; r++) {
      			contents.add(new ArrayList<String>());
      			for(int c = 0; c < length; c++) {
        			contents.get(r).add(bg);
      			}
    		}
  	}

	/**
	 * Sets this Client's contents to the single-character Strings in the 2-D ArrayList parameter.
	 * May break if the geometry of the parameter is different than the Screen geometry.
	 * @param c A 2-D ArrayList of single-character Strings.
	 * @since 0.1.0
	 */
  	public void setContents(ArrayList<ArrayList<String>> c) {
    		contents = c;
  	}

	/** 
	 * Overrides individual characters in contents to the characters of the String. Newline characters separate lines. If a line of the String is too short for the Client dimensions, the extra characters in contents will not be overridden. If a line of the String is too long for the Client dimensions, the extra characters will be cut off.
	 * @param s String to override contents with
	 * @since 0.1.0
	 */
  	public void setContents(String s) {
    		String[] arr = s.split("\n");
    		for(int i = 0; i < arr.length; i++) {
      			setLine(i, arr[i]);
    		}
  	}

	/**
	 * Overrides a single line with the characters in a String. Similar propreties to {@link #setContents(String)}
	 * @param line Line number to override
	 * @param con String to override the line with
	 * @since 0.1.0
	 */
  	public void setLine(int line, String con) {
    		for(int c = 0; c < length && c < con.length(); c++) {
      			contents.get(line).set(c, con.substring(c, c+1));
    		}
  	}
 
  	/* ########################## */
  	/* SCREEN INTERACTION METHODS */
  	/* ########################## */

	/**
	 * 'Attatches' a Client to a Screen. 
	 * @param s Screen to attatch this Client to
	 * @since 0.1.0
	 */
  	public void attatch(Screen s) {
    		context = s;
    		context.addClient(this);
  	}

	/**
	 * Actions to preform when this Client is attatched to a Screen; Use this when extending the Client class.
	 * @since 0.1.0
	 */
  	public void init() {
    		return;
  	}

	/**
	 * Actions to preform before this Client is removed from a Screen; Use this when extending the Client class.
	 * If this method returns true, the Client will be removed. If it returns false, the Client will remain.
	 * @return A boolean signaling whether this Client should be removed from the Screen (true) or not (false).
	 * @since 0.1.0
	 */
  	public boolean destroy() {
    		context = null;
    		return true;
  	}
  
  	/* ####### */
  	/* SETTERS */
  	/* ####### */

	/**
	 * Sets Client geometry. Clears the Client to apply changes.
	 * @param l New length
	 * @param h New height
	 * @since 0.1.0
	 */
  	public void setGeom(int l, int h) {
    		length = l;
    		height = h;
    		clear();
  	}

	/**
	 * Sets the coordinates of this Client. This Client does not have to be attatched to a Screen to call this method, but a Screen usually is useful as it is the only thing that currently uses these values.
	 * @param xc New X-Coordinate of the top-left corner of the Client.
	 * @param yc New Y-Coordinate of the top-left corner of the Client.
	 * @since 0.1.0
	 */
  	public void setCoordinates(int xc, int yc) {
    		x = xc;
    		y = yc;
  	}

	/**
	 * Sets the background character
	 * @param c The new background character
	 * @since 0.1.0
	 */
  	public void setBackground(char c) {
    		background = c;
  	}

	/**
	 * Sets the background character to the 1st character in a String.
	 * @param c String containing the new background character.
	 * @since 0.1.0
	 */
  	public void setBackground(String c) {
      		background = c.charAt(0);
  	}

  	/* ####### */
  	/* GETTERS */
  	/* ####### */

	/**
	 * Get the X-Coordinate of the top-left corner of this Client.
	 * @return The X-Coordinate of the top-left corner of this Client.
	 * @since 0.1.0
	 */
  	public int getX() { return x; }

	/**
	 * Get the Y-Coordinate of the top-left corner of this Client.
	 * @return The Y-Coordinate of the top-left corner of this Client.
	 * @since 0.1.0
	 */
  	public int getY() { return y; }

	/**
	 * Get the total length of this Client.
	 * @return The total length of this Client.
	 * @since 0.1.0
	 */
  	public int getLength() { return length; }

	/**
	 * Get the total height of this Client.
	 * @return The total height of this Client.
	 * @since 0.1.0
	 */
  	public int getHeight() { return height; }

	/**
	 * Get the background character in char form.
	 * @return The background character in char form
	 * @since 0.1.0
	 */
  	public char getBackgroundChar() { return background; }

	/**
	 * Get the background character in String form.
	 * @return The background character in String form
	 * @since 0.1.0
	 */
  	public String getBackgroundStr() {
    		char[] c = {background};
    		return new String(c);
  	}

	/**
	 * Get the contents of this Client in the form of a 2-D ArrayList of single-character Strings. Changes made to this WILL reflect on the actual Client (i think).
	 * @return The contents of this Client in the form of a 2-D ArrayList of single-character Strings
	 * @since 0.1.0
	 */
  	public ArrayList<ArrayList<String>> getContents() { return contents; }

	/**
	 * Get the Screen that this Client is currently attatched to.
	 * @return The Screen that this Client is currently attatched to
	 * @since 0.1.0
	 */
  	public Screen getContext() { return context; }

  	/* #### */
  	/* MISC */
  	/* #### */
	
	/**
	 * Returns the contents of this Client in the form of a String
	 * @return The contents of this Client in the form of a String
	 * @since 0.1.0
	 */
  	public String toString() {
    		String ret = "";
    		for(int r = 0; r < contents.size(); r++) {
      			ArrayList<String> row = contents.get(r);
      			for(int c = 0; c < row.size(); c++) {
        			ret += row.get(c);
      			}
      			ret += "\n";
    		}
    		return ret;
  	}
}
