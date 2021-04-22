package model;

import controller.UserController;

import javax.swing.*;

public class UserManager {
    private boolean error;

    public boolean userRegister(User user){
        if (checkPasswordFormat(user) || unequalPasswords(user)) {
            return false;
        } else {
            return true;
        }
    }

    //Comprovem format del correu
    public boolean checkMailFormat(String mail) {

        return error;
    }

    //Comprovem que el correu és únic a la BBDD
    public boolean checkUniqueMail(String mail) {

        return error;
    }

    //Comprovem format de la contrasenya
    public boolean checkPasswordFormat(User user) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        error = false;

        String[] errorMsg = new String[4];
        int index = 0;

        for (int i = 0; i < user.getPassword().length(); i++){
            ch = user.getPassword().charAt(i);
            if ((Character.isDigit(ch))) {      //Comprovem si la contrasenya té algun número
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {     //Comprovem si la contrasenya té alguna majúscula
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {     //Comprovem si la contrasenya té alguna minúscula

                lowerCaseFlag = true;
            }
        }

        if (user.getPassword().length() < 8){     //  Comprovem que la contrasenya contingui com a mínim 8 caràcters
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir 8 caràcters com a mínim\n";
            index++;
        }
        if (!numberFlag) {
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir algún valor numèric\n";
            index++;
        }
        if (!capitalFlag) {
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir alguna majúscula\n";
            index++;
        }
        if (!lowerCaseFlag) {
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir alguna minúscula\n";
            index++;
        }

        String finalError = "";
        for (int i = 0; i < errorMsg.length; i++) {     //Printem tots els errors trobats
            if(errorMsg[i] != null) {
                finalError = finalError.concat(errorMsg[i]);
            }
        }
        if (error) {
            JOptionPane.showMessageDialog(null, finalError, "Error Registre", JOptionPane.ERROR_MESSAGE);
        }

        return error;
    }

    //Comprovem si la confirmació de la contrasenya és correcte
    public boolean unequalPasswords(User user){
        if (user.getConfirmedPassword().equals(user.getPassword())) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: Les contrasenyes han de coincidir", "Error Registre", JOptionPane.ERROR_MESSAGE);

            return true;
        }
    }
}
