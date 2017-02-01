package com.company.Client;

import com.company.Network.Message;

import java.io.*;
import java.net.Socket;

public class Client extends Thread{

    private boolean running;

    private Socket server = null;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Client(String IP) {
        try {
            server = new Socket(IP, 1111);
            establishConnection();
        } catch (IOException e) {
            System.out.println("Es konnte keine Verbindung mit dem Server aufgebaut werden");
            System.exit(1);
        }
        System.out.println("Client connected");
    }

    @Override
    public void run(){
        try {
            while (running) {
                messangerServer((Message) inputStream.readObject());
            }
        }catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }finally {
            close();
        }
    }

    private void establishConnection() throws IOException {
        outputStream = new ObjectOutputStream(server.getOutputStream());
        inputStream = new ObjectInputStream(server.getInputStream());
        running = true;
    }

    private void close() {
        sendMessageToServer("leave", null);
        try {
            running = false;
            inputStream.close();
            outputStream.close();
            server.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private boolean sendMessageToServer(String header, Object payload) {
        try {
            outputStream.writeObject(new Message(header, payload));
            return true;
        } catch (IOException e) {
            System.out.println("Es konnte keine Message geschickt werden: " + e.getMessage());
            return false;
        }
    }

    public void messangerServer (Message message) throws IOException{
        switch (message.getHeader()) {
            case "startGame":
                break;
            case "endGame":
                break;
            case "handCard":
                break;
            case "openCard":
                break;
            case "disconnect":
                break;
            case "nextGameRound":
                break;
            case "userAccept":
                break;
            case "userDecline":
                break;
            case "setBet":
                break;
            case "role":
                break;
            case "chips":
                break;
            case "inGame":
                break;
            case "bet":
                break;
            default:
                break;
        }
    }
}