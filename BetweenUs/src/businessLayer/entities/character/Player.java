package businessLayer.entities.character;

/**
 * La classe 'Player' hereta les propietats i funcions de la classe 'Character' i
 * serà la del jugador específic de l'usuari
 * Subclasse que ens permet diferenciar entre tipus de 'Character' determinats.
 *
 * No s'han definit ni atributs ni mètodes perquè es diferència d'altres classes pel fet
 * de ser una subclasse de 'Character' (tipus específic)
 */
public class Player extends Character {
    // Parametrized constructor
    public Player(String color, int xCoordinate, int yCoordinate) {
        super(color, xCoordinate, yCoordinate);
    }

    // Parametrized constructor
    public Player(String color) {super(color);}
}
