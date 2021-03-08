package model.network;

import model.json.JsonReader;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Servidor extends Thread {

    private String password;
    private int portPeticions;
    private ServerSocket sSocket;
    private boolean isRunning;
    private LinkedList<ServidorDedicat> dServers; // els servidors dedicats
    private ObjectInputStream ois;
    private DataOutputStream dos;
    private Socket sClient;

    //constructor del servidor
    public Servidor() {
        try {
            JsonReader jr = new JsonReader();
            JsonReader.llegeixJSON();
            this.sSocket = new ServerSocket(jr.getDades().getListeningPort());
            this.isRunning = false;
            this.dServers = new LinkedList<ServidorDedicat>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //iniciem servidor
    public void startServer() {
        isRunning = true;
        System.out.println("Servidor iniciat...");
        this.start();
    }

    public void run() {
        while (isRunning) {
            try {
                // esperem peticions de connexio de clients
                System.out.println("Esperant peticio...");

                sClient = sSocket.accept();

                //segurament caldra afegir mes parametres
                ServidorDedicat dsClient = new ServidorDedicat(sClient,this);

                //afegim a la cua de servidors dedicats el client q sacaba de conectar
                dServers.add(dsClient);

                //encenem el servidor dedicat
                dsClient.startDedicatedServer();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}