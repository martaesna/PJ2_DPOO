package businessLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.game.Time;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Map;
import businessLayer.entities.maps.Mobility;

import java.util.LinkedList;

public class NpcManager {
    private LinkedList<CrewMember> crewMembers;
    private LinkedList<Impostor> impostors;
    private int[] moveOptions;
    private MapManager mapManager;

    public NpcManager(LinkedList<CrewMember> crewMembers, LinkedList<Impostor> impostors, MapManager mapManager) {
        this.crewMembers = crewMembers;
        this.impostors = impostors;
        moveOptions = new int[4];
        this.mapManager = mapManager;
    }

    public LinkedList<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(LinkedList<CrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public LinkedList<Impostor> getImpostors() {
        return impostors;
    }

    public void setImpostors(LinkedList<Impostor> impostors) {
        this.impostors = impostors;
    }

    public int getNextCrewMemberRoom(CrewMember crewMember) {
        Mobility mobility = crewMember.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int optionsCounter = 0;
        int randomPosition = getCrewMemberRandomPosition(counter, crewMember.getPreviousRoom());
        return chooseRoom(optionsCounter, randomPosition);
    }

    public int getNextImpostorRoom(Impostor impostor) {
        Mobility mobility = impostor.getCell().getMobility();
        int counter = setMoveOptions(mobility);
        int optionsCounter = 0;
        int randomPosition = getRandomPosition(counter);
        return chooseRoom(optionsCounter, randomPosition);
    }

    public boolean checkLeft(Mobility mobility) {
        if (mobility.getLeft() == 0) {
            return false;
        }
        return true;
    }

    public boolean checkRight(Mobility mobility) {
        if (mobility.getRight() == 0) {
            return false;
        }
        return true;
    }

    public boolean checkUp(Mobility mobility) {
        if (mobility.getUp() == 0) {
            return false;
        }
        return true;
    }

    public boolean checkDown(Mobility mobility) {
        if (mobility.getDown() == 0) {
            return false;
        }
        return true;
    }

    public boolean checkImpostorsWinCondition(Character character, Impostor impostor) {
        if (eliminateUserPlayer(character, impostor) || crewMembers.size() == impostors.size()) {

            //S'ACABA LA PARTIDA

            return true;
        }
        return false;
    }

    public void movementRegister() {

    }

    public boolean checkVentilation(Cell cell) {
        if (!cell.getAdjacencies().isEmpty()) {
            return true;
        }
        return false;
    }

    public int chooseVentilationRoom(Cell cell) {
        return getRandomPosition(cell.getAdjacencies().size());
    }

    public int setMoveOptions(Mobility mobility) {
        int counter = 0;
        if (checkLeft(mobility)) {
            moveOptions[0] = 1;
            counter++;
        } else {
            moveOptions[0] = 0;
        }
        if (checkRight(mobility)) {
            moveOptions[1] = 1;
            counter++;
        } else {
            moveOptions[1] = 0;
        }
        if (checkUp(mobility)) {
            moveOptions[2] = 1;
            counter++;
        } else {
            moveOptions[2] = 0;
        }
        if (checkDown(mobility)) {
            moveOptions[3] = 1;
            counter++;
        } else {
            moveOptions[3] = 0;
        }
        return counter;
    }

    public int getRandomPosition(int counter) {
        int min = 1;
        return (int) (Math.random() * (counter - min + 1) + min);
    }

    public int getCrewMemberRandomPosition(int counter, int previousRoom) {
        int min = 1;
        int position = (int) (Math.random() * (counter - min + 1) + min);
        if (Math.abs(position-previousRoom) == 2) {
            return (int) (Math.random() * (counter - min + 1) + min);
        }
        return position;
    }

    public int chooseRoom(int optionsCounter, int randomPosition) {
        for (int i = 0; i < 4; i++) {
            if (moveOptions[i] == 1) {
                optionsCounter++;
                if (optionsCounter == randomPosition - 1) {
                    return i;
                }
            }
        }
        return 0;
    }

    public CrewMember eliminateCrewMember(Cell cell) {
        int numImpostors = 0;
        int numCrewMembers = 0;
        int crewMemberPosition = 0;

        if (cell.getNumPlayers() == 2) {
            for (int i = 0; i < impostors.size(); i++) {
                if (impostors.get(i).getCell() == cell) {
                    numImpostors++;
                }
            }
            for (int i = 0; i < crewMembers.size(); i++) {
                if (crewMembers.get(i).getCell() == cell) {
                    numCrewMembers++;
                    crewMemberPosition = i;
                }
            }
            if (numImpostors == 1 && numCrewMembers == 1) {
                crewMembers.remove(crewMemberPosition);
                crewMembers.get(crewMemberPosition).stopThread();
                return crewMembers.get(crewMemberPosition);
            }
        }
        return null;
    }

    public boolean eliminateUserPlayer(Character character, Impostor impostor) {
        if (impostor.getCell() == character.getCell() && impostor.getCell().getNumPlayers() == 2) {
            return true;
        }
        return false;
    }

    public boolean flipCoin() {
        if ((int) (Math.random() * (2) + 1) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void setCrewMembersManager(LinkedList<CrewMember> crewMembers) {
        for (int i = 0; i < crewMembers.size(); i++) {
            crewMembers.get(i).setNpcManager(this);
        }
    }

    public void setImpostorsManager(LinkedList<Impostor> impostors) {
        for (int i = 0; i < impostors.size(); i++) {
            impostors.get(i).setNpcManager(this);
        }
    }

    public void crewMemberMovement(CrewMember crewMember) {
        if (crewMember.getStartInterval() + crewMember.getInterval() == crewMember.getTime().getSeconds() && crewMember.movement()) {
            int nextRoom = getNextCrewMemberRoom(crewMember);
            crewMember.setPreviousRoom(nextRoom);
            int[] nextCell = crewMember.getNextCoordinates(nextRoom);
            crewMember.setCell(mapManager.getMap().getCellByCoordinates(nextCell));
        }
    }

    public void impostorMovement(Impostor impostor, NpcManager npcManager) {
        if (impostor.getStartInterval() + impostor.getInterval() == impostor.getTime().getSeconds() && impostor.movement()) {
            if (npcManager.checkVentilation(impostor.getCell())) {
                if (npcManager.flipCoin()) {
                    int nextRoom = npcManager.chooseVentilationRoom(impostor.getCell());
                    String roomName = impostor.getCell().getAdjacencies().get(nextRoom);
                    impostor.setCell(mapManager.getMap().getCellByName(roomName));
                }
            } else {
                int nextRoom = npcManager.getNextImpostorRoom(impostor);
                int[] nextCell = impostor.getNextCoordinates(nextRoom);
                impostor.setCell(mapManager.getMap().getCellByCoordinates(nextCell));
            }
        }
    }
}
