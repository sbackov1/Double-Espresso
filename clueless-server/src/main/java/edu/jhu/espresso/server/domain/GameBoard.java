package edu.jhu.espresso.server.domain;

import java.util.*;

public class GameBoard {

    private ArrayList<Location> locationList;
    private ArrayList<Room> roomList;
    private Map<CharacterNames, Location> characterLocationMap;
    private ArrayList<Hallway> hallwayList;
    //private ArrayList<Location> legalMovesList;

    public GameBoard(){

        // Loop over RoomName values and create rooms.
        this.roomList = new ArrayList<Room>();
        for (RoomNames rooms : RoomNames.values()){
            roomList.add(new Room(rooms.name()));
        }

        //Loop over Hallway Number and Create New Hallway, Add to List
        ArrayList<Hallway> hallwayList = new ArrayList<Hallway>();
        for (int i =1; i < 13; i++ ){
            hallwayList.add(new Hallway("H" + String.valueOf(i)));
        }

        //Create HomeSquare Objects
        ArrayList<HomeSquare> homeSquareList = new ArrayList<HomeSquare>();
        for (CharacterNames cn: CharacterNames.values()){
            homeSquareList.add(new HomeSquare(cn));
        }

    }

}
