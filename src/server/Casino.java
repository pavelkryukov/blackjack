package server;

import java.util.HashMap;
import java.util.Map.Entry;

import base.Request;

public class Casino {
	private HashMap<String, Player> players;
	private Boolean isGame;

	public Casino() {
		this.players = new HashMap<String, Player>();
		this.isGame = false;
	}

	private Boolean GameShouldBeStarted() {
		if (isGame)
			return false;

		for (Entry<String, Player> entry : players.entrySet()) {
			if (!entry.getValue().IsReady())
				return false;
		}
		return true;
	}
	
	private void StartGame() {
		for (Entry<String, Player> entry : players.entrySet()) {
			entry.getValue().SetInGame();
		}
		isGame = true;
	}

	public void ProcessRequest(Request req) {
		final String id = req.GetId();
		if (req.IsConnect()) {
			players.put(id, new Player());
		}
		else if (!players.containsKey(id)) {
			System.out.println("There is no player " + id + " in current game");
		}
		else if (req.IsDisconnect()) {
			players.remove(id);
		}
		else if (!isGame) {
			System.out.println("Request from " + id + " is dropped because draw is over");
		}
		else if (players.get(id).IsInGame()) {
			try {
				players.get(id).ProcessRequest(req);
			}
			catch (Player.InvalidRequestException e) {
				System.out.println("Internal error: unhandled connect/disconnect request");
			}
		}
		else {
			System.out.println("Request from " + id + " is dropped because user had left the game");
		}
		UpdateState();
	}

	private void UpdateState() {
		if (GameShouldBeStarted())
			StartGame();
	}
}