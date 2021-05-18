package businessLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.maps.Cell;
import persitanceLayer.GameDAO;
import persitanceLayer.SQLGameDAO;
import businessLayer.entities.game.Game;
import businessLayer.entities.game.Time;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameManager {
    private final GameDAO gameDAO;
    private final Time timer;

    public GameManager() {
        gameDAO = new SQLGameDAO();
        timer = new Time();
    }

    public Time getTimer() {
        return timer;
    }

    public void createGame(String gameName, Game game) {
        gameDAO.createGame(game);
    }

    public void createConfiguredGame(String gameName) {
        if (gameDAO.gameExists(gameName+"(Copy)")) {
            JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc ja existeix", "Error Create Game", JOptionPane.ERROR_MESSAGE);
        } else {
            Game newGame = gameDAO.selectGame(gameName);
            newGame.setGameName(newGame.getGameName()+"(Copy)");
            gameDAO.createGame(newGame);
        }
    }

    public void deleteGame(String gameName) {
        gameDAO.deleteGame(gameName);
    }

    public boolean checkGame(String gameName) {
        if (gameDAO.gameExists(gameName)) {
            return true;
        }
        return false;
    }

    public boolean checkRecreateGame(String gameName) {
        if (gameDAO.recreatedGameExists(gameName)) {
            return true;
        }
        return false;
    }

    public Game chargeGame(String gameName) {
        return gameDAO.selectGame(gameName);
    }

    public LinkedList<Impostor> getImpostors(int impostorsNum, String userColor, int starterColor, ArrayList<String> colors) {
        LinkedList<Impostor> impostors = new LinkedList<>();
        for (int i = 0; i < impostorsNum; i++) {
            Impostor impostor = new Impostor(getNextColor(userColor, starterColor, colors));
            impostors.add(impostor);
        }
        return impostors;
    }

    public LinkedList<CrewMember> getCrewMembers(int crewMembersNum, String userColor, int starterColor, ArrayList<String> colors) {
        LinkedList<CrewMember> crewMembers = new LinkedList<>();
        for (int i = 0; i < crewMembersNum; i++) {
            CrewMember crewMember = new CrewMember(getNextColor(userColor, starterColor, colors));
            crewMembers.add(crewMember);
        }
        return crewMembers;
    }

    public String getNextColor(String userColor, int starterColor, ArrayList<String> colors) {
        for (int i = starterColor; i < colors.size(); i++) {
            if (colors.get(i) != userColor) {
                starterColor++;
                return colors.get(i);
            } else {
                starterColor++;
            }
        }
        return null;
    }

    public void setInitialCell(Character player, LinkedList<CrewMember> crewMembers, LinkedList<Impostor> impostors, LinkedList<Cell> cells) {
        Cell initialCell = getCoffeShopCell(cells);

        player.setCell(initialCell);
        for (int i = 0; i < crewMembers.size(); i++) {
            crewMembers.get(i).setCell(initialCell);
        }
        for (int i = 0; i < impostors.size(); i++) {
            impostors.get(i).setCell(initialCell);
        }
    }

    public Cell getCoffeShopCell(LinkedList<Cell> cells) {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i).getRoomName() == "cafeteria") {
                return  cells.get(i);
            }
        }
        return null;
    }

}
