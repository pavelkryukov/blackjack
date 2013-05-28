package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Broadcaster extends Thread {
	private final GameServer gs;
	public Broadcaster(GameServer dgs) {
		this.gs = dgs;
		this.addresses = new HashSet<InetAddress>();
	}
	private Set<InetAddress> addresses;
	public void AddAddress(InetAddress address) {
		addresses.add(address);
	}

	public void run() {
		while (true) {
			byte[] bytes;
			synchronized (gs.GetCasinoPublic()) {
				bytes = gs.GetCasinoPublic().toByteArray();
			}
			for (InetAddress address : addresses) {
				try {
					Socket clientSocket = new Socket(address, 7500);
					clientSocket.getOutputStream().write(bytes);
					clientSocket.close();
				} catch (IOException e) {
					// Connection failed, delete this address from set
		        	System.out.println("Invalid IP " + address.toString());
		        	addresses.remove(address);
				}
			}
		}
	}
}


