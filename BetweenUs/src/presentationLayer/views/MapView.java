package presentationLayer.views;

import businessLayer.entities.character.Character;
import businessLayer.entities.character.Player;
import businessLayer.entities.maps.*;
import presentationLayer.controllers.MapController;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class MapView extends JFrame {
    private JButton returnButton;
    private JButton configButton;
    private JButton mapButton;
    private final JButton up;
    private final JButton down;
    private final JButton right;
    private final JButton left;
    private final LinkedList<Character> players;
    private final Character userPlayer;
    private final Map map;
    private ImageIcon leftArrow;
    private ImageIcon rightArrow;
    private final HashMap<String, JButton> objectiveTrackingButtons = new HashMap<String, JButton>();
    private final HashMap<Integer, Integer> objectiveTrackingPosition = new HashMap<Integer, Integer>();
    private final HashMap<Integer, Integer> gameSolution = new HashMap<>();
    private final JPanel objectiveTracking;
    private JPanel jpCenter;
    private JPanel background;
    private boolean revealMap;

    public MapView(Map map, LinkedList<Character> players, Player userPlayer)/*throws IOException*/ {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.players = players;
        this.userPlayer = userPlayer;
        this.map = map;
        jpCenter = new JPanel();
        int numPlayers = players.size();
        revealMap = false;

        setTitle("Map");
        setSize((int)screenSize.getWidth(), (int)screenSize.getHeight() - 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //pintem el panell final
        background = new JPanel();
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
            mapButton.setActionCommand("Reveal");

            JpNorthEast.add(mapButton, BorderLayout.EAST);
            JpNorth.add(JpNorthEast, BorderLayout.CENTER);

            BufferedImage image4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/leftArrow.png")));
            Image scaled4 = image4.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
            leftArrow = new ImageIcon(scaled4);

            BufferedImage image5 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/rightArrow.png")));
            Image scaled5 = image5.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
            rightArrow = new ImageIcon(scaled5);


        } catch (IOException e) {
            e.printStackTrace();
        }

        background.add(JpNorth, BorderLayout.NORTH);
/*
        for(int i = 0; i< crewMembers.size();i++){
            System.out.println(i);
            System.out.println(crewMembers.get(i).getColor());
            System.out.println("");
        }*/

        // Pasar ho toto pel PaintComponent
       // JPanel mapa = new MapPaint(new GridLayout(map.getWidth(), map.getHeight()), map);

        MapPaint mp = new MapPaint(new GridLayout(map.getWidth(), map.getHeight()), map, players, userPlayer, revealMap);
        jpCenter = mp.creaMapa();

        //creamos un border layout dentro del EAST y ponemos los botones en cada lugar
        //background.add(control,BorderLayout.EAST); //aqui hemos de poner los botones
        JPanel controles = new JPanel(new BorderLayout());
        //per poder colocar els botons a la part de dalt
        JPanel auxControles = new JPanel(new BorderLayout());
        //coloquem els botons de adalt y abaix
        JPanel auxControlUpDown = new JPanel(new GridLayout(2, 1));
        JPanel auxControlLeft = new JPanel(new BorderLayout());
        JPanel auxControlRight = new JPanel(new BorderLayout());

        up = new JButton();
        up.setActionCommand("up");
        up.setText("^");

        down = new JButton();
        down.setActionCommand("down");
        down.setText("v");

        right = new JButton();
        right.setActionCommand("right");
        right.setText(">");

        left = new JButton();
        left.setActionCommand("left");
        left.setText("<");

        auxControlUpDown.add(up);
        auxControlUpDown.add(down);
        auxControles.add(auxControlUpDown, BorderLayout.CENTER);

        auxControlLeft.add(left, BorderLayout.SOUTH);
        auxControlRight.add(right, BorderLayout.SOUTH);
        auxControles.add(auxControlRight, BorderLayout.EAST);
        auxControles.add(auxControlLeft, BorderLayout.WEST);

        auxControlRight.setOpaque(false);
        auxControlLeft.setOpaque(false);
        auxControlUpDown.setOpaque(false);
        auxControles.setBackground(Color.GRAY);
        controles.setOpaque(false);

        //Coloquem els botons final al panell

        controles.add(auxControles, BorderLayout.NORTH);


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

        objectiveTracking = new JPanel();
        Color headerBackground = new Color(160,160,160);
        objectiveTracking.setLayout(new GridLayout(numPlayers + 1,3));
        objectiveTracking.setBackground(headerBackground);


        JLabel headerUnknown = new JLabel("Unknown", JLabel.CENTER);
        headerUnknown.setFont(new Font("Russo One", Font.PLAIN, 20));
        headerUnknown.setBorder(new LineBorder(Color.WHITE,1,false));
        headerUnknown.setForeground(Color.WHITE);

        JLabel headerSus = new JLabel("Suspicious", JLabel.CENTER);
        headerSus.setFont(new Font("Russo One", Font.PLAIN, 20));
        headerSus.setBorder(new LineBorder(Color.WHITE,1,false));
        headerSus.setForeground(Color.WHITE);

        JLabel headerInn = new JLabel("Innocent", JLabel.CENTER);
        headerInn.setFont(new Font("Russo One", Font.PLAIN, 20));
        headerInn.setBorder(new LineBorder(Color.WHITE,1,false));
        headerInn.setForeground(Color.WHITE);



        objectiveTracking.add(headerUnknown);
        objectiveTracking.add(headerSus);
        objectiveTracking.add(headerInn);


        for (int i = 0; i < numPlayers; i++) {

            Color playerColor = getUserColor(players.get(i).getColor());
            JPanel playerPanel = createPanel(players.get(i).getColor(), playerColor, i, objectiveTrackingButtons);
            JLabel empty = new JLabel("");
            JLabel empty2 = new JLabel("");
            objectiveTracking.add(playerPanel);
            objectiveTracking.add(empty);
            objectiveTracking.add(empty2);
            objectiveTrackingPosition.put(i, 0);
        }


        //getContentPane().add(background);
        //getContentPane().add(gui);
        background.add(objectiveTracking, BorderLayout.SOUTH);
        background.add(jpCenter, BorderLayout.CENTER);


        add(background);
       // time.initCounter();
        setVisible(true);
    }

    private Color getUserColor(String color) {
        Color playerColor = null;
        if (color.equals("PURPLE") || color.equals("BROWN") || color.equals("CYAN") || color.equals("LIME")) {
            int[] components = getColorComponents(color);
            playerColor = new Color(components[0], components[1], components[2]);
        } else {
            try {
                playerColor = (Color) Color.class.getField(color).get(null);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return playerColor;
    }

    public void moveRight(String element) {
        for (int key: objectiveTrackingPosition.keySet()) {
            if (key == Integer.parseInt(element) && canMoveRight(objectiveTrackingPosition.get(key))) {
                objectiveTrackingPosition.replace(key, objectiveTrackingPosition.get(key) + 1);
            }
        }
    }

    private boolean canMoveRight(Integer element) {
        return element != 2;
    }

    public void updateObjectiveTracking(MapController mc) {
        objectiveTracking.removeAll();

        JLabel headerUnknown = new JLabel("Unknown", JLabel.CENTER);
        headerUnknown.setFont(new Font("Russo One", Font.PLAIN, 20));
        headerUnknown.setBorder(new LineBorder(Color.WHITE,1,false));
        headerUnknown.setForeground(Color.WHITE);

        JLabel headerSus = new JLabel("Suspicious", JLabel.CENTER);
        headerSus.setFont(new Font("Russo One", Font.PLAIN, 20));
        headerSus.setBorder(new LineBorder(Color.WHITE,1,false));
        headerSus.setForeground(Color.WHITE);

        JLabel headerInn = new JLabel("Innocent", JLabel.CENTER);
        headerInn.setFont(new Font("Russo One", Font.PLAIN, 20));
        headerInn.setBorder(new LineBorder(Color.WHITE,1,false));
        headerInn.setForeground(Color.WHITE);

        objectiveTracking.add(headerUnknown);
        objectiveTracking.add(headerSus);
        objectiveTracking.add(headerInn);

        for (int key: objectiveTrackingPosition.keySet()) {
            Color playerColor = getUserColor(players.get(key).getColor());
            if (objectiveTrackingPosition.get(key) == 0) {
                JPanel playerPanel = createPanel(players.get(key).getColor(), playerColor, key, objectiveTrackingButtons);
                JLabel empty = new JLabel("");
                JLabel empty2 = new JLabel("");
                objectiveTracking.add(playerPanel);
                objectiveTracking.add(empty);
                objectiveTracking.add(empty2);
            } else if (objectiveTrackingPosition.get(key) == 1) {
                JPanel playerPanel = createPanel(players.get(key).getColor(), playerColor, key, objectiveTrackingButtons);
                JLabel empty = new JLabel("");
                JLabel empty2 = new JLabel("");
                objectiveTracking.add(empty);
                objectiveTracking.add(playerPanel);
                objectiveTracking.add(empty2);
            } else {
                JPanel playerPanel = createPanel(players.get(key).getColor(), playerColor, key, objectiveTrackingButtons);
                JLabel empty = new JLabel("");
                JLabel empty2 = new JLabel("");
                objectiveTracking.add(empty);
                objectiveTracking.add(empty2);
                objectiveTracking.add(playerPanel);
            }
        }
        objectiveTracking.revalidate();
        objectiveTracking.repaint();

        mainController(mc);
    }

    public void moveLeft(String element) {
        for (int key: objectiveTrackingPosition.keySet()) {
            if (key == Integer.parseInt(element) && canMoveLeft(objectiveTrackingPosition.get(key))) {
                objectiveTrackingPosition.replace(key, objectiveTrackingPosition.get(key) - 1);
            }
        }
    }

    private boolean canMoveLeft(Integer element) {
        return element != 0;
    }

    private JPanel createPanel(String colorName, Color color, int i, HashMap<String, JButton> objectiveTrackingButtons) {
        JPanel jpanel = new JPanel(new GridLayout(1,3));
        jpanel.setBorder(new LineBorder(Color.WHITE,1,false));

        JButton leftButton = new JButton(leftArrow);
        leftButton.setActionCommand("l_" + i);
        leftButton.setBackground(color);
        leftButton.setBorder(null);
        objectiveTrackingButtons.put("l_" + i, leftButton);
        jpanel.add(leftButton);

        JLabel playerLabel = new JLabel(colorName, JLabel.CENTER);
        playerLabel.setFont(new Font("Russo One", Font.BOLD, 18));
        playerLabel.setForeground(Color.GRAY);
        jpanel.add(playerLabel);

        JButton rightButton = new JButton(rightArrow);
        rightButton.setActionCommand("r_" + i);
        rightButton.setBackground(color);
        rightButton.setBorder(null);
        objectiveTrackingButtons.put("r_" + i, rightButton);
        jpanel.add(rightButton);

        jpanel.setBackground(color);

        return jpanel;
    }

    public void mainController(ActionListener actionListener) {
        up.addActionListener(actionListener);
        down.addActionListener(actionListener);
        left.addActionListener(actionListener);
        right.addActionListener(actionListener);
        mapButton.addActionListener(actionListener);
        configButton.addActionListener(actionListener);
        returnButton.addActionListener(actionListener);

        for (String key: objectiveTrackingButtons.keySet()) {
            objectiveTrackingButtons.get(key).addActionListener(actionListener);
        }
    }

    public int confirmSave(){
        return JOptionPane.showConfirmDialog(null,"Vols guardar l'estat actual de la partida?");
    }

    public int[] getColorComponents(String color) {
        int[] components = new int[3];
        switch (color) {
            case "PURPLE":
                components[0] = 102;
                components[2] = 153;
                return components;
            case "BROWN":
                components[0] = 102;
                components[1] = 51;
                return components;
            case "CYAN":
                components[1] = 255;
                components[2] = 255;
                return components;
            default:
                components[0] = 50;
                components[1] = 205;
                components[2] = 50;
                return components;
        }
    }

    public void updateView(Map map, LinkedList<Character> players, Character userPlayer, boolean revealMap) {
        jpCenter.removeAll();

        MapPaint mp = new MapPaint(new GridLayout(map.getWidth(), map.getHeight()), map, players, userPlayer, revealMap);
        jpCenter = mp.creaMapa();

        background.add(jpCenter, BorderLayout.CENTER);

        jpCenter.revalidate();
        jpCenter.repaint();
    }

    public boolean checkSolution() {
        return gameSolution.equals(objectiveTrackingPosition);
    }

    public void userWins() {
        JOptionPane.showMessageDialog(null, "HAS GUANYAAAAAAAAAAAAT!!!!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
    }

    public void printNoImplementationMsg(){
        JOptionPane.showMessageDialog(null, "Ho sentim, aquesta funcionalitat encara no està en funcionament.\nEstem treballant per solucionar-ho!", "Information MSG", JOptionPane.INFORMATION_MESSAGE);
    }

    public void impostorsWinMsg() {
        JOptionPane.showMessageDialog(null, "Els impostors han guanyat (han quedat el mateix número d'impostors que de tripulants).\nProva d'entrenar més!", "Game end", JOptionPane.INFORMATION_MESSAGE);
    }

    public void userDefeatMsg() {
        JOptionPane.showMessageDialog(null, "Has estat eliminat per un impostor.\nProva d'entrenar més!", "Game end", JOptionPane.INFORMATION_MESSAGE);
    }
}


