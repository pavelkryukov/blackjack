package server;

import java.util.LinkedList;
import java.util.Collections;

import base.Card;

/**
 * Deck of cards
 */
public class Deck {
	private LinkedList<Card> deck;
	
	/**
	 * Shows how many 52-carded decks are used
	 */
	private static final int decksNum = 4;

	/**
	 * Fill deck with full 52-carded decks
	 */
	private void FillDeck() {
		for (int i = 0; i < decksNum; ++i) {
			for (Card.Rank rank : Card.Rank.values()) {
				for (Card.Suit suit : Card.Suit.values()) {
					deck.add(new Card(rank, suit));
				}
			}
		}
		Collections.shuffle(this.deck); // Shuffle all decks!
	}

	public Deck() {
		this.deck = new LinkedList<Card>();
		FillDeck();
	}

	/**
	 * Give top card
	 * @return top card in deck
	 */
	public Card GetCard() {
		if (deck.isEmpty()) { // If deck is empty, get another one
			FillDeck();
		}
		return deck.pop();		
	}
}
