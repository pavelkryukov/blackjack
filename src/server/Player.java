package server;

import base.Hand;
import base.Request;

public class Player {
	public class InvalidRequestException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
	}
	
	private Hand hand;
	private Boolean isInGame;
	private Boolean isReady;
	
	public Player() {
		this.hand = new Hand();
		this.isInGame = false;
		this.isReady = false;
	}
	
	public void ProcessRequest(Request req) throws InvalidRequestException{
		switch (req.GetType()) {
		case CONNECT:
		case DISCONNECT:
			throw new InvalidRequestException();
		case RESIGN:
			isInGame = false;
			break;
		case START:
			isReady = true;
			break;
		}
	}
	
	public Boolean IsInGame() { return isInGame; }
	public Boolean IsReady() { return isReady; }
	public void SetInGame() {
		isInGame = true;
		isReady = false;
	}
}
