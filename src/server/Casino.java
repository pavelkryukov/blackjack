/**
 * Copyright (c) 2013 Svyatoslav Kuzmich, Pavel Kryukov. All rights reserved.
 */
package server;

import java.util.Map.Entry;

import base.CasinoPublic;
import base.Player;
import base.Request;

/**
 * Casino emulator
 */
public class Casino extends CasinoPublic {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Deck of cards
	 */
	private Deck deck;

	public Casino() {
		this.deck = new Deck();
	}

	/**
	 * Finds and drops losers from the current game
	 */
	private void DropLosers() {
		for (Entry<String, Player> entry : players.entrySet()) {
			entry.getValue().UpdateIfLost();
		}
	}

	/**
	 * Checks is all players are ready to start the game
	 * @return true if ready
	 */
	private Boolean GameShouldBeStarted() {
		if (isGame)
			return false;

		for (Entry<String, Player> entry : players.entrySet()) {
			if (!entry.getValue().IsReady())
				return false;
		}
		return true;
	}

	/**
	 * Starts a new game
	 */
	private void StartGame() {
		for (Entry<String, Player> entry : players.entrySet()) {
			entry.getValue().SetInGame();
			// Give two cards to everybody
			entry.getValue().GetCard(deck.GetCard());
			entry.getValue().GetCard(deck.GetCard());
		}
		isGame = true;
	}

	/**
	 * Process incoming request from client
	 * @param req incoming request
	 */
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

	/**
	 * Updates current state
	 */
	private void UpdateState() {
		if ( isGame) {
			/**
			 * Find and drop losers
			 */
			DropLosers();
		}
		else {
			/**
			 * Start game if it is necessary
			 */
			if (GameShouldBeStarted()) {
				StartGame();
			}
		}
	}
}