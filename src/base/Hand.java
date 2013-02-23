/**
 * Copyright (c) 2013 Svyatoslav Kuzmich. All rights reserved.
 */
package base;


import java.util.ArrayList;


/**
 *  Hand is a collection of cards.
 */
public class Hand {
    
    /**
     * Create empty Hand
     */
    public Hand() {
        cards = new ArrayList<Card>();
    }

    /**
     * Create hand with two initial cards. Usual case.
     */
    public Hand(Card card1, Card card2) {
        cards = new ArrayList<Card>();
        cards.add(card1);
        cards.add(card2);
    }
    
    /**
     * Calculate score as sum of score of each card in the hand. 
     * 
     * @return score of the hand
     */
    public int Score() {
        int number_of_aces = 0;
        int score = 0;
        
        assert cards.size() >= 2;
        assert cards.size() <= 11; /* Bound case: ten TWOs plus TWO or ACE */
        
        /* Counting score of hand without aces. */
        for (Card card: cards) {
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
            case TEN:    score += 10; break;
            
            /* Face cards are counted as ten points. */
            case JACK:    score += 10; break;
            case QUEEN:    score += 10; break;
            case KING:  score += 10; break;
            
            /* Aces will be counted later, depending on score without aces. */
            case ACE:   number_of_aces += 1; break;
            
            default: assert false;
            }
        }
        
        /* Counting score of aces */
        for (int i = 0; i < number_of_aces; i += 1) {
            /* Aces can be counted as 1 or 11. */
            if ((score + 11) > 21) {
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
    public void AddCard(Card card) {
        cards.add(card);
    }
    
    /**
     * Clear the hand
     */
    public void Clear() {
        cards.clear();
    }
    
    private ArrayList<Card> cards;
}
