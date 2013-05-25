package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Queue;

import base.Request;

public class GameServer {
	private Queue<Request> requests;
	private Casino casino;
	private ServerSocket serverSocket;

	public GameServer()
	{
		this.casino = new Casino();
		this.requests = new LinkedList<Request>();
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
		// TODO Auto-generated method stub
		
	}

	private void DoCommands() {
		while (!requests.isEmpty()) {
			this.casino.ProcessRequest(requests.poll());
		}
	}

	private void ReadCommands() {
		try {                
			Socket clientConn = serverSocket.accept();
			ObjectInputStream objectInput = new ObjectInputStream(clientConn.getInputStream());
            try {
                Object object = (Request) objectInput.readObject();
                Request tmp = (Request) object;
                requests.add(tmp);
            } catch (ClassNotFoundException e) {
            	System.out.println("Incorrect request is received");
                e.printStackTrace();
            }
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