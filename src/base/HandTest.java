/**
 * Copyright (c) 2013 Svyatoslav Kuzmich. All rights reserved.
 */

package base;

import static base.Card.Rank.*;
import static org.junit.Assert.*;

import org.junit.Test;

import base.Card.Suit;


public class HandTest {

    @Test
    public void test1() {
        Hand hand = new Hand();
        hand.addCard(new Card(TWO, Suit.SPADES));
        hand.addCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.score(), 4);
        hand.addCard(new Card(TWO, Suit.DIAMONDS));
        assertEquals(hand.score(), 6);
        hand.addCard(new Card(TWO, Suit.CLUBS));
        assertEquals(hand.score(), 8);
        hand.addCard(new Card(TWO, Suit.HEARTS));
        assertEquals(hand.score(), 10);
        hand.addCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.score(), 12);
        hand.addCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.score(), 14);
        hand.addCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.score(), 16);
        hand.addCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.score(), 18);
        hand.addCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.score(), 20);
        hand.addCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.score(), 22);
    }
    
    @Test
    public void test2() {
        Hand hand = new Hand();
        hand.addCard(new Card(THREE, Suit.SPADES));
        hand.addCard(new Card(FOUR, Suit.SPADES));
        assertEquals(hand.score(), 7);
        hand.addCard(new Card(FIVE, Suit.SPADES));
        assertEquals(hand.score(), 12);
        hand.addCard(new Card(SIX, Suit.SPADES));
        assertEquals(hand.score(), 18);
        hand.addCard(new Card(SEVEN, Suit.SPADES));
        assertEquals(hand.score(), 25);
    }
    
    @Test
    public void test3() {
        Hand hand = new Hand();
        hand.addCard(new Card(EIGHT, Suit.SPADES));
        hand.addCard(new Card(NINE, Suit.SPADES));
        assertEquals(hand.score(), 17);
        hand.addCard(new Card(TEN, Suit.SPADES));
        assertEquals(hand.score(), 27);
    }
    
    @Test
    public void test4() {
        Hand hand = new Hand();
        hand.addCard(new Card(QUEEN, Suit.DIAMONDS));
        hand.addCard(new Card(KING, Suit.CLUBS));
        assertEquals(hand.score(), 20);
        hand.addCard(new Card(JACK, Suit.HEARTS));
        assertEquals(hand.score(), 30);
    }
    
    @Test
    public void test5() {
        Hand hand = new Hand();
        hand.addCard(new Card(QUEEN, Suit.DIAMONDS));
        hand.addCard(new Card(KING, Suit.CLUBS));
        assertEquals(hand.score(), 20);
        hand.addCard(new Card(ACE, Suit.HEARTS));
        assertEquals(hand.score(), 21);
    }
    
    @Test
    public void test6() {
        Hand hand = new Hand( new Card(QUEEN, Suit.DIAMONDS),
                              new Card(ACE, Suit.CLUBS));
        assertEquals(hand.score(), 21);
    }
    
    @Test
    public void test7() {
        Hand hand = new Hand( new Card(ACE, Suit.DIAMONDS),
                              new Card(ACE, Suit.CLUBS));
        assertEquals(hand.score(), 12);
    }
    
    @Test
    public void test8() {
        Hand hand = new Hand( new Card(ACE, Suit.DIAMONDS),
                              new Card(ACE, Suit.CLUBS));
        assertEquals(hand.score(), 12);
        hand.clear();
        hand.addCard(new Card(ACE, Suit.HEARTS));
        hand.addCard(new Card(ACE, Suit.SPADES));
        assertEquals(hand.score(), 12);
        hand.addCard(new Card(ACE, Suit.SPADES));
        hand.addCard(new Card(ACE, Suit.SPADES));
        hand.addCard(new Card(ACE, Suit.SPADES));
        hand.addCard(new Card(ACE, Suit.SPADES));
        hand.addCard(new Card(ACE, Suit.SPADES));
        assertEquals(hand.score(), 17);
    }
}
