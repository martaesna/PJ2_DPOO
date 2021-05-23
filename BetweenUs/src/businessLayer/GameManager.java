package businessLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.json.JsonGame;
import businessLayer.entities.maps.Cell;
import persitanceLayer.GameDAO;
import persitanceLayer.SQLGameDAO;
import businessLayer.entities.game.Game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameManager {
    private final GameDAO gameDAO;
    private String gameName;

    public GameManager() {
        gameDAO = new SQLGameDAO();
    }

    /**
     * Mètode que crea un joc a la base de dades
     * @param gameName nom del joc
     * @param game classe joc amb la informació
     * @param userName nom de l'usuari
     */
    public void createGame(String gameName, Game game, String userName) {
        gameDAO.createGame(game, userName);
        this.gameName = game.getGameName();
    }

    /**
     * Mètode que crea un joc amb la configuració de l'anteriror
     * @param gameName nom del joc
     * @param userName nom de l'usuari
     */
    public void createConfiguredGame(String gameName, String userName) {
        if (gameDAO.gameExists(gameName+"(Copy)")) {
            JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc ja existeix", "Error Create Game", JOptionPane.ERROR_MESSAGE);
        } else {
            Game newGame = gameDAO.selectGame(gameName);
            newGame.setGameName(newGame.getGameName()+"(Copy)");
            gameDAO.createGame(newGame, userName);
            this.gameName = newGame.getGameName();
        }
    }

    /**
     * Mètode que elimina els jocs de l'usuari a la base de dades
     * @param userName nom de l'usuari
     */
    public void deleteUserGames(String userName) {gameDAO.deleteUserGames(userName);}

    /**
     * Mètode que selecciona un joc a la base de dades
     * @param gameName nom del joc
     * @return joc seleccionat per l'usuari
     */
    public Game selectGame(String gameName) {
        return gameDAO.selectGame(gameName);
    }

    /**
     * Mètode que elimina un joc de la base de dades
     * @param gameName nom de l'usuari
     */
    public void deleteGame(String gameName) {
        gameDAO.deleteGame(gameName);
    }

    /**
     * Mètode que mira si un joc existeix a la base de dades
     * @param gameName nom del joc
     * @return si existeix o no
     */
    public boolean checkGame(String gameName) {
        return gameDAO.gameExists(gameName);
    }

    /**
     * Mètode que comprova si la recreació del joc existeix
     * @param gameName nom del joc
     * @return si existeix o no
     */
    public boolean checkRecreateGame(String gameName) {
        return gameDAO.recreatedGameExists(gameName);
    }

    /**
     * Mètode que guarda la partida de l'usuari
     * @param game joc a guardar
     * @param characters llista de jugadors
     */
    public void saveGame(Game game, LinkedList<Character> characters) {
        //gameDAO.saveGame(userPlayer, impostors, crewMembers, gameName);
        JsonGame.jsonGame(game, characters);
    }

    /**
     * Mètode que carrega el joc de l'usuari
     * @param gameName nom del joc
     * @return joc carregat
     */
    public Game chargeGame(String gameName) {
        return gameDAO.selectGame(gameName);
    }

    /**
     * Mètode que crea els impostors de la partida
     * @param impostorsNum nombre d'impostors
     * @param userColor color de l'usuari
     * @param starterColor on ha de començar a triar el color
     * @param colors llista de colors
     * @param mapManager gestor del mapa
     * @return llista d'impostors creats
     */
    public LinkedList<Impostor> getImpostors(int impostorsNum, String userColor, int starterColor, ArrayList<String> colors, MapManager mapManager) {
        LinkedList<Impostor> impostors = new LinkedList<>();
        for (int i = 0; i < impostorsNum; i++) {
            Impostor impostor = new Impostor(getNextColor(userColor, starterColor, colors), mapManager);
            starterColor++;
            if (colors.get(starterColor).equals(userColor)) {
                starterColor++;
            }
            impostors.add(impostor);
        }
        return impostors;
    }

    /**
     * Mètode que crea els crewmates de la partida
     * @param crewMembersNum nombre de crewmembers
     * @param userColor color de l'usuari
     * @param starterColor on ha de començar a triar el color
     * @param colors llista de colors
     * @param mapManager gestor del mapa
     * @return llista de crewmates creats
     */
    public LinkedList<CrewMember> getCrewMembers(int crewMembersNum, String userColor, int starterColor, ArrayList<String> colors, MapManager mapManager) {
        LinkedList<CrewMember> crewMembers = new LinkedList<>();
        for (int i = 0; i < crewMembersNum; i++) {
            CrewMember crewMember = new CrewMember(getNextColor(userColor, starterColor, colors), mapManager);
            starterColor++;
            if (colors.get(starterColor).equals(userColor)) {
                starterColor++;
            }
            crewMembers.add(crewMember);
        }
        return crewMembers;
    }

    /**
     * Mètode que obté el pròxim color de la llista
     * @param userColor color de l'usuari
     * @param starterColor on ha de començar
     * @param colors llista de colors
     * @return pròxim color de la llista
     */
    public String getNextColor(String userColor, int starterColor, ArrayList<String> colors) {
        for (int i = starterColor; i < colors.size(); i++) {
            if (!colors.get(i).equals(userColor)) {
                return colors.get(i);
            }
        }
        return null;
    }

    /**
     * Mètode que inicia tant a l'usuari com als jugador la posició d'aquests
     * @param player usuari
     * @param players llista de jugadors
     * @param cells cel·les del mapa
     */
    public void setInitialCell(Character player, LinkedList<Character> players, LinkedList<Cell> cells) {
        Cell initialCell = getCoffeShopCell(cells);
        player.setCell(initialCell);
        for (Character character: players) {
            character.setCell(initialCell);
        }
    }

    /**
     * Mètode que busca la cel·la inicial
     * @param cells cel·les del mapa
     * @return cel·la inicial
     */
    public Cell getCoffeShopCell(LinkedList<Cell> cells) {
        for (Cell cell: cells) {
            if (cell.getRoomName().equals("cafeteria")) {
                return cell;
            }
        }
        return null;
    }

    /**
     * Mètode que troba la posició del color de l'usuari a l'array de colors
     * @param userColor color de l'usuari
     * @param colors array de colors
     * @return posició on es troba el color de l'usuari
     */
    public int getUserColorPosition(String userColor, ArrayList<String> colors) {
        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i).equals(userColor)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Mètode que inicia el Thread del jugador
     * @param character jugador a iniciar
     */
    public void startPlayers(Character character) {
        character.startThread();
    }

    public String getGameName() {
        return gameName;
    }
}
