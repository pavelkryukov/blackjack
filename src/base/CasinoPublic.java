package base;

import java.io.Serializable;
import java.util.HashMap;

import base.Player;

public class CasinoPublic implements Serializable {
	public static final long serialVersionUID = 1L;
	/**
	 * Hash map containing all info about players
	 * String name is used as ID
	 */
	public final HashMap<String, Player> players;
	
	/**
	 * Shows if game is performed or not
	 */
	public Boolean isGame;

	/**
	 * Constructor
	 */
	public CasinoPublic() {
		this.players = new HashMap<String, Player>();
		this.isGame = false;
	}

	/**
	 * Copy constructor
	 * @param src source
	 */
	public CasinoPublic(CasinoPublic src) {
		this.players = new HashMap<String, Player>(src.players);
		this.isGame = src.isGame;
	}
}