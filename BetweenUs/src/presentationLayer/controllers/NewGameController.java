package presentationLayer.controllers;

import businessLayer.UserManager;
import businessLayer.entities.user.User;
import presentationLayer.views.LoginView;
import presentationLayer.views.NewGameView;
import presentationLayer.views.RegisterView;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class NewGameController implements ActionListener {
    private NewGameView ngv;

    public NewGameController(NewGameView ngv) {
        this.ngv = ngv;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SelectFile")) { //cuando apretamos el boton
            String path = getMapsPath();
            JFileChooser jfc = new JFileChooser(path);

            int returnValue = jfc.showOpenDialog(null);
            // int returnValue = jfc.showSaveDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = jfc.getSelectedFile();
                String name = selectedFile.getName();
                ngv.setMapName(name);
            }
        }

        if (e.getActionCommand().equals("ColorLeft")) { //cuando apretamos el boton

        }

        if (e.getActionCommand().equals("ColorRight")) { //cuando apretamos el boton

        }

        if (e.getActionCommand().equals("ImpostorsLeft")) { //cuando apretamos el boton

        }

        if (e.getActionCommand().equals("ImpostorsRight")) { //cuando apretamos el boton

        }

        if (e.getActionCommand().equals("PlayersLeft")) { //cuando apretamos el boton

        }

        if (e.getActionCommand().equals("PlayersRight")) { //cuando apretamos el boton

        }

    }
    public String getMapsPath(){
        File f = new File("");
        String path = f.getAbsolutePath();
        return path + "\\BetweenUs\\src\\mapsFiles";
    }
}
