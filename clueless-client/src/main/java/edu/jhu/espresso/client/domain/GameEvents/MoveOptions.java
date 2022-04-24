package edu.jhu.espresso.client.domain.GameEvents;

import edu.jhu.espresso.client.domain.ClueLessProtocolType;
import edu.jhu.espresso.client.domain.GamePieces.LocationNames;
import edu.jhu.espresso.client.domain.Menus.Menu;
import edu.jhu.espresso.client.domain.Menus.MenuItem;

import java.util.List;

/**
 * MoveOptions allows the options for moves to be shown to the player.
 * */


public final class MoveOptions implements Comparable<MoveOptions>
{
    private List<LocationNames> validMoves;
    private LocationNames location;
    private ClueLessProtocolType clueLessProtocolType;
    private int handlerNumber;

    public List<LocationNames> getValidMoves()
    {
        return validMoves;
    }

    public void setValidMoves(List<LocationNames> validMoves)
    {
        this.validMoves = validMoves;
    }

    public ClueLessProtocolType getTurnIndicator()
    {
        return clueLessProtocolType;
    }

    public void setTurnIndicator(ClueLessProtocolType clueLessProtocolType)
    {
        this.clueLessProtocolType = clueLessProtocolType;
    }

    public int getHandlerNumber()
    {
        return handlerNumber;
    }

    public void setHandlerNumber(int handlerNumber)
    {
        this.handlerNumber = handlerNumber;
    }

    public LocationNames getLocation()
    {
        return location;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoveOptions that = (MoveOptions) o;

        if (handlerNumber != that.handlerNumber) return false;
        if (validMoves != null ? !validMoves.equals(that.validMoves) : that.validMoves != null) return false;
        return clueLessProtocolType == that.clueLessProtocolType;
    }

    @Override
    public int hashCode()
    {
        int result = validMoves != null ? validMoves.hashCode() : 0;
        result = 31 * result + (clueLessProtocolType != null ? clueLessProtocolType.hashCode() : 0);
        result = 31 * result + handlerNumber;
        return result;
    }

    @Override
    public String toString()
    {
        return "MessageStub{" +
                "validMoves=" + validMoves +
                ", turnIndicator=" + clueLessProtocolType +
                ", handlerNumber=" + handlerNumber +
                '}';
    }

    @Override
    public int compareTo(MoveOptions o)
    {
        return Integer.compare(handlerNumber, o.handlerNumber);
    }

    public String toString2()
    {
        return "Movement{" +
                "Move to=" + location + 
                '}';
    }

    public void printToString()
    {
        String output = toString2();
        System.out.println("\n" + output + " is the message to be transmitted.");
    }

    public void mainMoveMenu()
    {
        Menu moveMenu = new Menu();
        moveMenu.setTitle("*** Movement Menu ***");

        for(LocationNames validMove : this.validMoves)
        {
            moveMenu.addItem(new MenuItem(validMove.name(), this, "setMoveString", validMove.name()));
        }
        moveMenu.addItem(new MenuItem("Confirm Movement, Move Character", this, "printToString", null));

        moveMenu.execute();
    }

    public void setMove(LocationNames location)
    {
        this.location = location;
    }

    public void setMoveString(String moveString)
    {
        this.location = LocationNames.valueOf(moveString);
        System.out.println("\n" + this.location + " was selected.");

    }
}
