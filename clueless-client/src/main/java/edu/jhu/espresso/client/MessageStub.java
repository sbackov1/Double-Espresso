package edu.jhu.espresso.client;

public final class MessageStub
{
    private String message;
    private TurnIndicator turnIndicator;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public TurnIndicator getTurnIndicator()
    {
        return turnIndicator;
    }

    public void setTurnIndicator(TurnIndicator turnIndicator)
    {
        this.turnIndicator = turnIndicator;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageStub that = (MessageStub) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        return turnIndicator == that.turnIndicator;
    }

    @Override
    public int hashCode()
    {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (turnIndicator != null ? turnIndicator.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "MessageStub{" +
                "message='" + message + '\'' +
                ", someData=" + turnIndicator +
                '}';
    }
}
