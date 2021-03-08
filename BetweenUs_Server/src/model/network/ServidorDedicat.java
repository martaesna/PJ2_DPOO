package model.network;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class ServidorDedicat extends Thread {
    private boolean isRunning;
    private Socket sClient;
    private DataInputStream dataInput;
    private ObjectInputStream ois;
    private ObjectOutputStream objectOut;
    private DataOutputStream dos;
    private LinkedList<ServidorDedicat> clients;
    private Servidor servidor;



    public ServidorDedicat(Socket sClient, Servidor servidor) {
        this.sClient = sClient;
        this.servidor = servidor;
        this.isRunning = false;
    }

    public void startDedicatedServer() {
        isRunning = true;
        this.start();
    }

    public void stopDedicatedServer() {
        // aturem el servidor dedicat
        this.isRunning = false;
        this.interrupt();
    }

    public void enviaMissatge (Object missatge){
        try {
            objectOut = new ObjectOutputStream(sClient.getOutputStream());
            objectOut.writeObject(missatge);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run () {
        try {
            ois = new ObjectInputStream(sClient.getInputStream());
            //while is running(bucle infinit)
            while (isRunning) {
                System.out.println("estem al run");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
