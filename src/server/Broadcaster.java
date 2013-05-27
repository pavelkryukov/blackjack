package server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import base.CasinoPublic;

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
			CasinoPublic casino = null;
			synchronized (gs.GetCasinoPublic()) {
				ByteArrayInputStream bais = new ByteArrayInputStream(gs.GetCasinoPublic().toByteArray());
				try {
					ObjectInputStream ois = new ObjectInputStream(bais);
					casino = new CasinoPublic((CasinoPublic)ois.readObject());
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (casino == null)
				continue;
			for (InetAddress address : addresses) {
				try {
					Socket clientSocket = new Socket(address, port);
					ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
					objectOutput.writeObject(casino);
					clientSocket.close();
				//	System.out.println("Card sended");
				} catch (IOException e) {
					// Connection failed, delete this address from set
			//		System.out.println("Card not sended");
		    //    	System.out.println("Invalid IP " + address.toString());
		    //    	e.printStackTrace();
				}
			}
		}
	}
}


