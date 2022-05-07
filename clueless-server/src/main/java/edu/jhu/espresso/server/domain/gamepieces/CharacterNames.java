package edu.jhu.espresso.server.domain.gamepieces;

public enum CharacterNames
{
    MISS_SCARLET(1),
    COLONEL_MUSTARD(2),
    MRS_WHITE(3),
    MR_GREEN(4),
    MRS_PEACOCK(5),
    PROFESSOR_PLUM(6);

    private final int orderNumber;

    CharacterNames(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber()
    {
        return orderNumber;
    }
}
