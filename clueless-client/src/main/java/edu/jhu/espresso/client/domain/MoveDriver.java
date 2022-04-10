package edu.jhu.espresso.client.domain;

import java.util.ArrayList;

public class MoveDriver {

    public static void main(String[] args) {
        ArrayList<String> ValidMoves = new ArrayList<>();
        ValidMoves.add("HALLWAY5");
        ValidMoves.add("HALLWAY6");
        ValidMoves.add("HALLWAY11");

        MoveOptions moveOptions = new MoveOptions();

        moveOptions.setValidMoves(ValidMoves);
        moveOptions.mainMoveMenu();

    }
}
