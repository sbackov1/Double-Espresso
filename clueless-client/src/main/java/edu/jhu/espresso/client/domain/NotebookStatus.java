package edu.jhu.espresso.client.domain;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public enum NotebookStatus
{
    KNOWN,
    UNKNOWN,
    HAND,
    EXTRA;

    private final int size = 18;
    private final String fontName = "Times New Roman";
    private final Font handFont = Font.font(fontName, FontWeight.BOLD, FontPosture.REGULAR, size);
    private final Font knownFont = Font.font(fontName, FontWeight.NORMAL, FontPosture.ITALIC, size);
    private final Font unknownFont = Font.font(fontName, FontWeight.NORMAL, FontPosture.REGULAR, size);
    private final Font extraFont = Font.font(fontName, FontWeight.BOLD, FontPosture.ITALIC, size);

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
            case EXTRA:
                font = extraFont;
                break;
            default:
                throw new IllegalArgumentException(this + " not mapped to font");
        }
        return font;
    }
}
