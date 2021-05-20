package persitanceLayer;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.game.Game;

import java.util.LinkedList;

public interface GameDAO {
    void createGame(Game game);

    Game selectGame(String gameName);
    Player getUserPlayer(String gameName);
    LinkedList<Impostor> getImpostors(String gameName);
    LinkedList<CrewMember> getCrewMembers(String gameName);

    void deleteGame(String gameName);
    void deleteUserPlayer(String gameName);
    void deleteCrewMembers(String gameName);
    void deleteImpostors(String gameName);

    boolean gameExists(String gameName);
    boolean recreatedGameExists(String gameName);
    boolean checkCreator(String gameName, String username);

    void saveGame(Character userPlayer, LinkedList<Impostor> impostors, LinkedList<CrewMember> crewMembers, String gameName);
    void savePlayerCharacter(Character userPlayer, String gameName);
    void saveImpostors(LinkedList<Impostor> impostors, String gameName);
    void saveCrewMembers(LinkedList<CrewMember> crewMembers, String gameName);

}
