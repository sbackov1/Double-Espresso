package edu.jhu.espresso.client.domain;

import java.util.ArrayList;

public class MoveDriver {

    public static void main(String[] args) {
        ArrayList<String> ValidMoves = new ArrayList<>();
        ValidMoves.add("BALLROOM");
        ValidMoves.add("KITCHEN");

        MoveOptions moveOptions = new MoveOptions();

        moveOptions.setValidMoves(ValidMoves);
        moveOptions.mainMoveMenu();

    }
}
