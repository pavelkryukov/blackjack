/**
 * Copyright (c) 2013 Svyatoslav Kuzmich. All rights reserved.
 */

package base;

import static org.junit.Assert.*;

import org.junit.Test;

import base.Card.Rank;
import base.Card.Suit;


public class HandTest {

    @Test
    public void test1() {
        Hand hand = new Hand();
        hand.AddCard(new Card(Rank.TWO, Suit.SPADES));
        hand.AddCard(new Card(Rank.TWO, Suit.SPADES));
        assertEquals(hand.Score(), 4);
        hand.AddCard(new Card(Rank.TWO, Suit.DIAMONDS));
        assertEquals(hand.Score(), 6);
        hand.AddCard(new Card(Rank.TWO, Suit.CLUBS));
        assertEquals(hand.Score(), 8);
        hand.AddCard(new Card(Rank.TWO, Suit.HEARTS));
        assertEquals(hand.Score(), 10);
        hand.AddCard(new Card(Rank.TWO, Suit.SPADES));
        assertEquals(hand.Score(), 12);
        hand.AddCard(new Card(Rank.TWO, Suit.SPADES));
        assertEquals(hand.Score(), 14);
        hand.AddCard(new Card(Rank.TWO, Suit.SPADES));
        assertEquals(hand.Score(), 16);
        hand.AddCard(new Card(Rank.TWO, Suit.SPADES));
        assertEquals(hand.Score(), 18);
        hand.AddCard(new Card(Rank.TWO, Suit.SPADES));
        assertEquals(hand.Score(), 20);
        hand.AddCard(new Card(Rank.TWO, Suit.SPADES));
        assertEquals(hand.Score(), 22);
    }
    
    @Test
    public void test2() {
        Hand hand = new Hand();
        hand.AddCard(new Card(Rank.THREE, Suit.SPADES));
        hand.AddCard(new Card(Rank.FOUR, Suit.SPADES));
        assertEquals(hand.Score(), 7);
        hand.AddCard(new Card(Rank.FIVE, Suit.SPADES));
        assertEquals(hand.Score(), 12);
        hand.AddCard(new Card(Rank.SIX, Suit.SPADES));
        assertEquals(hand.Score(), 18);
        hand.AddCard(new Card(Rank.SEVEN, Suit.SPADES));
        assertEquals(hand.Score(), 25);
    }
    
    @Test
    public void test3() {
        Hand hand = new Hand();
        hand.AddCard(new Card(Rank.EIGHT, Suit.SPADES));
        hand.AddCard(new Card(Rank.NINE, Suit.SPADES));
        assertEquals(hand.Score(), 17);
        hand.AddCard(new Card(Rank.TEN, Suit.SPADES));
        assertEquals(hand.Score(), 27);
    }
    
    @Test
    public void test4() {
        Hand hand = new Hand();
        hand.AddCard(new Card(Rank.QUEEN, Suit.DIAMONDS));
        hand.AddCard(new Card(Rank.KING, Suit.CLUBS));
        assertEquals(hand.Score(), 20);
        hand.AddCard(new Card(Rank.JACK, Suit.HEARTS));
        assertEquals(hand.Score(), 30);
    }
    
    @Test
    public void test5() {
        Hand hand = new Hand();
        hand.AddCard(new Card(Rank.QUEEN, Suit.DIAMONDS));
        hand.AddCard(new Card(Rank.KING, Suit.CLUBS));
        assertEquals(hand.Score(), 20);
        hand.AddCard(new Card(Rank.ACE, Suit.HEARTS));
        assertEquals(hand.Score(), 21);
    }
    
    @Test
    public void test6() {
        Hand hand = new Hand( new Card(Rank.QUEEN, Suit.DIAMONDS),
                              new Card(Rank.ACE, Suit.CLUBS));
        assertEquals(hand.Score(), 21);
    }
    
    @Test
    public void test7() {
        Hand hand = new Hand( new Card(Rank.ACE, Suit.DIAMONDS),
                              new Card(Rank.ACE, Suit.CLUBS));
        assertEquals(hand.Score(), 12);
    }
    
    @Test
    public void test8() {
        Hand hand = new Hand( new Card(Rank.ACE, Suit.DIAMONDS),
                              new Card(Rank.ACE, Suit.CLUBS));
        assertEquals(hand.Score(), 12);
        hand.Clear();
        hand.AddCard(new Card(Rank.ACE, Suit.HEARTS));
        hand.AddCard(new Card(Rank.ACE, Suit.SPADES));
        assertEquals(hand.Score(), 12);
        hand.AddCard(new Card(Rank.ACE, Suit.SPADES));
        hand.AddCard(new Card(Rank.ACE, Suit.SPADES));
        hand.AddCard(new Card(Rank.ACE, Suit.SPADES));
        hand.AddCard(new Card(Rank.ACE, Suit.SPADES));
        hand.AddCard(new Card(Rank.ACE, Suit.SPADES));
        assertEquals(hand.Score(), 17);
    }
}
