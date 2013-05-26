package base;

import java.io.Serializable;
import java.util.HashMap;

import base.Player;

public class CasinoPublic implements Serializable {
	public static final long serialVersionUID = 1L;
	public HashMap<String, Player> players;
	public Boolean isGame;
	public CasinoPublic() {
		this.players = new HashMap<String, Player>();
		this.isGame = false;
	}
	public CasinoPublic(CasinoPublic src) {
		this.players = new HashMap<String, Player>(src.players);
		this.isGame = src.isGame;
	}
}