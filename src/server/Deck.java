/**
 * Copyright (c) 2013 Svyatoslav Kuzmich, Pavel Kryukov. All rights reserved.
 */
package server;

import java.util.LinkedList;
import java.util.Collections;

import base.Card;

/**
 * Deck of cards
 */
public final class Deck {
    /**
     * Linked list of cards
     */
    private final LinkedList<Card> deck;

    /**
     * Shows how many standard 52-carded decks are used
     */
    private static final int decksNum = 4;

    /**
     * Fill deck with full standard 52-carded decks
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

    /**
     * Constructor
     */
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
