package businessLayer;

import businessLayer.entities.character.CrewMember;
import businessLayer.entities.maps.Cell;

import java.util.LinkedList;

public class NpcManager {
    private LinkedList<Character> crewMembers;
    private LinkedList<Character> impostors;

    public NpcManager() {

    }

    public LinkedList<Character> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(LinkedList<Character> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public LinkedList<Character> getImpostors() {
        return impostors;
    }

    public void setImpostors(LinkedList<Character> impostors) {
        this.impostors = impostors;
    }

    public boolean checkMovement(){
        //PENALITZAR PELS TRIPULANTS

        return true;
    }

    public boolean checkMaxPlayers(){

        return true;
    }

    public void movementRegister(){


    }

    public boolean checkUserPlayer() {


        return true;
    }

    public boolean checkVentilation(){
        //IMPOSTOR


        return true;
    }

    public boolean checkAlonePlayer(){
        //IMPOSTOR


        return true;
    }
}
