package edu.jhu.espresso.server.domain.gameEvents;

import edu.jhu.espresso.server.domain.ClueLessProtocolType;
import edu.jhu.espresso.server.domain.gamepieces.LocationNames;

import java.util.List;

public final class MoveOptions implements Comparable<MoveOptions>
{
    private List<LocationNames> validMoves;
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
}
