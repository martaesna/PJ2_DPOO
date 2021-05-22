package presentationLayer.views;

import businessLayer.entities.character.Player;
import businessLayer.entities.maps.Mobility;
import presentationLayer.controllers.NewGameController;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class CorridorPaint extends JPanel{
    private final Mobility mov;
    private final String map;
    private final LinkedList<String> colors;
    private final Boolean userIsHere;
    private Color colori;
    private final NewGameController ngc = new NewGameController();

    public CorridorPaint(Mobility mov, String map, LinkedList<String> colors, Boolean userIsHere){
        this.mov = mov;
        this.map = map;
        this.colors = colors;
        this.userIsHere = userIsHere;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,400,400);

        int alturaX = (getHeight()/5)*3;
        int ampladaX = (getWidth()/5)*3;
        int alturaY = (getWidth()/5)*3;
        int ampladaY = (getHeight()/5)*3;

        // sabre posicio o tamant dels quadrats.
        if(mov.getUp() == 1){
            if (userIsHere) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.WHITE.darker().darker().darker().darker());
            }
            g.fillRect((getWidth()/2) - ampladaY/2,0, ampladaY, ampladaY);
        }
        if(mov.getDown() == 1){
            if (userIsHere) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.WHITE.darker().darker().darker().darker());
            }
            g.fillRect((getWidth()/2) - ampladaY/2,(getHeight()/5)*2, ampladaY,alturaY);
        }
        if(mov.getLeft() == 1){
            if (userIsHere) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.WHITE.darker().darker().darker().darker());
            }
            g.fillRect(0,getHeight()/5,(getWidth()/2) - ampladaY/2 + ampladaY, alturaX);
        }
        if(mov.getRight() == 1){
            if (userIsHere) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.WHITE.darker().darker().darker().darker());
            }
            g.fillRect((getWidth()/2) - ampladaY/2,getHeight()/5,ampladaX + alturaX/2, alturaX);
        }

        int separadorX = getWidth() / 4;
        int separadorY = getHeight() / 3;

        System.out.println("Corridor colors: " + colors.size());

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

            if(userIsHere) {
                g.setColor(colori);
            } else {
                g.setColor(colori.darker().darker().darker().darker());
            }
            g.fillOval(separadorX, separadorY, 15, 15);
            separadorX += 20;

            if (separadorX + 30 > getWidth()) {
                separadorY = separadorY + 20;
                separadorX = getWidth() / 4;
            }
        }
    }
}
