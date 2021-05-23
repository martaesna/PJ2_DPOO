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
    private final JButton jbDel;
    private final JButton jbLog;
    private JButton configButton;

    /**
     * mostra la vista dels settings
     */
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
        JSlider mslider = new JSlider(0, 10, 5);
        mslider.setVisible(true);
        mslider.setForeground(Color.WHITE);
        mslider.setPaintTicks(true);
        mslider.setMinorTickSpacing(1);
        mslider.setMajorTickSpacing(2);
        mslider.setPaintTrack(true);
        mslider.setPaintLabels(true);
        mslider.setFont(new Font("Serif",Font.ITALIC,15));
        mslider.setSnapToTicks(true);
        mslider.setBounds(500,150,300,50);
        background.add(mslider);

        //hacer que estas dos esten mas juntas
        JLabel jlSFX = new JLabel("SFX volum",JLabel.LEFT);
        jlSFX.setForeground(Color.WHITE);
        jlSFX.setBounds(150,250,250,50);
        jlSFX.setFont(font);
        background.add(jlSFX);


        //Slider dels efectes
        JSlider eslider = new JSlider(0, 10, 5);
        eslider.setVisible(true);
        eslider.setForeground(Color.WHITE);

        eslider.setPaintTicks(true);
        eslider.setMinorTickSpacing(1);
        eslider.setMajorTickSpacing(2);
        eslider.setPaintTrack(true);
        eslider.setPaintLabels(true);
        eslider.setFont(new Font("Serif",Font.ITALIC,15));
        eslider.setSnapToTicks(true);
        eslider.setBounds(500,250,300,50);

        background.add(eslider);


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

    /**
     * Fa que quan apretem els botons el cotroller ho sapiga
     * @param actionListener
     */
    public void mainController(ActionListener actionListener) {
        jbLog.addActionListener(actionListener);
        jbDel.addActionListener(actionListener);
        configButton.addActionListener(actionListener);
    }

    /*public void VolumCotroller(ChangeListener changeListener){
        eslider.addChangeListener(changeListener);
        mslider.addChangeListener(changeListener);

    }*/

    /**
     * JOoptionPane que ens confirma si volem borrar el compte
     * @return si volem borrar o no
     */
    public int confirmDeleteUser() {
        return JOptionPane.showConfirmDialog(null,"Segur que vols borrar el compte?");
    }

    /**
     * JOptionPane si volem sortir de la sessió
     * @return si volem sortir o no
     */
    public int confirmLogout(){
        return JOptionPane.showConfirmDialog(null,"Segur que vols sortir?");
    }
}
