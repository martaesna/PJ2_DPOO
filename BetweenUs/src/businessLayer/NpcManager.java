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

    /**
     * Mètode que elimina un crewmember per l'impostor
     * @param mapManager gestor del mapa
     * @param player usuari
     */
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

    /**
     * Mètode que comprova si l'impostor ha guanyat
     * @return booleà amb si ha guanyat
     */
    public boolean checkImpostorsWinCondition() {
        int crewMembersAlive = getNumCrewMembers();
        int numImpostors = getNumImpostors();

        return crewMembersAlive + 1 <= numImpostors;
    }

    /**
     * Mètode que elimina l'usuari si l'impostor el mapa
     * @param userPlayer usuari
     * @return si l'ha matat o no
     */
    public boolean eliminateUserPlayer(Character userPlayer) {
        if (getNpcNumCell(userPlayer.getCell()) == 1) {
            int npcListPosition = getNpcPosition(userPlayer.getCell());
            return players.get(npcListPosition) instanceof Impostor;
        }
        return false;
    }

    /**
     * Mètode que para els threads de tots els jugadors
     */
    public void interruptThreads(){
        for (Character character: players) {
            character.stopThread();
        }
    }

    /**
     * Mètode que retorna el nombre de crewmembers
     * @return nombre de crewmemebers
     */
    public int getNumCrewMembers() {
        int numCrewMembers = 0;
        for (Character character: players) {
            if (character instanceof CrewMember) {
                numCrewMembers++;
            }
        }
        return numCrewMembers;
    }

    /**
     * Mètode que retorna el nombre d'impostors
     * @return nombre d'impostors
     */
    public int getNumImpostors() {
        int numImpostors = 0;
        for (Character character: players) {
            if (character instanceof Impostor) {
                numImpostors++;
            }
        }
        return numImpostors;
    }

    /**
     * Mètode que retorna el nombre de npcs que hi ha a la cel·la de l'usuari
     * @param cell cel·la a comprovar
     * @return nombre de npcs que ha trobat
     */
    public int getNpcNumCell(Cell cell) {
        int numNpc = 0;
        for (Character character: players) {
            if (character.getCell() == cell) {
                numNpc++;
            }
        }
        return numNpc;
    }

    public int getNumCrewMembersCell(Cell cell) {
        int crewMembers = 0;
        for (Character character: players) {
            if (character.getCell() == cell && character instanceof CrewMember) {
                crewMembers++;
            }
        }
        return crewMembers;
    }

    /**
     * Mètode que troba la posició a la llista de jugadors del npc que hi ha a la cel·la de l'impostor
     * @param cell cel·la on es troba
     * @return posició a la llista de jugadors del npc que hi ha a la cel·la
     */
    public int getNpcPosition(Cell cell) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCell() == cell) {
                return i;
            }
        }
        return 0;
    }
}
