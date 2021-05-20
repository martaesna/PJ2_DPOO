package presentationLayer.views;
import businessLayer.entities.character.Character;
import businessLayer.entities.character.CrewMember;
import businessLayer.entities.character.Impostor;
import businessLayer.entities.maps.*;
import businessLayer.entities.game.Time;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
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
    private LinkedList<CrewMember> crewMembers;
    private LinkedList<Impostor> impostors;
    private Character userPlayer;
    private Map map;

    public MapView(Map map, LinkedList<CrewMember> crewMembers, LinkedList<Impostor> impostors, Character userPlayer)/*throws IOException*/ {
        this.crewMembers = crewMembers;
        this.impostors = impostors;
        this.userPlayer = userPlayer;
        this.map = map;

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
            JpNorth.add(JpNorthEast, BorderLayout.CENTER);


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

        JPanel JpCenter = new JPanel();

// Pasar ho toto pel PaintComponent
       // JPanel mapa = new MapPaint(new GridLayout(map.getWidth(), map.getHeight()), map);
        MapPaint mp = new MapPaint(new GridLayout(map.getWidth(), map.getHeight()), map);
        JpCenter = mp.CreaMapa();

        //creamos un border layout dentro del EAST y ponemos los botones en cada lugar
        //background.add(control,BorderLayout.EAST); //aqui hemos de poner los botones
        JPanel controles = new JPanel(new BorderLayout());
        //per poder colocar els botons a la part de dalt
        JPanel AuxControles = new JPanel(new BorderLayout());
        //coloquem els botons de adalt y abaix
        JPanel AuxControlUPDOWN = new JPanel(new GridLayout(2, 1));
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
        AuxControles.add(AuxControlUPDOWN, BorderLayout.CENTER);

        AuxControlLEFT.add(LEFT, BorderLayout.SOUTH);
        AuxControlRIGHT.add(RIGHT, BorderLayout.SOUTH);
        AuxControles.add(AuxControlRIGHT, BorderLayout.EAST);
        AuxControles.add(AuxControlLEFT, BorderLayout.WEST);

        AuxControlRIGHT.setOpaque(false);
        AuxControlLEFT.setOpaque(false);
        AuxControlUPDOWN.setOpaque(false);
        AuxControles.setBackground(Color.GRAY);
        controles.setOpaque(false);

        //Coloquem els botons final al panell

        controles.add(AuxControles, BorderLayout.NORTH);


        background.add(controles, BorderLayout.EAST);


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

        gui.setBackground(new Color(125, 125, 125, 99));//LE PONEMOS EL COLOR

        String[] header = {"Unknown", "Suspicious", "Innocent"}; //CREAMOS LA ETIQUETAS DEL TITULO

        //gui.setLayout(null);
        //gui.setBounds(100,300,875,200);

        DefaultTableModel model = new DefaultTableModel(data, header);
        JTable table = new JTable(model) {

            public Class getColumnClass(int column) {
                return ImageIcon.class;
            }
        };

        table.setRowHeight(45);
        table.setPreferredScrollableViewportSize(table.getPreferredSize()); //ficar que n fiqui mes de 3/4


        JScrollPane tableScroll = new JScrollPane(table);
        //tableScroll.setSize(600,160);
        //table.setBounds(0,0,835,160);

        tableScroll.setOpaque(false);
        tableScroll.setBackground(Color.black);

        //tableScroll.setBounds(20,20,835,160);
        //table.setBounds(20,20,835,160);

        gui.add(tableScroll, BorderLayout.CENTER);


        //getContentPane().add(background);
        //getContentPane().add(gui);
        background.add(gui, BorderLayout.SOUTH);

        //.----------------------------------------------------------------------------------------------------------------------------------
        background.add(JpCenter, BorderLayout.CENTER);


        add(background);
       // time.initCounter();
        setVisible(true);
    }

    public void mainController(ActionListener actionListener) {
        UP.addActionListener(actionListener);
        DOWN.addActionListener(actionListener);
        LEFT.addActionListener(actionListener);
        RIGHT.addActionListener(actionListener);
    }

    public void moveNpcPlayer(int i, int[] nextCell, boolean impostor) {
        if (impostor) {
            //Movem Impostor i a nextCell

        } else {
            //Movem Crew Member i a nextCell
        }
    }

    public int confirmSave(){
        return JOptionPane.showConfirmDialog(null,"Vols guardar l'estat actual de la partida?");
    }
}


