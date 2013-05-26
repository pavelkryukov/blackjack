package base;

import java.io.Serializable;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	public Hand hand;
	private Boolean isInGame;
	private Boolean isReady;
	private String name;
	private int victoryNum;

	public class InvalidRequestException extends Exception {
		private static final long serialVersionUID = 1L;	
	}

	public Player( String name) {
		this.hand = new Hand();
		this.isInGame = false;
		this.isReady = false;
		this.victoryNum = 0;
		this.setName(name);
	}
	
	public Player( Player src) {
		this.hand = new Hand(src.hand);
		this.isInGame = src.isInGame;
		this.isReady = src.isReady;
		this.victoryNum = src.victoryNum;
		this.name = src.name;
	}
	
	public void LoseGame() {
		isInGame = false;
		isReady  = false;
	}
	
	public void ProcessRequest(Request req) throws InvalidRequestException{
		switch (req.GetType()) {
		case RESIGN:
			this.LoseGame();
			break;
		case START:
			isReady = true;
			break;
		case REFRESH:
			break;
		default:
			throw new InvalidRequestException();
		}
	}

	public Boolean IsInGame() { return isInGame; }
	public Boolean IsReady() { return isReady; }
	public void SetInGame() {
		isInGame = true;
		isReady = false;
	}

	public void GetCard(Card card) {
		hand.addCard(card);
	}
	
	public void DropCards() {
		hand.clear();
	}
	
	public Boolean hasLost() {
		return hand.hasLost();
	}

	public Boolean hasWin() {
		return hand.hasWin();
	}

	public int GetScore() {
		return hand.score();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void IncrementVictories() {
		victoryNum++;
	}
	
	public int GetVictoriesNum() {
		return victoryNum;
	}
}