package presentationLayer.controllers;

import businessLayer.GameManager;
import businessLayer.UserManager;
import businessLayer.entities.game.Game;
import businessLayer.entities.user.User;
import presentationLayer.views.LoginView;
import presentationLayer.views.NewGameView;
import presentationLayer.views.PlayView;
import presentationLayer.views.RegisterView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NewGameController implements ActionListener {
    private NewGameView ngv;
    private ArrayList<String> colors;

    public NewGameController(NewGameView ngv) {
        this.ngv = ngv;
        colors = new ArrayList<>(List.of("RED","BLUE","GREEN","PINK","ORANGE","YELLOW","BLACK","WHITE","PURPLE","BROWN","CYAN","LIME"));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SelectFile")) { //cuando apretamos el boton
            String path = getMapsPath();
            JFileChooser jfc = new JFileChooser(path);

            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                String name = selectedFile.getName();
                ngv.setMapName(name);
            }
        }

        if (e.getActionCommand().equals("ColorLeft")) { //cuando apretamos el boton
            int position = getColorsPosition(ngv.getColor());
            if (position > 0) {
                ngv.setColor(colors.get(position-1));
            }
        }

        if (e.getActionCommand().equals("ColorRight")) { //cuando apretamos el boton
            int position = getColorsPosition(ngv.getColor());
            if (position < 11) {
                ngv.setColor(colors.get(position+1));
            }
        }

        if (e.getActionCommand().equals("ImpostorsLeft")) { //cuando apretamos el boton
            int impostors = ngv.getImpostors();
            if (impostors > 1) {
                ngv.setImpostors(impostors-1);
            }
        }

        if (e.getActionCommand().equals("ImpostorsRight")) { //cuando apretamos el boton
            int impostors = ngv.getImpostors();
            int maxImpostors = checkImpostors(ngv.getPlayers());
            if (impostors < 3 && maxImpostors > impostors) {
                ngv.setImpostors(impostors+1);
            }
        }

        if (e.getActionCommand().equals("PlayersLeft")) { //cuando apretamos el boton
            System.out.println("Restem jugador");
            int players = ngv.getPlayers();
            if (players > 3) {
                ngv.setPlayers(players-1);
            }
        }

        if (e.getActionCommand().equals("PlayersRight")) { //cuando apretamos el boton
            int players = ngv.getPlayers();
            if (players < 10) {
                ngv.setPlayers(players+1);
            }
        }

        if (e.getActionCommand().equals("Play")) {
            GameManager gameManager = new GameManager();
            Game game = new Game(ngv.getName(), ngv.getPlayers(), ngv.getImpostors(), ngv.getColor(), ngv.getMapName(), "creator");
            if(gameManager.checkGame(game.getGameName())) {
                ngv.printError();
            } else {
                gameManager.createGame(ngv.getName());
                //VISTA JOC
            }
        }

        if (e.getActionCommand().equals("Config")) {
            ngv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv);
            pv.mainController(pvc);
        }

        if (e.getActionCommand().equals("Return")) {
            ngv.setVisible(false);
            PlayView pv = new PlayView();
            PlayViewController pvc = new PlayViewController(pv);
            pv.mainController(pvc);
        }
    }

    public String getMapsPath(){
        File f = new File("");
        String path = f.getAbsolutePath();
        return path + "\\BetweenUs\\src\\mapsFiles";
    }

    public int getColorsPosition(String actualColor){
        for (int i = 0; i < colors.size(); i++) {
            if (colors.get(i) == actualColor) {
                return i;
            }
        }
        return 0;
    }

    public int checkImpostors(int players){
        return Math.floorDiv(players+1, 3);
    }
}
