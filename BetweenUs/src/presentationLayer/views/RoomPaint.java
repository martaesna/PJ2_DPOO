package presentationLayer.views;
import businessLayer.entities.maps.Map;
import javax.swing.*;
import java.awt.*;

public class RoomPaint extends JPanel {
    private Color color;

    public RoomPaint( Color color){
        this.color = color;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillRect(0,0,getWidth(),getHeight());



    }


}
