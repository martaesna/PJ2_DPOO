package model.json;


public class Data {

    private String serverIP;
    private int listeningPort;

    public Data(String serverIP, int listeningPort) {
        this.serverIP = serverIP;
        this.listeningPort = listeningPort;
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

