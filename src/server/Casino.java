package server;

import java.util.HashMap;
import java.util.Map.Entry;

import base.Deck;
import base.Request;

public class Casino {
	private HashMap<String, Player> players;
	private Boolean isGame;
	private Deck deck;

	public Casino() {
		this.players = new HashMap<String, Player>();
		this.isGame = false;
		this.deck = new Deck();
	}
	
	private void DropLosers() {
		for (Entry<String, Player> entry : players.entrySet()) {
			entry.getValue().UpdateIfLost();
		}
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
			entry.getValue().GetCard(deck.GetCard());
			entry.getValue().GetCard(deck.GetCard());
		}
		isGame = true;
	}

	public void ProcessRequest(Request req) {
		final String id = req.GetId();
		if (req.IsConnect()) {
			players.put(id, new Player());
			System.out.println("Player " + id + " joined Casino");
		}
		else if (!players.containsKey(id)) {
			System.out.println("There is no player " + id + " in current game");
		}
		else if (req.IsStart()) {
			try {
				players.get(id).ProcessRequest(req);
			}
			catch (Player.InvalidRequestException e) {
				System.out.println("Internal error: unhandled request");
			}
		}
		else if (req.IsDisconnect()) {
			players.remove(id);
		}
		else if (!isGame) {
			System.out.println("Request from " + id + " is dropped because draw is over");
		}
		else if (req.IsGive()) {
			players.get(id).GetCard(deck.GetCard());
		}
		else if (players.get(id).IsInGame()) {
			try {
				players.get(id).ProcessRequest(req);
			}
			catch (Player.InvalidRequestException e) {
				System.out.println("Internal error: unhandled request");
			}
		}
		else {
			System.out.println("Request from " + id + " is dropped because user had left the game");
		}
		UpdateState();
	}

	private void UpdateState() {
		if ( isGame) {
			DropLosers();
		}
		else {
			if (GameShouldBeStarted()) {
				StartGame();
			}
		}
	}

}