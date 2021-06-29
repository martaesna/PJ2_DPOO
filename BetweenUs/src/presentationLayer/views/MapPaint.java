package presentationLayer.views;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.character.Player;
import businessLayer.entities.maps.Cell;
import businessLayer.entities.maps.Map;
import java.awt.*;
import java.util.LinkedList;
import javax.sound.midi.Soundbank;
import javax.swing.*;

public class MapPaint extends JPanel {
    private Color color;
    private final Map map;
    private final LinkedList<Character> players;
    private final Character userPlayer;
    private JPanel room;
    private JPanel jpMapa;
    private boolean userIsHere;
    private boolean revealMap;

    public MapPaint(LayoutManager layoutManager, Map map,LinkedList<Character> players, Character userPlayer, Boolean revealMap) {
        super(layoutManager);
        this.map = map;
        this.players = players;
        this.userPlayer = userPlayer;
        this.revealMap = revealMap;
    }

    /**
     * Printa el Mapa de joc
     * @return un Panell amb el mapa creat
     */
    public JPanel creaMapa() {
        jpMapa = new JPanel(new GridLayout(map.getWidth(),map.getHeight()));

        for (int i = 0; i < map.getHeight(); ++i) {
            for (int j = 0; j < map.getWidth(); ++j) {
                int pos = -1;
                //Aqui printem cada part del mapa corresponent amb la posicio de les celles.
                //primer determinar quin tipus de cella es recorren tots les celles per mirar si esta la corresponent
                for (int m = 0; m < map.getCells().size(); m++) {
                    if (map.getCells().get(m).getX() == j && map.getCells().get(m).getY() == i)
                        pos = m;
                }
                if (pos == -1) {
                    JPanel empty = new JPanel();
                    empty.setBackground(Color.black);
                    empty.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    jpMapa.add(empty);
                } else {
                    LinkedList<String> colors = getCellColors(userPlayer, players, map.getCells().get(pos));
                    LinkedList<Boolean> corpses = getCellCorpses(userPlayer, players, map.getCells().get(pos));

                    if (map.getCells().get(pos).getType().equals("room")) {
                        try {
                             color = (Color) Color.class.getField(map.getCells().get(pos).getColor()).get(null);

                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        //Pintar els personatges amb contorn
                        String roomName = map.getCells().get(pos).getRoomName();

                        room = new RoomPaint(color, roomName, colors, checkUserPosition(userPlayer.getCell(), map.getCells().get(pos)), revealMap, corpses);
                        room.setBorder(BorderFactory.createLineBorder(Color.WHITE));//pintem els borders
                        jpMapa.add(room);
                    }

                    if (map.getCells().get(pos).getType().equals("corridor")) {
                        JPanel corridor = new CorridorPaint(map.getCells().get(pos).getMobility(), map.getMapName(), colors, checkUserPosition(userPlayer.getCell(),map.getCells().get(pos)), revealMap, corpses);

                        corridor.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        jpMapa.add(corridor);
                    }
                }
            }
        }
        return jpMapa;
    }

    /**
     * Coloca els colors als diferents personatges
     * @param userPlayer El nostre persontatge
     * @param characters llista dels participants de la partida menys nosaltres
     * @param cell les celles del mapa
     * @return una llista amb els colors de cada personatges
     */
    public LinkedList<String> getCellColors(Character userPlayer, LinkedList<Character> characters, Cell cell) {
        LinkedList<String> colors = new LinkedList<>();
        if (userPlayer.getCell() == cell) {
            colors.add(userPlayer.getColor());
        }
        for (Character character: characters) {
            if (cell == character.getCell()) {
                colors.add(character.getColor());
            }
        }
        return colors;
    }

    public LinkedList<Boolean> getCellCorpses(Character userPlayer, LinkedList<Character> characters, Cell cell) {
        LinkedList<Boolean> corpses = new LinkedList<>();
        if (userPlayer.getCell() == cell) {
            corpses.add(false);
        }
        for (Character character: characters) {
            if (cell == character.getCell()) {
                if(character.isDead()) {
                    corpses.add(true);
                } else {
                    corpses.add(false);
                }
            }
        }
        return corpses;
    }

    public boolean checkUserPosition(Cell userCell, Cell cell) {
        return userCell == cell;
    }
}
