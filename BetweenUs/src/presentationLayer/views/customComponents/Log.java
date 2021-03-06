package presentationLayer.views.customComponents;


/**
 * Classe on declarem els atributs del Log que farem servir després al map view.
 */
public class Log {
    private final String name;
    private final String room;
    private final int instant;

    public Log(String name, String room, int instant) {
        this.name = name;
        this.room = room;
        this.instant = instant;
    }

    public String getName() {
        return name;
    }
    public String getRoom() {
        return room;
    }
    public int getInstant() {
        return instant;
    }
}
