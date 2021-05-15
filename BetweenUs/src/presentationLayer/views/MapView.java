package presentationLayer.views;
import businessLayer.entities.maps.*;
import presentationLayer.views.customComponents.Time;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.StyleSheet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class MapView extends JFrame {

    private JButton returnButton;
    private JButton configButton;
    private JButton mapButton;
    private JButton UP;
    private JButton DOWN;
    private JButton RIGHT;
    private JButton LEFT;
    private Object[][] data;
    private Time time;


    public MapView(Map map)/*throws IOException*/ {
        setTitle("Map");
        setSize(1080, 600);//600

        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //pintem el panell final
        JPanel background = new JPanel();
        //background.setBounds(0,0,1080,600);
        background.setLayout(new BorderLayout());
        background.setBackground(Color.BLACK);

        //Fem un panel a la part de dalt.
        JPanel JpNorth = new JPanel(new BorderLayout());
        JpNorth.setOpaque(false);

        JPanel JpNorthEast = new JPanel(new BorderLayout());
        JpNorthEast.setOpaque(false);

        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/config.png")));
            Image scaled = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            configButton = new JButton(backgroundImage);
            configButton.setOpaque(false);
            configButton.setContentAreaFilled(false);
            configButton.setBorderPainted(false);
            configButton.setActionCommand("Config");

            JpNorth.add(configButton, BorderLayout.WEST);



            BufferedImage image2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/Home.png")));
            Image scaled2 = image2.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage2 = new ImageIcon(scaled2);
            returnButton = new JButton(backgroundImage2);
            returnButton.setOpaque(false);
            returnButton.setContentAreaFilled(false);
            returnButton.setBorderPainted(false);
            returnButton.setActionCommand("Return");

            JpNorth.add(returnButton, BorderLayout.EAST);


            BufferedImage image3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/map.png")));
            Image scaled3 = image3.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage3 = new ImageIcon(scaled3);
            mapButton = new JButton(backgroundImage3);
            mapButton.setOpaque(false);
            mapButton.setContentAreaFilled(false);
            mapButton.setBorderPainted(false);
            mapButton.setActionCommand("Return");

            JpNorthEast.add(mapButton, BorderLayout.EAST);
            JpNorth.add(JpNorthEast,BorderLayout.CENTER);


            //-----------------------------------------------------------------------
            BufferedImage image4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/images/background.jpg")));
            Image scaled4 = image4.getScaledInstance(278, 50, Image.SCALE_DEFAULT);
            ImageIcon myLabel = new ImageIcon(scaled4);
             data =
                     new Object[][]{
                             {myLabel, myLabel, myLabel},
                             {myLabel, myLabel, myLabel},
                             {myLabel, myLabel, myLabel},
                     };
             //-----------------------------------------------------------------------------

        } catch (IOException e) {
            e.printStackTrace();
        }

        background.add(JpNorth, BorderLayout.NORTH);

       JPanel JpCenter = new JPanel(new GridLayout(map.getWidth(), map.getHeight()));

        for(int i = 0; i < map.getHeight(); ++i) {
            for (int j = 0; j < map.getWidth(); ++j) {
                int pos = -1;
                //Aqui printem cada part del mapa corresponent amb la posicio de les celles.
                //primer determinar quin tipus de cella es recorren tots les celles per mirar si esta la corresponent
                for (int m = 0; m < map.getCells().size(); m++) {
                    if (map.getCells().get(m).getX() == j && map.getCells().get(m).getY() == i) pos = m;
                }

                //MIREM SI ES MAPA O CUADRAT NEGRE
                if (pos == -1) {
                    //aixo es un cuadrat negre
                    JPanel empty = new JPanel();
                    empty.setBackground(Color.BLACK);
                    JpCenter.add(empty);

                } else {
                    //MIREM SI ES SALA O PASSADIS


                    if (map.getCells().get(pos).getType().equals("room")) {
                        //creem un panell del color de la sala
                        JPanel room = new JPanel();
                        String color = map.getCells().get(pos).getColor();

                        StyleSheet s = new StyleSheet();
                        Color clr = s.stringToColor(map.getCells().get(pos).getColor());
                        room.setBackground(clr); //fer que el color sigui el que toca
                        JpCenter.add(room);
                    } else {

                        //si estem aqui som un pasadis, printem el mig del cuadrat de blanc
                        JPanel corridor = new JPanel(new BorderLayout());
                        JPanel center = new JPanel();
                        center.setBackground(Color.white);
                        corridor.setBackground(Color.BLACK);
                        corridor.add(center, BorderLayout.CENTER);
                        JpCenter.add(corridor);
                        //comprovem cap on podem anar per printar cap els costats on podem anar.

                        //MIREM SI POT ANAR CAP ADALT
                        if (map.getCells().get(pos).getMobility().getUp() == 1) {

                            JPanel up = new JPanel(new BorderLayout());
                            JPanel Cu = new JPanel();
                            Cu.setBackground(Color.white);
                            up.add(Cu, BorderLayout.CENTER);
                            corridor.add(up, BorderLayout.NORTH);
                        } else { //SINO PINTEM LES PARETS GRISES
                            JPanel b = new JPanel();
                            b.setBackground(Color.DARK_GRAY);
                            b.setBorder(new LineBorder(Color.DARK_GRAY,10,true));
                            corridor.add(b, BorderLayout.NORTH);
                        }

                        //MIREM SI POT ANAR CAP ABAIX
                        if (map.getCells().get(pos).getMobility().getDown() == 1) {

                            JPanel down = new JPanel(new BorderLayout());
                            JPanel du = new JPanel();
                            du.setBackground(Color.white);

                            down.add(du, BorderLayout.CENTER);
                            corridor.add(down, BorderLayout.SOUTH);
                        }else{
                            JPanel b1 = new JPanel();
                            b1.setBackground(Color.DARK_GRAY);
                            b1.setBorder(new LineBorder(Color.DARK_GRAY,10,true));
                            corridor.add(b1, BorderLayout.SOUTH);
                        }

                        //MIREM SI POT ANAR CAP A LA DRETA
                        if (map.getCells().get(pos).getMobility().getRight() == 1) {

                            JPanel right = new JPanel(new BorderLayout());
                            JPanel ru = new JPanel();
                            ru.setBackground(Color.white);
                            right.add(ru, BorderLayout.CENTER);
                            corridor.add(right, BorderLayout.EAST);
                        }else{
                            JPanel b2 = new JPanel();
                            b2.setBackground(Color.DARK_GRAY);
                            b2.setBorder(new LineBorder(Color.DARK_GRAY,15,true));
                            corridor.add(b2, BorderLayout.EAST);
                        }

                        //MIREM SI POT ANAR CAP A L ESQUERRA
                        if (map.getCells().get(pos).getMobility().getLeft() == 1) {

                            JPanel left = new JPanel(new BorderLayout());
                            JPanel lu = new JPanel();
                            lu.setBackground(Color.white);
                            left.add(lu, BorderLayout.CENTER);
                            left.setBackground(Color.black);
                            corridor.add(left, BorderLayout.WEST);
                        }else{
                            JPanel b3 = new JPanel();
                            b3.setBackground(Color.DARK_GRAY);
                            b3.setBorder(new LineBorder(Color.DARK_GRAY,15,true));
                            corridor.add(b3, BorderLayout.WEST);
                        }

                    }
                }
            }
        }



        //creamos un border layout dentro del EAST y ponemos los botones en cada lugar
        //background.add(control,BorderLayout.EAST); //aqui hemos de poner los botones
        JPanel controles = new JPanel(new BorderLayout());
        //per poder colocar els botons a la part de dalt
        JPanel AuxControles = new JPanel(new BorderLayout());
        //coloquem els botons de adalt y abaix
        JPanel AuxControlUPDOWN = new JPanel(new GridLayout(2,1));
        JPanel AuxControlLEFT = new JPanel(new BorderLayout());
        JPanel AuxControlRIGHT = new JPanel(new BorderLayout());

        UP = new JButton();
        UP.setActionCommand("up");
        UP.setText("^");

        DOWN = new JButton();
        DOWN.setActionCommand("down");
        DOWN.setText("v");

        RIGHT = new JButton();
        RIGHT.setActionCommand("right");
        RIGHT.setText(">");

        LEFT = new JButton();
        LEFT.setActionCommand("left");
        LEFT.setText("<");

        AuxControlUPDOWN.add(UP);
        AuxControlUPDOWN.add(DOWN);
        AuxControles.add(AuxControlUPDOWN,BorderLayout.CENTER);

        AuxControlLEFT.add(LEFT,BorderLayout.SOUTH);
        AuxControlRIGHT.add(RIGHT,BorderLayout.SOUTH);
        AuxControles.add(AuxControlRIGHT,BorderLayout.EAST);
        AuxControles.add(AuxControlLEFT,BorderLayout.WEST);

        AuxControlRIGHT.setOpaque(false);
        AuxControlLEFT.setOpaque(false);
        AuxControlUPDOWN.setOpaque(false);
        AuxControles.setBackground(Color.GRAY);
        controles.setOpaque(false);

        //Coloquem els botons final al panell

        controles.add(AuxControles,BorderLayout.NORTH);


        background.add(controles,BorderLayout.EAST);




        //JPanel bajo = new JPanel();
        //bajo.setBackground(Color.RED);
        //JPanel izquierda = new JPanel();
        //izquierda.setBackground(Color.RED);
       // background.add(bajo,BorderLayout.SOUTH);
        //background.add(izquierda,BorderLayout.WEST);

       //----------------------------------------------------------------------------------------------------------------


        /*
        JPanel background1 = new JLabel();
        background.setBackground(Color.BLACK);
        background.setBounds(0, 0, 1080, 600);
        background.setOpaque(true);*/

        //PANEL PARA VER LA TABLA

        JPanel gui = new JPanel(new BorderLayout());

        gui.setBackground(new Color(125,125,125,99));//LE PONEMOS EL COLOR

        String[] header = {"Unknown", "Suspicious", "Innocent"}; //CREAMOS LA ETIQUETAS DEL TITULO

        //gui.setLayout(null);
        //gui.setBounds(100,300,875,200);

        DefaultTableModel model = new DefaultTableModel(data, header);
        JTable table = new JTable(model){

            public Class getColumnClass(int column)
            {
                return ImageIcon.class;
            }
        };

        table.setRowHeight(50);
        table.setPreferredScrollableViewportSize(table.getPreferredSize()); //ficar que n fiqui mes de 3/4



        JScrollPane tableScroll = new JScrollPane(table);
        //tableScroll.setSize(600,160);
        //table.setBounds(0,0,835,160);

        tableScroll.setOpaque(false);
        tableScroll.setBackground(Color.black);

       //tableScroll.setBounds(20,20,835,160);
        //table.setBounds(20,20,835,160);

        gui.add(tableScroll,BorderLayout.CENTER);



        //getContentPane().add(background);
        //getContentPane().add(gui);
        background.add(gui,BorderLayout.SOUTH);

        //.----------------------------------------------------------------------------------------------------------------------------------
        background.add(JpCenter,BorderLayout.CENTER);



        add(background);
        time.initCounter();
        setVisible(true);
    }


}


