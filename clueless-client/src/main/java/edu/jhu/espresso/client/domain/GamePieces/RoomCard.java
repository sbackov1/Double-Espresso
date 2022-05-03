package edu.jhu.espresso.client.domain.GamePieces;

public class RoomCard implements Card {

    private final String name;
    private final String type = "ROOM";

    public RoomCard(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomCard roomCard = (RoomCard) o;

        if (name != null ? !name.equals(roomCard.name) : roomCard.name != null) return false;
        return type != null ? type.equals(roomCard.type) : roomCard.type == null;
    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
