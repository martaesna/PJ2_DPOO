package businessLayer.entities.user;

/**
 * La classe 'User' conté la informació general que l'usuari ha introduït
 * al regitrar-se
 *
 * Aquesta classe ens servirà per poder guardar les seves dades i fer modificacions
 * o comprovacions segons el cas
 *
 * Tenim els atributs que defineixen un usuari i els getters per poder llegir
 * la seva informació
 */
public class User {
    // Attributes
    private final String name;
    private final String mail;
    private final String password;
    private final String confirmedPassword;

    // Parametrized constructor
    public User (String name, String mail, String password, String confirmedPassword) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
    }

    public String getName() {
        return name;
    }
    public String getMail() {
        return mail;
    }
    public String getPassword() {
        return password;
    }
    public String getConfirmedPassword() {
        return confirmedPassword;
    }
}
