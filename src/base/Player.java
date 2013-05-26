package base;

public class Player {
	public Hand hand;
	private Boolean isInGame;
	private Boolean isReady;
	private String name;

	public class InvalidRequestException extends Exception {
		private static final long serialVersionUID = 1L;	
	}

	public Player( String name) {
		this.hand = new Hand();
		this.isInGame = false;
		this.isReady = false;
		this.setName(name);
	}

	private void LoseGame() {
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
	
	public void UpdateIfLost() {
		if (hand.hasLost())
			LoseGame();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}