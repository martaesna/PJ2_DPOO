package presentationLayer.views;

import businessLayer.entities.character.Character;
import businessLayer.entities.maps.Map;
import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;

public class MapPaint extends JPanel {
    private Color color;
    private final Map map;
    private final LinkedList<Character> players;
    private final Character userPlayer;

    public MapPaint(LayoutManager layoutManager, Map map,LinkedList<Character> players, Character userPlayer) {
        super(layoutManager);
        this.map = map;
        this.players = players;
        this.userPlayer = userPlayer;
    }

    public JPanel creaMapa() {
        JPanel jpMapa = new JPanel(new GridLayout(map.getWidth(),map.getHeight()));

        for (int i = 0; i < map.getHeight(); ++i) {
            for (int j = 0; j < map.getWidth(); ++j) {
                int pos = -1;
                //Aqui printem cada part del mapa corresponent amb la posicio de les celles.
                //primer determinar quin tipus de cella es recorren tots les celles per mirar si esta la corresponent
                for (int m = 0; m < map.getCells().size(); m++) {
                    if (map.getCells().get(m).getX() == j && map.getCells().get(m).getY() == i) pos = m;
                }
                if (pos == -1) {
                    JPanel empty = new JPanel();
                    empty.setBackground(Color.black);
                    empty.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    jpMapa.add(empty);
                } else {
                    if (map.getCells().get(pos).getType().equals("room")) {
                        try {
                             color = (Color) Color.class.getField(map.getCells().get(pos).getColor()).get(null);

                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        //Pintar els personatges amb contorn
                        String roomName = map.getCells().get(pos).getRoomName();
                        JPanel room = new RoomPaint(color, roomName, players,userPlayer);
                        room.setBorder(BorderFactory.createLineBorder(Color.WHITE));//pintem els borders
                        jpMapa.add(room);
                    }

                    if (map.getCells().get(pos).getType().equals("corridor")) {
                        JPanel corridor = new CorridorPaint(map.getCells().get(pos).getMobility(), map.getMapName());
                        corridor.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        jpMapa.add(corridor);
                    }
                }
            }
        }
        return jpMapa;
    }
}
