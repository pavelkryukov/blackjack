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
        hand.AddCard(new Card(TWO, Suit.SPADES));
        hand.AddCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.Score(), 4);
        hand.AddCard(new Card(TWO, Suit.DIAMONDS));
        assertEquals(hand.Score(), 6);
        hand.AddCard(new Card(TWO, Suit.CLUBS));
        assertEquals(hand.Score(), 8);
        hand.AddCard(new Card(TWO, Suit.HEARTS));
        assertEquals(hand.Score(), 10);
        hand.AddCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.Score(), 12);
        hand.AddCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.Score(), 14);
        hand.AddCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.Score(), 16);
        hand.AddCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.Score(), 18);
        hand.AddCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.Score(), 20);
        hand.AddCard(new Card(TWO, Suit.SPADES));
        assertEquals(hand.Score(), 22);
    }
    
    @Test
    public void test2() {
        Hand hand = new Hand();
        hand.AddCard(new Card(THREE, Suit.SPADES));
        hand.AddCard(new Card(FOUR, Suit.SPADES));
        assertEquals(hand.Score(), 7);
        hand.AddCard(new Card(FIVE, Suit.SPADES));
        assertEquals(hand.Score(), 12);
        hand.AddCard(new Card(SIX, Suit.SPADES));
        assertEquals(hand.Score(), 18);
        hand.AddCard(new Card(SEVEN, Suit.SPADES));
        assertEquals(hand.Score(), 25);
    }
    
    @Test
    public void test3() {
        Hand hand = new Hand();
        hand.AddCard(new Card(EIGHT, Suit.SPADES));
        hand.AddCard(new Card(NINE, Suit.SPADES));
        assertEquals(hand.Score(), 17);
        hand.AddCard(new Card(TEN, Suit.SPADES));
        assertEquals(hand.Score(), 27);
    }
    
    @Test
    public void test4() {
        Hand hand = new Hand();
        hand.AddCard(new Card(QUEEN, Suit.DIAMONDS));
        hand.AddCard(new Card(KING, Suit.CLUBS));
        assertEquals(hand.Score(), 20);
        hand.AddCard(new Card(JACK, Suit.HEARTS));
        assertEquals(hand.Score(), 30);
    }
    
    @Test
    public void test5() {
        Hand hand = new Hand();
        hand.AddCard(new Card(QUEEN, Suit.DIAMONDS));
        hand.AddCard(new Card(KING, Suit.CLUBS));
        assertEquals(hand.Score(), 20);
        hand.AddCard(new Card(ACE, Suit.HEARTS));
        assertEquals(hand.Score(), 21);
    }
    
    @Test
    public void test6() {
        Hand hand = new Hand( new Card(QUEEN, Suit.DIAMONDS),
                              new Card(ACE, Suit.CLUBS));
        assertEquals(hand.Score(), 21);
    }
    
    @Test
    public void test7() {
        Hand hand = new Hand( new Card(ACE, Suit.DIAMONDS),
                              new Card(ACE, Suit.CLUBS));
        assertEquals(hand.Score(), 12);
    }
    
    @Test
    public void test8() {
        Hand hand = new Hand( new Card(ACE, Suit.DIAMONDS),
                              new Card(ACE, Suit.CLUBS));
        assertEquals(hand.Score(), 12);
        hand.Clear();
        hand.AddCard(new Card(ACE, Suit.HEARTS));
        hand.AddCard(new Card(ACE, Suit.SPADES));
        assertEquals(hand.Score(), 12);
        hand.AddCard(new Card(ACE, Suit.SPADES));
        hand.AddCard(new Card(ACE, Suit.SPADES));
        hand.AddCard(new Card(ACE, Suit.SPADES));
        hand.AddCard(new Card(ACE, Suit.SPADES));
        hand.AddCard(new Card(ACE, Suit.SPADES));
        assertEquals(hand.Score(), 17);
    }
}
