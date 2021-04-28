package view;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;

public class SettingView extends JFrame  {


    public SettingView() {
        setVisible(true);
        setTitle("Settings"); // titol
        setSize(1080, 600); // tamaño de la caja
        setResizable(false); //para que no se pueda mover
        setLocationRelativeTo(null); //Centrarlo
        setDefaultCloseOperation(EXIT_ON_CLOSE); // cerrar con la x
        //setLayout(null);

        //Creem el fons

        JPanel background = new JPanel();
        background.setBackground(Color.black);
        background.setLayout(null);
        background.setBounds(0,0,1080,600);
        background.setVisible(true);


        JButton back = new JButton();
        back.setBounds(10,10,50,50);
        ImageIcon sets = new ImageIcon("/model/images/tuerca.png");
        back.setIcon(new ImageIcon(sets.getImage().getScaledInstance(back.getWidth(),back.getHeight(),Image.SCALE_SMOOTH)));
        background.add(back);





        JLabel title = new JLabel("Settings", JLabel.CENTER);
        title.setBounds(350,10,400,95);
        title.setFont(new Font("Russo One", Font.BOLD, 75));
        title.setForeground(Color.WHITE);
        background.add(title);




        //Creem un panel gridlayout de 2x2 per fer els settings
        JPanel jpSet = new JPanel();
        jpSet.setLayout(new GridLayout(2,2,0,0));
        jpSet.setBounds(80,130,1080,150);
       // jpSet.setLayout(null);
        jpSet.setBackground(Color.black);


        Font font = new Font("Russo One",Font.BOLD,35);

        JLabel jlMusic = new JLabel("Music volum",JLabel.CENTER);
        jlMusic.setForeground(Color.WHITE);
        jlMusic.setFont(font);
        jpSet.add(jlMusic);

        JLabel jlbola1 = new JLabel("la bola es la hostia",JLabel.LEFT);
        jlbola1.setForeground(Color.WHITE);
        jlbola1.setFont(new Font("Russo One",Font.BOLD,15));
        jpSet.add(jlbola1);

        //hacer que estas dos esten mas juntas
        JLabel jlSFX = new JLabel("SFX volum",JLabel.CENTER);
        jlSFX.setForeground(Color.WHITE);
        jlSFX.setFont(font);
        jpSet.add(jlSFX);


        JLabel jlbola2 = new JLabel("la bola es la hostia",JLabel.LEFT);
        jlbola2.setForeground(Color.WHITE);
        jlbola2.setFont(new Font("Russo One",Font.BOLD,15));
        jpSet.add(jlbola2);

        //Creem el panel gridLayout per fer els dos botons

        JPanel jpLoDe = new JPanel();
        jpLoDe.setLayout(new GridLayout(2,1,0,50));

        jpLoDe.setBounds(400,330,300,200);
        jpLoDe.setBackground(Color.BLACK);

        Border border = new LineBorder(Color.WHITE,3,true);


        //Boto1
        JButton jbLog = new JButton("Log out");
        jbLog.setForeground(Color.WHITE);
        jbLog.setBackground(Color.BLACK);
        jbLog.setLayout(null);
        jbLog.setBounds(0,330,150,40);
        jbLog.setFont(font);
        jbLog.setBorder(border);
        jpLoDe.add(jbLog);

        //boto2
        JButton jbDel = new JButton("Delete account");
        jbDel.setForeground(Color.RED);
        jbDel.setBackground(Color.BLACK);
        jbDel.setBounds(0,380,150,40);

        jbDel.setLayout(null);
        jbDel.setFont(font);
        jbDel.setBorder(border);
        jpLoDe.add(jbDel);







        getContentPane().add(background);
        background.add(jpSet);
        background.add(jpLoDe);
    }

  /*  public void mainController(ActionListener actionListener) {
        jbRegister.addActionListener(actionListener);
        jbLogin.addActionListener(actionListener);
    }*/
}
