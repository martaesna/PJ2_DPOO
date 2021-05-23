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

    /*
    public boolean checkImpostorsWinCondition(Player player, Impostor impostor) {
        if (eliminateUserPlayer(player, impostor) || crewMembers.size() == impostors.size()) {
            //S'ACABA LA PARTIDA
            return true;
        }
        return false;
    }

*/

    public boolean eliminateUserPlayer(Character character, Impostor impostor) {
        return impostor.getCell() == character.getCell() && getCellPlayers(impostor.getCell()) == 2;
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
}
