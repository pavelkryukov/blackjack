package client;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

import base.Player;

public class PlayerDrawer {
	HandDrawer hdr_;
	Font font;
	TrueTypeFont ttf;
	
	public PlayerDrawer(Resources res) {
		hdr_ = new HandDrawer(res);
		font = new Font("Verdana", Font.BOLD, 30);
		ttf = new TrueTypeFont(font, true);
	}
	
	public void drawPlayer(Player player, int x, int y, int width) {
		hdr_.DrawHand(player.hand, x, y, width);
		ttf.drawString(x + width/2 - ttf.getWidth(player.getName()) / 2,
				       y + 160,
				       player.getName(), Color.white );
		int score = player.hand.score();
		Color score_color;
		if ( score > 21 )
			score_color = Color.red;
		else if (score == 21)
			score_color = Color.blue;
		else
			score_color = Color.white;
		
		ttf.drawString(x + width/2 - ttf.getWidth(Integer.toString(score)) / 2,
			       y + 160 + ttf.getHeight(player.getName()) + 10,
			       Integer.toString(score) + (player.IsInGame() ? " [game]" : " [stand]"), score_color);
	}
}
