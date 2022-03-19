package edu.jhu.espresso;

public final class MessageStub
{
    private String message;
    private boolean someData;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public boolean isSomeData()
    {
        return someData;
    }

    public void setSomeData(boolean someData)
    {
        this.someData = someData;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageStub that = (MessageStub) o;

        if (someData != that.someData) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode()
    {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (someData ? 1 : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "MessageStub{" +
                "message='" + message + '\'' +
                ", someData=" + someData +
                '}';
    }
}
