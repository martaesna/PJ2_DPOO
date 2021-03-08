package model.json;

public class Data {

    private int port;
    private String user;
    private String password;
    private String db;
    private String serverIP;
    private int listeningPort;


    public Data(int port, String user, String password, String db, String serverIP, int listeningPort) {
        this.port = port;
        this.user = user;
        this.password = password;
        this.db = db;
        this.serverIP = serverIP;
        this.listeningPort = listeningPort;

    }
    public Data(){

    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public int getListeningPort() {
        return listeningPort;
    }

    public void setListeningPort(int listeningPort) {
        this.listeningPort = listeningPort;
    }
}
