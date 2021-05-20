package presentationLayer.views.customComponents;

public class Log {
    private String name;
    private String room;
    private int instant;

    public Log(String name, String room, int instant) {
        this.name = name;
        this.room = room;
        this.instant = instant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getInstant() {
        return instant;
    }

    public void setInstant(int instant) {
        this.instant = instant;
    }
}
