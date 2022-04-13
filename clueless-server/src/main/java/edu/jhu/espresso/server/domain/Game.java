package edu.jhu.espresso.server.domain;
import java.util.*;
public class Game {

    public int GameID;
    private CardPlayer cardPlayer;
    private GameBoard gameBoard;
    public ArrayList<Player> playerList;
    public GameTimer gameTimer;

    public Game(int gameID, ArrayList<Player> playerListy) {
        GameID = gameID;

        //Instantiate objects
        cardPlayer = new CardPlayer();
        playerList = playerListy;
        this.gameBoard = new GameBoard();
        gameTimer = new GameTimer(1, playerListy);
        }


    public int getGameID() {
        return GameID;
    }

    public CardPlayer getCardPlayer() {
        return cardPlayer;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public boolean isOver()
    {
        return false;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    //Startgame creates notebooks, deals cards, and starts the turn of the first active player.
    public void startGame(){
        for (Player p : playerList){
            p.makeNotebook(this.getCardPlayer().getCardDeck());
        }

        this.getCardPlayer().dealCards(this.getPlayerList());

    }

    //confirmAccusation returns true if accusation is accurate and false if not.
    public boolean confirmAccusation(Player accuser, ArrayList<Card> Accusation){
        boolean isTrue = Accusation.containsAll(this.getCardPlayer().getCaseFile());
        if (isTrue){return true;}

        //else set accuser to passive and replace them in the turn order.
        else {
            accuser.getPreviousPlayer().setNextPlayer(accuser.getNextPlayer());
            accuser.getNextPlayer().setPreviousPlayer(accuser.getPreviousPlayer());
            accuser.setActiveStatus(false);
            return false;
        }
    }


    //confirmSuggestion loops through players and checks their notebooks for the correct card.
    public ArrayList<Card> confirmSuggestion (ArrayList<Card> suggestion) {

        Player activePlayer = this.gameTimer.getActivePlayer();
        Player passivePlayer = activePlayer;

        //Stop when a full loop is created.
        while ( passivePlayer.getNextPlayer() != activePlayer){

            passivePlayer = this.gameTimer.setNextPassivePlayer();
            ArrayList<Card> disproveCards = passivePlayer.notebook.canDisproveSuggestion(suggestion);

            if (disproveCards.size() != 0) {return disproveCards;}
        }

        return null;

    }

    public void endTurn(){

        this.getGameTimer().setNewActivePlayer();

    }

    public Map<CharacterNames, LocationNames> getLocations()
    {
        EnumMap<CharacterNames, LocationNames> map = new EnumMap<>(CharacterNames.class);
        map.put(CharacterNames.MISS_SCARLET, LocationNames.HALLWAY4);
        map.put(CharacterNames.PROFESSOR_PLUM, LocationNames.CONSERVATORY);
        map.put(CharacterNames.MRS_PEACOCK, LocationNames.STUDY);
        map.put(CharacterNames.COLONEL_MUSTARD, LocationNames.CELLAR);
        return map;
    }

    public void applyMoveChoice(MoveChoice moveChoice)
    {

    }
}

