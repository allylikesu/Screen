import static java.lang.System.*;

import me.allison.screen.screen.Screen;


public class Tester {
	public static void main(String[] args) {
		Screen s1 = new Screen();
		//Screen s1 = new Screen(10,10);
		s1.populateWithBorder();
		//out.println(s1.toString().substring(0,1));
		out.println(s1);
	}
}
