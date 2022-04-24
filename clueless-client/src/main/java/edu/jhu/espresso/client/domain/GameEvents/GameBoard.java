package edu.jhu.espresso.client.domain.GameEvents;

import edu.jhu.espresso.client.domain.GamePieces.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

public class GameBoard
{
    private ArrayList<Location> locationList;
    private List<Room> roomList;
    private EnumMap<CharacterNames, Location> characterLocationMap;
    private ArrayList<Hallway> hallwayList;
    private ArrayList<HomeSquare> homeSquareList;

    //private ArrayList<Location> legalMovesList;

    public GameBoard(){

        // Loop over RoomName values and create rooms.
        this.roomList = new ArrayList<Room>();
        for (RoomNames rooms : RoomNames.values()){
            roomList.add(new Room(rooms.name()));
        }

        //Loop over Hallway Number and Create New Hallway, Add to List
        this.hallwayList = new ArrayList<Hallway>();
        Hallway H1 = new Hallway("H1");
        Hallway H2 = new Hallway("H2");
        Hallway H3 = new Hallway("H3");
        Hallway H4 = new Hallway("H4");
        Hallway H5 = new Hallway("H5");
        Hallway H6 = new Hallway("H6");
        Hallway H7 = new Hallway("H7");
        Hallway H8 = new Hallway("H8");
        Hallway H9 = new Hallway("H9");
        Hallway H10 = new Hallway("H10");
        Hallway H11 = new Hallway("H11");
        Hallway H12 = new Hallway("H12");
        Collections.addAll(this.hallwayList, H1, H2, H3, H4, H5, H6,H7, H8, H9, H10, H11, H12);

        /****
        for (int i =1; i < 13; i++ ){
            String newName = "H" + String.valueOf(i);
            this.hallwayList.add(new Hallway("H" + String.valueOf(i)));
        }****/

        //Create HomeSquare Objects and enumMap
        this.characterLocationMap = new EnumMap<>(CharacterNames.class);
        this.homeSquareList = new ArrayList<HomeSquare>();

        //Loop over character names, create new homesquare for each, and add to homesquare list and map.
        for (CharacterNames cn: CharacterNames.values()){
            HomeSquare thisHomeSquare = new HomeSquare(cn);
            this.homeSquareList.add(thisHomeSquare);
            this.characterLocationMap.put(cn, (Location) thisHomeSquare);
            }

        }

        public void moveCharacter(CharacterNames ch, Location newLoc){
            this.characterLocationMap.remove(ch);
            this.characterLocationMap.put(ch, newLoc);
        }

        public Location getCharacterLocation(CharacterNames ch){
            return this.characterLocationMap.get(ch);
        }

        //public Hallway getHallway(CharacterNames)

       /*** public ArrayList<Location> getLegalMoves(CharacterNames ch){
            Location startingLoc = getCharacterLocation(ch);
            ArrayList<String> possibleMoves = startingLoc.getPossibleDestinations();
            //TODO: Go through possible moves, find full hallways, and remove them from possible moves list.

        }
        ***/

    public ArrayList<Location> getLocationList()
    {
        return locationList;
    }

    public List<Room> getRoomList()
    {
        return roomList;
    }

    public EnumMap<CharacterNames, Location> getCharacterLocationMap()
    {
        return characterLocationMap;
    }

    public ArrayList<Hallway> getHallwayList()
    {
        return hallwayList;
    }

    public ArrayList<HomeSquare> getHomeSquareList()
    {
        return homeSquareList;
    }

    public void setLocationList(ArrayList<Location> locationList)
    {
        this.locationList = locationList;
    }

    public void setRoomList(List<Room> roomNamesList)
    {
        this.roomList = roomNamesList;
    }

    public void setCharacterLocationMap(EnumMap<CharacterNames, Location> characterLocationMap)
    {
        this.characterLocationMap = characterLocationMap;
    }

    public void setHallwayList(ArrayList<Hallway> hallwayList)
    {
        this.hallwayList = hallwayList;
    }

    public void setHomeSquareList(ArrayList<HomeSquare> homeSquareList)
    {
        this.homeSquareList = homeSquareList;
    }
}

