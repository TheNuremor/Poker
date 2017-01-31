package com.company.Client;

import com.company.Network.Message;

import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread{

    public static final Logger log = Logger.getGlobal();
    public ObjectOutputStream outputStream;
    public ObjectInputStream inputStream;
    public Client.GameGUI clientGUI = new Client.GameGUI();
    public Socket clientSocket = null;

    public static void main(String args[]) {


    }
    public Client() throws IOException {
        clientSocket = new Socket("localHost", 1111);
        System.out.println("Client connected");

        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }
    @Override
    public void run(){
        try {
            while (true) {
                messangerServer((Message) inputStream.readObject());
            }
        }catch (ClassNotFoundException | IOException e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage());
        }finally {
            close();
        }
    }


    public void messangerServer (Message message) throws IOException{
        clientGUI.textArea.setText("");
        switch (message.getHeader()) {

            case "NAMEADD":
                break;
            case "DISCONNECT":
                break;
            case "BET":
                break;
            case "NAME":
                Map<String, String> name =(Map<String, String>) message.getObject();
                break;
            case "CASH":
                break;
            case "PLAYERBET":
                break;
            case "ISALLIN":
                break;
            case "ISINGAME":
                break;
            case "ROLE":
                break;
            case "HANDSTACK":
                break;
            case "POT":
                break;
            case "TABLEBET":
                break;
            case "TABLESTACK":
                /*for (PokerCard card : clientGUI.insertImage(JPanel controllPanel)) {
                    clientGUI.insertImage(JPanel controllPanel)
                }*/
                break;
            default:
                clientGUI.textArea.append("------ES IST EIN FEHLER AUFGETRETEN!!------"+ message.getHeader());
                break;
        }
    }


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
            clientSocket.close();
        }catch (IOException e){
            log.log(Level.SEVERE, e.getMessage());
        }
    }
}