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
import java.util.LinkedList;
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
                PokerCard handCard = (Card) message.getObject();
                clientGUI.insertImage(clientGUI.playerCardsPanel, handCard, 125, 182);
                clientGUI.playerCardsPanel.updateUI();
                break;
            case "openCard":
                PokerCard openCard = (Card) message.getObject();
                clientGUI.insertImage(clientGUI.tableCardsPanel, openCard,125, 182);
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

                clientGUI.tableInfoPanel.updateUI();
                clientGUI.playerCardsPanel.updateUI();
                clientGUI.playerListPanel.updateUI();
                clientGUI.playerInfoPanel.updateUI();
                clientGUI.tableCardsPanel.updateUI();
                break;
            case  "nextRound":
                clientGUI.tableCardsPanel.updateUI();
                clientGUI.playerCardsPanel.updateUI();
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
                            clientGUI.playerInfoPanel.removeAll();
                            clientGUI.playerInfoPanel.add(clientGUI.createPlayerPanel(s));
                            ((JLabel) ((JPanel) clientGUI.playerInfoPanel.getComponent(0)).getComponent(1)).setText(s);
                            clientGUI.playerInfoPanel.revalidate();
                            clientGUI.playerInfoPanel.updateUI();
                        }else {
                            clientGUI.playerListPanel.removeAll();
                            clientGUI.playerListPanel.add(clientGUI.createPlayerPanel(s));
                            for (int i = 0; i  < clientGUI.playerListPanel.getComponentCount();i++) {
                                if (((JLabel) ((JPanel) clientGUI.playerListPanel.getComponent(i)).getComponent(1)).getText()== "")
                                ((JLabel) ((JPanel) clientGUI.playerListPanel.getComponent(i)).getComponent(1)).setText(s);
                            }
                            clientGUI.playerListPanel.revalidate();
                            clientGUI.playerListPanel.updateUI();
                        }
                        ((JLabel) clientGUI.findPlayerPanel(s).getComponent(7)).setText(Role.values()[integer].name());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
                break;
            case "chips":
                Map<String, Integer> playerChips = (Map<String, Integer>) message.getObject();

                String names[] = new String[playerChips.keySet().size()];
                playerChips.keySet().toArray(names);
                for (String currentPlayer : names) {
                    if (Objects.equals(currentPlayer, clientGUI.username)) {
                        if (clientGUI.playerInfoPanel.getComponents().length < names.length)
                            clientGUI.playerInfoPanel.revalidate();
                        clientGUI.playerInfoPanel.updateUI();
                    }
                    else {

                        if (clientGUI.playerListPanel.getComponents().length < names.length)
                            clientGUI.playerListPanel.revalidate();
                        clientGUI.playerListPanel.updateUI();
                    }
                    try {
                        ((JLabel)  clientGUI.findPlayerPanel(currentPlayer).getComponent(3)).setText(playerChips.get(currentPlayer).toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "inGame":
                break;
            case "bet":
                Map<String, Integer> playerBets = (Map<String, Integer>) message.getObject();

                playerBets.forEach((s, integer) -> {
                    try {
                        ((JLabel) clientGUI.findPlayerPanel(s).getComponent(5)).setText(integer.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });
                break;
            case "pot":
                Map<String, Integer> pot = (Map<String, Integer>) message.getObject();
                    try {
                        ((JLabel) clientGUI.tableInfoPanel.getComponent(1)).setText(pot.get("pot").toString());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                break;
            case "tableBet":
                Map<String, Integer> maxBet = (Map<String, Integer>) message.getObject();

                    try {
                        ((JLabel) clientGUI.tableInfoPanel.getComponent(3)).setText(maxBet.get("maxBet").toString());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                break;
            case "winners":

                break;
            default:
                System.out.println("Es ist in der Message: " + message.getHeader() + " ein Fehler aufgetreten!");
                break;
        }
    }
}