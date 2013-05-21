package server;

import java.util.LinkedList;
import java.util.Queue;
import base.Request;

public class GameServer {
	private Queue<Request> requests;
	private Casino casino;
	public GameServer()
	{
		this.casino = new Casino();
		this.requests = new LinkedList<Request>();
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
		// TODO Auto-generated method stub
		
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