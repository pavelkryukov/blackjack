/**
 * Copyright (c) 2013 Svyatoslav Kuzmich, Pavel Kryukov. All rights reserved.
 */
package base;

/**
 *  Playing card
 */
public class Card {
    public enum Rank  { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
                        EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };

    public enum Suit  { SPADES, HEARTS, DIAMONDS, CLUBS };

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    private Rank rank;
    private Suit suit;

    public Rank getRank() { return rank; }
    public void setRank(Rank rank) { this.rank = rank; }

    public Suit getSuit() { return suit; }
    public void setSuit(Suit suit) { this.suit = suit; }
}

