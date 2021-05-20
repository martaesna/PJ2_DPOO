package presentationLayer.views;

import businessLayer.entities.maps.Map;
import businessLayer.entities.maps.Mobility;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.html.StyleSheet;

public class MapPaint extends JPanel {

    private int pos;
    private String tipus;
    private Color color;
    private Map map;
    //private Graphics g;

    public MapPaint(LayoutManager layoutManager, Map map) {
        super(layoutManager);
        this.map = map;
        this.pos = pos;

    }

    public JPanel CreaMapa() {
        JPanel JPmapa = new JPanel(new GridLayout(map.getWidth(),map.getHeight()));

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
                    JPmapa.add(empty);
                } else {
                    if (map.getCells().get(pos).getType().equals("room")) {
                        try {
                             color = (Color) Color.class.getField(map.getCells().get(pos).getColor()).get(null);

                        } catch (IllegalAccessException | NoSuchFieldException e) {
                            e.printStackTrace();
                        }
                        //StyleSheet s = new StyleSheet();
                        //color = s.stringToColor(map.getCells().get(pos).getColor());
                        JPanel room = new RoomPaint(color);
                        room.setBorder(BorderFactory.createLineBorder(Color.WHITE));;
                        JPmapa.add(room);
                    }

                    if (map.getCells().get(pos).getType().equals("corridor")) {
                        JPanel corridor = new CorridorPaint(map.getCells().get(pos).getMobility(),map.getMapName());
                        corridor.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        JPmapa.add(corridor);
                    }
                }


            }
        }
        return JPmapa;
    }

}


        /*
        g.setColor(Color.GREEN);
        g.drawOval(0, 0, 10, 10);*/
