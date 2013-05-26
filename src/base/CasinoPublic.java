package base;

import java.io.Serializable;
import java.util.HashMap;

import base.Player;

public class CasinoPublic implements Serializable {
	public static final long serialVersionUID = 1L;
	public HashMap<String, Player> players;
	public Player dealer;
	public Boolean isGame;
	public Boolean isHitAllowed;
	public Boolean isStandAllowed;
	public CasinoPublic() {
		this.players = new HashMap<String, Player>();
		this.isGame = false;
		this.isHitAllowed = false;
		this.isStandAllowed = false;
	}
}