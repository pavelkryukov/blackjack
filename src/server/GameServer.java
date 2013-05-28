/**
 * Copyright (c) 2013 Svyatoslav Kuzmich, Pavel Kryukov. All rights reserved.
 */
package server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import base.CasinoPublic;
import base.Request;

public class GameServer {
	private Broadcaster broadcaster;
	
	/**
	 * Casino state
	 */
	private Casino casino;
	private ByteArrayOutputStream casinoO;
	public ByteArrayOutputStream GetCasinoPublic() {
		return casinoO;
	}
	
	/**
	 * Reading socket
	 */
	private ServerSocket serverSocket;

	public GameServer()
	{
		// Initialize game situation
		this.casino = new Casino();
		this.casinoO = new ByteArrayOutputStream();
		this.UpdateCasinoPublic();
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
			Request request = ReadRequest(); // Read commands from socket
			if (request != null)
				this.casino.ProcessRequest(request);
			UpdateCasinoPublic();
		}
	}
	
	private void UpdateCasinoPublic() {
		synchronized (this.casinoO) {
			this.casinoO.reset();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(this.casinoO);
				oos.writeObject(new CasinoPublic(casino));
				oos.close();
			} catch (IOException e) {
				System.out.println("Error during copy of casino to byte array");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Read commands from clients
	 */
	private Request ReadRequest() {
		try {                
			Socket clientConn = serverSocket.accept();
			broadcaster.AddAddress(clientConn.getInetAddress());
			System.out.println("Request from..." + clientConn.getInetAddress().toString());
			ObjectInputStream objectInput = new ObjectInputStream(clientConn.getInputStream());
            try {
                Object object = (Request) objectInput.readObject();
                Request tmp = (Request) object;
                return tmp;
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
		return null;
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