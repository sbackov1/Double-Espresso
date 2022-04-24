package edu.jhu.espresso.client.domain.GamePieces;

public class CharacterCard implements Card {

    private final String name;
    private final String type = "CHARACTER";

    public CharacterCard(String name) {
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

        CharacterCard that = (CharacterCard) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode()
    {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
