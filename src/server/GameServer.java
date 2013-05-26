/**
 * Copyright (c) 2013 Svyatoslav Kuzmich, Pavel Kryukov. All rights reserved.
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import base.CasinoPublic;
import base.Request;

public class GameServer {
	/**
	 *  Queue of incoming requests
	 */
	private Queue<Request> requests;

	private Broadcaster broadcaster;
	
	/**
	 * Casino state
	 */
	private Casino casino;
	private CasinoPublic casinoPublic;
	public CasinoPublic GetCasinoPublic() {
		return casinoPublic;
	}
	
	/**
	 * Reading socket
	 */
	private ServerSocket serverSocket;

	public GameServer()
	{
		// Initialize game situation
		this.casino = new Casino();
		this.casinoPublic = new CasinoPublic(casino);
		this.requests = new LinkedList<Request>();
		this.broadcaster = new Broadcaster(this);
		broadcaster.start();

		// Initialize socket
		try {
			this.serverSocket = new ServerSocket(7200);
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
			broadcaster.AddAddress(clientConn.getInetAddress());
			ObjectInputStream objectInput = new ObjectInputStream(clientConn.getInputStream());
            try {
                Object object = (Request) objectInput.readObject();
                Request tmp = (Request) object;
                requests.add(tmp);
            } catch (ClassNotFoundException e) {
            	System.out.println("Incorrect request is received");
                e.printStackTrace();
            }
        	System.out.println("Request from " + clientConn.getInetAddress() + " received");
            clientConn.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.casinoPublic = new CasinoPublic(casino);
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