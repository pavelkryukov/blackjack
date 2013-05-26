package client;

import java.util.HashMap;

import org.newdawn.slick.Image;

import base.Card.*;
import base.Card;

public class CardDrawer {
	private Resources res_;
	private HashMap<Rank, Image> spades_map;
	private HashMap<Rank, Image> hearts_map;
	private HashMap<Rank, Image> clubs_map;
	private HashMap<Rank, Image> diamonds_map;

	private int w, h;

	public CardDrawer(Resources res) {
		this.w = 100;
		this.h = 150;
		
		res_ = res;
		
		spades_map = new HashMap<Rank, Image>();
		spades_map.put(Rank.TWO,   res_.img_2_of_spades);
		spades_map.put(Rank.THREE, res_.img_3_of_spades);
		spades_map.put(Rank.FOUR,  res_.img_4_of_spades);
		spades_map.put(Rank.FIVE,  res_.img_5_of_spades);
		spades_map.put(Rank.SIX,   res_.img_6_of_spades);
		spades_map.put(Rank.SEVEN, res_.img_7_of_spades);
		spades_map.put(Rank.EIGHT, res_.img_8_of_spades);
		spades_map.put(Rank.NINE,  res_.img_9_of_spades);
		spades_map.put(Rank.TEN,   res_.img_10_of_spades);
		spades_map.put(Rank.JACK,  res_.img_jack_of_spades);
		spades_map.put(Rank.QUEEN, res_.img_queen_of_spades);
		spades_map.put(Rank.KING,  res_.img_king_of_spades);
		spades_map.put(Rank.ACE,   res_.img_ace_of_spades);
		
		hearts_map = new HashMap<Rank, Image>();
		hearts_map.put(Rank.TWO,   res_.img_2_of_hearts);
		hearts_map.put(Rank.THREE, res_.img_3_of_hearts);
		hearts_map.put(Rank.FOUR,  res_.img_4_of_hearts);
		hearts_map.put(Rank.FIVE,  res_.img_5_of_hearts);
		hearts_map.put(Rank.SIX,   res_.img_6_of_hearts);
		hearts_map.put(Rank.SEVEN, res_.img_7_of_hearts);
		hearts_map.put(Rank.EIGHT, res_.img_8_of_hearts);
		hearts_map.put(Rank.NINE,  res_.img_9_of_hearts);
		hearts_map.put(Rank.TEN,   res_.img_10_of_hearts);
		hearts_map.put(Rank.JACK,  res_.img_jack_of_hearts);
		hearts_map.put(Rank.QUEEN, res_.img_queen_of_hearts);
		hearts_map.put(Rank.KING,  res_.img_king_of_hearts);
		hearts_map.put(Rank.ACE,   res_.img_ace_of_hearts);
		
		diamonds_map = new HashMap<Rank, Image>();
		diamonds_map.put(Rank.TWO,   res_.img_2_of_diamonds);
		diamonds_map.put(Rank.THREE, res_.img_3_of_diamonds);
		diamonds_map.put(Rank.FOUR,  res_.img_4_of_diamonds);
		diamonds_map.put(Rank.FIVE,  res_.img_5_of_diamonds);
		diamonds_map.put(Rank.SIX,   res_.img_6_of_diamonds);
		diamonds_map.put(Rank.SEVEN, res_.img_7_of_diamonds);
		diamonds_map.put(Rank.EIGHT, res_.img_8_of_diamonds);
		diamonds_map.put(Rank.NINE,  res_.img_9_of_diamonds);
		diamonds_map.put(Rank.TEN,   res_.img_10_of_diamonds);
		diamonds_map.put(Rank.JACK,  res_.img_jack_of_diamonds);
		diamonds_map.put(Rank.QUEEN, res_.img_queen_of_diamonds);
		diamonds_map.put(Rank.KING,  res_.img_king_of_diamonds);
		diamonds_map.put(Rank.ACE,   res_.img_ace_of_diamonds);
		
		clubs_map = new HashMap<Rank, Image>();
		clubs_map.put(Rank.TWO,   res_.img_2_of_clubs);
		clubs_map.put(Rank.THREE, res_.img_3_of_clubs);
		clubs_map.put(Rank.FOUR,  res_.img_4_of_clubs);
		clubs_map.put(Rank.FIVE,  res_.img_5_of_clubs);
		clubs_map.put(Rank.SIX,   res_.img_6_of_clubs);
		clubs_map.put(Rank.SEVEN, res_.img_7_of_clubs);
		clubs_map.put(Rank.EIGHT, res_.img_8_of_clubs);
		clubs_map.put(Rank.NINE,  res_.img_9_of_clubs);
		clubs_map.put(Rank.TEN,   res_.img_10_of_clubs);
		clubs_map.put(Rank.JACK,  res_.img_jack_of_clubs);
		clubs_map.put(Rank.QUEEN, res_.img_queen_of_clubs);
		clubs_map.put(Rank.KING,  res_.img_king_of_clubs);
		clubs_map.put(Rank.ACE,   res_.img_ace_of_clubs);
	}
	
	public void drawCard(Card card, int x, int y) {
		Image img = null;
		switch (card.getSuit()) {
		case CLUBS:  	img = clubs_map.get(card.getRank() );	break;
		case DIAMONDS:	img = diamonds_map.get(card.getRank());	break;
		case HEARTS:	img = hearts_map.get(card.getRank());	break;
		case SPADES:	img = spades_map.get(card.getRank());	break;
		}

		img.draw(x, y, w, h);
	}
}
