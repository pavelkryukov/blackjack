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
public final class Casino extends CasinoPublic {
    private static final long serialVersionUID = 1L;
    
    /**
     * Deck of cards
     */
    private Deck deck;

    /**
     * Constructor
     */
    public Casino() {
        this.deck = new Deck();
    }

    /**
     * Makes everybody losing game
     */
    private void DropEverybody() {
        for (Entry<String, Player> entry : players.entrySet()) {
            entry.getValue().LoseGame();
        }
    }

    /**
     * Checks if there is (no) winner and performs action
     */
    private void ProcessGamers() {
        Player bestPlayer = null;
        Boolean isEverybodyStands = true;
        int bestScore = 0;
        for (final Entry<String, Player> entry : players.entrySet()) {
            final Player player = entry.getValue();
            if (player.hasLost()) { // More than 21
                player.LoseGame();
            }
            else if (entry.getValue().hasWin()) { // Has 21
                player.LoseGame();
                player.IncrementVictories();
                DropEverybody();
                break;
            }
            else if (!entry.getValue().IsInGame()) { // Less 21 and stands
                if (player.GetScore() > bestScore) {
                    bestScore = player.GetScore();
                    bestPlayer = player;
                }
            }
            else { // Less 21 and still in game
                isEverybodyStands = false;
            }
        }
        if (isEverybodyStands && bestPlayer != null) {
            bestPlayer.IncrementVictories();
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
        System.out.println("Game started!");
        for (Entry<String, Player> entry : players.entrySet()) {
            entry.getValue().SetInGame();
            // Drop cards off
            entry.getValue().DropCards();
            // Give two cards to everybody
            entry.getValue().GiveCard(deck.GetCard());
            entry.getValue().GiveCard(deck.GetCard());
        }
        isGame = true;
    }

    /**
     * Checks if game is over
     * @return true if everybody stands
     */
    private Boolean GameIsOver() {
        for (Entry<String, Player> entry : players.entrySet()) {
            if (entry.getValue().IsInGame())
                return false;
        }
        return true;
    }

    /**
     * Process incoming request from client
     * @param req incoming request
     */
    public void ProcessRequest(Request req) {
        if (req == null)
            return;
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
            System.out.println("Player " + id + " disconnected");
            players.remove(id);
        }
        else if (req.IsGive()) {
            if (players.get(id).IsInGame()) {
                players.get(id).GiveCard(deck.GetCard());
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
            ProcessGamers();
            if (GameIsOver()) {
                isGame = false;
            }
        }
        else {
            // Start game if it is necessary
            if (GameShouldBeStarted()) {
                StartGame();
            }
        }
    }
}