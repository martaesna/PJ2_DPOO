package businessLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.maps.Cell;

import java.awt.*;
import java.util.LinkedList;

/**
 * La classe 'NpcManager' ens permet gestionar la simulació dels personatges
 * que no controla l'usuari
 *
 * Els mètodes implementats controlen les diferents condicions de victòria i derrota,
 * l'eliminació de tripulants, la interrupció dels diferents fils d'execució,
 * l'obtenció de dades dels personatges no jugadors...
 */
public class NpcManager {
    // Attributes
    private final LinkedList<Character> players;

    // Parametrized constructor
    public NpcManager(LinkedList<Character> players) {
        this.players = players;
    }

    public LinkedList<Character> getPlayers() {
        return players;
    }

    /**
     * Mètode que elimina un crewmember per l'impostor
     * @param mapManager gestor del mapa
     * @param impostor impostor
     */
    public synchronized boolean eliminateCrewMember(MapManager mapManager, Impostor impostor) {
        if (getNpcNumCell(impostor.getCell()) == 2) {
            int crewMemberPosition = getCrewMemberPosition(impostor.getCell());
            if (!players.get(crewMemberPosition).isDead() && crewMemberPosition != -1) {
                int cellPosition = getCellPosition(mapManager, impostor.getCell());
                mapManager.getMap().getCells().get(cellPosition).setNumCorpses(mapManager.getMap().getCells().get(cellPosition).getNumCorpses() + 1);
                players.get(crewMemberPosition).setDead(true);
                players.get(crewMemberPosition).stopThread();

                impostor.getPeriodTime().resetCounter();

                return true;
            }
        }
        return false;
       /* if (isImpostor && isCrewMember) {
            mapManager.getMap().getCells().get(numCell).setNumCorpses(mapManager.getMap().getCells().get(numCell).getNumCorpses() + 1);
            //LinkedList<String> corpColors = mapManager.getMap().getCells().get(numCell).getCorpColor();
            //corpColors.add(players.get(crewMemberPosition).getColor());
            //mapManager.getMap().getCells().get(numCell).setCorpColor(corpColors);
            //players.get(crewMemberPosition).stopThread();
            //players.remove(crewMemberPosition);
        }*/
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
            if (players.get(npcListPosition) instanceof Impostor) {
                Impostor impostor = (Impostor) players.get(npcListPosition);
                if (impostor.checkKillingPeriod(impostor)) {
                    return true;
                }
                return impostor.isCanKill();
            }
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
                if (!character.isDead()) {
                    crewMembers++;
                }
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

    /**
     * Mètode que troba la posició a la llista de jugadors del tripulant que hi ha a la cel·la de l'impostor
     * @param cell cel·la on es troba
     * @return posició a la llista de jugadors del tripulant que hi ha a la cel·la
     */
    public int getCrewMemberPosition(Cell cell) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getCell() == cell && players.get(i) instanceof CrewMember) {
                return i;
            }
        }
        return -1;
    }

    public int getCellPosition (MapManager mapManager, Cell cell) {
        for (int i = 0; i < mapManager.getMap().getCells().size(); i++) {
            if (mapManager.getMap().getCells().get(i) == cell) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkLogPosition (Character character) {
        if (!character.getCell().getColor().equals(Color.BLACK.toString()) &&
                !character.getCell().getRoomName().equals("security")) {
            return  true;
        }
        return false;
    }
}
