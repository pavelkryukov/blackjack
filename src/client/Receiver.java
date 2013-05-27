package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import base.CasinoPublic;

public class Receiver extends Thread {
	private static ServerSocket server;
	public Receiver() {
    	try {
			server = new ServerSocket(7500);
		} catch (IOException e) {
			System.out.println("Error during receiver initialization");
			e.printStackTrace();
		}
	}
	public void run() {
		while (true) {
			receiveCards();
		}
	}
	private void receiveCards() {
		try {
			Socket socket = server.accept();
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            try {
                Object object = (CasinoPublic) in.readObject();
                CasinoPublic tmp = (CasinoPublic) object;
                BJGame.SetCasino(tmp);
            } catch (ClassNotFoundException e) {
            	System.out.println("Incorrect request is received");
            }
            socket.close();
		} catch (Exception e) {
            
		}
	}
}
