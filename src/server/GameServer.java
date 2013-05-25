package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import base.CasinoPublic;
import base.Request;

public class GameServer {
	private Queue<Request> requests;
	private Set<InetAddress> addresses;
	private Casino casino;
	private ServerSocket serverSocket;

	public GameServer()
	{
		this.casino = new Casino();
		this.requests = new LinkedList<Request>();
		this.addresses = new HashSet<InetAddress>();
		try {
			this.serverSocket = new ServerSocket(7000);
		} catch (IOException e) {
        	System.out.println("Error during server initialization");
			e.printStackTrace();
		}
		// Get my IP address and print
		// Initialize write sockets
		// Initialize game situation
	}

	private void Run()
	{
		while (true) {
			ReadCommands();
			DoCommands();
			SendCards();
		}
		
	}

	private void SendCards() {
		for (InetAddress address : addresses) {
			try {
				Socket clientSocket = new Socket(address, 7100);
				ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
				objectOutput.writeObject((CasinoPublic)casino);
				clientSocket.close();
			} catch (IOException e) {
            	System.out.println("Invalid IP " + address.toString());	
            	addresses.remove(address);
			}
			
		}
	}

	private void DoCommands() {
		while (!requests.isEmpty()) {
			this.casino.ProcessRequest(requests.poll());
		}
	}

	private void ReadCommands() {
		try {                
			Socket clientConn = serverSocket.accept();
			addresses.add(clientConn.getInetAddress());
			ObjectInputStream objectInput = new ObjectInputStream(clientConn.getInputStream());
            try {
                Object object = (Request) objectInput.readObject();
                Request tmp = (Request) object;
                requests.add(tmp);
            } catch (ClassNotFoundException e) {
            	System.out.println("Incorrect request is received");
                e.printStackTrace();
            }
            clientConn.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] arguments) {
        try {
        	System.out.println("BlackJack Server v0.1");
        	GameServer game = new GameServer();
        	game.Run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}