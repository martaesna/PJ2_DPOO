package businessLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.maps.Cell;
import java.util.LinkedList;

public class NpcManager {
    private final LinkedList<Character> players;

    public NpcManager(LinkedList<Character> players) {
        this.players = players;
    }

    public LinkedList<Character> getPlayers() {
        return players;
    }

    public void eliminateCrewMember(MapManager mapManager, Player player) {
        boolean isImpostor = false;
        boolean isCrewMember = false;
        int crewMemberPosition = 0;
        int numCell = 0;
        for (int i = 0; i < mapManager.getMap().getCells().size(); i++) {
            if (getNpcNumCell(mapManager.getMap().getCells().get(i)) == 2 && player.getCell() != mapManager.getMap().getCells().get(i)) {
                isImpostor = false;
                isCrewMember = false;
                for (int j = 0; j < players.size(); j++) {
                    if (players.get(j).getCell() == mapManager.getMap().getCells().get(i)) {
                        if (players.get(j) instanceof CrewMember) {
                            isCrewMember = true;
                            crewMemberPosition = j;
                        } else {
                            isImpostor = true;
                            numCell = i;
                        }
                    }
                }
            }
        }
        if (isImpostor && isCrewMember) {
            mapManager.getMap().getCells().get(numCell).setNumCorpses(mapManager.getMap().getCells().get(numCell).getNumCorpses() + 1);
            //LinkedList<String> corpColors = mapManager.getMap().getCells().get(numCell).getCorpColor();
            //corpColors.add(players.get(crewMemberPosition).getColor());
            //mapManager.getMap().getCells().get(numCell).setCorpColor(corpColors);
            players.get(crewMemberPosition).stopThread();
            players.remove(crewMemberPosition);
        }
    }

    public boolean checkImpostorsWinCondition() {
        int crewMembersAlive = getNumCrewMembers();
        int numImpostors = getNumImpostors();

        if (crewMembersAlive+1 <= numImpostors) {
            return true;
        }
        return false;
    }

    public boolean eliminateUserPlayer(Character userPlayer) {
        if (getNpcNumCell(userPlayer.getCell()) == 1) {
            int npcListPosition = getNpcPosition(userPlayer.getCell());
            if (players.get(npcListPosition) instanceof Impostor) {
                return true;
            }
        }
        return false;
    }

    public int getCellPlayers(Cell cell) {
        int numPlayers = 0;
        for (Character player: players) {
            if (player.getCell() == cell) {
                numPlayers++;
            }
        }
        return numPlayers;
    }

    public void interruptThreads(){
        for (Character character: players) {
            character.stopThread();
        }
    }

    public int getNumCrewMembers() {
        int numCrewMembers = 0;
        for (Character character: players) {
            if (character instanceof CrewMember) {
                numCrewMembers++;
            }
        }
        return numCrewMembers;
    }

    public int getNumImpostors() {
        int numImpostors = 0;
        for (Character character: players) {
            if (character instanceof Impostor) {
                numImpostors++;
            }
        }
        return numImpostors;
    }

    public int getNpcNumCell(Cell cell) {
        int numNpc = 0;
        for (Character character: players) {
            if (character.getCell() == cell) {
                numNpc++;
            }
        }
        return numNpc;
    }

    public int getNpcPosition(Cell cell) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCell() == cell) {
                return i;
            }
        }
        return 0;
    }
}
