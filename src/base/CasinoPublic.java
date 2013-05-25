package base;

import java.io.Serializable;
import java.util.HashMap;

import base.Player;

public class CasinoPublic implements Serializable {
	protected static final long serialVersionUID = 1L;
	protected HashMap<String, Player> players;
	protected Boolean isGame;
	protected CasinoPublic() {
		this.players = new HashMap<String, Player>();
		this.isGame = false;
	}
}