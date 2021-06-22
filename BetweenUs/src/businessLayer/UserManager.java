package businessLayer;

import persitanceLayer.SQLUserDAO;
import persitanceLayer.UserDAO;
import businessLayer.entities.user.User;

import javax.swing.*;

public class UserManager {
    private final UserDAO userDAO;

    public UserManager(){
        userDAO = new SQLUserDAO();
    }

    /**
     * Mètode que registra un usuari a la base de dades
     * @param user usuari a registrar
     */
    public void registerUser(User user) {
        userDAO.registerUser(user);
    }

    /**
     * Mètode que logeja a un usuari si el log in és correcte
     * @param userNameMail mail introduit per l'usuari
     * @param password contrasenya introduida per l'usuari
     * @return booleà amb si s'ha pogut logejar o no
     */
    public boolean loginUser(String userNameMail, String password) {
        return userDAO.checkLoginUser(userNameMail,password);
    }

    /**
     * Mètode que elimina a un usuari de la base de dades
     * @param userName nom de l'usuari
     */
    public boolean deleteUser(String userName) {
        if (userDAO.userNameExists(userName)) {
            userDAO.deleteUser(userName);
            return true;
        }
        return false;
    }

    /**
     * Mètode que comprova si el registre es correcte
     * @param user usuari a registrar
     * @return nombre de l'error o 0 si és correcte
     */
    public int checkRegister(User user){
        if (userDAO.userNameExists(user.getName())) {
            return 1;
        } else if (unequalPasswords(user)) {
            return 2;
        } else if(!checkPasswordFormat(user).equals("")) {
            return 3;
        } else if (!checkMailFormat(user.getMail())) {
            return 4;
        } else if (userDAO.userMailExists(user.getMail())){
            return 5;
        } else {
            return 0;
        }
    }

    /**
     * Mètode que comprova el format del correu
     * @param mail correu a comprovar
     * @return si és correcte o no
     */
    public boolean checkMailFormat(String mail) {
        String emailRegex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9]{3,}+.+[a-zA-Z0-9]{2,}+$";
        return mail.matches(emailRegex);
    }

    /**
     * Mètode que comprova el format de la contrasenya
     * @param user usuari a comprovar la contrasenya
     * @return missatge d'error a mostrar
     */
    public String checkPasswordFormat(User user) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        boolean error = false;

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
        }

        String finalError = "";
        if (error) {
            for (String s: errorMsg) {     //Printem tots els errors trobats
                if (s != null) {
                    finalError = finalError.concat(s);
                }
            }
        }
        return finalError;
    }

    /**
     * Mètode que comporva si les contrassenyes coinicideixen
     * @param user usuari a comprovar
     * @return si coincideixen o no
     */
    public boolean unequalPasswords(User user){
        return !user.getConfirmedPassword().equals(user.getPassword());
    }

    public String getUsername(String loginName) {
        return userDAO.getUsername(loginName);
    }
}
