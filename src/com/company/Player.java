package com.company;

import com.company.enums.Role;
import handChecker.HandChecker;
import handChecker.HandValue;
import handChecker.PokerCard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Player  {

    CardStack handstack;
    public String name;

    public int playerBet = 0;
    public int cash = 10000;
    public int interactionNumber;
    boolean inGame = true;
    boolean isAllIn = false;
    boolean betRight = true;
    HandValue hv;
    private Role role = Role.DEFAULT;

    public ClientThread clientThread;

    //Constructor
    Player() {
        handstack = new CardStack(2);
        interactionNumber = 0;
    }

    Player(ClientThread clientThread) {
        this();
        this.clientThread = clientThread;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public CardStack getHandstack() {
        return handstack;
    }

    String toString(Table table) {
        String output;
        output = "Cash:\t" + cash + "\nRole:\t" + role.toString() + "\nPlayer Bet:\t" + playerBet + "\nTable Bet:\t" + table.tableBet +
                "\nHandkarten:\t\t" + getHandstack().getCards().toString() + "\nTischkarten:\t" + table.getTablestack().getCards().toString();
        //clientThread.outputStream.writeObject(output);
        return "---\n" + output;
    }

    public void playerInteraction(Table table, int bet) {
        if (inGame && (!isAllIn)) {
            if (bet > 0 && (playerBet + bet) < table.tableBet) {
                //clientThread.sendData("Falsche Eingabe");
                betRight = false;
            } else if (bet < 0) {
                // Fold
                inGame = false;
            } else { // Call -wenn bet = tableBet - bzw Raise
                if (cash > bet) { // enough money, no allin
                    playerBet += bet;
                    cash -= bet;
                    table.pot += bet;
                    if (playerBet > table.tableBet) { //effektiver Raise
                        table.playerList.forEach(player1 -> {
                            //if (!player1.equals(this))
                                //player1.clientThread.sendData("Neuer Tablebet: " + playerBet);
                        });
                        table.tableBet = playerBet;
                    }
                } else {
                    //AllIn
                    isAllIn = true;
                    playerBet += cash;
                    if (table.tableBet < cash) {
                        table.tableBet = playerBet;
                    }
                    table.pot += cash;
                    cash = 0;
                }
                betRight = true;
            }
        }
    }

    public boolean isFinished() {
        return clientThread.isFinished();
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


}


