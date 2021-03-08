package model.network;

import model.Missatge;
import model.json.ReadJson;


import java.io.*;
import java.net.Socket;

public class ServerComunication extends Thread  {

    private boolean isOn;
    private Socket socketToServer;
    private ObjectInputStream objectIn;
    private static ObjectOutputStream objectOut;
    private DataOutputStream dataOut;
    private DataInputStream dataIn;

    public ServerComunication() {
        try {
            ReadJson Rj = new ReadJson();
            Rj.llegeixJSON();
            this.isOn = false;
            this.socketToServer = new Socket(Rj.getDades().getServerIP(),Rj.getDades().getListeningPort());
            this.objectOut = new ObjectOutputStream(socketToServer.getOutputStream());
            this.dataIn = new DataInputStream(socketToServer.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("** ESTA EL SERVIDOR EN EXECUCIO? ***");
        }
    }

    public void startServerComunication() {
        // iniciem la comunicacio amb el servidor
        isOn = true;
        this.start();
    }

    public void stopServerComunication() {
        // aturem la comunicacio amb el servidor
        this.isOn = false;
        this.interrupt();
    }

    public static void enviaMissatge(Object missatge){
        try {
            objectOut.writeObject(missatge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (isOn) {
            try {
                objectIn = new ObjectInputStream(socketToServer.getInputStream());
                Object object = objectIn.readObject();

                System.out.println("skr");
                Missatge missatge;
                missatge = (Missatge) object;
                String accio = missatge.getAccio();

                switch (accio) {

                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        stopServerComunication();
    }

}