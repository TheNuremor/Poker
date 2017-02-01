package com.company.Client;

import com.company.Network.Message;
import com.company.Client.ClientGUI;
import com.company.Game.Card;
import com.company.enums.Role;

import handChecker.PokerCard;


import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Map;

public class Client extends Thread{

    private boolean running;

    private Socket server = null;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private ClientGUI clientGUI;

    public Client(ClientGUI clientGUI, String IP) {
        this.clientGUI = clientGUI;
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

    public boolean sendMessageToServer(String header, Object payload) {
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
            case "handCards":
                clientGUI.handCards.add(((com.company.Game.Card) message.getObject()));
                clientGUI.insertImage(clientGUI.playerCardsPanel,clientGUI.handCards);
                clientGUI.playerCardsPanel.updateUI();
                break;
            case "openCard":
                clientGUI.openCards.add(((com.company.Game.Card) message.getObject()));
                clientGUI.insertImage(clientGUI.tableCardsPanel,clientGUI.openCards);
                clientGUI.tableCardsPanel.updateUI();
                break;
            case "disconnect":
                close();
                System.exit(0);
                break;
            case "nextGameRound":
                clientGUI.handCards.clearCards();
                clientGUI.openCards.clearCards();
                clientGUI.tableCardsPanel.removeAll();
                clientGUI.playerCardsPanel.removeAll();
                break;
            case "userAccept":
                break;
            case "userDecline":
                break;
            case "setBet":
                break;
            case "role":
                Map<String, Integer> playerRoles = (Map<String, Integer>) message.getObject();

                playerRoles.forEach((s, integer) -> {
                    try {
                        ((JLabel) PlayerPanel(s).getComponent(7)).setText(Role.values()[integer].name());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
                break;
            case "cash":
                break;
            case "inGame":
                break;
            case "bet":
                break;
            default:
                clientGUI.textArea.append("Es ist in der Message: "+ message.getHeader() + "ein Fehler aufgetreten!");
                break;
        }
    }
}