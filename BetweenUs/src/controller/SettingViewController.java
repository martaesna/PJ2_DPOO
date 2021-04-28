package controller;

import view.RegisterView;
import view.SettingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingViewController implements ActionListener {

    private SettingView sv;

    public SettingViewController(SettingView sv) {
        this.sv = sv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
