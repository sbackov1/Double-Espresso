package edu.jhu.espresso.client.domain;

import javafx.scene.text.Font;

public class CardNotebookStatus
{
    private NotebookStatus notebookStatus;
    private Font font;

    public CardNotebookStatus(NotebookStatus notebookStatus)
    {
        this.notebookStatus = notebookStatus;
    }

    public void setNotebookStatus(NotebookStatus notebookStatus)
    {
        this.notebookStatus = notebookStatus;
        font = notebookStatus.toFont();
    }

    public NotebookStatus getNotebookStatus()
    {
        return notebookStatus;
    }

    public Font getFont()
    {
        return font;
    }
}
