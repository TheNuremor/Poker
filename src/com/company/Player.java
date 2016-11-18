package com.company;

import com.company.enums.Role;

public class Player {

    public CardStack handstack;
    public int playerBet = 0;
    public int cash = 10000;
    public boolean inGame = true;
    public boolean isAllIn = false;
    public int interactionNumber;
    private Role role = Role.DEFAULT;

    //Constructor
    public Player() {
        handstack = new CardStack(2);
        interactionNumber = 0;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public String toString(int tableBet) {
        String output = "";
        output += cash;
        output += playerBet;
        output += tableBet;

        return output;
    }

    public void playerInteraction(Table table, int bet) {
        if (inGame && (!isAllIn)) {
            if (bet > 0 && (playerBet + bet) < table.tableBet) {
                System.out.println("Falsche Eingabe");
            } else if (bet < 0) {
                // Fold
                inGame = false;
            } else { // Call -wenn bet = tableBet - bzw Raise
                if (cash > bet) { // enough money, no allin
                    playerBet += bet;
                    cash -= bet;
                    table.pot += playerBet;
                    if (playerBet > table.tableBet) { //effektiver Raise
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
            }
        }
    }
}


