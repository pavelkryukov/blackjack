package server;

import java.util.Map.Entry;

import base.CasinoPublic;
import base.Deck;
import base.Player;
import base.Request;

public class Casino extends CasinoPublic {
	private static final long serialVersionUID = 1L;
	private Deck deck;

	public Casino() {
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
		final Boolean isPlayerFound = players.containsKey(id);
		if (req.IsConnect()) {
			if (isPlayerFound) {
				System.out.println("Player " + id + " is already in Casino");
			}
			else {
				players.put(id, new Player(id));
				System.out.println("Player " + id + " joined Casino");
			}
		}
		else if (!isPlayerFound) {
			System.out.println("There is no player " + id + " in current game");
		}
		else if (req.IsDisconnect()) {
			players.remove(id);
		}
		else if (req.IsGive()) {
			if (players.get(id).IsInGame()) {
				players.get(id).GetCard(deck.GetCard());
			}
			else {
				System.out.println("Player " + id + " is not in game anymore, request dropped");
			}
		}
		else {
			try {
				players.get(id).ProcessRequest(req);
			}
			catch (Player.InvalidRequestException e) {
				System.out.println("Internal error: unhandled request");
			}
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