package presentationLayer.views;

import presentationLayer.views.customComponents.RoundedBorder;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class DeleteGameView extends JFrame {
    private final JButton jbDelete;
    private JButton configButton;
    private JButton returnButton;
    private JComboBox<String> gameNames;


    /**
     * La classe ens permet veure la pantalla del ConfiguredGameView. Envia la informació que rep de l'usuari al seu controlador.
     * @param gameNamesString array d'strings amb els noms de les partids
     */

    public DeleteGameView(String[] gameNamesString) {
        setVisible(true);
        setTitle("Delete game"); // titol
        setSize(1080, 600); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        setLayout(null);

        //Creem el fons
        JPanel background = new JPanel();
        background.setBackground(Color.BLACK);
        background.setLayout(null);
        background.setBounds(0,0,1080,600);


        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/config.png")));
            Image scaled = image.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage = new ImageIcon(scaled);
            configButton = new JButton(backgroundImage);
            configButton.setOpaque(false);
            configButton.setContentAreaFilled(false);
            configButton.setBorderPainted(false);
            configButton.setActionCommand("Config");
            configButton.setBounds(10,10, 30, 30);
            background.add(configButton);

            BufferedImage image2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/tornar.png")));
            Image scaled2 = image2.getScaledInstance(40, 30, Image.SCALE_DEFAULT);
            ImageIcon backgroundImage2 = new ImageIcon(scaled2);
            returnButton = new JButton(backgroundImage2);
            returnButton.setOpaque(false);
            returnButton.setContentAreaFilled(false);
            returnButton.setBorderPainted(false);
            returnButton.setActionCommand("Return");
            returnButton.setBounds(1000,10, 40, 30);
            background.add(returnButton);
        } catch (IOException e) {
            e.printStackTrace();
        }


        JLabel title = new JLabel("Delete game", JLabel.LEFT);
        title.setBounds(250,50,600,95);
        title.setFont(new Font("Russo One", Font.BOLD, 75));
        title.setForeground(Color.WHITE);
        background.add(title);


        Font font = new Font("Russo One",Font.BOLD,35);

        JLabel jlMusic = new JLabel("Game name",JLabel.LEFT);
        jlMusic.setBounds(420,190,250,50);
        jlMusic.setFont(font);
        jlMusic.setForeground(Color.WHITE);
        background.add(jlMusic);

        gameNames = new JComboBox<>(gameNamesString);
        gameNames.setForeground(Color.BLACK);
        gameNames.setBackground(Color.WHITE);
        gameNames.setFont(new Font("", Font.BOLD, 16));
        gameNames.setBounds(350,260,350,40);
        background.add(gameNames);

        jbDelete = new JButton("Delete");
        jbDelete.setForeground(Color.RED);
        jbDelete.setBackground(Color.BLACK);
        jbDelete.setBounds(375,400,300,75);
        jbDelete.setActionCommand("Delete");
        jbDelete.setFont(font);
        jbDelete.setBorder(new RoundedBorder(50));
        background.add(jbDelete);

        setContentPane(background);
    }

    /**
     * Fa que quan apretem els botons el cotroller ho sapiga
     * @param actionListener escoltador d'accions
     */
    public void mainController(ActionListener actionListener) {
        jbDelete.addActionListener(actionListener);
        configButton.addActionListener(actionListener);
        returnButton.addActionListener(actionListener);
    }

    public String getGameName() { return String.valueOf(gameNames.getSelectedItem()); }

    /**
     * mostra JoptionPane per confirmar borrar un joc
     * @return un int confirmant o no si es borra el mapa
     */
    public int confirmDeleteGame(){
        return JOptionPane.showConfirmDialog(null,"Seguro que quieres borrar esta partida?");
    }

    /**
     * mostra un JoptionPane que ens diu que el joc que es vol borrar no existeix
     */
    public void printErrorNoExistance(){
        JOptionPane.showMessageDialog(null, "ERROR: El nom d'aquest joc no existeix", "Error Delete Game", JOptionPane.ERROR_MESSAGE);
    }
}