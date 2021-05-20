package presentationLayer.views;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.maps.Map;
import businessLayer.entities.maps.Mobility;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.text.html.StyleSheet;

public class MapPaint extends JPanel {

    private int pos;
    private String tipus;
    private Color color;
    private Map map;
    private String roomName;
    private LinkedList<CrewMember> crewMembers;
    private LinkedList<Impostor> impostors;
    private Character userPlayer;
    //private Graphics g;

    public MapPaint(LayoutManager layoutManager, Map map,LinkedList<CrewMember> crewMembers,LinkedList<Impostor> impostors, Character userPlayer) {
        super(layoutManager);
        this.map = map;
        this.crewMembers = crewMembers;
        this.impostors = impostors;
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
                    empty.setBorder(BorderFactory.createLineBorder(Color.WHITE));;
                    jpMapa.add(empty);
                } else {
                    if (map.getCells().get(pos).getType().equals("room")) {
                        try {
                             color = (Color) Color.class.getField(map.getCells().get(pos).getColor()).get(null);

                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        //Pintar els personatges amb contorn
                        roomName = map.getCells().get(pos).getRoomName();
                        JPanel room = new RoomPaint(color,roomName,crewMembers,impostors,userPlayer);
                        room.setBorder(BorderFactory.createLineBorder(Color.WHITE));//pintem els borders
                        jpMapa.add(room);
                    }

                    if (map.getCells().get(pos).getType().equals("corridor")) {
                        JPanel corridor = new CorridorPaint(map.getCells().get(pos).getMobility(),map.getMapName());
                        corridor.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        jpMapa.add(corridor);
                    }
                }


            }
        }
        return jpMapa;
    }


}


        /*
        g.setColor(Color.GREEN);
        g.drawOval(0, 0, 10, 10);*/
