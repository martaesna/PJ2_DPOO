package presentationLayer.views;

import businessLayer.entities.character.Character;
import presentationLayer.controllers.NewGameController;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class RoomPaint extends JPanel {
    private final Color color;
    private Color colori;
    private final String roomName;
    private final NewGameController ngc = new NewGameController();
    private final LinkedList<String> colors;

    public RoomPaint(Color color, String roomName, LinkedList<String> colors){
        this.color = color;
        this.roomName = roomName;
        this.colors = colors;
    }

    @Override
    public void paintComponent(Graphics g) {

        System.out.println("Entra room");

        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0,0,getWidth(),getHeight());

        int separadorX = getWidth() / 4;
        int separadorY = getHeight() / 3;

        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i).equals("PURPLE") || colors.get(i).equals("BROWN") || colors.get(i).equals("CYAN") || colors.get(i).equals("LIME")) {
                int[] components = ngc.getColorComponents(colors.get(i));
                colori = new Color(components[0], components[1], components[2]);
            } else {
                try {
                    colori = (Color) Color.class.getField(colors.get(i)).get(null);
                } catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
            g.setColor(Color.BLACK);
            g.fillOval(separadorX - 1, separadorY - 1, 17, 17);

            g.setColor(colori);
            g.fillOval(separadorX, separadorY, 15, 15);
            separadorX += 20;

            if (separadorX + 30 > getWidth()) {
                separadorY = separadorY + 20;
                separadorX = getWidth() / 4;
            }
        }
    }
}
