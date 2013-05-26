package client;

import com.aem.sticky.button.SimpleButton;

import base.CasinoPublic;
import base.Player;

public class CasinoDrawer {
	
	PlayerDrawer pld;
	public SimpleButton button;

	final int players_x = 70;
	final int players_y = 500;
	final int players_x_delta = 310;
	final int free_x_space_between_players = 50;
	final int dealer_x = 40;
	final int dealer_y = 60;
	final int dealer_width = 1200;
	
	public CasinoDrawer(Resources res) {
		this.pld = new PlayerDrawer(res);
	}
	
	public void DrawCasino(CasinoPublic casino, String myName) {
		int offset = 0;
		Player me = null;
        for (Player player: casino.players.values()) {
        	if (player.getName().equalsIgnoreCase(myName)) {
        		me = player;
        	}
        	else {
        		pld.drawPlayer(player, players_x + offset, players_y, players_x_delta - free_x_space_between_players);
        		offset += players_x_delta;
        	}
        }
 
        if (me != null) {
        	pld.drawPlayer(me, dealer_x, dealer_y, dealer_width);
        }
	}
}
