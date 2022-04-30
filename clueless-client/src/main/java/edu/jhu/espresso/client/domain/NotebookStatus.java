package edu.jhu.espresso.client.domain;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public enum NotebookStatus
{
    KNOWN,
    UNKNOWN,
    HAND;

    private final int size = 13;
    private final String fontName = "Courier";
    private final Font handFont = Font.font(fontName, FontWeight.BOLD, FontPosture.REGULAR, size);
    private final Font knownFont = Font.font(fontName, FontWeight.NORMAL, FontPosture.ITALIC, size);
    private final Font unknownFont = Font.font(fontName, FontWeight.BOLD, FontPosture.REGULAR, size);

    public Font toFont()
    {
        Font font;
        switch (this)
        {
            case HAND:
                font = handFont;
                break;
            case KNOWN:
                font = knownFont;
                break;
            case UNKNOWN:
                font = unknownFont;
                break;
            default:
                throw new IllegalArgumentException(this + " not mapped to font");
        }
        return font;
    }
}
