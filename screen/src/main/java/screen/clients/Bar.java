// Simple bar implementation of Client
package screen.clients;
import java.util.ArrayList;

public class Bar extends Client {
	public Bar() {
		super(1,1);
		setBackground('#');
	}
	public void init() {
		setCoordinates(0,0);
		setGeom(context.getLength(), 2);
		setContents(infoStr());
	}
	public String infoStr() {
		ArrayList<Client> clients = context.getClients();
		return "Clients: " + clients.size() + " | " + clients.get(clients.size() - 1).getClass().getSimpleName();
	}
}
