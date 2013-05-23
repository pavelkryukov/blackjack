/**
 * Copyright (c) 2013 Svyatoslav Kuzmich, Pavel Kryukov. All rights reserved.
 */
package base;

/**
 *  Playing card
 */
public class Card {
    public enum Rank  { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
                        EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE ;
    	public static Rank getRandom() {
    		return values()[(int) (Math.random() * values().length)];
    	}
    }

    public enum Suit  { SPADES, HEARTS, DIAMONDS, CLUBS;
    	public static Suit getRandom() {
    		return values()[(int) (Math.random() * values().length)];
    	}
    }

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    final private Rank rank;
    final private Suit suit;

    public Rank getRank() { return rank; }
    public Suit getSuit() { return suit; }
    
    public static Card GetRandomCard() {
    	return new Card(Rank.getRandom(), Suit.getRandom());
    }
}

