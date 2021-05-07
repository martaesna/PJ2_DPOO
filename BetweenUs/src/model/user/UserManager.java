package model.user;

import model.database.SQLUserDAO;
import model.database.UserDAO;
import model.user.User;

import javax.swing.*;

public class UserManager {
    private final UserDAO userDAO;
    private boolean error;
    private final String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9]{3,}+.+[a-zA-Z0-9]{2,}+$";

    public UserManager(){
        userDAO = new SQLUserDAO();
    }

    public void registerUser(User user) {
        userDAO.registerUser(user);
    }

    public boolean loginUser(String userNameMail, String password) {
        return userDAO.checkLoginUser(userNameMail,password);
    }

    public void deleteUser(String nameLogin) {
        boolean exists = userDAO.userNameExists(nameLogin);
        if (exists) {
            userDAO.deleteUser(nameLogin);
        } else {
            JOptionPane.showMessageDialog(null, "ERROR: Aquest usuari no existeix", "Error Registre", JOptionPane.ERROR_MESSAGE);
        }
    }

    public int checkRegister(User user){
        if (userDAO.userNameExists(user.getName())) {
            return 1;
        } else if (unequalPasswords(user)) {
            return 2;
        } else if(checkPasswordFormat(user) != "") {
            return 3;
        } else if (!checkMailFormat(user.getMail())) {
            return 4;
        } else if (userDAO.userMailExists(user.getMail())){
            return 5;
        } else {
            return 0;
        }
    }

    //Comprovem format del correu
    public boolean checkMailFormat(String mail) {
        return mail.matches(emailRegex);
    }

    //Comprovem format de la contrasenya
    public String checkPasswordFormat(User user) {
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
        if (error) {
            for (int i = 0; i < errorMsg.length; i++) {     //Printem tots els errors trobats
                if (errorMsg[i] != null) {
                    finalError = finalError.concat(errorMsg[i]);
                }
            }
        }
        return finalError;
    }

    //Comprovem si la confirmació de la contrasenya és correcte
    public boolean unequalPasswords(User user){
        if (user.getConfirmedPassword().equals(user.getPassword())) {
            return false;
        }
        return true;
    }
}
