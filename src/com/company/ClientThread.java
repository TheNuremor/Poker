package com.company;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Observable implements Runnable{
    public static final Logger log = Logger.getGlobal();
    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;
    private Socket client;

    public String bet;
    private boolean finished = false;
    private Table table;
    private String name;

    public ClientThread(Socket client, Table table) {
        this.table = table;
        this.client = client;

        try {
            outputStream = new ObjectOutputStream(client.getOutputStream());
            inputStream = new ObjectInputStream(client.getInputStream());
        }catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
        setChanged();
        notifyObservers();
    }
    public void messangerServer (Message message) throws IOException{

        switch (message.getHeader()) {

            case "Nameadd":
                break;
            case "Disconnect":
                break;
            default:
               //sendData("ERROR");
                break;
        }
    }

    @Override
    public void run(){
        try {
            while (true) {
                messangerServer((Message) inputStream.readObject());
            }
        }catch (ClassNotFoundException | IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }}

            /*output.println("Willkommen auf dem Server");
            output.println("/Nameadd");
            name = input.readLine();
            output.println("Ihr Name ist: " + name);
            Player player = new Player(this);
            player.setName(name);
            table.addPlayer(player, table.playerList);
            setFinished(true);
            System.out.println("Der Spieler " + name + " wurde hinzugefügt!");
            while (true) {
                bet = input.readLine();
                String betSplit[] = bet.split(":");
                if (betSplit[0] == "/betset") {
                    bet = betSplit[1];
                }
            }
        } catch (IOException e) { System.out.println("IOException in run()..."); }finally { close(); } }*/

    public boolean sendData(String header, Object object){
        try{
            outputStream.writeObject(new Message(header, object));
            outputStream.flush();
            return true;
        }catch (IOException e){
            log.log(Level.SEVERE, e.getMessage());
            return false;
        }
    }


    public void close(){
        try {
            log.info("Client disconnected");
            client.close();
        }catch (IOException e){
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}