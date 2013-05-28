package base;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * Players hand
     */
    public final Hand hand;
    
    /**
     * Shows is player playing now or not
     */
    private Boolean isInGame;

    /**
     * True if player is ready to start new game
     */
    private Boolean isReady;

    /**
     * Player name
     */
    private final String name;
    
    /**
     * Counter of victories
     */
    private int victoryNum;

    public class InvalidRequestException extends Exception {
        private static final long serialVersionUID = 1L;    
    }

    /**
     * New player constructor
     * @param name players name
     */
    public Player( String name) {
        this.hand = new Hand();
        this.isInGame = false;
        this.isReady = false;
        this.victoryNum = 0;
        this.name = name;
    }

    /**
     * Copy constructor
     * @param src original player
     */
    public Player( Player src) {
        this.hand = new Hand(src.hand);
        this.isInGame = src.isInGame;
        this.isReady = src.isReady;
        this.victoryNum = src.victoryNum;
        this.name = src.name;
    }

    /**
     * Say to player he is a loser
     */
    public void LoseGame() {
        isInGame = false;
        isReady  = false;
    }

    /**
     * Process request
     * @param req request
     * @throws InvalidRequestException
     */
    public void ProcessRequest(Request req) throws InvalidRequestException {
        switch (req.GetType()) {
        case RESIGN:
            this.LoseGame();
            break;
        case START:
            isReady = true;
            break;
        case REFRESH:
            break;
        default:
            throw new InvalidRequestException();
        }
    }

    /**
     * Checks if player is in game now
     * @return true if is in game
     */
    public Boolean IsInGame() { return isInGame; }
    
    /**
     * Checks if player is ready to start new game
     * @return true if is ready
     */
    public Boolean IsReady() { return isReady; }

    /**
     * Say to player he is in game
     */
    public void SetInGame() {
        isInGame = true;
        isReady = false;
    }

    /**
     * Give a card to player
     * @param card given card
     */
    public void GiveCard(Card card) {
        hand.addCard(card);
    }

    /**
     * Drop all cards from hand
     */
    public void DropCards() {
        hand.clear();
    }

    /**
     * Check if hand has lost
     * @return true if lost
     */
    public Boolean hasLost() {
        return hand.hasLost();
    }

    /**
     * Check if hand has win
     * @return true if win
     */
    public Boolean hasWin() {
        return hand.hasWin();
    }

    /**
     * Get current score
     * @return current score
     */
    public int GetScore() {
        return hand.score();
    }

    /**
     * Get players name
     * @return true if lost
     */
    public String getName() {
        return name;
    }

    /**
     * Increment victories number
     */
    public void IncrementVictories() {
        victoryNum++;
    }

    /**
     * Get victories number
     * @return victories number
     */
    public int GetVictoriesNum() {
        return victoryNum;
    }
}