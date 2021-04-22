package controller;

import model.User;

public class UserController {
    private User user;
    private boolean error;
    private String confirmPassword;

    public UserController(){
        error = false;
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
    public boolean checkPasswordFormat() {
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
            errorMsg[index] = "ERROR: La contrasenya ha de tenir 8 caràcters com a mínim";
            index++;
        }
        if (!numberFlag) {
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir algún valor numèric";
            index++;
        }
        if (!capitalFlag) {
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir alguna majúscula";
            index++;
        }
        if (!lowerCaseFlag) {
            error = true;
            errorMsg[index] = "ERROR: La contrasenya ha de tenir alguna minúscula";
            index++;
        }

        for (int i = 0; i < errorMsg.length; i++) {     //Printem tots els errors trobats
            if(errorMsg[i] != null) {
                System.out.println(errorMsg[i]);
            }
        }

        return error;
    }

    //Comprovem si la confirmació de la contrasenya és correcte
    public boolean unequalPasswords(String confirmedPassword){
        if (confirmedPassword.equals(user.getPassword())) {
            return false;
        } else {
            System.out.println("ERROR: Les contrasenyes han de coincidir");
            return true;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
