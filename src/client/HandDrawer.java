package client;

import base.Hand;

public class HandDrawer {
	private CardDrawer cdr_;
		
	public HandDrawer(CardDrawer cdr) {
		cdr_ = cdr;
	}
	
	public HandDrawer(Resources res) {
		cdr_ = new CardDrawer(res);
	}
	
	public void DrawHand(Hand hand, int x, int y, int width) {
		
		int offset;
		
		int card_width = 100;
		
		if (hand.cards.size() > 1) {
			offset = (width - card_width) / (hand.cards.size() - 1);
			if (offset > card_width)
				offset = card_width;
		} else {
			offset = 0;
		}
		
		int real_width = card_width + offset * (hand.cards.size() - 1);
		
		int x_centered = x + (width - real_width)/2;
		
		for (int i = 0; i < hand.cards.size(); i++) {
			cdr_.drawCard(hand.cards.get(i), x_centered + offset*i, y);
		}
	}
}
