package screen.clients;

import screen.core.Screen;
import java.util.ArrayList;

/**
 * A Client for a Screen object. Meant to be extended and customized.
 */
public class Client {
  /*
  int x: Stores x-value of Client on the Screen.
  int y: Stores y-value of Client on the Screen.
  Coodinates are relative to the top-left corner; A y-value of 0 puts the Client at the very top of the Screen.
  Coordinates are (0,0) by default.
  Set by void setCoordinates(int x, int y).
  Get them using int getX() and int getY().
  */
  private int x, y;

  /*
  int length: Stores total length of the Client, in characters.
  int height: Stores total height of the Client, in characters.
  The smallest a Client can be is 1x1, representing one character on a Screen.
  Set by the constructor and void setGeom(int l, int h).
  Get them using int getLength() and int getHeight().
  */
  private int length, height;

  /*
  int minLength, int minHeight: unused so far, may deprecate
  */
  public int minLength;
  public int minHeight;

  /*
  char background: Character that void clear() sets the client contents to.
  Set by void setBackground(String c) and void setBackground(char c).
  Get it using String getBackgroundStr() and char getBackgroundChar().
  */
  private char background;

  /*
  ArrayList<ArrayList<String>> contents: 2-D ArrayList of single-character Strings representing the characters of the Client.
  Initialized by the constructor
  Set by void clear(), void setContents(String c), and void setContents(ArrayList<ArrayList<String>> c).
  Get it using ArrayList<ArrayList<String>> getContents().
  */
  private ArrayList<ArrayList<String>> contents;

  /*
  Screen context: The Screen object that this Client is attatched to.
  set by void attatch().
  */
  public Screen context;

  /*
  Client(int l, int h): Constructor takes and sets length and height, and sets the background character. setBackground() must be called before setGeom(), as setGeom() calls clear().
  */
  public Client(int l, int h) {
    // setContents(new ArrayList<ArrayList<String>>());
    setBackground('X');
    setGeom(l, h);
    // length = l;
    // height = h;
    // contents = new ArrayList<ArrayList<String>>();
    // background = 'X';
    // clear();
  }

  /*
  void clear(): Re-initializes contents, fills it with the background character appropriately.
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
  
  /* ########################## */
  /* SCREEN INTERACTION METHODS */
  /* ########################## */
  /*
  void attatch(Screen s): Used to attatch a Client to a Screen. Sets the Screen taken as a parameter to the context variable, and then calls Screen.addClient(Client) to add this Client to the Screen.
  */
  public void attatch(Screen s) {
    context = s;
    context.addClient(this);
  }

  /*
  void init(): Called by Screen.addClient(Client). Empty in the base Client class. 
  When making custom Clients (extending the Client class), this method can be overridden to contain custom code to set up the Client in a custom way when it is attatched to a Screen.
  */
  public void init() {
    return;
  }

  /*
  boolean destroy(): Called by Screen.removeClient(Client) before removing the Client from the Screen. If a value of true is returned, the process of removing the Client will finish. If a value of false is returned, the Client will not be removed. Simply returns true in the base Client class.
  When making custom Clients (extending the Client class), this method can be overridden to contain custom code to execute when removing a Client. For example, this method could create an "are you sure you want to quit without saving?" window, and execute the appropriate code based on whether "save", "discard", or "cancel" are chosen.
  */
  public boolean destroy() {
    return true;
  }
  
  /* ####### */
  /* SETTERS */
  /* ####### */
  /*
  void setContents(ArrayList<ArrayList<String>> c): Sets contents to the 2-D ArrayList of Strings taken by the parameter.
  void setContents(String s): Overrides individual characters in contents to the characters of the String. Newline characters separate lines. If a line of the String is too short for the Client dimensions, the extra characters in contents will not be overridden. If a line of the String is too long for the Client dimensions, the extra characters will be cut off.
  void setLine(int line, String con): overrides the single line of index 'line' with the characters in 'con'. Used by setContents(String s).
  */
  public void setContents(ArrayList<ArrayList<String>> c) {
    contents = c;
  }
  public void setContents(String s) {
    String[] arr = s.split("\n");
    for(int i = 0; i < arr.length; i++) {
      setLine(i, arr[i]);
    }
  }
  public void setLine(int line, String con) {
    for(int c = 0; c < length && c < con.length(); c++) {
      contents.get(line).set(c, con.substring(c, c+1));
    }
  }

  /*
  void setGeom(int l, int h): Sets the length and height variables, and calls clear().
  */
  public void setGeom(int l, int h) {
    length = l;
    height = h;
    clear();
  }

  /*
  void setCoordinates(int sx, int yx): Sets the x and y variables.
  */
  public void setCoordinates(int xc, int yc) {
    x = xc;
    y = yc;
  }

  /*
  void setBackground(char c): Sets the background character.
  void setBackground(String c): Sets the background character to the 1st character (0th index) of c.
  */
  public void setBackground(char c) {
    background = c;
  }
  public void setBackground(String c) {
      background = c.charAt(0);
  }

  /* ####### */
  /* GETTERS */
  /* ####### */
  public int getX() { return x; }
  public int getY() { return y; }

  public int getLength() { return length; }
  public int getHeight() { return height; }

  public char getBackgroundChar() { return background; }
  public String getBackgroundStr() {
    char[] c = {background};
    return new String(c);
  }

  public ArrayList<ArrayList<String>> getContents() { return contents; }

  /* #### */
  /* MISC */
  /* #### */
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
