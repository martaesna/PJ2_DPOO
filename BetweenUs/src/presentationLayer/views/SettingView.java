package presentationLayer.views;
import presentationLayer.views.customComponents.RoundedBorder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

public class SettingView extends JFrame  {

    private JButton jbDel;
    private JButton jbLog;
    private JButton configButton;
    private JSlider Eslider;
    private JSlider Mslider;

    public SettingView() {
        setVisible(true);
        setTitle("Settings"); // titol
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel title = new JLabel("Settings", JLabel.LEFT);
        title.setBounds(375,10,400,95);
        title.setFont(new Font("Russo One", Font.BOLD, 75));
        title.setForeground(Color.WHITE);
        background.add(title);

        Font font = new Font("Russo One",Font.BOLD,35);

        JLabel jlMusic = new JLabel("Music volum",JLabel.LEFT);
        jlMusic.setBounds(150,150,250,50);
        jlMusic.setFont(font);
        jlMusic.setForeground(Color.WHITE);
        background.add(jlMusic);

        //Slider de la musica
        Mslider = new JSlider(0,10,5);
        Mslider.setVisible(true);
        Mslider.setForeground(Color.WHITE);
        Mslider.setPaintTicks(true);
        Mslider.setMinorTickSpacing(1);
        Mslider.setMajorTickSpacing(2);
        Mslider.setPaintTrack(true);
        Mslider.setPaintLabels(true);
        Mslider.setFont(new Font("Serif",Font.ITALIC,15));
        Mslider.setSnapToTicks(true);
        Mslider.setBounds(500,150,300,50);
        background.add(Mslider);

        //hacer que estas dos esten mas juntas
        JLabel jlSFX = new JLabel("SFX volum",JLabel.LEFT);
        jlSFX.setForeground(Color.WHITE);
        jlSFX.setBounds(150,250,250,50);
        jlSFX.setFont(font);
        background.add(jlSFX);


        //Slider dels efectes
        Eslider = new JSlider(0,10,5);
        Eslider.setVisible(true);
        Eslider.setForeground(Color.WHITE);

        Eslider.setPaintTicks(true);
        Eslider.setMinorTickSpacing(1);
        Eslider.setMajorTickSpacing(2);
        Eslider.setPaintTrack(true);
        Eslider.setPaintLabels(true);
        Eslider.setFont(new Font("Serif",Font.ITALIC,15));
        Eslider.setSnapToTicks(true);
        Eslider.setBounds(500,250,300,50);

        background.add(Eslider);


        //Boto1
        jbLog = new JButton("Log out");
        jbLog.setForeground(Color.WHITE);
        jbLog.setBackground(Color.BLACK);
        jbLog.setBounds(350,350,400,75);
        jbLog.setActionCommand("Logout");
        jbLog.setFont(font);
        jbLog.setBorder(new RoundedBorder(50));
        background.add(jbLog);

        //boto2
        jbDel = new JButton("Delete account");
        jbDel.setForeground(Color.RED);
        jbDel.setBackground(Color.BLACK);
        jbDel.setBounds(350,450,400,75);
        jbDel.setActionCommand("Delete");
        jbDel.setFont(font);
        jbDel.setBorder(new RoundedBorder(50));
        background.add(jbDel);

        setContentPane(background);
    }

    public void mainController(ActionListener actionListener) {
        jbLog.addActionListener(actionListener);
        jbDel.addActionListener(actionListener);
        configButton.addActionListener(actionListener);
    }

    /*public void VolumCotroller(ChangeListener changeListener){
        Eslider.addChangeListener(changeListener);
        Mslider.addChangeListener(changeListener);

    }*/

    public int confirmDeleteUser() {
        return JOptionPane.showConfirmDialog(null,"Seguro que quieres borrar la Cuenta?");
    }

    public int confirmLogout(){
        return JOptionPane.showConfirmDialog(null,"Seguro que quieres hacer log out?");
    }
}
