package base;


public class Deck {
	public Deck() {
		
	}
	public Card GetCard() {
		return new Card(Card.Rank.getRandom(), Card.Suit.getRandom());
	}
}
