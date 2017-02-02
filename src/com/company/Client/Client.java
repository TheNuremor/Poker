package com.company.Client;

import com.company.Game.Player;
import com.company.Network.Message;
import com.company.Client.ClientGUI;
import com.company.Game.Card;
import com.company.enums.Role;

import handChecker.PokerCard;


import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Map;
import java.util.Objects;

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
            case "handCard":
                clientGUI.handCards.add(((Card) message.getObject()));
                clientGUI.insertImage(clientGUI.playerCardsPanel,clientGUI.handCards, 125, 182);
                clientGUI.playerCardsPanel.updateUI();
                break;
            case "openCard":
                clientGUI.openCards.add(((Card) message.getObject()));
                clientGUI.insertImage(clientGUI.tableCardsPanel, clientGUI.openCards,125, 182);
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
            case "validateClient":

                break;
            case "userAccept":
                System.out.println("Spiel beigetreten");
                break;
            case "userDecline":
                System.out.println("Spiel nicht beigetreten");
                break;
            case "setBet":
                clientGUI.textArea.append((String) message.getObject());
                JOptionPane.showMessageDialog(clientGUI, "Du bist dran", "Zug erforderlich", JOptionPane.INFORMATION_MESSAGE);
                clientGUI.betRequestion = true;
                break;
            case "role":
                Map<String, Integer> playerRoles = (Map<String, Integer>) message.getObject();



                playerRoles.forEach((s, integer) -> {
                    try {

                        if (Objects.equals(s, clientGUI.username)) {
                            clientGUI.playerInfoPanel.add(clientGUI.createPlayerPanel(s));
                            clientGUI.playerInfoPanel.revalidate();
                            clientGUI.playerInfoPanel.updateUI();

                        }else {
                            clientGUI.playerListPanel.add(clientGUI.createPlayerPanel(s));
                            clientGUI.playerListPanel.revalidate();
                            clientGUI.playerListPanel.updateUI();

                        }

                        ((JLabel) clientGUI.findPlayerPanel(s).getComponent(5)).setText(Role.values()[integer].name());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
                break;
            case "chips":
                Map<String, Integer> playerChips = (Map<String, Integer>) message.getObject();

                String names[] = new String[playerChips.keySet().size()];
                playerChips.keySet().toArray(names);
                for (int i = 0; i < names.length; i++) {
                    if (Objects.equals(names[i], clientGUI.username)) {
                        if (clientGUI.playerInfoPanel.getComponents().length < names.length)
                            //clientGUI.playerInfoPanel.add(clientGUI.createPlayerPanel(names[i]));
                        //clientGUI.playerInfoPanel.revalidate();
                        ((JLabel) ((JPanel) clientGUI.playerInfoPanel.getComponent(i)).getComponent(1)).setText(playerChips.get(names[i]).toString());
                        clientGUI.playerInfoPanel.updateUI();
                    }
                    else {
                        if (clientGUI.playerListPanel.getComponents().length < names.length)
                            //clientGUI.playerListPanel.add(clientGUI.createPlayerPanel(names[i]));
                        //clientGUI.playerListPanel.revalidate();
                        ((JLabel) ((JPanel) clientGUI.playerListPanel.getComponent(i)).getComponent(1)).setText(playerChips.get(names[i]).toString());
                        clientGUI.playerListPanel.updateUI();
                    }

                }
                break;
            case "inGame":
                break;
            case "bet":
                Map<String, Integer> playerBets = (Map<String, Integer>) message.getObject();

                playerBets.forEach((s, integer) -> {
                    try {
                        ((JLabel) clientGUI.findPlayerPanel(s).getComponent(3)).setText(integer.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
                break;
            default:
                System.out.println("Es ist in der Message: " + message.getHeader() + " ein Fehler aufgetreten!");
                break;
        }
    }
}