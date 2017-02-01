package de.Nuremosh.Game;

import de.Nuremosh.Network.Message;
import de.Nuremosh.enums.Role;
import handChecker.HandChecker;
import handChecker.HandValue;
import handChecker.PokerCard;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player extends Thread{

    CardStack handstack;
    public String name;

    public int playerBet;
    public int cash = 10000;
    boolean inGame = true;
    boolean isAllIn = false;
    boolean betRight = true;
    HandValue hv;
    private Role role = Role.DEFAULT;

    //SERVER
    Socket client;
    boolean running = true;
    boolean isFinished = false;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    Integer sendBet;

    public Player(Socket client) {
        this.client = client;
        handstack = new CardStack(2);
    }

    @Override
    public void run() {
        establishConnection();
        try {
            while (running) {
                Message message = ((Message) inputStream.readObject());
                readMessage(message);
            }
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        } finally {
            close();
        }
    }

    void establishConnection() {
        try {
            inputStream = new ObjectInputStream(client.getInputStream());
            outputStream = new ObjectOutputStream(client.getOutputStream());

            //Validate Username and Password
            sendMessageToClient("validateClient", null);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void readMessage(Message message) {
        switch (message.getHeader()) {
            case "registrateClient":

                isFinished = true;
                break;
            case "loginClient":

                isFinished = true;
                break;
            case "bet":
                sendBet = ((Integer) message.getObject());
                notify();
                break;
            case "leave":
                running = false;
                break;
            default:
                System.out.println("Der Client " + name + " hat eine fehlerhafte Message geschickt");
        }
    }

    void close() {
        sendMessageToClient("disconnect", null);
        running = false;
        try {
            inputStream.close();
            outputStream.close();
            client.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    boolean sendMessageToClient(String header, Object payload) {
        try {
            outputStream.writeObject(new Message(header, payload));
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public boolean isInGame() {
        return inGame;
    }

    public Role getRole() {
        return role;
    }

    void setRole(Role role) {
        this.role = role;
    }

    public void playerInteraction(Table table, int bet) {
        if (inGame && (!isAllIn)) {
            if (bet > 0 && (playerBet + bet) < table.maxBet) {
                //clientThread.sendData("Falsche Eingabe");
                betRight = false;
            } else if (bet < 0) {
                // Fold
                inGame = false;
            } else { // Call -wenn bet = maxBet - bzw Raise
                if (cash > bet) { // enough money, no allin
                    playerBet += bet;
                    cash -= bet;
                    table.pot += bet;
                    if (playerBet > table.maxBet) { //effektiver Raise
                        table.playerList.forEach(player1 -> {
                            //if (!player1.equals(this))
                                //player1.clientThread.sendData("Neuer Tablebet: " + playerBet);
                        });
                        table.maxBet = playerBet;
                    }
                } else {
                    //AllIn
                    isAllIn = true;
                    playerBet += cash;
                    if (table.maxBet < cash) {
                        table.maxBet = playerBet;
                    }
                    table.pot += cash;
                    cash = 0;
                }
                betRight = true;
            }
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    List<PokerCard> getCompleteHandstack(CardStack tablestack) {
        return Stream.concat(handstack.getCards().stream(), tablestack.getCards().stream()).collect(Collectors.toList());
    }

    HandValue getHandValue(List<PokerCard> completestack) {
        if (hv == null) {
            HandChecker handChecker = new HandChecker();
            hv = handChecker.check(completestack);
        }
        return hv;
    }

    public int getCash() {
        return cash;
    }
}


