package presentationLayer.views;

import businessLayer.entities.maps.Mobility;
import javax.swing.*;
import java.awt.*;

public class CorridorPaint extends JPanel{
    private final Mobility mov;
    private final String map;

    public CorridorPaint(Mobility mov, String map){
        this.mov = mov;
        this.map = map;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,400,400);

        int alturaX = (getHeight()/5)*3;
        int ampladaX = (getWidth()/5)*3;
        int alturaY = (getWidth()/5)*3;
        int ampladaY = (getHeight()/5)*3;

        // sabre posicio o tamant dels quadrats.
        if(mov.getUp() == 1){
            g.setColor(Color.WHITE);
            g.fillRect((getWidth()/2) - ampladaY/2,0, ampladaY, ampladaY);
        }
        if(mov.getDown() == 1){
            g.setColor(Color.WHITE);

            g.fillRect((getWidth()/2) - ampladaY/2,(getHeight()/5)*2, ampladaY,alturaY);
        }
        if(mov.getLeft() == 1){
            g.setColor(Color.WHITE);
            g.fillRect(0,getHeight()/5,(getWidth()/2) - ampladaY/2 + ampladaY, alturaX);
        }
        if(mov.getRight() == 1){
            g.setColor(Color.WHITE);
            g.fillRect((getWidth()/2) - ampladaY/2,getHeight()/5,ampladaX + alturaX/2, alturaX);
        }
       }

}
