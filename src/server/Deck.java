package server;

import java.util.LinkedList;
import java.util.Collections;

import base.Card;

public class Deck {
	private LinkedList<Card> deck;
	private static final int decksNum = 4;

	private void FillDeck() {
		for (int i = 0; i < decksNum; ++i) {
			for (Card.Rank rank : Card.Rank.values()) {
				for (Card.Suit suit : Card.Suit.values()) {
					deck.add(new Card(rank, suit));
				}
			}
		}
		Collections.shuffle(this.deck);
	}

	public Deck() {
		this.deck = new LinkedList<Card>();
		FillDeck();
	}

	public Card GetCard() {
		if (deck.isEmpty()) {
			FillDeck();
		}
		return deck.pop();		
	}
}
