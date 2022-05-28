
package screen.core;

import screen.clients.Client;
import java.util.ArrayList;

/**
 * Screen: Terminal-based window manager. Windows, in the form of clients, can be instantiated and extended from the {@link screen.clients.Client} class.
 * @author allison
 * @version 0.1.0
 */
public class Screen {
	/**
	 * Stores total (including border) width of this screen, in characters.
	 * Gotten with {@link #getLength()}
	 * @since 0.1.0
	 */
	private int length;
  
	/**
	 * Stores total height (including border) length of this screen, in characters.
	 * Gotten with {@link #getHeight()}
	 * @since 0.1.0
	 */
	private int height;
  
	/**
	 * The background character, used in {@link #clear()}.
	 * Gotten with {@link #setBackgroundChar()}, {@link #setBackgroundStr()}
	 * Set with {@link #setBg()}
	 * @since 0.1.0
	 */
	private char background;

	/**
	 * Where the attatched Client objects are stored. Clients at the beginning of the array are drawn first, meaning that they will be under other Clients.
	 * A shallow copy can be gotten with {@link #getClients()}.
	 * Clients are added and removed with {@link #addClient()} and {@link #removeCient()}.
	 * @since 0.1.0
	 */
	private ArrayList<Client> clients;
  
	/**
	 * 2-D array of single character Strings that represent the screen. 
	 * @since 0.1.0
	 */
	private String[][] contents;

	/**
	 * Creates a new Screen. Calls {@link screen.clients.Client#init()}.
	 * @param l Screen length
	 * @param h Screen height
	 * @since 0.1.0
	 */
	public Screen(int l, int h) {
		length = l;
		height = h;
		clients = new ArrayList<Client>();
		contents = new String[h][l];
		background = ' ';
		clear();
	}

	/* ######################### */
	/* CHARACTER SETTING METHODS */
	/* ######################### */

	/**
	 * Goes through all attatched Clients and draws them on the Screen. Calls {@link #printScreen()}.
	 * Clients are drawn in the order they appear in they were attatched, meaning the last attatched Client will appear on top.
	 * @since 0.1.0
	 */
	public void refresh() {
		for(int i = 0; i < clients.size(); i++) { // for each client
  		Client client = clients.get(i);
      			for(int screenR = client.getY(), clientR = 0;
          			clientR < client.getContents().size() && screenR < height;
          			screenR++, clientR++) { // while there are more client rows to draw && more screen rows to draw to
        			for(int screenC = client.getX(), clientC = 0;
            				clientC < client.getContents().get(clientR).size() && screenC < length;
            				screenC++, clientC++) { // while there are more client columns to draw && more screen columns to draw to
          				contents[screenR][screenC] = client.getContents().get(clientR).get(clientC);
        			}
      			}
    		}
    		printScreen();
  	}

	/**
	 * Prints this Screen's contents to stdout.
	 * @since 0.1.0
	 */
  	public void printScreen() {
    		String buffer = "";
    		for(int r = 0; r < contents.length; r++) {
      			for(int c = 0; c < contents[r].length; c++) {
        			buffer += contents[r][c];
      			}
      			buffer += "\n";
    		}
    		System.out.println(buffer);
  	}

  	/* ###################### */
  	/* SCREEN UTILITY METHODS */
  	/* ###################### */

	/**
	 * Draws borders on this Screen. Does not effect Screen geometry, so Clients posisioned on the Screen edges will cover these borders.
	 * @since 0.1.0
	 */
  	public void drawBorders() {
    		// top & bottom lines
    		for(int r = 0; r < height; r += height - 1) {
      			for(int c = 0; c < length; c++) {
        			contents[r][c] = "#"; 
      			}
    		}
    		// left & right
    		for(int r = 1; r < height - 1; r++) {
      			for(int c = 0; c < length; c += length - 1) {
        			contents[r][c] = "#";
      			}
    		}
  	}

	/**
	 * Resets all screen characters to the background character.
	 * @since 0.1.0
	 */
  	public void clear() {
    		char[] bgc = {background}; String bg = new String(bgc);
    		for(int r = 0; r < contents.length; r++) {
      			for(int c = 0; c < contents[r].length; c++) {
        			contents[r][c] = bg;
      			}
    		}
    		drawBorders();
  	}

  	/* ######################### */
  	/* CLIENT MANAGEMENT METHODS */
  	/* ######################### */

	/**
	 * Adds a Client to this Screen. Calls {@link screen.clients.Client#init()}. 
	 * @param c The Client to add to this Screen
	 * @since 0.1.0
	 */
  	public void addClient(Client c) {
    		clients.add(c);
    		c.init();
  	}

	/**
	 * Removes a Client from this Screen, only if {@link screen.clients.Client#destroy()} returns true.
	 * @param c The Client to be removed
	 * @since 0.1.0
	 */
  	public void removeClient(Client c) {
    		if(c.destroy()) {
      			clients.remove(c);  
    		}
  	}

  	/* ####### */
  	/* SETTERS */
  	/* ####### */

	/**
	 * Sets the background character. Calling {@link #clear()} then {@link #refresh()} is required for the changes to fully take effect.
	 * @param c The background character to be set
	 * @since 0.1.0
	 */
  	public void setBg(char c) {
    		background = c;
  	}

  	/* ####### */
  	/* GETTERS */
  	/* ####### */

	/**
	 * Returns the total length of this Screen.
	 * @return The total length of this Screen
	 * @since 0.1.0
	 */
  	public int getLength() { return length; }

	/**
	 * Returns the total height of this Screen.
	 * @return The total height of this Screen
	 * @since 0.1.0
	 */
  	public int getHeight() { return height; }

	/**
	 * Returns this Screen's background character in the form of a char.
	 * @return The background character in the form of a char.
	 * @since 0.1.0
	 */
  	public char getBackgroundChar() { return background; }

	/**
	 * Returns this Screen's background character in the form of a String.
	 * @return The background character in the form of a String.
	 * @since 0.1.0
	 */
  	public String getBackgroundStr() {
    		char[] arr = {background};
    		return new String(arr);
  	}

	/**
	 * Returns an ArrayList of this Screen's Clients.
	 * @return A shallow copy of this Screen's ArrayList of Clients.
	 * @since 0.1.0
	 */
  	public ArrayList<Client> getClients() {
    		return (ArrayList<Client>)clients.clone();
  	}

  	/* #### */
  	/* MISC */
  	/* #### */

	/**
	 * Similar to {@link #printScreen()}, except the contents are returned instead of printed to stdout.
	 * @return The contents of this Screen. 
	 * @since 0.1.0
	 */
  	public String toString() {
    		String ret = "";
    		for(int r = 0; r < height; r++) {
      			for(int c = 0; c < length; c++) {
        			ret += contents[r][c];
      			}
      			ret += "\n";
    		}
    		return ret;
  	}
}
