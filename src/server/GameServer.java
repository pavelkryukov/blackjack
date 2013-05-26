/**
 * Copyright (c) 2013 Svyatoslav Kuzmich, Pavel Kryukov. All rights reserved.
 */
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
	/**
	 *  Queue of incoming requests
	 */
	private Queue<Request> requests;
	
	/**
	 * Storage of IP addresses of request senders
	 */
	private Set<InetAddress> addresses;
	
	/**
	 * Casino state
	 */
	private Casino casino;
	
	/**
	 * Reading socket
	 */
	private ServerSocket serverSocket;

	public GameServer()
	{
		// Initialize game situation
		this.casino = new Casino();
		this.requests = new LinkedList<Request>();
		this.addresses = new HashSet<InetAddress>();

		// Initialize socket
		try {
			this.serverSocket = new ServerSocket(7000);
		} catch (IOException e) {
        	System.out.println("Error during server initialization");
			e.printStackTrace();
		}
	}

	private void Run()
	{
		while (true) {
			ReadCommands(); // Read commands from socket
			DoCommands();   // Perform actions on Casino
			SendCards();    // Send changed state to clients
		}
	}

	/**
	 * Sends game state through TCP/IP
	 */
	private void SendCards() {
		for (InetAddress address : addresses) {
			try {
				Socket clientSocket = new Socket(address, 7100);
				ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
				objectOutput.writeObject((CasinoPublic)casino);
				clientSocket.close();
			} catch (IOException e) {
				// Connection failed, delete this address from set
            	System.out.println("Invalid IP " + address.toString());	
            	addresses.remove(address);
			}
		}
	}

	/**
	 * Perform action on Casino
	 */
	private void DoCommands() {
		while (!requests.isEmpty()) {
			this.casino.ProcessRequest(requests.poll());
		}
	}
	
	/**
	 * Read commands from clients
	 */
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

	/**
	 * Entry point
	 * @param arguments ignored
	 */
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