package edu.jhu.espresso.client;

import java.util.List;

public final class MessageStub implements Comparable<MessageStub>
{
    private List<String> validMoves;
    private TurnIndicator turnIndicator;
    private int handlerNumber;

    public List<String> getValidMoves()
    {
        return validMoves;
    }

    public void setValidMoves(List<String> validMoves)
    {
        this.validMoves = validMoves;
    }

    public TurnIndicator getTurnIndicator()
    {
        return turnIndicator;
    }

    public void setTurnIndicator(TurnIndicator turnIndicator)
    {
        this.turnIndicator = turnIndicator;
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

        MessageStub that = (MessageStub) o;

        if (handlerNumber != that.handlerNumber) return false;
        if (validMoves != null ? !validMoves.equals(that.validMoves) : that.validMoves != null) return false;
        return turnIndicator == that.turnIndicator;
    }

    @Override
    public int hashCode()
    {
        int result = validMoves != null ? validMoves.hashCode() : 0;
        result = 31 * result + (turnIndicator != null ? turnIndicator.hashCode() : 0);
        result = 31 * result + handlerNumber;
        return result;
    }

    @Override
    public String toString()
    {
        return "MessageStub{" +
                "validMoves=" + validMoves +
                ", turnIndicator=" + turnIndicator +
                ", handlerNumber=" + handlerNumber +
                '}';
    }

    @Override
    public int compareTo(MessageStub o)
    {
        return Integer.compare(handlerNumber, o.handlerNumber);
    }
}
