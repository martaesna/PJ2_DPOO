package businessLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.maps.Cell;
import java.util.LinkedList;

public class NpcManager {
    private final LinkedList<CrewMember> crewMembers;
    private final LinkedList<Impostor> impostors;

    public NpcManager(LinkedList<CrewMember> crewMembers, LinkedList<Impostor> impostors) {
        this.crewMembers = crewMembers;
        this.impostors = impostors;
    }

    public LinkedList<CrewMember> getCrewMembers() {
        return crewMembers;
    }
    public LinkedList<Impostor> getImpostors() {
        return impostors;
    }

    public boolean checkImpostorsWinCondition(Player player, Impostor impostor) {
        if (eliminateUserPlayer(player, impostor) || crewMembers.size() == impostors.size()) {
            //S'ACABA LA PARTIDA
            return true;
        }
        return false;
    }

    public CrewMember eliminateCrewMember(Cell cell) {
        int numImpostors = 0;
        int numCrewMembers = 0;
        int crewMemberPosition = 0;

        if (cell.getNumPlayers() == 2) {
            for (Impostor impostor: impostors) {
                if (impostor.getCell() == cell) {
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
        return impostor.getCell() == character.getCell() && impostor.getCell().getNumPlayers() == 2;
    }
}
