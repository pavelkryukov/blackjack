/**
 * Copyright (c) 2013 Svyatoslav Kuzmich. All rights reserved.
 */
package base;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *  Hand is a collection of cards.
 */
public class Hand implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * Main number of the game
     */
    private static final int BLACKJACK = 21;
    
    /**
     * Create empty Hand
     */
    public Hand() {
        cards = new ArrayList<Card>();
    }

    /**
     * Copy constructor
     * @param src source
     */
    public Hand(Hand src) {
        cards = new ArrayList<Card>(src.cards);
    }

    /**
     * Calculate score as sum of score of each card in the hand. 
     * 
     * @return score of the hand
     */
    public int score() {
        int number_of_aces = 0;
        int score = 0;

        assert cards.size() >= 2;
        assert cards.size() <= 11; /* Bound case: ten TWOs plus TWO or ACE */

        /* Counting score of hand without aces. */
        for (final Card card: cards) {
            Card.Rank rank = card.getRank();

            switch (rank) {
            /* Cards are counted as the numeric value shown on the card. */
            case TWO:   score += 2; break;
            case THREE: score += 3; break;
            case FOUR:  score += 4; break;
            case FIVE:  score += 5; break;
            case SIX:   score += 6; break;
            case SEVEN: score += 7; break;
            case EIGHT: score += 8; break;
            case NINE:  score += 9; break;

            /* Face cards are counted as ten points. */
            case TEN:
            case JACK:
            case QUEEN:
            case KING:  score += 10; break;

            /* Aces will be counted later, depending on score without aces. */
            case ACE:   number_of_aces += 1; break;

            default: assert false;
            }
        }
        
        /* Counting score of aces */
        for (int i = 0; i < number_of_aces; ++i) {
            /* Aces can be counted as 1 or 11. */
            if (score > 10) { // If add 11, there will be overload
                score += 1;
            } else {
                score += 11;
            }
        }

        /* asserting the bound situation: 
         * set of cards with score 20 plus one card with score 10  */
        assert score <= 30;

        return score;
    }
    
    /**
     *  Add card to hand
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Clear the hand
     */
    public void clear() {
        cards.clear();
    }
    
    /**
     * Check over
     * @return true, if greater than 21
     */
    public Boolean hasLost() {
        return this.score() > BLACKJACK;
    }

    /**
     * Check score
     * @return true, if equal than 21
     */
    public Boolean hasWin() {
        return this.score() == BLACKJACK;
    }

    public final ArrayList<Card> cards;
}
